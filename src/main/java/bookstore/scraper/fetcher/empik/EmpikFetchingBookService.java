package bookstore.scraper.fetcher.empik;

import bookstore.scraper.book.Book;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpikFetchingBookService {

    private static final int FIRST_PART = 0;
    private static final int SECOND_PART = 1;
    @Value("${external.library.url.empik.concrete.book}")
    private String concreteBookEmpikURL;

    @Value("${external.library.url.empik.romances}")
    private String romancesCategoryEmpikURL;

    @Value("${external.library.url.empik.biographies}")
    private String biographiesCategoryEmpikURL;

    @Value("${external.library.url.empik.crime}")
    private String crimeCategoryEmpikURL;

    @Value("${external.library.url.empik.guides}")
    private String guidesCategoryEmpikURL;

    @Value("${external.library.url.empik.fantasy}")
    private String fantasyCategoryEmpikURL;

    private static final int BESTSELLERS_NUMBER_TO_FETCH = 5;
    private static final int CATEGORIZED_BOOKS_NUMBER_TO_FETCH = 15;

    public Book getMostPreciseEmpikBook(Document document) {
        String author = document.select("div.smartAuthorWrapper.ta-product-smartauthor").select("a").first().text();
        String price = convertEmpikPriceWithPossibleDiscountToActualPrice(document.select("div.price.ta-price-tile").first().text());
        String title = document.select("div.productWrapper").select("strong").first().text();
        String productID = document.select("div.productWrapper").select("a").first().attr("data-product-id");
        String bookURL = createBookURL(title, productID);

        return new Book.BookBuilder()
                .withAuthor(author)
                .withPrice(price)
                .withTitle(title)
                .withProductID(productID)
                .withBookURL(bookURL)
                .build();
    }

    public List<Book> get5BestSellersEmpik(Document document) {
        List<Element> siteElements = document.select("div.productWrapper");
        List<Book> empikBestSellers = new ArrayList<>();

        for (int i = 0; i < BESTSELLERS_NUMBER_TO_FETCH; i++) {
            String author = siteElements.get(i).select("div.smartAuthorWrapper.ta-product-smartauthor").select("a").first().text();
            String price = convertEmpikPriceWithPossibleDiscountToActualPrice(siteElements.get(i).select("div.price.ta-price-tile").first().text());
            String title = siteElements.get(i).select("strong").first().ownText();
            String productID = siteElements.get(i).select("div.productWrapper").select("a").first().attr("data-product-id");
            String bookURL = createBookURL(title, productID);

            empikBestSellers.add(new Book.BookBuilder()
                    .withAuthor(author)
                    .withPrice(price)
                    .withTitle(title)
                    .withProductID(productID)
                    .withBookURL(bookURL)
                    .build());
        }
        return empikBestSellers;
    }

    public List<Book> get15BooksFromCategory(Document document) {
        List<Book> books = new ArrayList<>();
        List<Element> siteElements = document.select("div.productBox__info");
        for (int iterator = 0; iterator < CATEGORIZED_BOOKS_NUMBER_TO_FETCH; iterator++) {
            String author = executeFetchingAuthorProcess(siteElements, iterator);
            String price = convertEmpikPriceWithPossibleDiscountToActualPrice(siteElements.get(iterator).select("div.productBox__price").first().text());
            String title = siteElements.get(iterator).select("span").first().ownText();
            String productID = siteElements.get(iterator).select("a").first().attr("data-product-id");
            String BookURL = createBookURL(title, productID);

            books.add(new Book.BookBuilder()
                    .withAuthor(author)
                    .withPrice(price)
                    .withTitle(title)
                    .withProductID(productID)
                    .withBookURL(BookURL)
                    .build());
        }
        return books;
    }

    private String convertEmpikPriceWithPossibleDiscountToActualPrice(String price) {
        String[] splittedElements = price.split("\\s+");
        return splittedElements[FIRST_PART] + splittedElements[SECOND_PART];
    }

    private String createBookURL(String title, String productID) {
        return String.format(concreteBookEmpikURL, title, productID);
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
