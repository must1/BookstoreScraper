package bookstore.scraper.historysystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorySystemService {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistorySystemService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    List<AccountHistory> getHistoryOfUser(int accountID) {
        if (!historyRepository.existsAccountHistoriesByAccountID(accountID)) {
            throw new IllegalArgumentException("Account with that ID does not exist");
        }

        return historyRepository.findAccountHistoriesByAccountID(accountID);
    }

    public void saveAccountHistory(int accountID, String action) {
        historyRepository.save(AccountHistory.builder().accountID(accountID).actionName(action).build());
    }
}
