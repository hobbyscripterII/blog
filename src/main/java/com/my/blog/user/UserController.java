package com.my.blog.user;

import com.my.blog.common.Const;
import com.my.blog.common.Utils;
import com.my.blog.user.model.UserEntity;
import com.my.blog.user.model.UserSignInDto;
import com.my.blog.user.model.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
        return service.signUp(dto);
    }

    @GetMapping("/sign-in")
    public String signIn(Model model) {
        model.addAttribute("dto", new UserSignInDto());
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(@Validated @ModelAttribute(name = "dto") UserSignInDto dto, BindingResult br, HttpServletRequest request) {
        try {
            UserEntity user = service.signIn(dto);

            if (Utils.isNotNull(user)) {
                HttpSession session = request.getSession();
                session.setAttribute(Const.USER_ID, user.getIuser());
                session.setAttribute(Const.USER_NAME, user.getUnm());
                return "redirect:/";
            }
            br.reject("error", "아이디 혹은 비밀번호를 확인해주세요."); // object error
            return "/sign-in";
        } catch (Exception e) {
            return "/sign-in";
        }
    }

//    @PostMapping("/sign-in")
//    @ResponseBody
//    public int signIn(@RequestBody UserSignInDto dto, HttpServletRequest request) {
//        try {
//            UserEntity user = service.signIn(dto);
//
//            if (Utils.isNotNull(user)) {
//                HttpSession session = request.getSession();
//                session.setAttribute(Const.USER_ID, user.getIuser());
//                session.setAttribute(Const.USER_NAME, user.getUnm());
//                return Const.SUCCESS;
//            }
//            return Const.FAIL;
//        } catch (Exception e) {
//            return Const.FAIL;
//        }
//    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}