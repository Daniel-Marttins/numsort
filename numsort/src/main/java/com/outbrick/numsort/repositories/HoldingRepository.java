package com.outbrick.numsort.repositories;

import com.outbrick.numsort.entities.Holding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoldingRepository extends JpaRepository<Holding, Long> {
    List<Holding> findByUserId_Id(Long userId);
}
