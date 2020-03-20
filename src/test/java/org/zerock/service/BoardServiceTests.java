package org.zerock.service;

import static org.junit.Assert.assertNotNull;

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
public class BoardServiceTests {

	@Setter(onMethod_ = { @Autowired })
	public BoardService service;

	@Test
	@Ignore
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}

	@Test
	@Ignore
	public void testRegister() {
		BoardVo vo = new BoardVo();
		vo.setTitle("새로운쓰레기제목");
		vo.setContent("새로운쓰레기내용");
		vo.setWriter("새로운글쓴이");

		service.register(vo);

		log.info("새로운게시글 글번호" + vo.getBno());
		log.info("새로운게시글 글제목" + vo.getTitle());
	}

	@Test
	@Ignore
	public void testGetList() {
		for (BoardVo vo : service.getList(new Criteria(2, 10))) {
			log.info(vo);
		}
	}

	@Test
	@Ignore
	public void testGet() {
		log.info(service.get(5L));
	}

	@Test
	@Ignore
	public void testdelete() {
		log.info("삭제게시글번호" + service.remove(25L));
	}

	@Test
	@Ignore
	public void testupdate() {
		BoardVo vo = service.get(10L);

		if (vo == null)
			return;

		vo.setTitle("제목변경");
		log.info("변경" + service.modify(vo));

	}
	

}
