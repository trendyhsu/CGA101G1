package com.utils;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CompositeQuery_BidProduct {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("BidProductNo".equals(columnName) || "BidApplyListNo".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("BidName".equals(columnName) || "BidProdDescription".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("BidProductNo", new String[] { "31001" });
		map.put("BidApplyListNo", new String[] { "34001" });
		map.put("BidName", new String[] { "星" });
		map.put("BidProdDescription", new String[] { "之" });

		String finalSQL = "select * from bidproduct "
				          + CompositeQuery_BidProduct.get_WhereCondition(map)
				          + "order by BidProductNo";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
