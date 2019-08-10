package bookstore.scraper.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @Test(expected = IllegalArgumentException.class)
    public void createAccountWhenNicknameAlreadyExists() {
        Account dummyAccount = createDummyAccount();

        when(accountRepository.existsByNickname(dummyAccount.getNickname())).thenReturn(true);

        Account actualAccount = accountService.createAccount(dummyAccount);

        assertEquals(dummyAccount, actualAccount);
    }

    @Test
    public void createAccountWhenNicknameNotExistsInDB() {
        Account dummyAccount = createDummyAccount();

        when(accountRepository.existsByNickname(dummyAccount.getNickname())).thenReturn(false);
        when(accountRepository.save(any(Account.class))).thenReturn(dummyAccount);

        Account actualAccount = accountService.createAccount(dummyAccount);

        assertEquals(dummyAccount, actualAccount);
    }

    private Account createDummyAccount() {
        return Account
                .builder()
                .nickname("Piotr")
                .password(BCrypt.hashpw("123", BCrypt.gensalt()))
                .build();
    }
}