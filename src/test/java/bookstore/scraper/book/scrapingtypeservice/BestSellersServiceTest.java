/*
package bookstore.scraper.book.scrapingtypeservice;

import bookstore.scraper.book.Book;
import bookstore.scraper.dataprovider.EmpikBookProvider;
import bookstore.scraper.dataprovider.MerlinBookProvider;
import bookstore.scraper.enums.Bookstore;
import bookstore.scraper.fetcher.empik.EmpikFetchingBookService;
import bookstore.scraper.fetcher.merlin.MerlinFetchingBookService;
import bookstore.scraper.urlproperties.EmpikUrlProperties;
import bookstore.scraper.urlproperties.MerlinUrlProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BestSellersServiceTest {

    @Mock
    private EmpikFetchingBookService empikBookService;
    @Mock
    private MerlinFetchingBookService merlinBookService;
    @Mock
    private EmpikUrlProperties empikUrlProperties;
    @Mock
    private MerlinUrlProperties merlinUrlProperties;

    @InjectMocks
    private BestSellersService bestSellersService;

    @Test
    public void getBestSellers() {
        List<Book> merlinBestsellers = EmpikBookProvider.prepare5Bestsellers();
        List<Book> empikBestsellers = MerlinBookProvider.prepare5Bestsellers();

        when(empikBookService.get5BestSellersEmpik(any())).thenReturn(empikBestsellers);
        when(merlinBookService.get5BestSellersMerlin(any())).thenReturn(merlinBestsellers);

        Map<Bookstore, List<Book>> actualMap = bestSellersService.getBestSellers();
        Map<Bookstore, List<Book>> expectedMap = null;

        assertEquals(expectedMap, actualMap);
        assertThat(actualMap).hasSize(expectedMap.size());
    }
}*/
