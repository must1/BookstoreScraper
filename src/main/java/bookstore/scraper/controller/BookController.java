package bookstore.scraper.controller;

import bookstore.scraper.book.Book;
import bookstore.scraper.book.BookService;
import bookstore.scraper.book.rankingsystem.CategorizedBooksRankingService;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.enums.CategoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    private final CategorizedBooksRankingService categorizedBooksRankingService;
    private final BookService bookService;

    @Autowired
    public BookController(CategorizedBooksRankingService categorizedBooksRankingService, BookService bookService) {
        this.categorizedBooksRankingService = categorizedBooksRankingService;
        this.bookService = bookService;
    }

    @GetMapping("/bestsellers")
    public Map<Bookstore, List<Book>> getBestSellers() {
        return bookService.getBestsellers();
    }

    @GetMapping("/book/{title}")
    public Map<Bookstore, Book> getBookByTitle(@PathVariable String title) {
        return bookService.getMostPreciseBOok(title);
    }

    @GetMapping("/{categoryType}")
    public Map<Bookstore, List<Book>> get15RomanticBooks(@PathVariable CategoryType categoryType) {
        return bookService.getBooksByCategory(categoryType);
    }

    @GetMapping("/ranking/{categoryType}")
    public Map<String, Integer> getRankingForCategory(@PathVariable CategoryType categoryType) {
        return categorizedBooksRankingService.getRankingForCategory(CategoryType.forName(categoryType));
    }
}