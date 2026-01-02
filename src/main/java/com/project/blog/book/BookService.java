package com.project.blog.book;

import java.util.List;
import org.springframework.stereotype.Service;
import com.project.blog.book.dto.BookGetDto;
import com.project.blog.book.dto.BookSelDto;
import com.project.blog.book.vo.BookGetVo;
import com.project.blog.book.vo.BookSelVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookMapper mapper;
	
	public List<BookGetVo> getBook(BookGetDto dto) {
		return mapper.getBook(dto);
	}
	
	public BookSelVo selBook(BookSelDto dto) {
		return mapper.selBook(dto);
	}
	
	public int getBookCnt(BookGetDto dto) {
		return mapper.getBookCnt(dto);
	}
}
