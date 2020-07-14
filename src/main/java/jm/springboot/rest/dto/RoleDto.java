package jm.springboot.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jm.springboot.rest.model.Role;
import jm.springboot.rest.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class RoleDto {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String role;
    @JsonProperty
    private List<String> users;

    public static RoleDto convertToRoleDto(Role roleModel) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(roleModel.getId());
        roleDto.setRole(roleModel.getRole());
        roleDto.setUsers(roleModel.getUsers().stream()
                            .map(User::getName).collect(Collectors.toList()));
        return roleDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
