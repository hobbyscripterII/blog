package com.my.blog.board;

import com.my.blog.board.model.BoardCategoryDto;
import com.my.blog.board.model.BoardInsDto;
import com.my.blog.board.model.BoardVo;
import com.my.blog.common.CommonUtil;
import com.my.blog.common.Const;
import com.my.blog.common.PageNation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;

    @GetMapping("/list")
    public String getBoardList(PageNation.Criteria criteria, @RequestParam(name = "category_id", required = false) int categoryId, @RequestParam(name = "keyword", required = false) String keyword, Model model) {
        criteria.setCategoryId(categoryId);
        BoardCategoryDto board = service.getBoardCategory(categoryId);
        // 'keyword'에 값이 있다면 mybatis if문으로 검색어가 포함된 게시글을 찾는다.
        List<BoardVo.Get> list = service.getBoard(criteria);
        PageNation pageNation = new PageNation(criteria, list.size());
        model.addAttribute("board", board); // 게시판 카테고리 아이디, 게시판 이름
        model.addAttribute("list", list);
        model.addAttribute("pageNation", pageNation);
        return "/board/list";
    }

    @GetMapping("/read")
    public String selBoard(@RequestParam(name = "board_id") int boardId, Model model) {
        BoardVo.Sel board = service.selBoard(boardId);
        // 1. 게시글 불러올 때 마크다운 렌더링
//        String contents_ = board.getContents();
//        String contents = CommonUtil.markdown(contents_);
//        board.setContents(contents);
        model.addAttribute("board", board);
        return "/board/read";
    }

    @GetMapping("/write")
    public String insBoard(@RequestParam(name = "category_id") int categoryId, Model model) {
        model.addAttribute("categoryId", categoryId);
        return "/board/form";
    }

    @PostMapping("/write")
    @ResponseBody
    public int insBoard(@RequestParam(name = "category_id") int categoryId, @RequestBody BoardInsDto dto, HttpServletRequest request) {
        dto.setCategoryId(categoryId);
        dto.setUserId(getUserId(request));
        // 2. 게시글 등록할 때 마크다운 렌더링
        String contents_ = dto.getContents();
        String contents = CommonUtil.markdown(contents_);
        dto.setContents(contents);
        return service.insBoard(dto);
    }

    @PostMapping("/delete")
    @ResponseBody
    public int delBoard(int boardId) {
        return service.delBoard(boardId);
    }

    // session에 저장된 회원 아이디(PK)
    public int getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return Integer.parseInt(String.valueOf(session.getAttribute(Const.IUSER)));
    }
}