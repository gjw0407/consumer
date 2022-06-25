package com.example.consumer.model;

import com.example.consumer.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDto {
    private String email;
    private String password;

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
