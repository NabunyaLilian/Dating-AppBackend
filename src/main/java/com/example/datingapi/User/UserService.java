package com.example.datingapi.User;

import com.example.datingapi.User.User;
import com.example.datingapi.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }

    public User getUser(int id){
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public void updateUser(User user, int id){
        userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

}
