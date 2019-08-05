package bookstore.scraper.controller;


import bookstore.scraper.account.Account;
import bookstore.scraper.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public Account createUser(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/z")
    public List<Account> z() {
        return accountService.getAllAccounts();
    }
}