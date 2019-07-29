package bookstore.scraper.rankingsystem;

import bookstore.scraper.book.Book;
import bookstore.scraper.book.scrapingtypeservice.CategorizedBookService;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.enums.CategoryType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Slf4j
@Component
public class CategorizedBooksRankingService {

    private final CategorizedBookService categorizedBookService;

    @Autowired
    public CategorizedBooksRankingService(CategorizedBookService categorizedBookService) {
        this.categorizedBookService = categorizedBookService;
    }

    public Map<String, Integer> getRankingForCategory(CategoryType category) {
        Map<Bookstore, List<Book>> bookstoreWith15CategorizedBooks = chooseGetterImplementationByCategory(category);

        List<Book> merlinBooks = bookstoreWith15CategorizedBooks.get(Bookstore.MERLIN);
        List<Book> empikBooks = bookstoreWith15CategorizedBooks.get(Bookstore.EMPIK);

        Map<String, List<String>> purifiedTitleWithOriginalTitles = getPurifiedTitleWithAccordingOriginalTitles(merlinBooks, empikBooks);
        Map<String, Integer> bookTitleWithOccurrencesNumber = getTitleWithOccurrences(purifiedTitleWithOriginalTitles);

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

    private Map<Bookstore, List<Book>> chooseGetterImplementationByCategory(CategoryType categoryType) {
        Map<Bookstore, List<Book>> map = new EnumMap<>(Bookstore.class);

        if (categoryType.equals(CategoryType.CRIME))
            map = categorizedBookService.get15BooksFromCrimeCategory();
        if (categoryType.equals(CategoryType.ROMANCES))
            map = categorizedBookService.get15BooksFromRomanceCategory();
        if (categoryType.equals(CategoryType.FANTASY))
            map = categorizedBookService.get15BooksFromFantasyCategory();
        if (categoryType.equals(CategoryType.GUIDES))
            map = categorizedBookService.get15BooksFromGuidesCategory();
        if (categoryType.equals(CategoryType.BIOGRAPHY))
            map = categorizedBookService.get15BooksFromBiographiesCategory();

        return map;
    }
}