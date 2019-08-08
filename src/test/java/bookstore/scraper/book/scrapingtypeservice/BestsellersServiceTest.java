//package bookstore.scraper.book.scrapingtypeservice;
//
//import bookstore.scraper.book.Book;
//import bookstore.scraper.dataprovider.EmpikBookProvider;
//import bookstore.scraper.dataprovider.MerlinBookProvider;
//import bookstore.scraper.enums.Bookstore;
//import bookstore.scraper.fetcher.empik.EmpikFetchingBookService;
//import bookstore.scraper.fetcher.merlin.MerlinFetchingBookService;
//import bookstore.scraper.urlproperties.EmpikUrlProperties;
//import bookstore.scraper.urlproperties.MerlinUrlProperties;
//import bookstore.scraper.utilities.JSoupConnector;
//import org.jsoup.nodes.Document;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.List;
//import java.util.Map;
//
//import static bookstore.scraper.dataprovider.MergedBestsellersMapProvider.prepareExpectedMergedBestSellerMap;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class BestsellersServiceTest {
//
//    @Mock
//    private EmpikFetchingBookService empikBookService;
//    @Mock
//    private MerlinFetchingBookService merlinBookService;
//    @Mock
//    private EmpikUrlProperties empikUrlProperties;
//    @Mock
//    private MerlinUrlProperties merlinUrlProperties;
//    @Mock
//    private EmpikUrlProperties.Empik empikMock;
//    @Mock
//    private MerlinUrlProperties.Merlin merlinMock;
//    @Mock
//    JSoupConnector jSoupConnector;
//
//    @InjectMocks
//    private BestSellersService bestSellersService;
//
//    @Test
//    public void getBestSellers() {
//        List<Book> merlinBestsellers = MerlinBookProvider.prepare5Bestsellers();
//        List<Book> empikBestsellers = EmpikBookProvider.prepare5Bestsellers();
//        Document empikDocument = mock(Document.class);
//        Document merlinDocument = mock(Document.class);
//
//        when(jSoupConnector.connect("https://www.empik.com/bestsellery/ksiazki")).thenReturn(empikDocument);
//        when(empikUrlProperties.getEmpik()).thenReturn(empikMock);
//        when(empikMock.getBestSellers()).thenReturn("https://www.empik.com/bestsellery/ksiazki");
//        when(empikBookService.get5BestSellersEmpik(empikDocument)).thenReturn(empikBestsellers);
//
//        when(jSoupConnector.connect("https://merlin.pl/bestseller/?option_80=10349074")).thenReturn(merlinDocument);
//        when(merlinMock.getBestSellers()).thenReturn("https://merlin.pl/bestseller/?option_80=10349074");
//        when(merlinUrlProperties.getMerlin()).thenReturn(merlinMock);
//        when(merlinBookService.get5BestSellersMerlin(merlinDocument)).thenReturn(merlinBestsellers);
//
//        Map<Bookstore, List<Book>> actualMap = bestSellersService.getBestSellers();
//        Map<Bookstore, List<Book>> expectedMap = prepareExpectedMergedBestSellerMap();
//
//        assertEquals(expectedMap, actualMap);
//    }
//}