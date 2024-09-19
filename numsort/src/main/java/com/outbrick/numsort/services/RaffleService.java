package com.outbrick.numsort.services;

import com.outbrick.numsort.entities.Raffle;
import com.outbrick.numsort.exceptions.RaffleExceptions;
import com.outbrick.numsort.repositories.RaffleRepository;
import com.outbrick.numsort.usecases.SystemUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaffleService {

    @Autowired
    private RaffleRepository raffleRepository;

    @Transactional
    public Raffle saveRaffle(Raffle raffle) {
        return raffleRepository.save(raffle);
    }

    @Transactional
    public Raffle getRaffle(Long id) {
        return raffleRepository.findById(id)
                .orElseThrow(() -> new RaffleExceptions
                        .RaffleNotFound("Sorteio não encontrado!")
                );
    }

    @Transactional
    public Raffle getRaffleByLink(String sharedLink) {
        Raffle raffle = raffleRepository.findBySharedLink(sharedLink);
        if(raffle == null) throw new RaffleExceptions.RaffleNotFound("Sorteio não encontrado com link fornecido.");
        return raffleRepository.findBySharedLink(sharedLink);
    }

    @Transactional
    public List<Raffle> getAllRaffles() {
        return raffleRepository.findAll();
    }

    @Transactional
    public Raffle updateRaffle(Long id, Raffle updatedRaffle) {
        return raffleRepository.findById(id)
                .map(existingRaffle -> {
                    SystemUtils.modelMapper.map(updatedRaffle, existingRaffle);
                    return raffleRepository.save(existingRaffle);
                }).orElseThrow(() -> new RaffleExceptions
                        .RaffleNotFound(STR."Sorteio com ID \{id} não encontrado!")
                );
    }

    @Transactional
    public void deleteRaffle(Long id) {
        Raffle raffle = raffleRepository.findById(id)
                .orElseThrow(() -> new RaffleExceptions.RaffleNotFound(STR."Sorteio com ID \{id} não encontrado"));
        raffleRepository.delete(raffle);
    }

}
