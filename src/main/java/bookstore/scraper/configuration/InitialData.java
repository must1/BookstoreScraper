package bookstore.scraper.configuration;

import bookstore.scraper.account.Account;
import bookstore.scraper.account.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitialData {
    private final AccountRepository accountRepository;

    @Autowired
    public InitialData(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addUsersToDB() {
        log.info("Persisted account data to database");

        accountRepository.save(Account.builder()
                .nickname("user")
                .password("$2a$10$iwcnDknrJDy2kHMMgsJ/MeGMMAMjjAtHWrl1VXVVpIqCby5AVXJMS") //1234
                .build());

        accountRepository.save(Account.builder()
                .nickname("user2")
                .password("$2a$10$W.YbViN.Jhuhn9bV0v/zlON5Rh0pKm85QVbiDvTjADlPwp.gFuy/C") //123
                .build());
    }
}
