package org.example.mapper;

import org.example.dto.UserDto;
import org.example.entity.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {
    public static UserDto entityToDto(User user){
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
    public static User dtoToEntity(UserDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
