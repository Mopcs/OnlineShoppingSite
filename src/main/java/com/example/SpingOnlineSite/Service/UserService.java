package com.example.SpingOnlineSite.Service;

import com.example.SpingOnlineSite.Entity.User;
import com.example.SpingOnlineSite.Repository.UserRepository;
import com.example.SpingOnlineSite.Request.LoginRequest;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден с почтой: " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден с id: " + userId));
    }

    public User registerUser(User user) {
        if (!findEmail(user.getEmail())) {
            if (!findPhoneNumber(user.getPhoneNumber())) {
                user.setCreatedAt(LocalDateTime.now());
                user.setUpdatedAt(LocalDateTime.now());
                return userRepository.save(user);
            } else {
                throw new IllegalArgumentException("Пользователь с таким номером телефона уже существует.");
            }
        } else {
            throw new IllegalArgumentException("Пользователь с такой электронной почтой уже существует.");
        }
    }


    public Optional<User> loginUser(LoginRequest request) {
        return userRepository.findByPhoneNumberAndPassword(request.getPhoneNumber(), request.getPassword());
    }

    public User updateUser(int userId, User user) {
        User existingUser = getUserById(userId);

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(existingUser);
    }

    public boolean findPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    public boolean findEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Integer getUserIdByPhoneNumber(String phoneNumber) {
        Optional<User> user = findByPhoneNumber(phoneNumber);
        return user.get().getUserId();
    }

    public void deleteUser(int userId) {
        getUserById(userId);

        userRepository.deleteById(userId);
    }

    public Optional<User> findByPhoneNumber(String phoneNumber) { return  userRepository.findByPhoneNumber(phoneNumber);}

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByGender(String gender) {
        return userRepository.findByGender(gender);
    }
}
