package bookstore.scraper.book.rankingsystem;

import bookstore.scraper.account.LoggedAccountService;
import bookstore.scraper.book.Book;
import bookstore.scraper.book.BookService;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.enums.CategoryType;
import bookstore.scraper.historysystem.HistorySystemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static bookstore.scraper.dataprovider.MergedMapProvider.prepareCrimeBooksMap;
import static bookstore.scraper.dataprovider.MergedMapProvider.prepareExpectedRankingMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategorizedBooksRankingServiceTest {

    @Mock
    HistorySystemService historySystemService;
    @Mock
    BookService bookService;
    @Mock
    LoggedAccountService loggedAccountService;

    @InjectMocks
    CategorizedBooksRankingService categorizedBooksRankingService;

    @Before
    public void setUp() {
        when(loggedAccountService.getLoggedAccountID()).thenReturn(1);
    }

    @Test
    public void getRankingForCrimeCategory() {
        Map<Bookstore, List<Book>> bookstoreWith15CrimeBooks = prepareCrimeBooksMap();

        when(bookService.getBooksByCategory(CategoryType.CRIME)).thenReturn(bookstoreWith15CrimeBooks);

        Map<String, Integer> actualMap = categorizedBooksRankingService.getRankingForCategory(CategoryType.CRIME);
        Map<String, Integer> expectedMap = prepareExpectedRankingMap();

        assertEquals(expectedMap, actualMap);
    }
}
