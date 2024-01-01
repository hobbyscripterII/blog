package com.my.blog.board;

import com.my.blog.board.model.BoardCategoryDto;
import com.my.blog.board.model.BoardInsDto;
import com.my.blog.board.model.BoardUpdDto;
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
        board.setContents(CommonUtil.markdown(board.getContents()));
        model.addAttribute("board", board);
        return "/board/read";
    }

    @GetMapping("/write")
    public String insBoard(@RequestParam(name = "category_id") int categoryId, Model model) {
        BoardCategoryDto board = service.getBoardCategory(categoryId);
        List<BoardVo.Subject> subject = service.getSubject(categoryId);
        model.addAttribute("board", board);
        model.addAttribute("subject", subject);
        return "/board/form";
    }

    @PostMapping("/write")
    @ResponseBody
    public int insBoard(@RequestParam(name = "category_id") int categoryId, @RequestBody BoardInsDto dto, HttpServletRequest request) {
        dto.setCategoryId(categoryId);
        dto.setUserId(getUserId(request));
        return service.insBoard(dto);
    }

    @GetMapping("/update")
    public String updBoard(@RequestParam(name = "board_id") int boardId, Model model) {
        BoardVo.Sel dto = service.selBoard(boardId);
        BoardCategoryDto board = service.getBoardCategory(dto.getCategoryId());
        model.addAttribute("dto", dto);
        model.addAttribute("board", board);
        return "/board/form";
    }

    @PostMapping("/update")
    @ResponseBody
    public int updBoard(@RequestBody BoardUpdDto dto) {
        return service.updBoard(dto);
    }

    @PostMapping("/delete")
    @ResponseBody
    public int delBoard(int boardId) {
        return service.delBoard(boardId);
    }

    // session에 저장된 회원 아이디(PK)
    public int getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return Integer.parseInt(String.valueOf(session.getAttribute(Const.USER_ID)));
    }
}