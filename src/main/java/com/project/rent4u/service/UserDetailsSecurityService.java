package com.project.rent4u.service;

import com.project.rent4u.model.User;
import com.project.rent4u.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserDetailsSecurityService implements UserDetailsService {

    // == fields ==
    private final UserRepository userRepository;

    // == constructor ==
    public UserDetailsSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // == methods ==
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(email);

        if (!byEmail.isPresent()) {
            throw new UsernameNotFoundException(email);
        }

        User user = byEmail.get();
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
    }
}
