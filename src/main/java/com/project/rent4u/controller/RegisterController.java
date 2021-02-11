package com.project.rent4u.controller;

import com.project.rent4u.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class RegisterController {


    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        log.info("getRegisterPage called");
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping
    public String postRegisterPage(Model model, UserDto userDto, BindingResult bindingResult) {
        log.info("postRegisterPage called");

    }


}
