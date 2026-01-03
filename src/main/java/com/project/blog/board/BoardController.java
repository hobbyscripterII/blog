package com.project.blog.board;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.blog.board.dto.BoardGetDto;
import com.project.blog.board.vo.BoardGetVo;
import com.project.blog.cmmn.Pagination;
import com.project.blog.cmmn.enums.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static com.project.blog.cmmn.Const.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService service;
	
	@GetMapping("/board/{category}")
	public String board(@PathVariable(name = "category") String category, @RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "search", required = false) String search, Model model) {
		Map<String, Object> boardInfo = getBoardInfo(category);
		String icode = String.valueOf(boardInfo.get("icode"));
		String boardName = String.valueOf(boardInfo.get("boardName"));
		int offset = getOffset(page);
		
		BoardGetDto boardGetDto = new BoardGetDto();
		boardGetDto.setOffset(offset);
		boardGetDto.setIcode(icode);
		boardGetDto.setSearch(search);
		
		List<BoardGetVo> vo = service.getBoard(boardGetDto);
		
		DecimalFormat df = new DecimalFormat("#,###");
		int cnt = service.getBoardCnt(boardGetDto);
		String cntStr = df.format(cnt);
		Pagination pagination = new Pagination(page, cnt, category);
		
		model.addAttribute(BOARD_NAME, boardName);
		model.addAttribute(VO, vo);
		model.addAttribute(BOARD_CNT, cntStr);
		model.addAttribute(PAGINATION, pagination);
		model.addAttribute(CATEGORY, category);
		
		return "board/list";
	}
	
	private int getOffset(int page) {
		return (page == 1 ? 0 : (page - 1) * 10);
	}
	
	private Map<String, Object> getBoardInfo(String category) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String boardName = null;
		String icode     = null;
		
		switch (category) {
			case "music":
				boardName = Board.MUSIC.BOARD_NAME;
				icode     = Board.MUSIC.ICODE;
			break;
			
			case "photo":
				boardName = Board.PHOTO.BOARD_NAME;
				icode     = Board.PHOTO.ICODE;
			break;
			
			case "daily":
				boardName = Board.DAILY.BOARD_NAME;
				icode     = Board.DAILY.ICODE;
			break;
			
			default:
			break;
		}
		
		map.put("boardName", boardName);
		map.put("icode"    , icode);
		
		return map;
	}
}