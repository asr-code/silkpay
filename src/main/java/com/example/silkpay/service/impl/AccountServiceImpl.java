package com.example.silkpay.service.impl;

import com.example.silkpay.model.Account;
import com.example.silkpay.repository.AccountRepository;
import com.example.silkpay.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public void transferMoney(Long sourceId, Long targetId, BigDecimal amount) {

        Account sourceAccount = accountRepository.findById(sourceId)
                .orElseThrow(() -> new IllegalArgumentException("Счет с таким ID отправителя не существует"));

        Account targetAccount = accountRepository.findById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("Счет с таким ID получателя не существует"));

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient balance in the source account");
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        targetAccount.setBalance(targetAccount.getBalance().add(amount));

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
    }

    @Override
    public BigDecimal getBalance(Long id) {
        if (accountRepository.findById(id).isPresent())
            return accountRepository.findById(id).get().getBalance();
        else {
            throw new IllegalArgumentException("Счет с таким ID не существует");
        }
    }

    @Override
    public void createAccount(BigDecimal amount) {
        Account account = new Account();
        account.setBalance(amount);
        accountRepository.save(account);
    }
}
