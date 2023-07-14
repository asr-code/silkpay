package com.example.silkpay;

import com.example.silkpay.model.Account;
import com.example.silkpay.repository.AccountRepository;
import com.example.silkpay.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SilkpayApplicationTests {
//
//
//	@Mock
//	private AccountRepository accountRepository;
//
//	@InjectMocks
//	private AccountServiceImpl accountService;
//
//	@BeforeEach
//	void setup() {
//		MockitoAnnotations.openMocks(this);
//	}
//
//	@Test
//	void transferMoney_ValidAccounts_TransfersAmountBetweenAccounts() {
//		// Arrange
//		Long sourceId = 1L;
//		Long targetId = 2L;
//		BigDecimal amount = new BigDecimal("100");
//
//		Account sourceAccount = new Account();
//		sourceAccount.setId(sourceId);
//		sourceAccount.setBalance(new BigDecimal("500"));
//
//		Account targetAccount = new Account();
//		targetAccount.setId(targetId);
//		targetAccount.setBalance(new BigDecimal("200"));
//
//		when(accountRepository.findById(sourceId)).thenReturn(Optional.of(sourceAccount));
//		when(accountRepository.findById(targetId)).thenReturn(Optional.of(targetAccount));
//
//		// Act
//		accountService.transferMoney(sourceId, targetId, amount);
//
//		// Assert
//		Assertions.assertEquals(new BigDecimal("400"), sourceAccount.getBalance());
//		Assertions.assertEquals(new BigDecimal("300"), targetAccount.getBalance());
//
//		verify(accountRepository, times(2)).save(any(Account.class));
//	}
//
//	@Test
//	void transferMoney_InsufficientBalance_ThrowsException() {
//		// Arrange
//		Long sourceId = 1L;
//		Long targetId = 2L;
//		BigDecimal amount = new BigDecimal("100");
//
//		Account sourceAccount = new Account();
//		sourceAccount.setId(sourceId);
//		sourceAccount.setBalance(new BigDecimal("50"));
//
//		Account targetAccount = new Account();
//		targetAccount.setId(targetId);
//		targetAccount.setBalance(new BigDecimal("200"));
//
//		when(accountRepository.findById(sourceId)).thenReturn(Optional.of(sourceAccount));
//		when(accountRepository.findById(targetId)).thenReturn(Optional.of(targetAccount));
//
//		// Act & Assert
//		Assertions.assertThrows(IllegalStateException.class, () -> {
//			accountService.transferMoney(sourceId, targetId, amount);
//		});
//
//		verify(accountRepository, never()).save(any(Account.class));
//	}
//
//	@Test
//	void getBalance_ExistingAccount_ReturnsAccountBalance() {
//		// Arrange
//		Long accountId = 1L;
//		BigDecimal balance = new BigDecimal("500");
//
//		Account account = new Account();
//		account.setId(accountId);
//		account.setBalance(balance);
//
//		when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
//
//		// Act
//		BigDecimal result = accountService.getBalance(accountId);
//
//		// Assert
//		Assertions.assertEquals(balance, result);
//	}
//
//	@Test
//	void getBalance_NonExistingAccount_ThrowsException() {
//		// Arrange
//		Long accountId = 1L;
//
//		when(accountRepository.findById(accountId)).thenReturn(Optional.empty());
//
//		// Act & Assert
//		Assertions.assertThrows(IllegalArgumentException.class, () -> {
//			accountService.getBalance(accountId);
//		});
//	}
//
//	@Test
//	void createAccount_CreatesAccountWithInitialBalance() {
//		// Arrange
//		BigDecimal initialBalance = new BigDecimal("100");
//
//		// Act
//		accountService.createAccount(initialBalance);
//
//		// Assert
//		verify(accountRepository, times(1)).save(argThat(account ->
//				account.getBalance().equals(initialBalance)));
//	}

}
