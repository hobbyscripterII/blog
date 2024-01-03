package com.my.blog.board;

import com.my.blog.board.model.BoardCategoryDto;
import com.my.blog.board.model.BoardInsDto;
import com.my.blog.board.model.BoardUpdDto;
import com.my.blog.board.model.BoardVo;
import com.my.blog.common.Const;
import com.my.blog.common.PageNation;
import com.my.blog.common.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.my.blog.common.Const.FAIL;
import static com.my.blog.common.Const.SUCCESS;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;

    public List<BoardVo.Subject> getBoardSubject(int categoryId) {
        return mapper.getBoardSubject(categoryId);
    }

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

    public int delBoard(int boardId) {
        return Utils.isNotNull(mapper.delBoard(boardId)) ? SUCCESS : FAIL;
    }

    public int updBoard(BoardUpdDto dto) {
        return Utils.isNotNull(mapper.updBoard(dto)) ? SUCCESS : FAIL;
    }
}
