package bookstore.scraper.book.scrapingtypeservice;

import bookstore.scraper.Bookstore;
import bookstore.scraper.book.Book;
import bookstore.scraper.fetcher.empik.EmpikFetchingBookService;
import bookstore.scraper.fetcher.merlin.MerlinFetchingBookService;
import bookstore.scraper.urlproperties.EmpikUrlProperties;
import bookstore.scraper.urlproperties.MerlinUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static bookstore.scraper.JSoupConnector.connect;

@Service
public class MostPreciseBookService {

    private final EmpikFetchingBookService empikBookService;
    private final MerlinFetchingBookService merlinBookService;


    private final EmpikUrlProperties empikUrlProperties;
    private final MerlinUrlProperties merlinUrlProperties;

    @Value("${external.library.url.empik.most.precise.book}")
    private String mostPreciseBookEmpikURL;

    @Value("${external.library.url.merlin.most.precise.book}")
    private String mostPreciseBookMerlinURL;

    @Autowired
    public MostPreciseBookService(EmpikFetchingBookService empikBookService, MerlinFetchingBookService merlinBookService, EmpikUrlProperties empikUrlProperties, MerlinUrlProperties merlinUrlProperties) {
        this.empikBookService = empikBookService;
        this.merlinBookService = merlinBookService;
        this.empikUrlProperties = empikUrlProperties;
        this.merlinUrlProperties = merlinUrlProperties;
    }

    public Map<Bookstore, Book> getBookByTitle(String title) {
        Map<Bookstore, Book> bookstoreWithMostPreciseBook = new HashMap<>();

        bookstoreWithMostPreciseBook.put(Bookstore.MERLIN,
                merlinBookService.getMostPreciseMerlinBook(
                        connect(concatURLWithTitle(mostPreciseBookMerlinURL, title))));

        bookstoreWithMostPreciseBook.put(Bookstore.EMPIK,
                empikBookService.getMostPreciseEmpikBook(
                        connect(concatURLWithTitle(mostPreciseBookEmpikURL, title))));

        return bookstoreWithMostPreciseBook;
    }

    private String concatURLWithTitle(String URL, String title) {
        return String.format(URL, title);
    }
}