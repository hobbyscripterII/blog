package com.my.blog.user;

import com.my.blog.user.model.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    @ResponseBody
    public int signUp(@RequestBody UserSignUpDto dto) {
        log.info("dto = {}", dto);
        return service.signUp(dto);
    }
}