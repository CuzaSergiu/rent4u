package com.project.rent4u.controller;

import com.project.rent4u.dto.UserDto;
import com.project.rent4u.service.UserService;
import com.project.rent4u.service.validator.UserDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class RegisterController {

    private UserDtoValidator userDtoValidator;
    private UserService userService;

    @Autowired
    public RegisterController(UserDtoValidator userDtoValidator, UserService userService) {
        this.userDtoValidator = userDtoValidator;
        this.userService = userService;
    }


    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        log.info("getRegisterPage called");
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping
    public String postRegisterPage(Model model, UserDto userDto, BindingResult bindingResult) {
        log.info("postRegisterPage called");
        userDtoValidator.validate(userDto,bindingResult);
        if (bindingResult.hasErrors()){
            //todo - cum pot sa pastrez datele dupa ce primesc eroare
            model.addAttribute("userDto", userDto);
            return "register";
        }
        userService.register(userDto);
        return "redirect:/register";
    }


}
