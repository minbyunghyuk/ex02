package org.zerock.mapper;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVo;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;

	@Test
	@Ignore
	public void testGetList() {
		for (BoardVo Bvo : boardMapper.getList()) {
			log.info(Bvo);
		}
	}

	@Test
	@Ignore
	public void testinsert() {
		BoardVo vo = new BoardVo();
		vo.setTitle("테스트쓰레기제목");
		vo.setContent("쓰레기내용");
		vo.setWriter("Tmfprl");

		boardMapper.insert(vo);

		log.info(vo);
	}

	@Test
	@Ignore
	public void testinsertselkey() {
		BoardVo vo = new BoardVo();
		vo.setTitle("테스트쓰레기제목");
		vo.setContent("쓰레기내용");
		vo.setWriter("Tmfprl");

		boardMapper.insertSelectkey(vo);

		log.info(vo);
	}

	@Test
	@Ignore
	public void testRead() {
		BoardVo vo = boardMapper.read(5L);

		log.info(vo);

	}

	@Test
	@Ignore
	public void testdelete() {
		log.info("delete count :" + boardMapper.delete(3L));
	}

	@Test
	@Ignore
	public void testupdate() {
		BoardVo vo = new BoardVo();
		vo.setBno(5L);
		vo.setTitle("변경제목");
		vo.setContent("변경제목");
		vo.setWriter("username");

		int count = boardMapper.update(vo);
		log.info("update count" + count);
	}

	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		
		cri.setPageNum(3); //3페이지
		cri.setAmount(10); //10개
		List<BoardVo> list = boardMapper.getListWtihPaing(cri);
		for (BoardVo vo : list) {
			log.info(vo);
		}
	}

}
