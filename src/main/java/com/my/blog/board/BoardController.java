package com.my.blog.board;

import com.my.blog.board.model.BoardCategoryDto;
import com.my.blog.board.model.BoardVo;
import com.my.blog.common.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;

    @GetMapping("/list")
    public String getBoardList(@RequestParam(name = "category_id", required = false) int categoryId, Model model) {
        BoardCategoryDto board = service.getBoardCategory(categoryId);
        List<BoardVo.Get> list = service.getBoard(categoryId);
        model.addAttribute("board", board);
        model.addAttribute("list", list);
        return "/board/list";
    }

    @GetMapping("/read")
    public String selBoard(@RequestParam(name = "board_id") int boardId, Model model) {
        BoardVo.Sel board = service.selBoard(boardId);
        // markdown 렌더링
//        String contents_ = board.getContents();
//        String contents = CommonUtil.markdown(contents_);
//        board.setContents(contents);
        model.addAttribute("board", board);
        return "/board/read";
    }
}
