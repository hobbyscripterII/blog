package com.my.blog.board;

import com.my.blog.board.model.BoardCategoryDto;
import com.my.blog.board.model.BoardInsDto;
import com.my.blog.board.model.BoardUpdDto;
import com.my.blog.board.model.BoardVo;
import com.my.blog.common.PageNation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVo.Subject> getBoardSubject(int categoryId);
    BoardCategoryDto getBoardCategory(int categoryId);
    List<BoardVo.Get> getBoard(PageNation.Criteria criteria);
    BoardVo.Sel selBoard(int boardId);
    int insBoard(BoardInsDto dto);
    int delBoard(int boardId);
    int updBoard(BoardUpdDto dto);
}