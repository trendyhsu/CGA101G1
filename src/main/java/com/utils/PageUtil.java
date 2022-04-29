package com.utils;

import java.util.ArrayList;
import java.util.List;

public class PageUtil<T> {

	public List<T> getPagination(List<T> datas, int pageSize, int pageNo) {
		
		// 開始的位置
		int startNum = (pageNo - 1) * pageSize + 1; 
		if (startNum > datas.size()) {
			return null;
		}
		List<T> res = new ArrayList<>();
		int rum = datas.size() - startNum;
		if (rum < 0) {
			return null;
		}
		
		// 如果剛好是最後一個
		if (rum == 0) { 
			int index = datas.size() - 1;
			res.add(datas.get(index));
			return res;
		}
		
		// 剩下的資料還夠湊成一頁
		if (rum / pageSize >= 1) { 
			for (int i = startNum; i < startNum + pageSize; i++) { // 擷取從startNum開始的數據
				res.add(datas.get(i - 1));
			}
			return res;
		} else if ((rum / pageSize == 0) && rum > 0) { // 不夠一頁，直接列出剩下的
			for (int j = startNum; j <= datas.size(); j++) {
				res.add(datas.get(j - 1));
			}
			return res;
		} else {
			return null;
		}
	}
}
