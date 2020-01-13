package com.example.datingapi;

import com.example.datingapi.User.User;
import com.example.datingapi.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DatingController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user){
        if(user.getName().isEmpty()){
            throw new CustomException(
                    "Wrong Name",
                    "You've entered an incorrect passcode, try again with correct one",
                    "Orange, juicy and sweet",
                    "Ask your friends for access at http://wwww.letmeinthesecretdoor.com",
                    "Reach out to http://wwww.letmeinthesecretdoor.com/support for more help");
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added successfully");
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id){

        return userService.getUser(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable int id){
        userService.updateUser(user, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated Successfully");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id ){

        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Field deleted");
    }
}
