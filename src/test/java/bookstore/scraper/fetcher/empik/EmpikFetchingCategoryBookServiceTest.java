package bookstore.scraper.fetcher.empik;

import bookstore.scraper.book.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmpikFetchingBookServiceTest {

    @Autowired
    EmpikFetchingBookService empikFetchingBookService;

    @Test
    public void get15BooksFromCrimeCategory() throws IOException {
        File in = getFile("/CrimeCategoryEmpik.html");
        Document doc = Jsoup.parse(in, "UTF-8");

        List<Book> actualBooks = empikFetchingBookService.get15BooksFromCategory(doc);
        List<Book> expectedBooks = prepare15CrimeBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    private static File getFile(String resourceName) {
        try {
            return new File(EmpikFetchingBookServiceTest.class.getResource(resourceName).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Book> prepare15CrimeBooks() {
        return Arrays.asList(
                new Book.BookBuilder().withAuthor("Puzyńska Katarzyna").withTitle("Pokrzyk").build(),
                new Book.BookBuilder().withAuthor("Lillegraven Ruth").withTitle("Odbiorę ci wszystko").build(),
                new Book.BookBuilder().withAuthor("Nesbo Jo").withTitle("Nóż. Harry Hole. Tom 12").build(),
                new Book.BookBuilder().withAuthor("Paris B.A.").withTitle("Za zamkniętymi drzwiami").build(),
                new Book.BookBuilder().withAuthor("Hoover Colleen").withTitle("Coraz większy mrok").build(),
                new Book.BookBuilder().withAuthor("Lagercrantz David").withTitle("Ta, która musi umrzeć").build(),
                new Book.BookBuilder().withAuthor("Taylor C. L.").withTitle("Teraz zaśniesz").build(),
                new Book.BookBuilder().withAuthor("Paris B.A.").withTitle("Na skraju załamania").build(),
                new Book.BookBuilder().withAuthor("Mróz Remigiusz").withTitle("Listy zza grobu").build(),
                new Book.BookBuilder().withAuthor("Mróz Remigiusz").withTitle("Rewizja. Joanna Chyłka. Tom 3").build(),
                new Book.BookBuilder().withAuthor("Severski Vincent V.").withTitle("Odwet").build(),
                new Book.BookBuilder().withAuthor("Mróz Remigiusz").withTitle("Immunitet. Joanna Chyłka. Tom 4").build(),
                new Book.BookBuilder().withAuthor("Mróz Remigiusz").withTitle("Inwigilacja. Joanna Chyłka. Tom 5").build(),
                new Book.BookBuilder().withAuthor("Tudor C. J.").withTitle("Zniknięcie Annie Thorne").build(),
                new Book.BookBuilder().withAuthor("Mróz Remigiusz").withTitle("Kontratyp. Joanna Chyłka. Tom 8").build());
    }
}