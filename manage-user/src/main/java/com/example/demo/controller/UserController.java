package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.modelDto.UserDto;
import com.example.demo.modelDto.request.CreateUserRequest;
import com.example.demo.modelDto.request.UpdateUserRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getListUsers(){
        List<UserDto> users=userService.getListUser();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/all")
    public ResponseEntity<?> listUsers(){
        List<User> users=userService.listUser();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){

       UserDto result=userService.getUserById(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/search")
    public ResponseEntity<?> getUserByKeyword(@RequestParam(defaultValue = "") String keyword){
        UserDto users=userService.getUserByKeyword(keyword);
        return ResponseEntity.ok(users);

    }
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest rq){

        UserDto result=new UserDto();
        result=userService.createUser(rq);
        return ResponseEntity.ok(result);

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest rq, @PathVariable int id){

        UserDto result=new UserDto();
        result=userService.updateUser(rq, id);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
            return ResponseEntity.ok("Delete success");

    }
}
