package com.project.blog.board;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.project.blog.board.dto.BoardGetDto;
import com.project.blog.board.dto.BoardSelDto;
import com.project.blog.board.vo.BoardGetVo;
import com.project.blog.board.vo.BoardSelVo;

@Mapper
public interface BoardMapper {
	List<BoardGetVo> getBoard(BoardGetDto dto);
	BoardSelVo selBoard(BoardSelDto dto);
	int getBoardCnt(BoardGetDto dto);
}