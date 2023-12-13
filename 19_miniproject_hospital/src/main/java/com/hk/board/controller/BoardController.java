package com.hk.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartRequest;


import com.hk.board.command.DelBoardCommand;
import com.hk.board.command.InsertBoardCommand;
import com.hk.board.command.UpdateBoardCommand;
import com.hk.board.dtos.BoardDto;
import com.hk.board.service.BoardService;
import com.hk.board.utils.Paging;
import com.hk.board.utils.Util;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping(value = "/boardList")
	public String boardList(Model model, HttpServletRequest request) {
		System.out.println("글목록 보기");
		
		
		String pnum = request.getParameter("pnum");
		
		if(pnum == null) {
			pnum = (String) request.getSession().getAttribute("pnum"); // 현재 조회중인 페이지번호
		}else {
			// 새로 페이지를 요청할 경우 세션에 저장
			request.getSession().setAttribute("pnum", pnum);
		}
		
		List<BoardDto> list=boardService.getAllList(pnum);
		model.addAttribute("list", list);
		model.addAttribute("delBoardCommand", new DelBoardCommand());
		
		int pcount = boardService.getPCount(); 
		request.setAttribute("pcount", pcount);
		System.out.println("============="+pcount);
		Map<String,Integer> map = Paging.pagingValue(pcount, pnum, 5);
		request.setAttribute("pMap", map);

		System.out.println("=========----====");
		
		return "board/boardList";// forward 기능, "redirect:board/boardList"
				
	}
	
	
	
	
	@GetMapping(value = "/boardInsert")
	public String boardInsertForm(Model model) {
		model.addAttribute("insertBoardCommand", new InsertBoardCommand());
		return "board/boardInsertForm";
	}

	@PostMapping(value = "/boardInsert")
	public String boardInsert(@Validated InsertBoardCommand insertBoardCommand, BindingResult result,
			MultipartRequest multipartRequest // multipart data를 처리할때 사용
			, HttpServletRequest request, Model model) throws IllegalStateException, IOException {
		if (result.hasErrors()) {
			System.out.println("글을 모두 입력하세요");
			return "board/boardInsertForm";
		}

		boardService.insertBoard(insertBoardCommand, multipartRequest, request);

		return "redirect:/board/boardList";
	}

	// 상세보기
	@GetMapping(value = "/boardDetail")
	public String boardDeString(int board_seq, String id, Model model) {
		BoardDto dto = boardService.getBoard(board_seq,id);
	

		// 유효값 처리용
		model.addAttribute("updateBoardCommand", new UpdateBoardCommand());
		// 출력용
		model.addAttribute("dto", dto);


		return "board/boardDetail";
	}

	@PostMapping(value = "/boardUpdate")
	public String boardUpdate(@Validated UpdateBoardCommand updateBoardCommand, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("수정내용을 모두 입력하세요");
			return "board/boardDetail";
		}

		boardService.updateBoard(updateBoardCommand);

		return "redirect:/board/boardDetail?board_seq=" + updateBoardCommand.getBoard_seq();
	}
	
	/*
	 * @PostMapping(value = "/replyBoard") public String replyBoard(Model model,
	 * AnsDto dto) { boolean isS = ansService.replyBoard(dto); if(isS) { return
	 * "redirect:boardList.do"; }else { model.addAttribute("msg","답글추가실패"); return
	 * "error"; }
	 * 
	 * }
	 */
	
	
	
	

	@RequestMapping(value = "mulDel", method = { RequestMethod.POST, RequestMethod.GET })
	public String mulDel(@Validated DelBoardCommand delBoardCommand, BindingResult result, Model model) {
		
		boardService.mulDel(delBoardCommand.getSeq());
		System.out.println("글삭제함");
		return "redirect:/board/boardList";
	}

}