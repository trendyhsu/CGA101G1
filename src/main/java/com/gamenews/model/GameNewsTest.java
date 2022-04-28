package com.gamenews.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


public class GameNewsTest {

	public static void main(String[] args) throws IOException {
	
		
		GameNewsDAOImpl dao = new GameNewsDAOImpl();

		// 新增
//		GameNewsVO gameNewsVO1 = new GameNewsVO();
//		gameNewsVO1.setGamePlatformNo(64004);
//		gameNewsVO1.setManagerNo(71004);
//		gameNewsVO1.setGameNewsTitle("爐石《海底歷險記》Meta牌組預測（三）：惡魔獵人跌落神壇？");
//		gameNewsVO1.setGameNewsContent("而惡魔獵人下一版可能會蠻符合海底歷險記的標題的，強度真的下降到海底。"
//				+ "絲瑟諾女士其實就是一個比較強的喚焰者，可能可以在任務解完後一次性造成大量傷害。而任性的賢者目前看不出能在任務裡做什麼事，應該比較值得放進快攻裡。");
//		byte[] pic = getPictureByteArray("C:\\Users\\Tibame_T14\\Desktop\\HearthStone.jpg");
//		gameNewsVO1.setGameNewsPic(pic);
//		dao.insert(gameNewsVO1);

		 //修改
//		GameNewsVO gameNewsVO2 = new GameNewsVO();
//		gameNewsVO2.setGameNewsNo(76001);
//		gameNewsVO2.setGamePlatformNo(64001);
//		gameNewsVO2.setManagerNo(71001);
//		gameNewsVO2.setGameNewsTitle("《瑪利歐高爾夫》從 4 月 15 日起加入 Switch Online 擴充包");
//		gameNewsVO2.setGameNewsContent("任天堂的 Switch Online 擴充包又將迎來新內容。官方日前確認從 4 月 15 日起，最初發行於 1999 年的 N64 經典作品《瑪利歐高爾夫》（Mario Golf）將正式加入遊戲庫向訂閱者開放。該作共設有包括競標賽、速度賽等在內的 10 種不同遊戲模式，玩家可操作的角色多達 14 個，涵蓋瑪利歐和他的夥伴、敵人。對於小朋友或喜歡輕鬆遊戲的朋友來說，它應該會是 Switch Online 很好的補充。");
//		byte[] pic = getPictureByteArray("C:\\Users\\Tibame_T14\\Desktop\\MarioNews.jpg");
//		gameNewsVO2.setGameNewsPic(pic);
//		dao.update(gameNewsVO2);
		
		// (新增一筆後) //刪除
//		GameNewsVO gameNewsVO3 = new GameNewsVO();
//		gameNewsVO3.setGamePlatformNo(64002);
//		gameNewsVO3.setManagerNo(71001);
//		gameNewsVO3.setGameNewsTitle("PS5上市已超過15個月仍短缺 日本網站統計七成玩家仍未買到機");
//		gameNewsVO3.setGameNewsContent("根據日本遊戲媒體「inside game」的調查，接受訪問的382位讀者中，只有70位（約18%）成功購入PS5，而表示想買但買不到的人多達290位（約75%），最後的22人更表1為一直買不到，已決定放棄。而Sony早亦表示過因為疫情及半導體供應持續緊張，PS5生產量亦因而受限，短期內難以大量生產，相信PS5的短缺情況還會維持一段時間。");
//		gameNewsVO3.setGameNewsPic(null);
//		dao.insert(gameNewsVO3);
//		dao.delete(76003);

		// 查詢單筆
		System.out.println(dao.findByPrimaryKey(76003).getGameNewsPic().length);
		

		// 查詢
//		List<GameNewsVO> list = dao.getAll();
//		for (GameNewsVO oneGameNews : list) {
//			System.out.println(oneGameNews);
//		}
		
		
	}
	
	// 使用byte[]方式建立圖片
		public static byte[] getPictureByteArray(String path) throws IOException {
			FileInputStream fis = new FileInputStream(path);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			return buffer;
		}

}
