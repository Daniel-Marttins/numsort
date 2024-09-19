package com.outbrick.numsort.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.rmi.MarshalException;
import java.time.LocalDateTime;
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

    @NotBlank(message = "Status cannot be null")
    @Column(columnDefinition = "VARCHAR(1)", nullable = false)
    private String status;

    @NotNull(message = "Price cannot be null")
    @Column(columnDefinition = "DOUBLE PRECISION", nullable = false)
    private double ticketPrice;

    @Column(columnDefinition = "INT DEFAULT 0", nullable = true)
    private int holdings;

    @ElementCollection
    @Column(nullable = true)
    private List<String> imgUrls;

    @Column(nullable = true)
    private List<Integer> numbers;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String sharedLink;

    @NotNull(message = "Owner cannot be null")
    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "id", nullable = false)
    private User ownerId;

    @OneToMany(mappedBy = "raffleId")
    private List<Holdings> numberHoldings;

    @NotNull(message = "Create date cannot be null")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = true)
    private LocalDateTime updatedAt;

}
