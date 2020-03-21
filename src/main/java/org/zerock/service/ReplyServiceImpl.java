package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVo;
import org.zerock.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Service
@Log
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	
	//생성자가 있어서 자동주입?
	
	private ReplyMapper mapper;
	@Override
	public int register(ReplyVo vo) {
	
		log.info("register"+ vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVo get(Long rno) {
		
		log.info("get" + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVo vo) {
	
		log.info("update" + vo);
		return mapper.update(vo);
		
	}

	@Override
	public int remove(Long rno) {
		log.info("delete"+rno);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVo> getList(Criteria cri, Long no) {

		log.info(no+"페이지로드");
		return mapper.getListWithPaging(cri, no);
	}

}
