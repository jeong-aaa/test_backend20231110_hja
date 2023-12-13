package com.hk.board.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.board.command.InsertBoardCommand;
import com.hk.board.command.UpdateBoardCommand;
import com.hk.board.dtos.BoardDto;

import com.hk.board.mapper.BoardMapper;


import jakarta.servlet.http.HttpServletRequest;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	
	//글목록 조회
	public List<BoardDto> getAllList(String pnum){
		Map<String, String>map=new HashMap<String, String>();
		map.put("pnum", pnum);
		return boardMapper.getAllList(map);
	}
	
	public int getPCount() {
//		int count = 0;
		
		return boardMapper.getPCount();
	}

	
	//글 추가, 파일업로드및 파일정보 추가
		@Transactional
		public void insertBoard(InsertBoardCommand insertBoardCommand
				              , MultipartRequest multipartRequest
				              , HttpServletRequest request) 
				              throws IllegalStateException, IOException {
			//command:UI -> dto:DB 데이터 옮겨담기
			BoardDto boardDto=new BoardDto();
			boardDto.setId(insertBoardCommand.getId());
			boardDto.setTitle(insertBoardCommand.getTitle());
			boardDto.setContent(insertBoardCommand.getContent());
			
			//새글을 추가할때 파라미터로 전달된 boardDto객체에 자동으로,
			//증가된 board_seq값이 저장
			boardMapper.insertBoard(boardDto);//새글 추가
			
			
			
			
		}
		
		// 상세내용 조회
		public BoardDto getBoard(int board_seq, String id) {
			return boardMapper.getBoard(board_seq, id);
		}
		
		// 수정하기
		public boolean updateBoard(UpdateBoardCommand updateBoardCommand) {
			// command:UI ---> DTO:DB
			BoardDto dto = new BoardDto();
			dto.setBoard_seq(updateBoardCommand.getBoard_seq());
			dto.setTitle(updateBoardCommand.getTitle());
			dto.setContent(updateBoardCommand.getContent());
			
			return boardMapper.updateBoard(dto);
		}
		
		public boolean mulDel(String[] seqs) {
			return boardMapper.mulDel(seqs);
		}

	
}





