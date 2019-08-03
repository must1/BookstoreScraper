package bookstore.scraper.rankingsystem;

import bookstore.scraper.book.Book;
import bookstore.scraper.book.scrapingtypeservice.CategorizedBookService;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.enums.CategoryType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static bookstore.scraper.dataprovider.MerlinBookProvider.prepareExpectedRankingMap;
import static bookstore.scraper.dataprovider.MerlinBookProvider.prepareMapWithBookstoreAndCrimeBooks;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategorizedBooksRankingServiceTest {

    @Mock
    CategorizedBookService categorizedBookService;

    @InjectMocks
    CategorizedBooksRankingService categorizedBooksRankingService;

    @Test
    public void getRankingForCrimeCategory() {
        Map<Bookstore, List<Book>> bookstoreWith15CrimeBooks = prepareMapWithBookstoreAndCrimeBooks();

        when(categorizedBookService.get15BooksFromCrimeCategory()).thenReturn(bookstoreWith15CrimeBooks);

        Map<String, Integer> actualMap = categorizedBooksRankingService.getRankingForCategory(CategoryType.CRIME);
        Map<String, Integer> expectedMap = prepareExpectedRankingMap();

        assertEquals(expectedMap, actualMap);
    }
}