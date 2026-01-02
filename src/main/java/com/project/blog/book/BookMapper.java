package com.project.blog.book;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.project.blog.book.dto.BookGetDto;
import com.project.blog.book.dto.BookSelDto;
import com.project.blog.book.vo.BookGetVo;
import com.project.blog.book.vo.BookSelVo;

@Mapper
public interface BookMapper {
	List<BookGetVo> getBook(BookGetDto dto);
	BookSelVo selBook(BookSelDto dto);
	int getBookCnt(BookGetDto dto);
}
