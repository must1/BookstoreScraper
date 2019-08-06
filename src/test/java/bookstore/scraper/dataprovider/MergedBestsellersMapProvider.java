package bookstore.scraper.dataprovider;

import bookstore.scraper.book.Book;
import bookstore.scraper.enums.Bookstore;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class MergedBestsellersMapProvider {

    public static Map<Bookstore, List<Book>> prepareExpectedMergedBestSellerMap() {
        Map<Bookstore, List<Book>> expectedMap = new EnumMap<>(Bookstore.class);

        List<Book> empikBestsellers = EmpikBookProvider.prepare5Bestsellers();
        List<Book> merlinBestsellers = MerlinBookProvider.prepare5Bestsellers();

        expectedMap.put(Bookstore.EMPIK,empikBestsellers);
        expectedMap.put(Bookstore.MERLIN,merlinBestsellers);

        return expectedMap;
    }
}
