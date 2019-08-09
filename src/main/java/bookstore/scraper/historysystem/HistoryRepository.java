package bookstore.scraper.historysystem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends CrudRepository<AccountHistory, Integer> {

    boolean existsAccountHistoriesByAccountID(int accountID);

    List<AccountHistory> findAccountHistoriesByAccountID(int accountID);
}
