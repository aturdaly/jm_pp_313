package jm.springboot.rest.controller;

import jm.springboot.rest.dto.UserDto;
import jm.springboot.rest.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api")
public class UserRestController {

    @GetMapping(value = "/info")
    public ResponseEntity<UserDto> getUser() {
        final UserDto userDto = UserDto.convertToUserDto((User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}