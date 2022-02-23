package com.example.demo.modelDto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String name;

    @Email(message = "Email not format")
    private String email;

    @Size(min=4, max=20, message = "Password between 4-20 characters")
    private String password;

    private String phone;
}
