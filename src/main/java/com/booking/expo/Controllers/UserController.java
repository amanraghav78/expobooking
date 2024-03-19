package com.booking.expo.Controllers;

import com.booking.expo.Entities.User;
import com.booking.expo.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userService.createUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user){
        User updatedUser= userService.updateUser(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable Integer id){
        User user= userService.showUser(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> showAllUsers(){
        List<User> users = userService.showAllUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}
