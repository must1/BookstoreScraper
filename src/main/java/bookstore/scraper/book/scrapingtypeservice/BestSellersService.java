package bookstore.scraper.book.scrapingtypeservice;

import bookstore.scraper.Bookstore;
import bookstore.scraper.book.Book;
import bookstore.scraper.fetcher.empik.EmpikFetchingBookService;
import bookstore.scraper.fetcher.merlin.MerlinFetchingBookService;
import bookstore.scraper.urlproperties.EmpikUrlProperties;
import bookstore.scraper.urlproperties.MerlinUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static bookstore.scraper.utilities.JSoupConnector.connect;

@Service
public class BestSellersService {

    private final EmpikUrlProperties empikUrlProperties;
    private final MerlinUrlProperties merlinUrlProperties;
    private final EmpikFetchingBookService empikBookService;
    private final MerlinFetchingBookService merlinBookService;

    @Autowired
    public BestSellersService(EmpikFetchingBookService empikBookService, MerlinFetchingBookService merlinBookService, EmpikUrlProperties empikUrlProperties, MerlinUrlProperties merlinUrlProperties) {
        this.empikBookService = empikBookService;
        this.merlinBookService = merlinBookService;
        this.empikUrlProperties = empikUrlProperties;
        this.merlinUrlProperties = merlinUrlProperties;
    }

    public Map<Bookstore, List<Book>> getBestSellers() {
        Map<Bookstore, List<Book>> bookstoreWithBestSellers = new EnumMap<>(Bookstore.class);

        bookstoreWithBestSellers.put(Bookstore.EMPIK, empikBookService
                .get5BestSellersEmpik(connect(empikUrlProperties.getEmpik().getBestSellers())));
        bookstoreWithBestSellers.put(Bookstore.MERLIN, merlinBookService
                .get5BestSellersMerlin(connect(merlinUrlProperties.getMerlin().getBestSellers())));

        return bookstoreWithBestSellers;
    }
}