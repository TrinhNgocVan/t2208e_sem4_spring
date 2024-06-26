package org.example.dto;

import java.io.Serializable;
import java.util.List;

public class DepartmentDto implements Serializable {
    private long id;
    private String name;
    private List<UserDto> userDto;

    public List<UserDto> getUserDto() {
        return userDto;
    }

    public void setUserDto(List<UserDto> userDto) {
        this.userDto = userDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
