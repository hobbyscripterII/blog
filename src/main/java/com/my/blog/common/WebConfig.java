package com.my.blog.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration // bean 등록 + 설정 파일 명시
public class WebConfig {
    @Bean // bean 등록
    /*
    ckfinder는 반환 값으로 uploaded와 url 값을 json으로 받는다.
    controller에서 modelAndView로 json을 반환할 수 있게 메소드를 작성한다.
     */
    MappingJackson2JsonView jsonView() {
        return new MappingJackson2JsonView();
    }
}
