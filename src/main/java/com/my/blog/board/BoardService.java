package com.my.blog.board;

import com.my.blog.board.model.BoardCategoryDto;
import com.my.blog.board.model.BoardInsDto;
import com.my.blog.board.model.BoardVo;
import com.my.blog.common.Const;
import com.my.blog.common.PageNation;
import com.my.blog.common.Utils;
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

    public List<BoardVo.Get> getBoard(PageNation.Criteria criteria) {
        return mapper.getBoard(criteria);
    }

    public BoardVo.Sel selBoard(int boardId) {
        return mapper.setBoard(boardId);
    }

    public int insBoard(BoardInsDto dto) {
        if (Utils.isNotNull(mapper.insBoard(dto))) {
            return dto.getBoardId();
        } else {
            return Const.FAIL;
        }
    }
}
