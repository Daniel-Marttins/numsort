package com.outbrick.numsort.usecases;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

public class SystemUtils {

    // Método estático para acessar a única instância da classe
    // Instância única da classe
    @Getter
    private static final SystemUtils instance = new SystemUtils();

    // Construtor privado para impedir a criação de novas instâncias
    private SystemUtils() {}

    /*
     * METODO PARA CRIPTOGRAFAR A SENHA DO USUÁRIO
     * METHOD FOR ENCRYPTING THE USER’S PASSWORD
     */
    public String encryptPassword(String profilePassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(profilePassword);
    }

    /*
     * GERAR UMA STRING ALEATORIA
     * GENERATE AN RANDOM STRING
     */
    public String generateRandomString(int length) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final SecureRandom random = new SecureRandom();

        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

}
