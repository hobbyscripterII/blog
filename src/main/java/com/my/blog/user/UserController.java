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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

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

    // >>>>> form
    @GetMapping("/sign-in")
    public String signIn(Model model, HttpServletRequest request) {
        try {
            model.addAttribute("dto", new UserSignInDto()); // 로그인 페이지 들어갈 때 필요한 객체 dto (<form:form modelAttribute="dto" ...>)
            Cookie[] cookies = request.getCookies(); // http 요청 정보에 있는 cookie는 배열 형태로 받는다.

            if (Utils.isNotNull(cookies)) {
                // Optional - NPE 강제 처리를 위한 wrapper 클래스
                Optional<String> uid_ = stream(cookies)
                        // filter - 조건에 맞는 요소를 추출한다.
                        .filter(c -> c.getName().equals("uid"))
                        // map - 메소드의 반환 값으로 변환한다.
                        .map(c -> c.getValue())
                        // findAny - 첫번째 요소를 참조하는 Optional 객체를 반환한다.
                        .findAny();

                // 값이 없다면 'Optional.empty'가 출력된다.
                log.info("uid_ = {}", uid_);

                String uid = null;
                // isPresent - 객체에 값이 존재하는지 여부를 확인한다.
                if (uid_.isPresent()) {
                    // get - 객체 저장된 값에 접근한다.
                    uid = uid_.get();
                } else {
                    // orElse - 해당 객체에 값이 없다면 대체 값을 반환한다.
                    uid = uid_.orElse("0");
                }

                log.info("uid = {}", uid); // 최종 uid 값
                model.addAttribute("uid", uid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sign-in";
    }

    // >>>>> ajax
//    @GetMapping("/sign-in")
//    public String signIn(@CookieValue String uid, Model model, HttpServletRequest request) {
//        try {
//            model.addAttribute("dto", new UserSignInDto());
//
//            if (Utils.isNotNull(uid)) {
//                model.addAttribute("uid", uid);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "sign-in";
//    }

    @PostMapping("/sign-in")
    public String signIn(@Validated @ModelAttribute(name = "dto") UserSignInDto dto, BindingResult br, HttpServletRequest request, HttpServletResponse response) {
        try {
            UserEntity user = service.signIn(dto);
            HttpSession session = request.getSession();

            if (Utils.isNotNull(user)) {
                session.setAttribute(Const.USER_ID, user.getIuser());
                session.setAttribute(Const.USER_NAME, user.getUnm());

                // 아이디 저장 기능
                if (dto.isUidSaveFl()) {
                    Cookie cookie = new Cookie("uid", dto.getUid());
                    cookie.setMaxAge(86400 * 30);
                    response.addCookie(cookie);
                    log.info("cookie 저장 완료");
                } else {
                    Cookie cookie = new Cookie("uid", null);
                    cookie.setMaxAge(0); // 쿠키를 만료시켜 삭제한다.
                    response.addCookie(cookie); // 삭제 또한 응답에 추가해서 없어지도록 처리해줘야 한다.
                    log.info("cookie 삭제 완료");
                }
                return "redirect:/";
            } else {
                br.reject("error", "아이디 혹은 비밀번호를 확인해주세요."); // object error
                return "/sign-in";
            }
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