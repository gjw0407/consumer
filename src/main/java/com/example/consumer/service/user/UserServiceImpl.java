package com.example.consumer.service.user;

import com.example.consumer.dao.UserDao;
import com.example.consumer.entity.User;
import com.example.consumer.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserDao userDao;

    @Override
    public boolean login(UserDto user) {
        String encodedPassword = userDao.findPwd(user.getEmail());
        User userET = userDao.findByEmail(user.getEmail());

        if (passwordEncoder.matches(user.getPassword(), encodedPassword)
                && userET.getEmail().equals(user.getEmail())) {
            user.setPassword(encodedPassword);
            return userDao.existsByEmailAndPassword(user.getEmail(), user.getPassword());
        }

        return false;
    }

    @Override
    public boolean register(UserDto user) {
        if (userDao.findByEmail(user.getEmail()) != null) return false;

        User userET = user.toEntity();

        try {
            userDao.save(userET);
            return true;
        } catch (Exception e) {
            // TODO Exception Handling
            return false;
        }

    }
}
