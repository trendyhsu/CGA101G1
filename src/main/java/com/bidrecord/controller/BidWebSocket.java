package com.bidrecord.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.bidrecord.model.BidRecordService;
import com.bidrecord.model.BidRecordVO;
import com.core.utils.MailService;
import com.google.gson.Gson;
import com.member.model.MemVO;

/**
 * @ServerEndpoint 註解是一個類層次的註解，它的功能主要是將目前的類定義成一個websocket伺服器端,
 *                 註解的值將被用於監聽使用者連線的終端訪問URL地址,客戶端可以通過這個URL來連線到WebSocket伺服器端
 */
@ServerEndpoint("/bid/webSocket/{bidProductNo}")
public class BidWebSocket {
	// 靜態變數，用來記錄當前線上連線數。應該把它設計成執行緒安全的。
	private static int onlineCount = 0;

	// concurrent包的執行緒安全Set，用來存放每個客戶端對應的MyWebSocket物件。若要實現服務端與單一客戶端通訊的話，可以使用Map來存放，其中Key可以為使用者標識
	// 此處用一個Map來存 競標商品編號 ( bidProductNo ) 以及其對應的 Session set (連線進來的人)
	private static final Map<String, Set<Session>> bids = new ConcurrentHashMap();

	// 與某個客戶端的連線會話，需要通過它來給客戶端傳送資料
	private Session session;

	// 所有 BidRecordVO
	private List<BidRecordVO> bidRecordVOs;

	// 連線建立成功呼叫的方法
	@OnOpen
	public void onOpen(@PathParam("bidProductNo") String bidProductNo, Session session) {
		// 將session按照房間名來儲存，將各個房間的使用者隔離
		if (!bids.containsKey(bidProductNo)) {
			// 建立房間不存在時，建立該競標商品bidRoom房間
			Set<Session> bidRoom = new HashSet<>();
			// 新增使用者
			bidRoom.add(session);
			bids.put(bidProductNo, bidRoom);
		} else {
			// 房間已存在，直接新增使用者到相應的房間
			bids.get(bidProductNo).add(session);
		}
		System.out.println("有一個連線進來了!");
		BidRecordService bidRecordSvc = new BidRecordService();
		bidRecordVOs = bidRecordSvc.getAll();
	}

	// 連線關閉呼叫的方法
	@OnClose
	public void onClose(@PathParam("bidProductNo") String bidProductNo, Session session) {
		bids.get(bidProductNo).remove(session);
		System.out.println("有一個連線離開了!");
	}

	// 收到客戶端訊息後呼叫的方法
	@OnMessage
	public void onMessage(@PathParam("bidProductNo") String bidProductNo, String message, Session session)
			throws Exception {
		// 拿到 出價的 以及 會員編號 的 message json
		System.out.println("來自客戶端的訊息:" + message);
		Gson gson = new Gson();
		BidRecordVO bidRecordVO = gson.fromJson(message, BidRecordVO.class);
		Integer memNo = bidRecordVO.getMemNo();
		Integer bidPrice = bidRecordVO.getBidPrice();
				
		// 取得現在時間
		long time = System.currentTimeMillis();
		Timestamp now = new Timestamp(time);
		System.out.println(now);

		// 調用Service方法
		BidRecordService bidRecordSvc = new BidRecordService();
		
		// 取得該商品原本最高出價者
		BidRecordVO bidrecordHighestVO = bidRecordSvc.getHighestByBidProductNo(Integer.valueOf(bidProductNo));
		
		// 寄信通知原最高出價者 價格被超過了
//		MailService mailService = new MailService();
//		
//		String to = "the38245691@gmail.com";
//
//		String subject = "您的出價被超過囉！";
//
//		String targetName = String.valueOf(bidrecordHighestVO.getMemNo());
//		
//		String messageText = "親愛的 " + targetName + " 帕GAME會員您好! " + "\n\n" + "在此通知您出價被超過了！";
//		
//		mailService.sendMail(to, subject, messageText);
		
		// insert 進資料庫
		bidRecordSvc.addBidRecord(Integer.valueOf(bidProductNo), memNo, bidPrice, now);
		
		// 取出新的bidRecordVO list 傳至前面更新價格
		bidRecordVOs = bidRecordSvc.getAll();

		// 轉換 bidRecordsVOs 成 Json 傳到前端
		String json = gson.toJson(bidRecordVOs);

		// 此處應該有html過濾
		message = session.getId() + ":" + message;
		System.out.println(message);

		// 接收到資訊後進行廣播
		broadcast(bidProductNo, json);
	}

	// 按照房間名進行廣播
	public static void broadcast(String bidProductNo, String message) throws Exception {
		for (Session session : bids.get(bidProductNo)) {
			session.getBasicRemote().sendText(message);
		}
	}

	// 發生錯誤時呼叫
//	@OnError
//	public void onError(Session session, Throwable error) {
//		System.out.println("發生錯誤");
//		error.printStackTrace();
//	}

	/**
	 * 這個方法與上面幾個方法不一樣。沒有用註解，是根據自己需要新增的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
//this.session.getAsyncRemote().sendText(message);
	}
}