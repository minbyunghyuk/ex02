package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVo;

public interface ReplyMapper {

	// 외래키 등록
	public int insert(ReplyVo vo);

	public ReplyVo read(Long no);

	public int delete(Long no);

	public int update(ReplyVo vo);

	// 리스트처리 //mapperxml쿼리문변경 0323
	public List<ReplyVo> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	public int getCountByBno(Long no);
	

}
