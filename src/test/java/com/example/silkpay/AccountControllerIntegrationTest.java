package com.example.silkpay;

import com.example.silkpay.model.Account;
import com.example.silkpay.repository.AccountRepository;
import com.example.silkpay.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void transferMoney_ValidParameters_ReturnsSuccessResponse() throws Exception {
        Long sourceId = 1L;
        Long targetId = 2L;
        BigDecimal amount = new BigDecimal("100");

        Account sourceAccount = new Account();
        sourceAccount.setId(sourceId);
        sourceAccount.setBalance(new BigDecimal("500"));

        Account targetAccount = new Account();
        targetAccount.setId(targetId);
        targetAccount.setBalance(new BigDecimal("200"));

        when(accountService.getBalance(sourceId)).thenReturn(sourceAccount.getBalance());
        when(accountService.getBalance(targetId)).thenReturn(targetAccount.getBalance());

        mockMvc.perform(MockMvcRequestBuilders.post("/account/transfer")
                .param("sourceId", String.valueOf(sourceId))
                .param("targetId", String.valueOf(targetId))
                .param("amount", amount.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Транзакция совершена успешно"));

        verify(accountService, times(1)).transferMoney(sourceId, targetId, amount);
    }

    @Test
    void checkBalance_ExistingAccountId_ReturnsAccountBalance() throws Exception {
        Long accountId = 1L;
        BigDecimal balance = new BigDecimal("500");

        Account account = new Account();
        account.setId(accountId);
        account.setBalance(balance);
        accountRepository.save(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/account/checkBalance/{id}", accountId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(account.getBalance())));
    }


    @Test
    void createAccount_ValidAmount_ReturnsSuccessResponse() throws Exception {
        BigDecimal amount = new BigDecimal("100");

        mockMvc.perform(MockMvcRequestBuilders.post("/account/createAccount")
                .param("amount", amount.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Счет успешно создан"));

        verify(accountService, times(1)).createAccount(amount);
    }
}
