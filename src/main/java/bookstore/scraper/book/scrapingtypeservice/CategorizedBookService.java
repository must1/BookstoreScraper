package bookstore.scraper.book.scrapingtypeservice;

import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.book.Book;
import bookstore.scraper.fetcher.empik.EmpikFetchingBookService;
import bookstore.scraper.fetcher.merlin.MerlinFetchingBookService;
import bookstore.scraper.urlproperties.EmpikUrlProperties;
import bookstore.scraper.urlproperties.MerlinUrlProperties;
import bookstore.scraper.utilities.JSoupConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CategorizedBookService {

    private final EmpikFetchingBookService empikFetchingBookService;
    private final MerlinFetchingBookService merlinFetchingBookService;
    private final EmpikUrlProperties empikUrlProperties;
    private final MerlinUrlProperties merlinUrlProperties;
    private final JSoupConnector jSoupConnector;

    @Autowired
    public CategorizedBookService(EmpikFetchingBookService empikFetchingBookService, MerlinFetchingBookService merlinFetchingBookService, EmpikUrlProperties empikUrlProperties, MerlinUrlProperties merlinUrlProperties, JSoupConnector jSoupConnector) {
        this.empikFetchingBookService = empikFetchingBookService;
        this.merlinFetchingBookService = merlinFetchingBookService;
        this.empikUrlProperties = empikUrlProperties;
        this.merlinUrlProperties = merlinUrlProperties;
        this.jSoupConnector = jSoupConnector;
    }

    public Map<Bookstore, List<Book>> get15BooksFromRomanceCategory() {
        return get15BooksFrom(empikUrlProperties.getEmpik().getRomances(), merlinUrlProperties.getMerlin().getRomances());
    }

    public Map<Bookstore, List<Book>> get15BooksFromFantasyCategory() {
        return get15BooksFrom(empikUrlProperties.getEmpik().getFantasy(), merlinUrlProperties.getMerlin().getFantasy());
    }

    public Map<Bookstore, List<Book>> get15BooksFromCrimeCategory() {
        return get15BooksFrom(empikUrlProperties.getEmpik().getCrime(), merlinUrlProperties.getMerlin().getCrime());
    }

    public Map<Bookstore, List<Book>> get15BooksFromGuidesCategory() {
        return get15BooksFrom(empikUrlProperties.getEmpik().getGuides(), merlinUrlProperties.getMerlin().getGuides());
    }

    public Map<Bookstore, List<Book>> get15BooksFromBiographiesCategory() {
        return get15BooksFrom(empikUrlProperties.getEmpik().getBiographies(), merlinUrlProperties.getMerlin().getBiographies());
    }

    private Map<Bookstore, List<Book>> get15BooksFrom(String bookStoreEmpikURL, String bookStoreMerlinURL) {
        Map<Bookstore, List<Book>> bookstoreWith15CategorizedBooks = new EnumMap<>(Bookstore.class);

        bookstoreWith15CategorizedBooks.put(Bookstore.EMPIK, empikFetchingBookService
                .get15BooksFromCategory(jSoupConnector.connect(bookStoreEmpikURL)));
        bookstoreWith15CategorizedBooks.put(Bookstore.MERLIN, merlinFetchingBookService
                .get15BooksFromCategory(jSoupConnector.connect(bookStoreMerlinURL)));

        return bookstoreWith15CategorizedBooks;
    }
}