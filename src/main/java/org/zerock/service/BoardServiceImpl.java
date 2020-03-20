package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVo;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	@Override
	public void register(BoardVo vo) {
		log.info("register" + vo);
		mapper.insertSelectkey(vo);

	}

	@Override
	public BoardVo get(Long no) {
		log.info("가져온Vo객체 :" + no);
		return mapper.read(no);
	}

	@Override
	public boolean modify(BoardVo vo) {

		log.info("변경"+ vo);
		return mapper.update(vo) == 1;
	}

	@Override
	public boolean remove(Long no) {
		log.info("삭제"+ no);
		return mapper.delete(no)==1;
	}

	@Override
	public List<BoardVo> getList(Criteria cri) {

		log.info("paging list" + cri);
		return mapper.getListWtihPaing(cri);
	}

}
