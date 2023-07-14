package com.example.silkpay.controller;

import com.example.silkpay.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(
            @RequestParam("sourceId") Long sourceId,
            @RequestParam("targetId") Long targetId,
            @RequestParam("amount") BigDecimal amount
            ) {
        accountService.transferMoney(sourceId, targetId, amount);

        return ResponseEntity.ok("Транзакция совершена успешно");
    }

    @GetMapping("/checkBalance/{id}")
    public ResponseEntity<BigDecimal> checkBalance(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(accountService.getBalance(id));
    }

    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(
            @RequestParam(value = "amount", required = false, defaultValue = "0") BigDecimal amount
    ){
        accountService.createAccount(amount);
        return ResponseEntity.ok("Счет успешно создан");
    }
}
