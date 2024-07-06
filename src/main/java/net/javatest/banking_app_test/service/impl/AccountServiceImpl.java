package net.javatest.banking_app_test.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javatest.banking_app_test.dto.AccountDto;
import net.javatest.banking_app_test.entity.Account;
import net.javatest.banking_app_test.mapper.AccountMapper;
import net.javatest.banking_app_test.reporsitory.AccountRepo;
import net.javatest.banking_app_test.service.AccountService;



@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.geAccount(accountDto);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.geAccountDto(savedAccount);
    }

    @SuppressWarnings("unused")
    @Override
    public AccountDto getAccountById(long id) {
        // Account account = accountRepo.getReferenceById(id);
        // if (account == null) {
        //     throw new RuntimeException("Account does not exists");
        // }
        // return AccountMapper.geAccountDto(account);
       Account account = accountRepo.findById(id)
       .orElseThrow(() -> new RuntimeException("Account Not found")); 
       return AccountMapper.geAccountDto(account);
    }
    @SuppressWarnings("unused")
    @Override
    public AccountDto depositBalance(long id, double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account Not found")); 
        account.setBalance(account.getBalance() + amount);
        Account saveAccount = accountRepo.save(account);
        return AccountMapper.geAccountDto(saveAccount);
    }

    @Override
    public AccountDto withdrawBalance(long id, double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account Not found")); 
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }
        account.setBalance(account.getBalance() - amount);
        Account saveAccount = accountRepo.save(account);
        return AccountMapper.geAccountDto(saveAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> list = accountRepo.findAll();
        return list.stream().map((account) -> AccountMapper.geAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public boolean deleteAccount(long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account Not found"));
        if (account == null) {
            return false;
        }
        accountRepo.delete(account);
        return true;
    }

}
