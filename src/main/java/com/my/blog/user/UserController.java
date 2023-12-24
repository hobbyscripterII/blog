package com.my.blog.user;

import com.my.blog.common.Const;
import com.my.blog.common.Utils;
import com.my.blog.user.model.UserEntity;
import com.my.blog.user.model.UserSignInDto;
import com.my.blog.user.model.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @PostMapping("/sign-in")
    @ResponseBody
    public int signIn(@RequestBody UserSignInDto dto, HttpServletRequest request) {
        try {
            UserEntity user = service.signIn(dto);

            if (Utils.isNotNull(user)) {
                HttpSession session = request.getSession();
                session.setAttribute(Const.IUSER, user.getIuser());
                session.setAttribute(Const.UNM, user.getUnm());
                return Const.SUCCESS;
            }
            return Const.FAIL;
        } catch (Exception e) {
            return Const.FAIL;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}