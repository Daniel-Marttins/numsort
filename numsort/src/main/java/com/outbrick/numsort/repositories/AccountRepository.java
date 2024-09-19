package com.outbrick.numsort.repositories;

import com.outbrick.numsort.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByIdentificationKey(String identificationKey);
}
