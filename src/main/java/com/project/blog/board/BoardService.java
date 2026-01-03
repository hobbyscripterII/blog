package com.project.blog.board;

import java.util.List;
import org.springframework.stereotype.Service;
import com.project.blog.board.dto.BoardGetDto;
import com.project.blog.board.dto.BoardSelDto;
import com.project.blog.board.vo.BoardGetVo;
import com.project.blog.board.vo.BoardSelVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardMapper mapper;
	
	List<BoardGetVo> getBoard(BoardGetDto dto) {
		return mapper.getBoard(dto);
	}
	
	BoardSelVo selBoard(BoardSelDto dto) {
		return mapper.selBoard(dto);
	}
	
	int getBoardCnt(BoardGetDto dto) {
		return mapper.getBoardCnt(dto);
	}
}