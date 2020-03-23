package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVo;

public interface ReplyService {

	//c
	public int register(ReplyVo vo);
	
	//1개 read
	public ReplyVo get(Long rno);
	
	//u
	public int modify(ReplyVo vo);
	
	//d
	public int remove(Long rno);
	//Criteria 는 페이지처리객체 
	public List<ReplyVo> getList(Criteria cri , Long no);
	
	//ReplyPage DTD 반환함
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
	

}
