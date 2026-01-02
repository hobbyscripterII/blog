package com.project.blog.home;

import org.springframework.stereotype.Service;
import com.project.blog.home.vo.ContentsCntGetVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {
	private final HomeMapper mapper;
	
	public ContentsCntGetVo getContentsCnt() {
		return mapper.getContentsCnt();
	}
}
