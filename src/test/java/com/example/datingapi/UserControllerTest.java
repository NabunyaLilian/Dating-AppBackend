package com.example.datingapi;

import com.example.datingapi.User.User;
import com.example.datingapi.User.UserRepository;
import com.example.datingapi.User.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DatingController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserService userService;

    User user;
    List<User> users;

    @BeforeEach
    public void createUser() throws Exception{
        User user = new User("Lilian", "Lia", "L3mon6de#");
        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content(asJsonString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void addUserTest() throws Exception{
        User user = new User("Lilian", "Lia", "L3mon6de#");
        mockMvc.perform(post("/users")
                .contentType("application/json")
                .content(asJsonString(user)))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void updateUserTest() throws Exception{
        User updateUser = new User("Lian", "Lia", "L3mon6de#");
        when(userService.updateUser(user, 1)).thenReturn(user);
        mockMvc.perform(put("/users/{id}", 1)
                .contentType("application/json")
                .content(asJsonString(updateUser)))
                .andExpect(status().isAccepted());
    }

    @Test
    public void deleteUserTest() throws Exception{
        mockMvc.perform(delete("/users/{id}", 1))
                .andExpect(status().isAccepted());
    }

    @Test
    public void getUserTest() throws Exception{
        when(userService.getUser(1)).thenReturn(user);
        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void fetchUsers() throws Exception{
        when(userService.getAllUsers()).thenReturn(users);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
