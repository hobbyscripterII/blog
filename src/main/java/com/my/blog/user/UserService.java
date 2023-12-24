package com.my.blog.user;

import com.my.blog.user.model.UserEntity;
import com.my.blog.user.model.UserSignInDto;
import com.my.blog.user.model.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public int signUp(UserSignUpDto dto) {
        dto.setUpw(BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt()));
        return mapper.signUp(dto);
    }

    public UserEntity signIn(UserSignInDto dto) {
        try {
            UserEntity user = mapper.signIn(dto.getUid());

            // 암호화된 비밀번호가 일치할 경우 회원 정보를 반환한다.
            if (BCrypt.checkpw(dto.getUpw(), user.getUpw())) {
                return user;
            }
            return null; // 일치하지 않을 경우에는 null을 반환한다.
        } catch (Exception e) {
            return null; // 예외가 터져도 null을 반환한다.
        }
    }
}
