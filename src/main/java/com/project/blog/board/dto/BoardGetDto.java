package com.project.blog.board.dto;

import lombok.Data;

@Data
public class BoardGetDto {
	private String icode;
	private int offset;
	private int amount = 10;
	private String search;
	private String role;
}