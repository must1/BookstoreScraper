package bookstore.scraper.booksource;

import bookstore.scraper.book.Book;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.enums.CategoryType;

import java.util.List;

public interface BookServiceSource {
    Bookstore getName();

    List<Book> getBooksByCategory(CategoryType category);

    Book getMostPreciseBook(String title);

    List<Book> getBestSellers();
}