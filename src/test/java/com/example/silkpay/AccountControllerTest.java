package com.example.silkpay;

import com.example.silkpay.controller.AccountController;
import com.example.silkpay.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void transferMoney_ValidParameters_ReturnsSuccessResponse() {
        // Arrange
        Long sourceId = 1L;
        Long targetId = 2L;
        BigDecimal amount = new BigDecimal("100");

        // Act
        ResponseEntity<String> response = accountController.transferMoney(sourceId, targetId, amount);

        // Assert
        verify(accountService, times(1)).transferMoney(sourceId, targetId, amount);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Транзакция совершена успешно", response.getBody());
    }

    @Test
    void checkBalance_ExistingAccountId_ReturnsAccountBalance() {
        // Arrange
        Long accountId = 1L;
        BigDecimal balance = new BigDecimal("500");

        when(accountService.getBalance(accountId)).thenReturn(balance);

        // Act
        ResponseEntity<BigDecimal> response = accountController.checkBalance(accountId);

        // Assert
        verify(accountService, times(1)).getBalance(accountId);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(balance, response.getBody());
    }

    @Test
    void createAccount_ValidAmount_ReturnsSuccessResponse() {
        // Arrange
        BigDecimal amount = new BigDecimal("100");

        // Act
        ResponseEntity<String> response = accountController.createAccount(amount);

        // Assert
        verify(accountService, times(1)).createAccount(amount);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Счет успешно создан", response.getBody());
    }
}
