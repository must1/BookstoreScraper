package bookstore.scraper.fetcher.merlin;

import bookstore.scraper.book.Book;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MerlinFetchingBookService {

    private static final int BESTSELLERS_NUMBER_TO_FETCH = 6;
    private static final int CATEGORIZED_BOOKS_NUMBER_TO_FETCH = 15;

    @Value("${external.library.url.merlin.concrete.book}")
    private String concreteBookMerlinURL;

    public Book getMostPreciseMerlinBook(Document document) {
        String title = document.select("div.b-products-list__title-holder").select("a").first().text();
        String price = document.select("div.b-products-list__price-holder > a").first().text();
        String author = document.select("div.b-products-list__manufacturer-holder").select("a").first().text();
        String productID = document.select("div.grid__col.grid__col--20-80-80.b-products-wrap > ul > li:nth-child(1)").first().attr("data-ppc-id");
        String bookURL = createBookURL(title, productID);

        return new Book.BookBuilder()
                .withAuthor(author)
                .withPrice(price)
                .withTitle(title)
                .withProductID(productID)
                .withBookURL(bookURL)
                .build();
    }

    public List<Book> get5BestSellersMerlin(Document document) {
        List<Book> merlinBestSellers = new ArrayList<>();

        for (int iterator = 1; iterator < BESTSELLERS_NUMBER_TO_FETCH; iterator++) {
            merlinBestSellers.add(getBestSellerOrCategorizedBook(document, iterator));
        }
        return merlinBestSellers;
    }

    public List<Book> get15BooksFromCategory(Document document) {
        List<Book> books = new ArrayList<>();

        for (int iterator = 1; iterator < CATEGORIZED_BOOKS_NUMBER_TO_FETCH; iterator++) {
            books.add(getBestSellerOrCategorizedBook(document, iterator));
        }
        return books;
    }

    //Todo
    //need to think about better name for the method as it can be used for fetching bestseller and categorized book
    private Book getBestSellerOrCategorizedBook(Document document, int iteratedBook) {
        Elements siteElements = document.select("div.grid__col.grid__col--20-80-80.b-products-wrap > ul > li:nth-child(" + iteratedBook + ")");
        String author = siteElements.select(" > div > div.b-products-list__desc-wrap > div > div.b-products-list__main-content > div.b-products-list__desc-prime > div.b-products-list__manufacturer-holder").select("a").first().text();
        String title = siteElements.select(" > div > div.b-products-list__desc-wrap > div > div.b-products-list__main-content > div.b-products-list__desc-prime > div.b-products-list__title-holder > a").first().text();
        String price = siteElements.select(" div.b-products-list__price-holder > a").first().text();
        String productID = siteElements.first().attr("data-ppc-id");
        String bookURL = createBookURL(title, productID);

        return new Book.BookBuilder()
                .withAuthor(author)
                .withPrice(price)
                .withTitle(title)
                .withProductID(productID)
                .withBookURL(bookURL)
                .build();
    }

    private String createBookURL(String title, String productID) {
        return String.format(concreteBookMerlinURL, title, productID);
    }
}