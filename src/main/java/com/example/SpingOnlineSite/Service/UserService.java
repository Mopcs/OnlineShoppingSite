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

/**
 * The type User service.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository the user repository
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
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

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     */
    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден с id: " + userId));
    }

    /**
     * Register user user.
     *
     * @param user the user
     * @return the user
     */
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


    /**
     * Login user optional.
     *
     * @param request the request
     * @return the optional
     */
    public Optional<User> loginUser(LoginRequest request) {
        return userRepository.findByPhoneNumberAndPassword(request.getPhoneNumber(), request.getPassword());
    }

    /**
     * Update user user.
     *
     * @param userId the user id
     * @param user   the user
     * @return the user
     */
    public User updateUser(int userId, User user) {
        User existingUser = getUserById(userId);

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(existingUser);
    }

    /**
     * Find phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    public boolean findPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    /**
     * Find email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean findEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    /**
     * Gets user id by phone number.
     *
     * @param phoneNumber the phone number
     * @return the user id by phone number
     */
    public Integer getUserIdByPhoneNumber(String phoneNumber) {
        Optional<User> user = findByPhoneNumber(phoneNumber);
        return user.get().getUserId();
    }

    /**
     * Delete user.
     *
     * @param userId the user id
     */
    public void deleteUser(int userId) {
        getUserById(userId);

        userRepository.deleteById(userId);
    }

    /**
     * Find by phone number optional.
     *
     * @param phoneNumber the phone number
     * @return the optional
     */
    public Optional<User> findByPhoneNumber(String phoneNumber) { return  userRepository.findByPhoneNumber(phoneNumber);}

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Find by gender optional.
     *
     * @param gender the gender
     * @return the optional
     */
    public Optional<User> findByGender(String gender) {
        return userRepository.findByGender(gender);
    }
}
