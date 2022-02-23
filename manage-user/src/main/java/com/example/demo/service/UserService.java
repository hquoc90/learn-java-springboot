package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.modelDto.UserDto;
import com.example.demo.modelDto.request.CreateUserRequest;
import com.example.demo.modelDto.request.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.net.CacheRequest;
import java.util.List;

@Service
public interface UserService {
    public List<UserDto> getListUser();
    public UserDto getUserById(int id);
    public UserDto getUserByKeyword(String keyword);
    public UserDto createUser(CreateUserRequest rq);
    public List<User> listUser();
    public UserDto updateUser(UpdateUserRequest rq, int id);
    public void deleteUser(int id);
}
