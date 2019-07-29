package bookstore.scraper.book.scrapingtypeservice;

import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.book.Book;
import bookstore.scraper.fetcher.empik.EmpikFetchingBookService;
import bookstore.scraper.fetcher.merlin.MerlinFetchingBookService;
import bookstore.scraper.urlproperties.EmpikUrlProperties;
import bookstore.scraper.urlproperties.MerlinUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

import static bookstore.scraper.utilities.JSoupConnector.connect;

@Service
public class MostPreciseBookService {

    private final EmpikFetchingBookService empikBookService;
    private final MerlinFetchingBookService merlinBookService;

    private final EmpikUrlProperties empikUrlProperties;
    private final MerlinUrlProperties merlinUrlProperties;

    @Autowired
    public MostPreciseBookService(EmpikFetchingBookService empikBookService, MerlinFetchingBookService merlinBookService, EmpikUrlProperties empikUrlProperties, MerlinUrlProperties merlinUrlProperties) {
        this.empikBookService = empikBookService;
        this.merlinBookService = merlinBookService;
        this.empikUrlProperties = empikUrlProperties;
        this.merlinUrlProperties = merlinUrlProperties;
    }

    public Map<Bookstore, Book> getBookByTitle(String title) {
        Map<Bookstore, Book> bookstoreWithMostPreciseBook = new EnumMap<>(Bookstore.class);

        bookstoreWithMostPreciseBook.put(Bookstore.MERLIN,
                merlinBookService.getMostPreciseMerlinBook(
                        connect(concatUrlWithTitle(merlinUrlProperties.getMerlin().getMostPreciseBook(), title))));

        bookstoreWithMostPreciseBook.put(Bookstore.EMPIK,
                empikBookService.getMostPreciseEmpikBook(
                        connect(concatUrlWithTitle(empikUrlProperties.getEmpik().getMostPreciseBook(), title))));

        return bookstoreWithMostPreciseBook;
    }

    private String concatUrlWithTitle(String url, String title) {
        return String.format(url, title);
    }
}