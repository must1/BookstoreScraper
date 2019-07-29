package bookstore.scraper.rankingsystem;

import bookstore.scraper.Bookstore;
import bookstore.scraper.book.Book;
import bookstore.scraper.book.scrapingtypeservice.CategorizedBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

import java.util.stream.Collectors;

@Slf4j
@Component
public class CategorizedBooksRanking {

    private final CategorizedBookService categorizedBookService;

    @Autowired
    public CategorizedBooksRanking(CategorizedBookService categorizedBookService) {
        this.categorizedBookService = categorizedBookService;
    }

    public Map<String, Integer> getRankingForCategory(String category) {
        Map<String, Integer> bookTitleWithOccurrencesNumber = new LinkedHashMap<>();
        int occurrencesOfIteratedBook;
        String iteratedMerlinTitle;

        Map<Bookstore, List<Book>> bookstoreWith15CategorizedBooks = chooseGetterImplementationByCategory(category);

        List<Book> merlinBooks = bookstoreWith15CategorizedBooks.get(Bookstore.MERLIN);
        List<Book> empikBooks = bookstoreWith15CategorizedBooks.get(Bookstore.EMPIK);

        List<String> purifiedMerlinBookTitles = purifyListOfTitles(merlinBooks);
        List<String> purifiedEmpikBookTitles = purifyListOfTitles(empikBooks);

        for (int i = 0; i < purifiedMerlinBookTitles.size(); i++) {
            occurrencesOfIteratedBook = 1;
            iteratedMerlinTitle = purifiedMerlinBookTitles.get(i);
            for (String iteratedEmpikTitle : purifiedEmpikBookTitles) {

                if (iteratedMerlinTitle.equals(iteratedEmpikTitle))
                    occurrencesOfIteratedBook++;
            }
            bookTitleWithOccurrencesNumber.put(merlinBooks.get(i).getTitle(), occurrencesOfIteratedBook);
        }
        return getSortedLinkedHashMappedByValue(bookTitleWithOccurrencesNumber);
    }

    private List<String> purifyListOfTitles(List<Book> listToPurify) {
        return listToPurify
                .stream()
                .map(Book::getTitle)
                .map(title -> title.replaceAll("[^A-Za-z]+", "")
                        .toLowerCase())
                .collect(Collectors.toList());
    }

    private Map<String, Integer> getSortedLinkedHashMappedByValue(Map<String, Integer> mapToSort) {
        return mapToSort.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
    }

    private Map<Bookstore, List<Book>> chooseGetterImplementationByCategory(String category) {
        if (category.equals("crimes"))
            return categorizedBookService.get15BooksFromCrimeCategory();
        if (category.equals("romances"))
            return categorizedBookService.get15BooksFromRomanceCategory();
        if (category.equals("fantasies"))
            return categorizedBookService.get15BooksFromFantasyCategory();
        if (category.equals("guides"))
            return categorizedBookService.get15BooksFromGuidesCategory();
        if (category.equals("biographies"))
            return categorizedBookService.get15BooksFromBiographiesCategory();
        else {
            log.error(category + " is invalid category");
            throw new IllegalArgumentException();
        }
    }
}