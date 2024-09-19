package com.outbrick.numsort.services;

import com.outbrick.numsort.entities.Account;
import com.outbrick.numsort.exceptions.AccountExceptions;
import com.outbrick.numsort.repositories.AccountRepository;
import com.outbrick.numsort.usecases.SystemUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Account saveAccount(Account account) {
        if(accountRepository.findByIdentificationKey(account.getIdentificationKey()) != null) {
            throw new AccountExceptions.AccountExistsException("Chave de indentificação já existe!");
        }
        return accountRepository.save(account);
    }

    @Transactional
    public Account getAccount(Long id) {
        if(accountRepository.findById(id).isEmpty()) throw new AccountExceptions
                .AccountNotFoundException("Nenhuma conta encontrada!");
        return accountRepository.findById(id).get();
    }

    @Transactional
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public Account updateAccount(Long id, Account updatedAccount) {
        return accountRepository.findById(id)
                .map(existingAccount -> {
                    SystemUtils.modelMapper.map(updatedAccount, existingAccount);
                    return accountRepository.save(existingAccount);
                }).orElseThrow(() -> new AccountExceptions.AccountNotFoundException(
                        STR."Conta com o ID \{id} não encontrada")
                );
    }

    @Transactional
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountExceptions.AccountNotFoundException(STR."Conta com ID \{id} não encontrada"));
        accountRepository.delete(account);
    }

}
