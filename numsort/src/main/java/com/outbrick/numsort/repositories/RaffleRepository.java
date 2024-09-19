package com.outbrick.numsort.repositories;

import com.outbrick.numsort.entities.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaffleRepository extends JpaRepository<Raffle, Long> {
    Raffle findBySharedLink(String sharedLink);
}
