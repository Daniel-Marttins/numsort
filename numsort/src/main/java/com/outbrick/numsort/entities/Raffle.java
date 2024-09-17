package com.outbrick.numsort.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "raffles")
public class Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be null")
    @Column(columnDefinition = "VARCHAR(100)", length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column(columnDefinition = "INT DEFAULT 0", nullable = true)
    private int holdings;

    @ElementCollection
    @Column(nullable = true)
    private List<String> imgUrls;

    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private User ownerId;

}
