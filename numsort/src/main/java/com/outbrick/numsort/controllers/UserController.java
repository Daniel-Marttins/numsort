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
import java.util.Map;

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
        } catch (UserExceptions.UserExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao criar usuário: \{e.getMessage()}");
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao buscar usuário: \{e.getMessage()}");
        }
    }

    @RequestMapping(value = "/find/login", method = RequestMethod.GET)
    public ResponseEntity<?> findByLogin(@RequestBody(required = true) Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        try {
            User user = userService.getByLogin(email, password);
            return ResponseEntity.ok(user);
        } catch (UserExceptions.UserUnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/find/all", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao buscar usuários: \{e.getMessage()}");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@Valid @RequestParam(value = "id", required = true) Long id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (UserExceptions.UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao atualizar usuário: \{e.getMessage()}");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@RequestParam(value = "id", required = true) Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário deletado com sucesso!");
        } catch (UserExceptions.UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao deletar usuário: \{e.getMessage()}");
        }
    }

}
