package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVo;
import org.zerock.domain.Criteria;

public interface BoardMapper {

	// @Select("SELECT * FROM tbl_board WHERE bno>0 ")
	public List<BoardVo> getList();

	public List<BoardVo> getListWtihPaing(Criteria cri);
	
	public void insert(BoardVo vo);

	public void insertSelectkey(BoardVo vo);

	public BoardVo read(Long no);

	public int delete(Long bno);

	public int update(BoardVo vo);
}
