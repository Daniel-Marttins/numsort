package com.outbrick.numsort.repositories;

import com.outbrick.numsort.entities.Holdings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoldingRepository extends JpaRepository<Holdings, Long> { }
