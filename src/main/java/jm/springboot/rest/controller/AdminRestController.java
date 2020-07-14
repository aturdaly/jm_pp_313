package jm.springboot.rest.controller;

import jm.springboot.rest.dto.UserDto;
import jm.springboot.rest.model.Role;
import jm.springboot.rest.model.User;
import jm.springboot.rest.service.RoleService;
import jm.springboot.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/api")
public class AdminRestController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminRestController(@Qualifier("userServiceImp") UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getPassword(), userDto.getAge(), findUserRoles(userDto));
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        final List<UserDto> usersDto = userService.getAllUser().stream()
                                        .map(UserDto::convertToUserDto).collect(Collectors.toList());
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        final UserDto userDto = UserDto.convertToUserDto(userService.getUserById(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        User user = new User(id, userDto.getName(), userDto.getPassword(), userDto.getAge(), findUserRoles(userDto));
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Set<Role> findUserRoles(UserDto userDto) {
        return userDto.getRoles().stream()
                .map(role->roleService.getRoleByName(role)).collect(Collectors.toSet());
    }
}