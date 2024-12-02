package com.example.jobseekingsystem.Service;


import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void addUser(User user) {
        userRepository.save(user);
    }

    public String updateUser(Integer userId,User user) {

        for (User user1 : userRepository.findAll()) {
            if (user1.getId() == userId) {

                user1.setName(user.getName());
                user1.setEmail(user.getEmail());
                user1.setPassword(user.getPassword());
                user1.setAge(user.getAge());
                user1.setRole(user.getRole());
                userRepository.save(user1);
                return null;

            }
        }

        return "User not found";
    }

    public String deleteUser(Integer userId) {
        for (User user1 : userRepository.findAll()) {
            if (user1.getId().equals( userId)) {
                userRepository.delete(user1);
                return null;
            }
        }
        return "User not found";
    }


    public User getUserById(Integer id) {
        for (User user : userRepository.findAll()) {
            if (user.getId().equals( id)) {
                return user;
            }
        }
        return null;
    }

}
