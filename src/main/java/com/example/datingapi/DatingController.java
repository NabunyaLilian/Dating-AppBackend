package com.example.datingapi;

import com.example.datingapi.User.User;
import com.example.datingapi.User.UserRepository;
import com.example.datingapi.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class DatingController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody @Valid User user){
        userService.addUser(user);
//        return ResponseEntity.accepted().body("Added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body("Added successfully");
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) throws Exception{

        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody @Valid User user, @PathVariable int id){
        userService.updateUser(user, id);
        return ResponseEntity.accepted().body("Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id ) throws Exception{;
        userService.deleteUser(id);
        return  ResponseEntity.accepted().body("Field deleted");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }


}
