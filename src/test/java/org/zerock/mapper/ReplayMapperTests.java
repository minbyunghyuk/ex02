package org.zerock.mapper;

import java.util.stream.IntStream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.ReplyVo;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplayMapperTests {
	
	private Long[] bnoArr = {646681L,646661L,646656L,646655L,646651L};
		

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test @Ignore
	public void testMapper() {
		log.info(mapper);
	}
	
	@Test @Ignore
	
	public void Testinsert()
	{
		IntStream.rangeClosed(1, 10).forEach(i->{
			ReplyVo vo = new ReplyVo();
			vo.setBno(bnoArr[i%5]);
			vo.setReply("테스트댓글"+i);
			vo.setReplyer("테스트글쓴이"+i);
			
			mapper.insert(vo);
		});
	}
	@Test@Ignore
	public void Testread()
	{
		Long trno =5L;
		ReplyVo vo = mapper.read(trno);
		
		log.info(vo);
	}
	@Test@Ignore
	public void Testdelete()
	{
		Long trno = 5L;
		mapper.delete(trno);
		
		
	}
	
	@Test
	public void Testupdate()
	{
		Long trno = 10L;
		ReplyVo vo=  mapper.read(trno);
		vo.setReply("updatetrash");
		int count = mapper.update(vo);
		log.info("udpate count = "+count);
	}
	
	
	
	
}
