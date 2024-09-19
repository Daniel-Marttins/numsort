package com.outbrick.numsort.controllers;

import com.outbrick.numsort.entities.User;
import com.outbrick.numsort.exceptions.UserExceptions;
import com.outbrick.numsort.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (UserExceptions e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
        try {
            User user = userService.getUser(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(user);
        } catch (UserExceptions.UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/find/all", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.FOUND).body(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar usuários: " + e.getMessage());
        }
    }

}
