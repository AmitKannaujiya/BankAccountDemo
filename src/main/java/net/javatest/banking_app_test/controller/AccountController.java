package net.javatest.banking_app_test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;

import net.javatest.banking_app_test.dto.AccountDto;
import net.javatest.banking_app_test.service.AccountService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Add Account Rest Api
    @PostMapping("/add")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(this.accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(this.accountService.getAccountById(id));
    }

    // Deposit Amount Rest Api
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAmountById(@PathVariable Long id, @RequestBody HashMap<String, Double> request) {
        double amount = request.getOrDefault("amount", 0.0);
        return ResponseEntity.ok(this.accountService.depositBalance(id, amount));
    }

    // Widraw Amount Rest Api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> widrawAmountById(@PathVariable Long id, @RequestBody HashMap<String, Double> request) {
        double amount = request.getOrDefault("amount", 0.0);
        return ResponseEntity.ok(this.accountService.withdrawBalance(id, amount));
    }
    //Get All Acoounts
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(this.accountService.getAllAccounts());
    }

    // Delete Account
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
        boolean success = this.accountService.deleteAccount(id);
        String msg = "Account deleted successfully!";
        if(!success) {
            msg = "Account does not exists!";
        }
        Map<String, Object> resp = new HashMap<>();
        resp.put("status", success);
        resp.put("msg", msg);
        return new ResponseEntity<Object>(resp, HttpStatus.OK);
    }

}
