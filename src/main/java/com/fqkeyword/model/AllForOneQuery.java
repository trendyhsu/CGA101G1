package com.fqkeyword.model;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AllForOneQuery {

	
	public static String makeCondition(String columnName, String value){
		String condition = null;
		
		if("fqKeyWordNo".equals(columnName)) {
			condition = columnName + "=" + value;
		}else if ("fqKeyWordContent".equals(columnName) || "answerContent".equals(columnName)) {
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
			System.out.println("value1= "+value);
			System.out.println("value2= "+map.get("fQKeyWordContent")[1]);
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
		map.put("fQKeyWordNo", new String[] { "1" });
		map.put("fQKeyWordContent", new String[] { "HELLO" ,"HHH"});
		map.put("answerContent", new String[] { "World" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from emp2"
				          + anyConditions(map);
		System.out.println("●●finalSQL = " + finalSQL);

	}

}
