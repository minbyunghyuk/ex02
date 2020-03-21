package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.java.Log;

//restcontoller sample class
@RestController
@RequestMapping("/sample")
@Log
public class SampleController {

	// 기존에는 JSP이름으로 처리하지만 여기서는 순수 문자열이 처리됨
	// produces 속성은 해당메서드가 생산하는 MIME 타입을 의미함
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE" + org.springframework.http.MediaType.TEXT_PLAIN_VALUE);
		return "안녕하세요";
	}

	@GetMapping(value = "/getsample", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public SampleVO getSample() {
		return new SampleVO(112, "쓰레기", "로드");
	}

	@GetMapping(value = "/getsample2")
	public SampleVO getSample2() {
		return new SampleVO(1, "쓰레기2", "로드2");
	}

	@GetMapping(value = "/getlist")
	public List<SampleVO> getList() {
		// range 1~10 이니까 1~9까지
		// mapToobj 새로운 sample객체를 만드는데
		// Collectors.tolist가 이걸리스트로 정렬

		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "쓰레기", i + "라스트")).collect(Collectors.toList());
	}

	@GetMapping(value = "/getmap")
	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "쓰레기", "개쓰레기"));
		map.put("second", new SampleVO(222, "쓰레기", "개쓰레기"));
		return map;
	}

	// ResponseEntity
	// REST방식에서는 DATA를 전송하므로 데이터의 정상유무를 체크해줘야됨
	// HTTP상태 코드와 에러메시지를 데이터랑같이전송할수있음
	@GetMapping(value = "/check", params = { "height", "weight" })
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);
		ResponseEntity<SampleVO> result = null;
		if (height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		// http://localhost:8090/sample/check?height=140&weight=60
		// 파라미터에서 height 150 보다 낮으면 Badgatway정보를 전송함.

		return result;

	}

	// PathVariable
	// URL상에서 경로의 ㅇ리부를 파라미터로 사용할수있음
	@GetMapping("/product/{cat}/{pid}")
	public String[] getpath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
		return new String[] { "category :" + cat, "productID" + pid };
	}

	// @RequestBody
	// 원하는 데이터의 포맷에맞춰서 보내고 이를 해석하는경우

	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("ticket" + ticket);
		return ticket;
	}

}
