package bookstore.scraper.book;

import bookstore.scraper.account.LoggedAccountService;
import bookstore.scraper.book.booksource.BookServiceSource;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.enums.CategoryType;
import bookstore.scraper.historysystem.ActionType;
import bookstore.scraper.historysystem.HistorySystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final List<BookServiceSource> sources;
    private final HistorySystemService historySystemService;
    private final LoggedAccountService loggedAccountService;

    @Autowired
    public BookService(List<BookServiceSource> sources, HistorySystemService historySystemService, LoggedAccountService loggedAccountService) {
        this.sources = sources;
        this.historySystemService = historySystemService;
        this.loggedAccountService = loggedAccountService;
    }

    public Map<Bookstore, List<Book>> getBooksByCategory(CategoryType category) {
        historySystemService.saveAccountHistory(
                loggedAccountService.getLoggedAccountID(), ActionType.CATEGORIZED_BOOK);

        return sources.stream()
                .collect(Collectors.toMap(BookServiceSource::getName,
                        source -> source.getBooksByCategory(category)));
    }

    public Map<Bookstore, Book> getMostPreciseBook(String title) {
        historySystemService.saveAccountHistory(
                loggedAccountService.getLoggedAccountID(), ActionType.MOST_PRECISE_BOOK);

        return sources.stream()
                .collect(Collectors.toMap(BookServiceSource::getName,
                        source -> source.getMostPreciseBook(title)));
    }

    public Map<Bookstore, List<Book>> getBestsellers() {
        historySystemService.saveAccountHistory(
                loggedAccountService.getLoggedAccountID(), ActionType.BEST_SELLERS);

        return sources.stream()
                .collect(Collectors.toMap(BookServiceSource::getName,
                        BookServiceSource::getBestSellers));
    }
}