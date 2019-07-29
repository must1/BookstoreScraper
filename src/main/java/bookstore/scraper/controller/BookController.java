package bookstore.scraper.controller;

import bookstore.scraper.Bookstore;
import bookstore.scraper.book.Book;
import bookstore.scraper.book.scrapingtypeservice.BestSellersService;
import bookstore.scraper.book.scrapingtypeservice.CategorizedBookService;
import bookstore.scraper.book.scrapingtypeservice.MostPreciseBookService;
import bookstore.scraper.rankingsystem.CategorizedBooksRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    private final MostPreciseBookService mostPreciseBookService;
    private final CategorizedBookService categorizedBookService;
    private final BestSellersService bestSellersService;
    private final CategorizedBooksRanking categorizedBooksRanking;

    @Autowired
    public BookController(MostPreciseBookService bookOperationsService, CategorizedBookService categorizedBookService, BestSellersService bestSellersService, CategorizedBooksRanking categorizedBooksRanking) {
        this.mostPreciseBookService = bookOperationsService;
        this.categorizedBookService = categorizedBookService;
        this.bestSellersService = bestSellersService;
        this.categorizedBooksRanking = categorizedBooksRanking;
    }

    @GetMapping("/bestsellers")
    public Map<Bookstore, List<Book>> getBestSellers() {
        return bestSellersService.getBestSellers();
    }

    @GetMapping("/book/{title}")
    public Map<Bookstore, Book> getBookByTitle(@PathVariable String title) {
        return mostPreciseBookService.getBookByTitle(title);
    }

    @GetMapping("/romances")
    public Map<Bookstore, List<Book>> get15RomanticBooks() {
        return categorizedBookService.get15BooksFromRomanceCategory();
    }

    @GetMapping("/biographies")
    public Map<Bookstore, List<Book>> get15BiographiesBooks() {
        return categorizedBookService.get15BooksFromBiographiesCategory();
    }

    @GetMapping("/guides")
    public Map<Bookstore, List<Book>> get15GuidesBooks() {
        return categorizedBookService.get15BooksFromGuidesCategory();
    }

    @GetMapping("/fantasy")
    public Map<Bookstore, List<Book>> get15FantasyBooks() {
        return categorizedBookService.get15BooksFromFantasyCategory();
    }

    @GetMapping("/crime")
    public Map<Bookstore, List<Book>> get15CrimeBooks() {
        return categorizedBookService.get15BooksFromCrimeCategory();
    }

    @GetMapping("/{category}/ranking")
    public Map<String, Integer> getRankingForCategory(@PathVariable String category) {
        return categorizedBooksRanking.getRankingForCategory(category);
    }
}
