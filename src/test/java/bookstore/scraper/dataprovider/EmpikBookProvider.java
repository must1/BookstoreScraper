package bookstore.scraper.dataprovider;

import bookstore.scraper.book.Book;

import java.util.Arrays;
import java.util.List;

public class EmpikBookProvider {

    public static List<Book> prepare15CrimeBooks() {
        return Arrays.asList(
                Book.builder().author("Puzyńska Katarzyna").title("Pokrzyk").build(),
                Book.builder().author("Lillegraven Ruth").title("Odbiorę ci wszystko").build(),
                Book.builder().author("Nesbo Jo").title("Nóż. Harry Hole. Tom 12").build(),
                Book.builder().author("Paris B.A.").title("Za zamkniętymi drzwiami").build(),
                Book.builder().author("Hoover Colleen").title("Coraz większy mrok").build(),
                Book.builder().author("Lagercrantz David").title("Ta, która musi umrzeć").build(),
                Book.builder().author("Taylor C. L.").title("Teraz zaśniesz").build(),
                Book.builder().author("Paris B.A.").title("Na skraju załamania").build(),
                Book.builder().author("Mróz Remigiusz").title("Listy zza grobu").build(),
                Book.builder().author("Mróz Remigiusz").title("Rewizja. Joanna Chyłka. Tom 3").build(),
                Book.builder().author("Severski Vincent V.").title("Odwet").build(),
                Book.builder().author("Mróz Remigiusz").title("Immunitet. Joanna Chyłka. Tom 4").build(),
                Book.builder().author("Mróz Remigiusz").title("Inwigilacja. Joanna Chyłka. Tom 5").build(),
                Book.builder().author("Tudor C. J.").title("Zniknięcie Annie Thorne").build(),
                Book.builder().author("Mróz Remigiusz").title("Kontratyp. Joanna Chyłka. Tom 8").build());
    }

    public static Book prepareMostPreciseBook() {
        return Book.builder().author("Sienkiewicz Henryk").title("W pustyni i w puszczy. Lektura z opracowaniem").build();
    }

    public static List<Book> prepare5Bestsellers() {
        return Arrays.asList(
                Book.builder().author("Petitcollin Christel").title("Jak mniej myśleć. Dla analizujących bez końca i wysoko wrażliwych").build(),
                Book.builder().author("Lipińska Blanka").title("Kolejne 365 dni").build(),
                Book.builder().author("Głowińska Anita").title("Kicia Kocia w pociągu").build(),
                Book.builder().author("Głowińska Anita").title("Kicia Kocia i straszna burza").build(),
                Book.builder().author("Głowińska Anita").title("Kicia Kocia. Witaminowe przyjęcie").build());
    }
}
