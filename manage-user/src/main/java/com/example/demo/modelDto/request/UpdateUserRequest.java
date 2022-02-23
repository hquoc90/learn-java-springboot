package com.example.demo.modelDto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UpdateUserRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String avatar;

}
