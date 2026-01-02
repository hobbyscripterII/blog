package com.project.blog.api;

import com.project.blog.cmmn.enums.Error;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
	private Error apiStatus;

	public ApiException(Error apiStatus) {
		this.apiStatus = apiStatus;
	}
}