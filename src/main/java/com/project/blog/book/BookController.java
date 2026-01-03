package com.project.blog.book;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.blog.api.ApiResponse;
import com.project.blog.book.dto.BookGetDto;
import com.project.blog.book.dto.BookSelDto;
import com.project.blog.book.vo.BookSelVo;
import com.project.blog.cmmn.Pagination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.project.blog.cmmn.Const.*;
import static com.project.blog.cmmn.enums.Error.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {
	private final BookService service;
	
	@GetMapping("/book")
	public String book(Model model, @RequestParam(name = "page", defaultValue = "1", required = true) int page, @RequestParam(name = "search", required = false) String search) {
		int offset = getOffset(page);
		
		BookGetDto bookGetDto = new BookGetDto();
		bookGetDto.setOffset(offset);
		bookGetDto.setSearch(search);
		
		int cnt = service.getBookCnt(bookGetDto);
		String category = "book";
		
		Pagination pagination = new Pagination(page, cnt, category);
		
		model.addAttribute(VO, service.getBook(bookGetDto));
		model.addAttribute(PAGINATION, pagination);
		
		return "book";
	}
	
	@ResponseBody
	@GetMapping("/book/{ihighlight}")
	public ResponseEntity<ApiResponse<BookSelVo>> selBook(@PathVariable(name = "ihighlight") int ihighlight) {
		ApiResponse<BookSelVo> apiResponse = null;
		
		try {
			BookSelDto dto = new BookSelDto();
			dto.setIhighlight(ihighlight);
			
			BookSelVo vo = service.selBook(dto);
			
			apiResponse = ApiResponse.success(vo);
		} catch(RuntimeException e) {
			apiResponse = ApiResponse.error(INTERNAL_SERVER_ERROR.getStatusCode(), INTERNAL_SERVER_ERROR.getMessage(), e.getMessage());
		}
		
		return ResponseEntity.ok(apiResponse);
	}
	
	private int getOffset(int page) {
		return (page == 1 ? 0 : (page - 1) * 10);
	}
}
