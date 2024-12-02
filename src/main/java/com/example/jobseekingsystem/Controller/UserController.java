package com.example.jobseekingsystem.Controller;


import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/job-seeking-system/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User added successfully");
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable int userId, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

       String response =  userService.updateUser(userId, user);

        if (response == null) {
            return ResponseEntity.status(200).body("User updated successfully");
        }
        return ResponseEntity.status(400).body(response);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable int userId) {

        String response =  userService.deleteUser(userId);
        if (response == null) {
            return ResponseEntity.status(200).body("User deleted successfully");
        }
        return ResponseEntity.status(400).body(response);
    }


}
