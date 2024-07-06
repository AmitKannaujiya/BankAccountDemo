package net.javatest.banking_app_test.mapper;

import net.javatest.banking_app_test.dto.AccountDto;
import net.javatest.banking_app_test.entity.Account;

public class AccountMapper {

    public static AccountDto geAccountDto(Account account) {
        return new AccountDto(account.getId(), account.getAccountHolderName(), account.getBalance());
    }

    public static Account geAccount(AccountDto accountDto) {
        return new Account(accountDto.getId(), accountDto.getAccountHolderName(), accountDto.getBalance());
    }

}
