package bookstore.scraper.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    boolean existsByNickname(String nickname);

    Optional<Account> findByNickname(String nickname);
}