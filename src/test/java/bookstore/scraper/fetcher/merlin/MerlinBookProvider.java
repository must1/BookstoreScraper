package bookstore.scraper.fetcher.merlin;

import bookstore.scraper.book.Book;

import java.util.Arrays;
import java.util.List;

public class MerlinBookProvider {

    static List<Book> prepare15CrimeBooks() {
        return Arrays.asList(
                new Book.BookBuilder().withAuthor("Richard Hill-Whittal").withTitle("Indie games. Podręcznik niezależnego twórcy gier").build(),
                new Book.BookBuilder().withAuthor("Jo Nesbo").withTitle("NÓŻ").build(),
                new Book.BookBuilder().withAuthor("Joanna Steinke-Kalembka").withTitle("Dodaj mi skrzydeł. Jak rozwijać u dzieci motywację wewnętrzną?").build(),
                new Book.BookBuilder().withAuthor("Yuval Zommer").withTitle("Wielka księga robali").build(),
                new Book.BookBuilder().withAuthor("Katarzyna Grochola").withTitle("ZRANIĆ MARIONETKĘ").build(),
                new Book.BookBuilder().withAuthor("Katarzyna Berenika Miszczuk").withTitle("JAGA").build(),
                new Book.BookBuilder().withAuthor("Sarah J. Maas").withTitle("Imperium burz. Szklany tron").build(),
                new Book.BookBuilder().withAuthor("Charles Duhigg").withTitle("Siła nawyku. Dlaczego robimy to, co robimy i jak można to zmienić w życiu i biznesie").build(),
                new Book.BookBuilder().withAuthor("Katarzyna Berenika Miszczuk").withTitle("Przesilenie. Kwiat paproci").build(),
                new Book.BookBuilder().withAuthor("Hans Rosling").withTitle("Factfulness Dlaczego Świat Jest Lepszy Niż Myślimy Czyli Jak Stereotypy Zastąpić Realną Wiedzą").build(),
                new Book.BookBuilder().withAuthor("ARETA SZPURA").withTitle("JAK URATOWAĆ ŚWIAT CZYLI CO DOBREGO MOŻESZ ZROBIĆ DLA PLANETY").build(),
                new Book.BookBuilder().withAuthor("Remigiusz Mróz").withTitle("Listy zza grobu").build(),
                new Book.BookBuilder().withAuthor("Opracowanie zbiorowe").withTitle("ĆWICZENIA 2-LATKA Z NAKLEJKAMI JUŻ TO WIEM").build(),
                new Book.BookBuilder().withAuthor("Daniel Kahneman").withTitle("Pułapki myślenia - o myśleniu szybkim i wolnym").build(),
                new Book.BookBuilder().withAuthor("Michel Houellebecq").withTitle("SEROTONINA").build());

    }

    static Book prepareMostPreciseBook() {
        return new Book.BookBuilder().withAuthor("Henryk Sienkiewicz").withTitle("W pustyni i w puszczy. Lektury z omówieniem, szkoła podstawowa").build();
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
