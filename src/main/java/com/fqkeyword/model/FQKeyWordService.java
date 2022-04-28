package com.fqkeyword.model;

import java.util.List;
import java.util.Map;

public class FQKeyWordService {

	private FQKeyWordDAO dao;
	
	public FQKeyWordService() {
		dao = new FQKeyWordDAOImpl();
	}
	
	public FQKeyWordVO addKeyWord(String fqKeyWordContent, String answerContent) {
		
		FQKeyWordVO fqKeyWordVO = new FQKeyWordVO();
		fqKeyWordVO.setFqKeyWordContent(fqKeyWordContent);
		fqKeyWordVO.setAnswerContent(answerContent);
		dao.insert(fqKeyWordVO);
		
		return fqKeyWordVO;
		
	}
	
	public FQKeyWordVO updateKeyWord(Integer fqKeyWordNo, String fqKeyWordContent, String answerContent) {
		
		FQKeyWordVO fqKeyWordVO = new FQKeyWordVO();
		fqKeyWordVO.setFqKeyWordContent(fqKeyWordContent);
		fqKeyWordVO.setAnswerContent(answerContent);
		fqKeyWordVO.setFqKeyWordNo(fqKeyWordNo);
		dao.update(fqKeyWordVO);
		
		return fqKeyWordVO;
	}
	
	public void deleteKeyWord(Integer fqKeyWordNo) {
		dao.delete(fqKeyWordNo);
	}
	
	public List<FQKeyWordVO> getAll(Map<String, String[]>map){
		return dao.getAll(map);
	}
	
	public List<FQKeyWordVO> getAll(){
		return dao.getAll();
	}
	
	public FQKeyWordVO getOne(Integer fqKeyWordNo) {
		return dao.findByPrimaryKey(fqKeyWordNo);
	}
}
