package com.outbrick.numsort.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100)", length = 100, nullable = false)
    @NotBlank(message = "Name cannot be null")
    private String name;

    @Column(columnDefinition = "VARCHAR(100)", length = 100, nullable = false)
    @NotBlank(message = "Email cannot be null")
    private String email;

    @Column(columnDefinition = "VARCHAR(100)", length = 100, nullable = false)
    @NotBlank(message = "Password cannot be null")
    private String password;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String imgUrl;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String bio;

    @Column(columnDefinition = "INT DEFAULT 0", nullable = true)
    private int rate;

    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Raffle> raffles;

    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = true)
    private LocalDateTime updatedAt;

}
