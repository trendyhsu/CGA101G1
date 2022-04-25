package com.member.model;

import java.util.List;

public interface MemDAO_interface {

	public void insert(MemVO memVO);
	public void update(MemVO memVO);
	public MemVO getOne(Integer memNo);
	public List<MemVO> getAll();
}
