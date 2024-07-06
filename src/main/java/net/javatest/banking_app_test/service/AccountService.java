package net.javatest.banking_app_test.service;

import java.util.List;

import net.javatest.banking_app_test.dto.AccountDto;

public interface AccountService {
    
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(long id);

    AccountDto depositBalance(long id, double amount);

    AccountDto withdrawBalance(long id, double amount);

    List<AccountDto> getAllAccounts();

    boolean deleteAccount(long id);
}
