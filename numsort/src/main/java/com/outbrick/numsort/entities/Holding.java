package com.outbrick.numsort.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "holdings")
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Number list cannot be null")
    @Column(nullable = false)
    private List<Integer> numbers;

    @NotNull(message = "User cannot be null")
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;

    @NotNull(message = "Raffle cannot be null")
    @ManyToOne
    @JoinColumn(name = "raffleId", referencedColumnName = "id")
    private Raffle raffleId;

}
