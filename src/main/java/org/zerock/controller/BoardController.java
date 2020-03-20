package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVo;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

	private BoardService service;

	// service 에구현되어있는 메서드 사용
	// 전체목록 list
	@GetMapping("/list")
	public void list(Criteria cri ,Model model) {
		// 일반자료형말고 모델을 만들어서 넣어줘야되지
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri,200));
		model.addAttribute("amount",cri.getAmount());
		
	}
	
    @GetMapping("/register")
    public void register() {    }

    @PostMapping("/register")
    public String register(BoardVo board, RedirectAttributes rttr) {
        log.info("register: " + board);
        service.register(board);
        rttr.addFlashAttribute("result", board.getBno());
        return "redirect:/board/list";
    }
//
//	@GetMapping("/get")
//	public void get(@RequestParam("bno") long no, Model model) {
//		log.info("/get");
//		model.addAttribute("board", service.get(no));
//	}
	@GetMapping({"/get","modify",})
	public void get(@RequestParam("bno") long no,@ModelAttribute("cri")Criteria cri, Model model) {
		log.info("/get");
		model.addAttribute("board", service.get(no));
	}


	// GET방식으로 접근하지만 실제작업은 POST방식으로
	// 객체를 가져와서 객체내부내용을수정해야되서
	@PostMapping("/modify")
	public String modify(BoardVo vo, RedirectAttributes rttr) {
		
		System.out.println("타긴하니냐");
		log.info("modify : " + vo);
		
		
		if (service.modify(vo))
			rttr.addFlashAttribute("result", "success");

		return "redirect:/board/list";
	}

	// 특정번호만 가져와서 삭제하믄대지
	@PostMapping("remove")
	public String remove(@RequestParam("bno") Long no, RedirectAttributes rttr) {
		log.info("remove" + no);
		if (service.remove(no))
			rttr.addFlashAttribute("result", "success");

		return "redirect:/board/list";
	}

}
