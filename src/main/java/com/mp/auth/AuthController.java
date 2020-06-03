package com.mp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;

    @GetMapping(value = "/register")
    @ResponseBody
    public String register(@RequestParam String username, @RequestParam String password){
        User newUser = new User();
        newUser.setUsername(username);
        Integer hash = password.hashCode();
        newUser.setHash(hash);
        authService.save(newUser);
        return "You have successfully registered";
    }

    @GetMapping(value = "/auth")
    @ResponseBody
    public String auth(@RequestParam String username, @RequestParam String password){
        Optional<User> user = authService.getByUsername(username);
        if (user.isPresent()){
            Integer hash = user.get().getHash();
            Integer currentHash = password.hashCode();
            if (hash.equals(currentHash)) {
                AuthServiceImpl.setCurrentUser(username);
                return "LogIn successfully";
            }
            else return "Incorrect Data";
        }
        else return "User doesn't exist";
    }

}
