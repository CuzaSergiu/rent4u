package com.project.rent4u.service;

import com.project.rent4u.dto.UserDto;
import com.project.rent4u.mapper.UserMapper;
import com.project.rent4u.model.Role;
import com.project.rent4u.model.User;
import com.project.rent4u.repository.RoleRepository;
import com.project.rent4u.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    //== fields ==
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // == constructor ==
    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository,
                       RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // == public methods ==
    public void register(UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
        assignRole(user, userDto);
        encodePasswordFor(user);
        userRepository.save(user);
    }

    // == private methods ==
    private void assignRole(User user, UserDto userDto) {
        Optional<Role> optionalRole;
        if (userDto.getIsAdmin()) {
            optionalRole = roleRepository.findByName("ROLE_ADMINISTRATOR");
        } else {
            optionalRole = roleRepository.findByName("ROLE_USER");
        }

        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            user.setRole(role);
        }
    }

    private void encodePasswordFor(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }
}
