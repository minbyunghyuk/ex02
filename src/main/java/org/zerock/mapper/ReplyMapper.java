package org.zerock.mapper;

import org.zerock.domain.ReplyVo;

public interface ReplyMapper {

	
	//외래키 등록
	public int insert(ReplyVo vo);
	
	public ReplyVo read(Long no);
	
	public int delete(Long no);
	
	public int update(ReplyVo vo);
}
