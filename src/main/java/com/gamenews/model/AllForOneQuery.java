package com.gamenews.model;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AllForOneQuery {

	
	public static String makeCondition(String columnName, String value){
		String condition = null;
		
		if("gameNewsNo".equals(columnName) || "gamePlatformNo".equals(columnName) || "managerNo".equals(columnName)) {
			condition = columnName + "=" + value;
		}else if ("gameNewsContent".equals(columnName) || "gameNewsTitle".equals(columnName)) {
			condition = columnName + " like '%" + value + "%'"; //文字查詢 
		}
		
		return condition + " ";
	}
	
	public static String anyConditions(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer Conditions = new StringBuffer();
		int count = 0;
		for(String key : keys) {
			String value = map.get(key)[0];
			if(value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String condition = makeCondition(key,value.trim());
				if(count == 1) {
					Conditions.append(" where "+ condition);
				}else {
					Conditions.append("and " + condition);
				}
				System.out.println("有送出查詢資料的欄位數count = " + count);
				System.out.println(Conditions);
			}
		}
		return Conditions.toString();
	}
	public static void main(String[] args) {
		
		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("gameNewsNo", new String[] { "520" });
		map.put("gamePlatformNo", new String[] { "223" });
		map.put("managerNo", new String[] { "54002" });
		map.put("gameNewsTitle", new String[] { "馬力歐" });
		map.put("gameNewsContent", new String[] { "炒雞ㄏ勝" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from emp2"
				          + anyConditions(map);
		System.out.println("●●finalSQL = " + finalSQL);

	}

}
