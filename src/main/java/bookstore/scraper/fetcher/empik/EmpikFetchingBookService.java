package bookstore.scraper.fetcher.empik;

import bookstore.scraper.book.Book;
import bookstore.scraper.urlproperties.EmpikUrlProperties;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class EmpikFetchingBookService {

    private static final int FIRST_PART_PRICE = 0;
    private static final int SECOND_PART_PRICE = 1;

    private static final int BESTSELLERS_NUMBER_TO_FETCH = 5;
    private static final int CATEGORIZED_BOOKS_NUMBER_TO_FETCH = 15;
    private static final String DIV_PRODUCT_WRAPPER = "div.productWrapper";
    private static final String DATA_PRODUCT_ID = "data-product-id";

    private final EmpikUrlProperties empikUrlProperties;

    @Autowired
    public EmpikFetchingBookService(EmpikUrlProperties empikUrlProperties) {
        this.empikUrlProperties = empikUrlProperties;
    }

    public Book getMostPreciseEmpikBook(Document document) {
        String author = document.select("div.smartAuthorWrapper.ta-product-smartauthor").select("a").first().text();
        String price = convertEmpikPriceWithPossibleDiscountToActualPrice(document.select("div.price.ta-price-tile").first().text());
        String title = document.select(DIV_PRODUCT_WRAPPER).select("strong").first().text();
        String productID = document.select(DIV_PRODUCT_WRAPPER).select("a").first().attr(DATA_PRODUCT_ID);
        String bookUrl = createBookURL(title, productID);

        return Book.builder()
                .author(author)
                .price(price)
                .title(title)
                .productID(productID)
                .bookURL(bookUrl).build();
    }

    public List<Book> get5BestSellersEmpik(Document document) {
        List<Element> siteElements = document.select(DIV_PRODUCT_WRAPPER);
        List<Book> empikBestSellers = new ArrayList<>();

        IntStream.range(0, BESTSELLERS_NUMBER_TO_FETCH)
                .forEach(iteratedElement -> {

                    String author = siteElements.get(iteratedElement).select("div.smartAuthorWrapper.ta-product-smartauthor").select("a").first().text();
                    String price = convertEmpikPriceWithPossibleDiscountToActualPrice(siteElements.get(iteratedElement).select("div.price.ta-price-tile").first().text());
                    String title = siteElements.get(iteratedElement).select("strong").first().ownText();
                    String productID = siteElements.get(iteratedElement).select(DIV_PRODUCT_WRAPPER).select("a").first().attr(DATA_PRODUCT_ID);
                    String bookUrl = createBookURL(title, productID);

                    empikBestSellers.add(Book.builder()
                            .author(author)
                            .price(price)
                            .title(title)
                            .productID(productID)
                            .bookURL(bookUrl)
                            .build());
                });
        return empikBestSellers;
    }

    public List<Book> get15BooksFromCategory(Document document) {
        List<Book> books = new ArrayList<>();
        List<Element> siteElements = document.select("div.productBox__info");

        IntStream.range(0, CATEGORIZED_BOOKS_NUMBER_TO_FETCH)
                .forEach(iteratedElement -> {

                    String author = executeFetchingAuthorProcess(siteElements, iteratedElement);
                    String price = convertEmpikPriceWithPossibleDiscountToActualPrice(siteElements.get(iteratedElement).select("div.productBox__price").first().text());
                    String title = siteElements.get(iteratedElement).select("span").first().ownText();
                    String productID = siteElements.get(iteratedElement).select("a").first().attr(DATA_PRODUCT_ID);
                    String bookUrl = createBookURL(title, productID);

                    books.add(Book.builder()
                            .author(author)
                            .price(price)
                            .title(title)
                            .productID(productID)
                            .bookURL(bookUrl)
                            .build());
                });

        return books;
    }

    private String convertEmpikPriceWithPossibleDiscountToActualPrice(String price) {
        String[] splittedElements = price.split("\\s+");
        return splittedElements[FIRST_PART_PRICE] + splittedElements[SECOND_PART_PRICE];
    }

    private String createBookURL(String title, String productID) {
        return String.format(empikUrlProperties.getEmpik().getConcreteBook(), title, productID);
    }

    //method is required as on empik site, sometimes occurs null for author and we need to change code for fetching
    private static String executeFetchingAuthorProcess(List<Element> siteElements, int i) {
        String author;
        Element authorElements = siteElements.get(i).select("span > a").first();
        if (authorElements != null)
            author = authorElements.ownText();
        else
            author = siteElements.get(i).select("> span > span").first().text();
        return author;
    }
}