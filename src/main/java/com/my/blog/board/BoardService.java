package com.my.blog.board;

import com.my.blog.board.model.BoardCategoryDto;
import com.my.blog.board.model.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;

    public BoardCategoryDto getBoardCategory(int categoryId) {
        return mapper.getBoardCategory(categoryId);
    }

    public List<BoardVo.Get> getBoard(int categoryId) {
        return mapper.getBoard(categoryId);
    }

    public BoardVo.Sel selBoard(int boardId) {
        return mapper.setBoard(boardId);
    }
}
