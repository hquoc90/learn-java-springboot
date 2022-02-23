package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateException;
import com.example.demo.exception.InternalException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.modelDto.UserDto;
import com.example.demo.modelDto.mapper.UserMapper;
import com.example.demo.modelDto.request.CreateUserRequest;
import com.example.demo.modelDto.request.UpdateUserRequest;
import com.example.demo.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private static ArrayList<User> users=new ArrayList<User>();
   /* static{
       users.add(new User(1, "hoang quoc", "hoangquoc90@gmail.com","088899988","google.com","123445"));
        users.add(new User(2, "Long lan", "Long@gmail.com","2223232","google.com","123445"));
        users.add(new User(3, "Ngoc trinh", "Ngoc@gmail.com","12312312","google.com","123445"));
        users.add(new User(4, "Apple", "Apple@gmail.com","123122222","google.com","123445"));
        users.add(new User(5, "fitter", "fitter@gmail.com","123122222","google.com","123445"));
    }*/

    @Override
    public List<UserDto> getListUser() {
       List<User> users= userRepository.findAll();
       List<UserDto> result=new ArrayList<UserDto>();
        for (User user :
                users) {
            result.add(UserMapper.toUserDto(user));
        }
        return result;
       /* List<UserDto> result=new ArrayList<UserDto>();
        UserMapper um=new UserMapper();
        for (User user: users
             ) {
            result.add(um.toUserDto(user));
        }
        return null;*/
    }

    @Override
    public UserDto getUserById(int id) {
        Optional<User> user=userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("user not found");
        }
        return UserMapper.toUserDto(user.get());


        /*UserMapper us=new UserMapper();
        for (User user: users
             ) {
            if (user.getId() == id) {
               return us.toUserDto(user);
            }
        }*/

    }

    @Override
    public UserDto getUserByKeyword(String keyword) {

        try{
            User user= userRepository.findUserByName(keyword);
            return UserMapper.toUserDto(user);
        }catch (Exception e){
            throw new NotFoundException("del tim thay");
        }

/* UserMapper us=new UserMapper();
        List<UserDto> result=new ArrayList<UserDto>();
        for (User user :
                users) {
            if (user.getName().toLowerCase().contains(keyword)) {
                result.add(us.toUserDto(user));
            }
        }
        return result;*/


    }

    @Override
    public UserDto createUser(CreateUserRequest rq) {
       /* for (User user :
                users) {
            if (user.getEmail().equals(rq.getEmail())) {
                throw new DuplicateException("Email da ton tai tren he thong");
            }
        }
        User user=new User();
        user.setId(users.size()+1);
        user.setEmail(rq.getEmail());
        user.setName(rq.getName());
        user.setPhone(rq.getPhone());
        user.setPassword(BCrypt.hashpw(rq.getPassword(), BCrypt.gensalt(12)));
        users.add(user);
        UserMapper um=new UserMapper();
        return um.toUserDto(user);*/

//        User result=userRepository.findByEmail(rq.getEmail());
        //su dung custom query
        User result=userRepository.myCustomQuery2(rq.getEmail());
        if (result!=null) {
                throw new DuplicateException("User da ton tai");
        }
        User user=new User();
        user.setEmail(rq.getEmail());
        user.setName(rq.getName());
        user.setPhone(rq.getPhone());
        user.setPassword(BCrypt.hashpw(rq.getPassword(), BCrypt.gensalt(12)));
        try{
            userRepository.save(user);
        }catch (Exception ex){
            throw new InternalException("Khong add dc user");
        }
        return  UserMapper.toUserDto(user);

    }

    @Override
    public List<User> listUser() {
        return users;
    }

    @Override
    public UserDto updateUser(UpdateUserRequest rq, int id) {

       /* for (User user :
                users) {
          if (user.getId()==id){
              if (!user.getEmail().equals(rq.getEmail())) {
                  for (User tmp :
                          users) {
                      if (tmp.getEmail().equals(rq.getEmail())) {
                          throw new DuplicateException("email da ton tai trong he thong");
                      }
                  }
                  user.setEmail(rq.getEmail());
              }
              user.setAvatar(rq.getAvatar());
              user.setPhone(rq.getPhone());
              user.setName(rq.getName());
              UserMapper um=new UserMapper();
              return um.toUserDto(user);
          }
        }
        throw new NotFoundException("User not found");*/
        Optional<User> result=userRepository.findById(id);
        if (result.isEmpty() ) {
            throw new NotFoundException("User not found");
        }
        User user=result.get();
        user.setEmail(rq.getEmail());
        user.setName(rq.getName());
        user.setPhone(rq.getPhone());
        user.setPassword(BCrypt.hashpw(rq.getPassword(), BCrypt.gensalt(12)));
        try{
            userRepository.save(user);
        }catch (Exception ex){
            throw new InternalException("Database errors");
        }
        return  UserMapper.toUserDto(user);

    }

    @Override
    public void deleteUser(int id) {
        Optional<User> result=userRepository.findById(id);
        if (result.isEmpty() ) {
            throw new NotFoundException("User not found");
        }
        try{
            userRepository.deleteById(id);
        }catch (Exception ex){
            throw new InternalException("Database errors");
        }
       /* for (User user :
                users) {
            if (user.getId() == id) {
                users.remove(user);
                return true;
            }
            throw new NotFoundException("user not found");
        }
        return false;*/

    }
}
