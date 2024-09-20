package com.outbrick.numsort.services;

import com.outbrick.numsort.entities.Holding;
import com.outbrick.numsort.exceptions.HoldingException;
import com.outbrick.numsort.repositories.HoldingRepository;
import com.outbrick.numsort.usecases.SystemUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoldingService {

    @Autowired
    private HoldingRepository holdingRepository;

    @Transactional
    private Holding saveHolding(Holding holding) {
        return holdingRepository.save(holding);
    }

    @Transactional
    private Holding getHolding(Long id) {
        return holdingRepository.findById(id)
                .orElseThrow(() -> new HoldingException
                        .HoldingNotFoundException("Participação não encontrada!")
                );
    }

    @Transactional
    private List<Holding> getAllHoldings() {
        return holdingRepository.findAll();
    }

    @Transactional
    private List<Holding> getAllHoldingsByUser(Long userId) {
        return holdingRepository.findByUserId_Id(userId);
    }

    @Transactional
    private Holding updateHolding(Long id, Holding updatedHolding) {
        return holdingRepository.findById(id)
                .map(existingHolding -> {
                    SystemUtils.modelMapper.map(updatedHolding, existingHolding);
                    return holdingRepository.save(existingHolding);
                }).orElseThrow(() -> new HoldingException
                        .HoldingNotFoundException(STR."Participação com ID \{id} não encontrada!")
                );
    }

    @Transactional
    private void deleteHolding(Long id) {
        Holding holding = holdingRepository.findById(id)
                .orElseThrow(() -> new HoldingException
                        .HoldingNotFoundException(STR."Participação com ID \{id} não encontrada!")
                );
        holdingRepository.delete(holding);
    }

}
