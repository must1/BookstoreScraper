package bookstore.scraper.book.rankingsystem;

import bookstore.scraper.account.LoggedAccountService;
import bookstore.scraper.book.Book;
import bookstore.scraper.book.BookService;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.enums.CategoryType;
import bookstore.scraper.historysystem.ActionDescription;
import bookstore.scraper.historysystem.HistorySystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Slf4j
@Component
public class CategorizedBooksRankingService {

    private final BookService bookService;
    private final HistorySystemService historySystemService;
    private final LoggedAccountService loggedAccountService;

    @Autowired
    public CategorizedBooksRankingService(BookService bookService, HistorySystemService historySystemService, LoggedAccountService loggedAccountService) {
        this.bookService = bookService;
        this.historySystemService = historySystemService;
        this.loggedAccountService = loggedAccountService;
    }

    public Map<String, Integer> getRankingForCategory(CategoryType category) {
        Map<Bookstore, List<Book>> bookstoreWith15CategorizedBooks = bookService.getBooksByCategory(category);

        List<Book> merlinBooks = bookstoreWith15CategorizedBooks.get(Bookstore.MERLIN);
        List<Book> empikBooks = bookstoreWith15CategorizedBooks.get(Bookstore.EMPIK);

        Map<String, List<String>> purifiedTitleWithOriginalTitles = getPurifiedTitleWithAccordingOriginalTitles(merlinBooks, empikBooks);
        Map<String, Integer> bookTitleWithOccurrencesNumber = getTitleWithOccurrences(purifiedTitleWithOriginalTitles);

        historySystemService.saveAccountHistory
                (loggedAccountService.getLoggedAccountID(), ActionDescription.CATEGORIZED_BOOKS_RANKING.toString());

        return getSortedLinkedHashMapByValue(bookTitleWithOccurrencesNumber);
    }

    private Map<String, List<String>> getPurifiedTitleWithAccordingOriginalTitles(List<Book> list1, List<Book> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                .collect(
                        groupingBy(Book::getPurifiedTitle, mapping(Book::getTitle, toList())));
    }

    private Map<String, Integer> getTitleWithOccurrences(Map<String, List<String>> map) {
        return map.values().stream().collect(toMap(list -> list.get(0), List::size));
    }

    private Map<String, Integer> getSortedLinkedHashMapByValue(Map<String, Integer> mapToSort) {
        return mapToSort.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
    }
}