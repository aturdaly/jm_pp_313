package jm.springboot.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jm.springboot.rest.model.Role;
import jm.springboot.rest.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String password;
    @JsonProperty
    private Integer age;
    @JsonProperty
    private List<String> roles;

    public static UserDto convertToUserDto(User userModel) {
        UserDto userDto = new UserDto();
        userDto.setId(userModel.getId());
        userDto.setName(userModel.getName());
        userDto.setPassword(userModel.getPassword());
        userDto.setAge(userModel.getAge());
        userDto.setRoles(userModel.getRoles().stream()
                            .map(Role::getRole).collect(Collectors.toList()));
        return userDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
