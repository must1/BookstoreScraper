package bookstore.scraper.book;

import bookstore.scraper.booksource.BookServiceSource;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.enums.CategoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final List<BookServiceSource> sources;

    @Autowired
    public BookService(List<BookServiceSource> sources) {
        this.sources = sources;
    }

    public Map<Bookstore, List<Book>> getBooksByCategory(CategoryType category) {
        return sources.stream()
                .collect(Collectors.toMap(BookServiceSource::getName,
                        source -> source.getBooksByCategory(category)));
    }

    public Map<Bookstore, Book> getMostPreciseBOok(String title) {
        return sources.stream()
                .collect(Collectors.toMap(BookServiceSource::getName,
                        source -> source.getMostPreciseBook(title)));
    }

    public Map<Bookstore, List<Book>> getBestsellers() {
        return sources.stream()
                .collect(Collectors.toMap(BookServiceSource::getName,
                        BookServiceSource::getBestSellers));
    }
}