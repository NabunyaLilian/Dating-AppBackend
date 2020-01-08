package com.example.datingapi.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }

    public User getUser(int id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("No user record exist for given id");
        }
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user , int id){
        return userRepository.findById(id)
                .map(usr -> {
                    usr.setName(user.getName());
                    usr.setUsername(user.getUsername());
                    usr.setPassword(user.getPassword());
                    return userRepository.save(usr);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });
    }

    public void deleteUser(int id) throws Exception{
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new Exception("No user found with this Id");
        }
    }

}
