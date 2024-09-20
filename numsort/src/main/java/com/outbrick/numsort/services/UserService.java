package com.outbrick.numsort.services;

import com.outbrick.numsort.entities.Account;
import com.outbrick.numsort.entities.Raffle;
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

        if (user == null) throw new UserExceptions.UserUnauthorizedException("Usuário não encontrado com o email fornecido.");
        if (!SystemUtils.passwordEncoder.matches(password, user.getPassword())) throw new UserExceptions.UserUnauthorizedException("Senha incorreta.");

        return user;
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {

                    List<Account> existingAccounts = existingUser.getAccounts();
                    List<Raffle> existingRaffles = existingUser.getRaffles();

                    updatedUser.setPassword(existingUser.getPassword());
                    SystemUtils.modelMapper.map(updatedUser, existingUser);

                    existingUser.setAccounts(existingAccounts);
                    existingUser.setRaffles(existingRaffles);

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new UserExceptions.UserNotFoundException(STR."Usuário com ID \{id} não encontrado"));
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserExceptions.UserNotFoundException(STR."Usuário com ID \{id} não encontrado"));
        userRepository.delete(user);
    }

}
