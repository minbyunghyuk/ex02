package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVo;
import org.zerock.domain.Criteria;

public interface BoardService {

	public void register(BoardVo vo);
	
	public BoardVo get(Long no);
	
	public boolean modify(BoardVo vo);
	
	public boolean remove(Long no);
	
	public List<BoardVo> getList(Criteria cri);
	
	public int getTotalCount(Criteria cri);
	
	
}
