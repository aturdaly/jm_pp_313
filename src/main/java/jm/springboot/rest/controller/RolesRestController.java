package jm.springboot.rest.controller;

import jm.springboot.rest.dto.RoleDto;
import jm.springboot.rest.service.RoleService;
import jm.springboot.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/api")
public class RolesRestController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public RolesRestController(@Qualifier("userServiceImp") UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        final List<RoleDto> rolesDto = roleService.getAllRole().stream()
                .map(RoleDto::convertToRoleDto).collect(Collectors.toList());
        return new ResponseEntity<>(rolesDto, HttpStatus.OK);
    }
}
