package com.project.rent4u.service.validator;

import com.project.rent4u.dto.UserDto;
import com.project.rent4u.model.User;
import com.project.rent4u.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
public class UserDtoValidator {

    //todo - metoda validare pt parola de un anumit format

    //== fields ==
    private final UserRepository userRepository;

    @Autowired
    public UserDtoValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(UserDto userDto, BindingResult bindingResult){
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()){
            bindingResult.addError(new FieldError("userDto","email","This email is already in use"));
        }
    }
}
