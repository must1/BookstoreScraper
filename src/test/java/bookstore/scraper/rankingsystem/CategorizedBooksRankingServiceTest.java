package bookstore.scraper.rankingsystem;

import static bookstore.scraper.dataprovider.MerlinBookProvider.prepareMapWithBookstoreAndCrimeBooks;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import bookstore.scraper.book.Book;
import bookstore.scraper.book.scrapingtypeservice.CategorizedBookService;
import bookstore.scraper.dataprovider.EmpikBookProvider;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.dataprovider.MerlinBookProvider;
import bookstore.scraper.enums.CategoryType;

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
        Map<String,Integer> expectedMap = prepareExpectedMap();

        assertEquals(actualMap,expectedMap);
    }

    private Map<String, Integer> prepareExpectedMap()
    {
        Map<String,Integer> expectedMap = new LinkedHashMap<>();

        expectedMap.put("Listy zza grobu",2);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5",1);
    }
}