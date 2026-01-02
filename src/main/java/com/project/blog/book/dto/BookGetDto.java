package com.project.blog.book.dto;

import lombok.Data;

@Data
public class BookGetDto {
	private int offset;
	private int amount = 10;
	private String search;
}
