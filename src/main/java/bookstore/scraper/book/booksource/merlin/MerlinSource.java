package bookstore.scraper.book.booksource.merlin;

import bookstore.scraper.book.Book;
import bookstore.scraper.book.booksource.BookServiceSource;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.enums.CategoryType;
import bookstore.scraper.urlproperties.MerlinUrlProperties;
import bookstore.scraper.utilities.JSoupConnector;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MerlinSource implements BookServiceSource {

    private static final int CATEGORIZED_BOOKS_NUMBER_TO_FETCH = 16;
    private static final int BESTSELLERS_NUMBER_TO_FETCH = 6;

    private final JSoupConnector jSoupConnector;
    private final MerlinUrlProperties merlinUrlProperties;

    @Autowired
    public MerlinSource(JSoupConnector jSoupConnector, MerlinUrlProperties merlinUrlProperties) {
        this.jSoupConnector = jSoupConnector;
        this.merlinUrlProperties = merlinUrlProperties;
    }

    @Override
    public Bookstore getName() {
        return Bookstore.MERLIN;
    }

    @Override
    public List<Book> getBooksByCategory(CategoryType categoryType) {
        Document document = jSoupConnector.connect(merlinUrlProperties.getCategory(categoryType));

        return IntStream.range(1, CATEGORIZED_BOOKS_NUMBER_TO_FETCH)
                .mapToObj(iterator -> getBestSellerOrCategorizedBook(document, iterator))
                .collect(Collectors.toList());
    }

    @Override
    public Book getMostPreciseBook(String givenTitle) {
        String concatedUrl = concatUrlWithTitle(merlinUrlProperties.getCategory(CategoryType.MOST_PRECISE_BOOK), givenTitle);

        Document document = jSoupConnector.connect(concatedUrl);

        String title = document.select("div.b-products-list__title-holder").select("a").first().text();
        String price = document.select("div.b-products-list__price-holder > a").first().text();
        String author = document.select("div.b-products-list__manufacturer-holder").select("a").first().text();
        String productID = document.select("div.grid__col.grid__col--20-80-80.b-products-wrap > ul > li:nth-child(1)").first().attr("data-ppc-id");
        String bookUrl = createBookUrl(title, productID);

        return Book.builder()
                .author(author)
                .price(price)
                .title(title)
                .productID(productID)
                .bookURL(bookUrl)
                .build();
    }

    @Override
    public List<Book> getBestSellers() {
        Document document = jSoupConnector.connect(merlinUrlProperties.getCategory(CategoryType.BESTSELLER));

        return IntStream.range(1, BESTSELLERS_NUMBER_TO_FETCH)
                .mapToObj(iterator -> getBestSellerOrCategorizedBook(document, iterator))
                .collect(Collectors.toList());
    }

    private Book getBestSellerOrCategorizedBook(Document document, int iteratedBook) {
        Elements siteElements = document.select("div.grid__col.grid__col--20-80-80.b-products-wrap > ul > li:nth-child(" + iteratedBook + ")");
        String author = siteElements.select(" > div > div.b-products-list__desc-wrap > div > div.b-products-list__main-content > div.b-products-list__desc-prime > div.b-products-list__manufacturer-holder").select("a").first().text();
        String title = siteElements.select(" > div > div.b-products-list__desc-wrap > div > div.b-products-list__main-content > div.b-products-list__desc-prime > div.b-products-list__title-holder > a").first().text();
        String price = siteElements.select(" div.b-products-list__price-holder > a").first().text();
        String productID = siteElements.first().attr("data-ppc-id");
        String bookUrl = createBookUrl(title, productID);

        return Book.builder()
                .author(author)
                .price(price)
                .title(title)
                .productID(productID)
                .bookURL(bookUrl)
                .build();
    }

    private String createBookUrl(String title, String productID) {
        return String.format(merlinUrlProperties.getConcreteBook(), title, productID);
    }

    private Map<CategoryType, String> createCategoryToMerlinURLMap() {
        Map<CategoryType, String> map = new EnumMap<>(CategoryType.class);

        map.put(CategoryType.CRIME, merlinUrlProperties.getCrime());
        map.put(CategoryType.BESTSELLER, merlinUrlProperties.getBestSellers());
        map.put(CategoryType.BIOGRAPHY, merlinUrlProperties.getBiographies());
        map.put(CategoryType.FANTASY, merlinUrlProperties.getFantasy());
        map.put(CategoryType.GUIDES, merlinUrlProperties.getGuides());
        map.put(CategoryType.MOST_PRECISE_BOOK, merlinUrlProperties.getMostPreciseBook());
        map.put(CategoryType.ROMANCES, merlinUrlProperties.getRomances());

        return map;
    }

    private String concatUrlWithTitle(String url, String title) {
        return String.format(url, title);
    }
}
