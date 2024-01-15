package com.example.SpingOnlineSite.Controller;

import com.example.SpingOnlineSite.Entity.User;
import com.example.SpingOnlineSite.Error.ErrorResponse;
import com.example.SpingOnlineSite.Request.LoginRequest;
import com.example.SpingOnlineSite.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type User controller.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     */
    @GetMapping("/getUserById/userId={userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    /**
     * Register user user.
     *
     * @param user the user
     * @return the user
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * Login user response entity.
     *
     * @param loginRequest the login request
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.loginUser(loginRequest);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update user user.
     *
     * @param userId the user id
     * @param user   the user
     * @return the user
     */
    @PutMapping("/update/userId={userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    /**
     * Delete user.
     *
     * @param userId the user id
     */
    @DeleteMapping("/deleteUserById/userId={userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

    /**
     * Find by username response entity.
     *
     * @param username the username
     * @return the response entity
     */
    @GetMapping("/findByUsername/username={username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/getUserIdByPhoneNumber/phoneNumber={phoneNumber}")
    public ResponseEntity<Map<String, Integer>> getUserIdByPhoneNumber(@PathVariable String phoneNumber) {
        Integer userId = userService.getUserIdByPhoneNumber(phoneNumber);

        Map<String, Integer> response = new HashMap<>();
        response.put("userId", userId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUserByPhoneNumber/phoneNumber={phoneNumber}")
    public ResponseEntity<User> getUserByPhoneNumber(@PathVariable String phoneNumber)
    {
        Optional<User> user = userService.findByPhoneNumber(phoneNumber);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Find by email response entity.
     *
     * @param email the email
     * @return the response entity
     */
    @GetMapping("/findByEmail/email={email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Find by gender response entity.
     *
     * @param gender the gender
     * @return the response entity
     */
    @GetMapping("/findByGender/gender={gender}")
    public ResponseEntity<User> findByGender(@PathVariable String gender) {
        Optional<User> user = userService.findByGender(gender);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
