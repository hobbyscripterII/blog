package com.my.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public List<String> getUser() {
        return mapper.getUser();
    }
}
