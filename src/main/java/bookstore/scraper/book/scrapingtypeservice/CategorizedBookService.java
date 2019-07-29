package bookstore.scraper.book.scrapingtypeservice;

import bookstore.scraper.Bookstore;
import bookstore.scraper.book.Book;
import bookstore.scraper.fetcher.empik.EmpikFetchingBookService;
import bookstore.scraper.fetcher.merlin.MerlinFetchingBookService;
import bookstore.scraper.urlproperties.EmpikUrlProperties;
import bookstore.scraper.urlproperties.MerlinUrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bookstore.scraper.JSoupConnector.connect;

@Service
@Slf4j
public class CategorizedBookService {

    private final EmpikFetchingBookService empikBookService;
    private final MerlinFetchingBookService merlinFetchingBookService;
    private final EmpikUrlProperties empikUrlProperties;

    private final MerlinUrlProperties merlinUrlProperties;

    @Autowired
    public CategorizedBookService(EmpikFetchingBookService empikBookService, MerlinFetchingBookService merlinFetchingBookService, EmpikUrlProperties empikUrlProperties, MerlinUrlProperties merlinUrlProperties) {
        this.empikBookService = empikBookService;
        this.merlinFetchingBookService = merlinFetchingBookService;
        this.empikUrlProperties = empikUrlProperties;
        this.merlinUrlProperties = merlinUrlProperties;
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
        Map<Bookstore, List<Book>> bookstoreWith15CategorizedBooks = new HashMap<>();

        bookstoreWith15CategorizedBooks.put(Bookstore.EMPIK, empikBookService
                .get15BooksFromCategory(connect(bookStoreEmpikURL)));
        bookstoreWith15CategorizedBooks.put(Bookstore.MERLIN, merlinFetchingBookService
                .get15BooksFromCategory(connect(bookStoreMerlinURL)));

        return bookstoreWith15CategorizedBooks;
    }
}