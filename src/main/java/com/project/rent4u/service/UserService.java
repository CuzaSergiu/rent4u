package com.project.rent4u.service;

import com.project.rent4u.dto.UserDto;
import com.project.rent4u.mapper.UserMapper;
import com.project.rent4u.model.User;
import com.project.rent4u.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //== fields ==
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    // == constructor ==
    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    // == public methods ==
    public void register(UserDto userDto){
        User user = userMapper.mapToEntity(userDto);
        userRepository.save(user);
    }
}
