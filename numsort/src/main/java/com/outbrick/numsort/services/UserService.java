package com.outbrick.numsort.services;

import com.outbrick.numsort.entities.User;
import com.outbrick.numsort.exceptions.UserExceptions;
import com.outbrick.numsort.repositories.UserRepository;
import com.outbrick.numsort.usecases.SystemUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public User saveUser(User user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserExceptions.UserExistsException("Usuário com este e-mail já está cadastrado");
        }

        String password = SystemUtils.getInstance().generateRandomString(6);
        user.setPassword(SystemUtils.getInstance().encryptPassword(password));
        User savedUser = userRepository.save(user);
        emailService.sendEmail(
                savedUser.getEmail(),
                "Cadastro realizado com sucesso",
                savedUser.getName(),
                password
        );
        return savedUser;
    }

    @Transactional
    public User getUser(Long id) {
        if(userRepository.findById(id).isEmpty()) throw new UserExceptions.UserNotFoundException("Usuário com não encontrado!");
        return userRepository.findById(id).get();
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User getByLogin(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user != null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, user.getPassword())) return user;
            return null;
        }
        return null;
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser != null) {
            modelMapper.map(updatedUser, existingUser);
            return userRepository.save(existingUser);
        };
        return null;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
