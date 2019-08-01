package bookstore.scraper.fetcher.empik;

import bookstore.scraper.book.Book;

import java.util.Arrays;
import java.util.List;

class EmpikBookProvider {

    static List<Book> prepare15CrimeBooks() {
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

    static Book prepareMostPreciseBook() {
        return new Book.BookBuilder().withAuthor("Sienkiewicz Henryk").withTitle("W pustyni i w puszczy. Lektura z opracowaniem").build();
    }

    static List<Book> prepare5Bestsellers() {
        return Arrays.asList(
                new Book.BookBuilder().withAuthor("Petitcollin Christel").withTitle("Jak mniej myśleć. Dla analizujących bez końca i wysoko wrażliwych").build(),
                new Book.BookBuilder().withAuthor("Lipińska Blanka").withTitle("Kolejne 365 dni").build(),
                new Book.BookBuilder().withAuthor("Głowińska Anita").withTitle("Kicia Kocia w pociągu").build(),
                new Book.BookBuilder().withAuthor("Głowińska Anita").withTitle("Kicia Kocia i straszna burza").build(),
                new Book.BookBuilder().withAuthor("Głowińska Anita").withTitle("Kicia Kocia. Witaminowe przyjęcie").build());
    }
}
