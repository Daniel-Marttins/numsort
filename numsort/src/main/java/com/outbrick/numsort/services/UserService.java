package com.outbrick.numsort.services;

import com.outbrick.numsort.entities.User;
import com.outbrick.numsort.repositories.UserRepository;
import com.outbrick.numsort.usecases.SystemUtils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public User saveUser(User user) {
        user.setPassword(SystemUtils.getInstance().encryptPassword(
                SystemUtils.getInstance().generateRandomString(6)
        ));
        return userRepository.save(user);
    }

    @Transactional
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<User> getAllUser() {
        return userRepository.findAll();
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
