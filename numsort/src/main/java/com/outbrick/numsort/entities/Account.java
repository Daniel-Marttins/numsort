package com.outbrick.numsort.entities;

import com.outbrick.numsort.types.KeyType;
import jakarta.persistence.*;

@Entity(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(150)", nullable = false)
    private String identificationKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KeyType keyType;

    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "id")
    private User ownerId;

}
