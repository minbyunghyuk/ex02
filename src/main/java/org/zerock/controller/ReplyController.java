package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVo;
import org.zerock.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@RestController
@Log
@RequestMapping("/replies/")
@AllArgsConstructor
public class ReplyController {

	private ReplyService service;
	//등록 -> (/replies/new) POST
	//REST방식처리 : 브라우저의 호출시 데이터의 포맷과 서버에서 보내주는 데이터의 포맷이 명확해야함
	//이번 댓글 예제의 경우 브라우저에서는 JSON타입을 된 댓글데이터를 전송한다.
	//처리결과는 서버에서 문자로 알려준다.
	//value는 /new 이면 -> /replies/new 가됨 
	//consumes , produces 설정으로 json파일 ,text로만 처리하게
	@PostMapping(value= "/new" , consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVo vo)
	{
		log.info("ReplyVO :" + vo);
		
		int insertcount = service.register(vo);
		
		return insertcount==1
				?new ResponseEntity<>("sucess",HttpStatus.OK):new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	//페이지 -> (/replies/pages/:bno/:page) get
	@GetMapping(value = "/pages/{bno}/{page}",produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVo>> getList(@PathVariable("page")int page , @PathVariable("bno") Long bno)
	{
		//여기서페이지번호는 메인글 번호 
		log.info("getList");
		Criteria cri = new Criteria(page,10);
		//log.info(String.valueOf(cri.getAmount())+"Amount"); //10개까지
		//log.info(String.valueOf(cri.getPageNum())+"Page");
		log.info("페이지처리객체"+cri);
		//ResponseEntity return시에는 객체+httpStatus 를 리턴해줘야함.
		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
		
	}
	//조회 -> (/replies/:rno) GET //특정게시물의 댓글목록확인
	//조회할때는 xml,json으로 처리
	@GetMapping(value = "/{rno}", produces ={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVo> get(@PathVariable("rno") Long rno)
	{
		log.info("get :" +rno);
		return new ResponseEntity<ReplyVo>(service.get(rno),HttpStatus.OK); 
		
	}
	
	//삭제 -> (/replies/:rno) Delete
	//어떤기능을 동작시킨다 (create delete)다면
	//기능성공에대한 여부를 리턴을 해줘야한다.
	@DeleteMapping(value = "/{rno}",produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove (@PathVariable("rno")Long rno)
	{
		log.info("delete" + rno);
		int deletecount =service.remove(rno);
		
		if(deletecount == 1)
		{
			return new ResponseEntity<String>("sucess", HttpStatus.OK);
		}
		else
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//수정  -> (/replies/:rno)put or patch
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
			value = "/{rno}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE}) 
	public ResponseEntity<String> modify(@RequestBody ReplyVo vo, @PathVariable("rno")Long rno)
	{
		
		//put :  요청된 객체을 수정(UPDATE)한다. 
		//patch : PATCH는 해당자원의 일부를 교체하는 의미로 사용.
		//실제수정되는 데이터는 json으로 명시되있고 ,@RequestBody로 처리되는데이터는 일반파라미터로처리불가  @PathVariable 파라미터도 포함
		//그래서  @PathVariable 매개변수로 rno를 받아서 객체에주입한다음에 Reply vo객체 Set해주고 직접처리
		//
		vo.setRno(rno);
		
		log.info("댓글번호"+rno);
		log.info("댓글객체"+vo);
		
		int updateresult = service.modify(vo);
		
		if(updateresult == 1)
		{
			return new ResponseEntity<String>("sucess", HttpStatus.OK);
		}
		else
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	
	
}
