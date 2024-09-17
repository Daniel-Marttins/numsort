package com.outbrick.numsort.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "holdings")
public class Holdings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Number cannot be null")
    @Column(columnDefinition = "INT", nullable = false)
    private Integer number;

    @NotNull(message = "User cannot be null")
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;

    @NotNull(message = "Raffle cannot be null")
    @ManyToOne
    @JoinColumn(name = "raffleId", referencedColumnName = "id")
    private Raffle raffleId;

}
