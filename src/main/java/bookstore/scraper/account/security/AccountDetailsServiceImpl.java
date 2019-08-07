package bookstore.scraper.account.security;

import bookstore.scraper.account.Account;
import bookstore.scraper.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) {
        Account account = accountRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("Account does not exist!"));

        return new AccountPrincipal(account);
    }
}
