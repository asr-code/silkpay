package com.example.silkpay.service;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface AccountService {
    @Transactional
    void transferMoney(Long sourceAccountId, Long targetAccountId, BigDecimal amount);

    BigDecimal getBalance(Long id);

    void createAccount(BigDecimal amount);
}
