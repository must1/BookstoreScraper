package bookstore.scraper.dataprovider;

import bookstore.scraper.book.Book;

import java.util.Arrays;
import java.util.List;

public class MerlinBookProvider {

    public static List<Book> prepare15CrimeBooks() {
        return Arrays.asList(
                Book.builder().author("Richard Hill-Whittal").title("Indie games. Podręcznik niezależnego twórcy gier").build(),
                Book.builder().author("Jo Nesbo").title("NÓŻ").build(),
                Book.builder().author("Joanna Steinke-Kalembka").title("Dodaj mi skrzydeł. Jak rozwijać u dzieci motywację wewnętrzną?").build(),
                Book.builder().author("Yuval Zommer").title("Wielka księga robali").build(),
                Book.builder().author("Katarzyna Grochola").title("ZRANIĆ MARIONETKĘ").build(),
                Book.builder().author("Katarzyna Berenika Miszczuk").title("JAGA").build(),
                Book.builder().author("Sarah J. Maas").title("Imperium burz. Szklany tron").build(),
                Book.builder().author("Charles Duhigg").title("Siła nawyku. Dlaczego robimy to, co robimy i jak można to zmienić w życiu i biznesie").build(),
                Book.builder().author("Katarzyna Berenika Miszczuk").title("Przesilenie. Kwiat paproci").build(),
                Book.builder().author("Hans Rosling").title("Factfulness Dlaczego Świat Jest Lepszy Niż Myślimy Czyli Jak Stereotypy Zastąpić Realną Wiedzą").build(),
                Book.builder().author("ARETA SZPURA").title("JAK URATOWAĆ ŚWIAT CZYLI CO DOBREGO MOŻESZ ZROBIĆ DLA PLANETY").build(),
                Book.builder().author("Remigiusz Mróz").title("Listy zza grobu").build(),
                Book.builder().author("Opracowanie zbiorowe").title("ĆWICZENIA 2-LATKA Z NAKLEJKAMI JUŻ TO WIEM").build(),
                Book.builder().author("Daniel Kahneman").title("Pułapki myślenia - o myśleniu szybkim i wolnym").build(),
                Book.builder().author("Michel Houellebecq").title("SEROTONINA").build());

    }

    public static Book prepareMostPreciseBook() {
        return Book.builder()
                .author("Henryk Sienkiewicz")
                .title("W pustyni i w puszczy. Lektury z omówieniem, szkoła podstawowa")
                .build();
    }

    public static List<Book> prepare5Bestsellers() {
        return Arrays.asList(Book.builder().author("Richard Hill-Whittal").title("Indie games. Podręcznik niezależnego twórcy gier")
                .build(), Book.builder().author("Jo Nesbo").title("NÓŻ")
                .build(), Book.builder().author("Joanna Steinke-Kalembka").title("Dodaj mi skrzydeł. Jak rozwijać u dzieci motywację wewnętrzną?")
                .build(), Book.builder().author("Yuval Zommer").title("Wielka księga robali")
                .build(), Book.builder().author("Katarzyna Grochola").title("ZRANIĆ MARIONETKĘ")
                .build());
    }
}
