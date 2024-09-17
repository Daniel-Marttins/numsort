package com.outbrick.numsort.repositories;

import com.outbrick.numsort.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
