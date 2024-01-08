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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService service;

    @PostMapping("/image-upload")
    public ModelAndView image(MultipartHttpServletRequest request, ServletConfig servletConfig) throws Exception {
        ModelAndView mv = new ModelAndView("jsonView");
        MultipartFile uploadFile = request.getFile("upload");
        String originalFileName = uploadFile.getOriginalFilename();
        String ext = originalFileName.substring(originalFileName.indexOf("."));
        String newFileName = UUID.randomUUID() + ext;
        String realPath = servletConfig.getServletContext().getRealPath("/");
        String savePath = realPath + "upload/" + newFileName;
        String uploadPath = "./upload/" + newFileName;
        File file = new File(savePath);
        uploadFile.transferTo(file);
        mv.addObject("uploaded", true);
        mv.addObject("url", uploadPath);
        return mv;
    }

    @GetMapping("/list")
    public String getBoardList(PageNation.Criteria criteria, @RequestParam(name = "category_id", required = false) int categoryId, @RequestParam(name = "keyword", required = false) String keyword, Model model) {
        criteria.setCategoryId(categoryId);
        BoardCategoryDto board = service.getBoardCategory(categoryId);
        List<BoardVo.Subject> subject = service.getBoardSubject(categoryId);
        List<BoardVo.Get> list = service.getBoard(criteria); // 'keyword'에 값이 있다면 mybatis if문으로 검색어가 포함된 게시글을 찾는다.
        PageNation pageNation = new PageNation(criteria, list.size());
        model.addAttribute("board", board); // 게시판 카테고리 아이디, 게시판 이름
        model.addAttribute("list", list);
        model.addAttribute("pageNation", pageNation);
        model.addAttribute("subject", subject);
        return "/board/list";
    }

    @GetMapping("/read")
    public String selBoard(@RequestParam(name = "board_id") int boardId, Model model) {
        BoardVo.Sel board = service.selBoard(boardId);
//        board.setContents(CommonUtil.markdown(board.getContents()));
        log.info("board = {}", board);
        model.addAttribute("board", board);
        return "/board/read";
    }

    @GetMapping("/write")
    public String insBoard(@RequestParam(name = "category_id") int categoryId, Model model) {
        BoardCategoryDto board = service.getBoardCategory(categoryId);
        List<BoardVo.Subject> subject = service.getBoardSubject(categoryId);
        model.addAttribute("board", board);
        model.addAttribute("subject", subject);
        return "/board/form-we";
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
        BoardVo.Sel vo = service.selBoard(boardId);
        BoardCategoryDto board = service.getBoardCategory(vo.getCategoryId());
        List<BoardVo.Subject> subject = service.getBoardSubject(vo.getCategoryId());
        model.addAttribute("vo", vo);
        model.addAttribute("board", board);
        model.addAttribute("subject", subject);
        return "/board/form-we";
    }

    @PostMapping("/update")
    @ResponseBody
    public int updBoard(@RequestBody BoardUpdDto dto) {
        log.info("dto = {}", dto);
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