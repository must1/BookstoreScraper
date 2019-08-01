package bookstore.scraper.fetcher.merlin;

import bookstore.scraper.book.Book;
import bookstore.scraper.urlproperties.MerlinUrlProperties;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MerlinFetchingBookService {

    private static final int BESTSELLERS_NUMBER_TO_FETCH = 6;
    private static final int CATEGORIZED_BOOKS_NUMBER_TO_FETCH = 16;

    private final MerlinUrlProperties merlinUrlProperties;

    @Autowired
    public MerlinFetchingBookService(MerlinUrlProperties merlinUrlProperties) {
        this.merlinUrlProperties = merlinUrlProperties;
    }

    public Book getMostPreciseMerlinBook(Document document) {
        String title = document.select("div.b-products-list__title-holder").select("a").first().text();
        String price = document.select("div.b-products-list__price-holder > a").first().text();
        String author = document.select("div.b-products-list__manufacturer-holder").select("a").first().text();
        String productID = document.select("div.grid__col.grid__col--20-80-80.b-products-wrap > ul > li:nth-child(1)").first().attr("data-ppc-id");
        String bookUrl = createBookUrl(title, productID);

        return new Book.BookBuilder()
                .withAuthor(author)
                .withPrice(price)
                .withTitle(title)
                .withProductID(productID)
                .withBookUrl(bookUrl)
                .build();
    }

    public List<Book> get5BestSellersMerlin(Document document) {
        return IntStream.range(1, BESTSELLERS_NUMBER_TO_FETCH)
                .mapToObj(iterator -> getBestSellerOrCategorizedBook(document, iterator))
                .collect(Collectors.toList());
    }

    public List<Book> get15BooksFromCategory(Document document) {
        return IntStream.range(1, CATEGORIZED_BOOKS_NUMBER_TO_FETCH)
                .mapToObj(iterator -> getBestSellerOrCategorizedBook(document, iterator))
                .collect(Collectors.toList());
    }

    //Todo
    //need to think about better name for the method as it can be used for fetching bestseller and categorized book
    private Book getBestSellerOrCategorizedBook(Document document, int iteratedBook) {
        Elements siteElements = document.select("div.grid__col.grid__col--20-80-80.b-products-wrap > ul > li:nth-child(" + iteratedBook + ")");
        String author = siteElements.select(" > div > div.b-products-list__desc-wrap > div > div.b-products-list__main-content > div.b-products-list__desc-prime > div.b-products-list__manufacturer-holder").select("a").first().text();
        String title = siteElements.select(" > div > div.b-products-list__desc-wrap > div > div.b-products-list__main-content > div.b-products-list__desc-prime > div.b-products-list__title-holder > a").first().text();
        String price = siteElements.select(" div.b-products-list__price-holder > a").first().text();
        String productID = siteElements.first().attr("data-ppc-id");
        String bookUrl = createBookUrl(title, productID);

        return new Book.BookBuilder()
                .withAuthor(author)
                .withPrice(price)
                .withTitle(title)
                .withProductID(productID)
                .withBookUrl(bookUrl)
                .build();
    }

    private String createBookUrl(String title, String productID) {
        return String.format(merlinUrlProperties.getMerlin().getConcreteBook(), title, productID);
    }
}