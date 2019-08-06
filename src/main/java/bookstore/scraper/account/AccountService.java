package bookstore.scraper.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        if (accountRepository.existsByNickname(account.getNickname())) {
            throw new IllegalArgumentException("Account with that nickname already exists");
        }
        Account encryptedAccount = Account.builder()
                .nickname(account.getNickname())
                .password(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()))
                .build();

        return accountRepository.save(encryptedAccount);
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }
}