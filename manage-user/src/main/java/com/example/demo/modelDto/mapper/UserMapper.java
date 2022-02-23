package com.example.demo.modelDto.mapper;

import com.example.demo.entity.User;
import com.example.demo.modelDto.UserDto;

public class UserMapper {
    // Tra ve gia tri theo yeu cau cua FE, neu khac gia tri thi tao them model DTO
    public static UserDto toUserDto(User user){
        UserDto tmp=new UserDto();
        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        tmp.setAvatar(user.getAvatar());
        return tmp;
    }
}
