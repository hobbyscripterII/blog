package com.my.blog.board;

import com.my.blog.board.model.BoardCategoryDto;
import com.my.blog.board.model.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    BoardCategoryDto getBoardCategory(int categoryId);
    List<BoardVo.Get> getBoard(int categoryId);
    BoardVo.Sel setBoard(int boardId);
}
