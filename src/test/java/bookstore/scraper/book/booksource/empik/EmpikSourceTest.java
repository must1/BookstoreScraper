package bookstore.scraper.book.booksource.empik;

import bookstore.scraper.book.Book;
import bookstore.scraper.enums.CategoryType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static bookstore.scraper.dataprovider.EmpikBookProvider.prepare15CrimeBooks;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmpikSourceTest {

    @Autowired
    EmpikSource empikSource;

    @Test
    public void getName() {
    }

    @Test
    public void getBooksByCategory() {
 /*       File in = getFile("/empik/CrimeCategoryEmpik.html");
        Document doc = Jsoup.parse(in, "UTF-8");
*/
        List<Book> actualBooks = empikSource.getBooksByCategory(CategoryType.CRIME);
        List<Book> expectedBooks = prepare15CrimeBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void getMostPreciseBook() {
    }

    @Test
    public void getBestSellers() {
    }
}