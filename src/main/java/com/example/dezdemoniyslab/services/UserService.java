package com.example.dezdemoniyslab.services;

import com.example.dezdemoniyslab.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BaseUserService baseUserService;

    public List<User> getAllUsers() {
        return baseUserService.getAllUsersFromDatabase();
    }

    public void createUser(User user) {
        baseUserService.saveUserToDatabase(user);
    }

    public void updateUser(User user) {
        baseUserService.saveUserToDatabase(user);
    }

    public void deleteUser(Long id) {
        baseUserService.softDeleteUserFromDatabaseById(id);
    }

    public User getUserById(Long id) {
        return baseUserService.getUserFromDatabaseById(id);
    }
}
