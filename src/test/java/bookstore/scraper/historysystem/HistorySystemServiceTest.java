package bookstore.scraper.historysystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HistorySystemServiceTest {

    @Mock
    HistoryRepository historyRepository;

    @InjectMocks
    HistorySystemService historySystemService;

    @Test(expected = IllegalArgumentException.class)
    public void getHistoryOfUserWhenItDoesNotHaveAny() {
        AccountHistory dummyAccountHistory = createDummyAccountHistory();

        when(historyRepository.existsAccountHistoriesByAccountID(anyInt())).thenReturn(false);

        historySystemService.getHistoryOfUser(dummyAccountHistory.getAccountID());
    }

    @Test
    public void getHistoryOfUserWhenItsExists() {
        AccountHistory dummyAccountHistory = createDummyAccountHistory();

        when(historyRepository.existsAccountHistoriesByAccountID(anyInt())).thenReturn(true);
        when(historyRepository.findAccountHistoriesByAccountID(anyInt())).thenReturn(Collections.singletonList(dummyAccountHistory));

        List<AccountHistory> actualAccountHistory = historySystemService.getHistoryOfUser(dummyAccountHistory.getAccountID());

        assertThat(actualAccountHistory, hasSize(1));
    }

    @Test
    public void saveAccountHistory() {
        AccountHistory dummyAccountHistory = createDummyAccountHistory();
        historySystemService.saveAccountHistory(dummyAccountHistory.getAccountID(), dummyAccountHistory.getActionName());

        verify(historyRepository).save(dummyAccountHistory);
        verify(historyRepository, times(1)).save(createDummyAccountHistory());
    }

    private AccountHistory createDummyAccountHistory() {
        return AccountHistory
                .builder()
                .accountID(0)
                .actionName(ActionType.BEST_SELLERS.toString())
                .build();
    }

}