package bookstore.scraper.account;

import bookstore.scraper.account.security.AccountPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoggedAccountService {

    private LoggedAccountService() {
        throw new IllegalStateException("Utility class");
    }

    public static int getLoggedAccountID() {
        AccountPrincipal accountPrincipal = (AccountPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountPrincipal.getId();
    }
}
