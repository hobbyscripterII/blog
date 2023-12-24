package com.my.blog.user;

import com.my.blog.common.Utils;
import com.my.blog.user.model.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public int signUp(UserSignUpDto dto) {
        dto.setUpw(BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt()));
        return Utils.isNotNull(mapper.signUp(dto));
    }

    
}
