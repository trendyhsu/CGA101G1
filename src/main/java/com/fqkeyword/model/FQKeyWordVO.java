package com.fqkeyword.model;

public class FQKeyWordVO {
	private	Integer fqKeyWordNo;
	private String fqKeyWordContent;
	private String answerContent;
	
	public FQKeyWordVO () {
		
	}
	public FQKeyWordVO(Integer fqKeyWordNo, String fqKeyWordContent, String answerContent) {
		this.fqKeyWordNo = fqKeyWordNo;
		this.fqKeyWordContent = fqKeyWordContent;
		this.answerContent = answerContent;
	}


	@Override
	public String toString() {
		String text = String.format("KeyWordNo: %s, KeyWordContent: %s, AnswerContent: %s",fqKeyWordNo,fqKeyWordContent,answerContent);
		return text;
	}
	public Integer getFqKeyWordNo() {
		return fqKeyWordNo;
	}
	public void setFqKeyWordNo(Integer fqKeyWordNo) {
		this.fqKeyWordNo = fqKeyWordNo;
	}
	public String getFqKeyWordContent() {
		return fqKeyWordContent;
	}
	public void setFqKeyWordContent(String fqKeyWordContent) {
		this.fqKeyWordContent = fqKeyWordContent;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}


	


	


	


	
	
	
	
	
	
	
}
