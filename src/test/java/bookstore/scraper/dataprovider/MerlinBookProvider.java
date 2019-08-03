package bookstore.scraper.dataprovider;

import bookstore.scraper.book.Book;
import bookstore.scraper.enums.Bookstore;

import java.util.*;

public class MerlinBookProvider {

    public static List<Book> prepare15CrimeBooks() {
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

    public static Book prepareMostPreciseBook() {
        return new Book.BookBuilder()
                .withAuthor("Henryk Sienkiewicz")
                .withTitle("W pustyni i w puszczy. Lektury z omówieniem, szkoła podstawowa")
                .build();
    }

    public static List<Book> prepare5Bestsellers() {
        return Arrays.asList(new Book.BookBuilder().withAuthor("Richard Hill-Whittal").withTitle("Indie games. Podręcznik niezależnego twórcy gier")
                .build(), new Book.BookBuilder().withAuthor("Jo Nesbo").withTitle("NÓŻ")
                .build(), new Book.BookBuilder().withAuthor("Joanna Steinke-Kalembka").withTitle("Dodaj mi skrzydeł. Jak rozwijać u dzieci motywację wewnętrzną?")
                .build(), new Book.BookBuilder().withAuthor("Yuval Zommer").withTitle("Wielka księga robali")
                .build(), new Book.BookBuilder().withAuthor("Katarzyna Grochola").withTitle("ZRANIĆ MARIONETKĘ")
                .build());
    }

    public static Map<Bookstore, List<Book>> prepareMapWithBookstoreAndCrimeBooks() {
        Map<Bookstore, List<Book>> map = new EnumMap<>(Bookstore.class);
        map.put(Bookstore.EMPIK, EmpikBookProvider.prepare15CrimeBooks());
        map.put(Bookstore.MERLIN, MerlinBookProvider.prepare15CrimeBooks());

        return map;
    }

    public static Map<String, Integer> prepareExpectedRankingMap() {
        Map<String, Integer> expectedMap = new LinkedHashMap<>();

        expectedMap.put("Listy zza grobu", 2);
        expectedMap.put("Inwigilacja. Joanna Chyłka. Tom 5", 1);
        expectedMap.put("Na skraju załamania", 1);
        expectedMap.put("Wielka księga robali", 1);
        expectedMap.put("Rewizja. Joanna Chyłka. Tom 3", 1);
        expectedMap.put("JAK URATOWAĆ ŚWIAT CZYLI CO DOBREGO MOŻESZ ZROBIĆ DLA PLANETY", 1);
        expectedMap.put("Odbiorę ci wszystko", 1);
        expectedMap.put("ZRANIĆ MARIONETKĘ", 1);
        expectedMap.put("Nóż. Harry Hole. Tom 12", 1);
        expectedMap.put("NÓŻ", 1);
        expectedMap.put("Imperium burz. Szklany tron", 1);
        expectedMap.put("Odwet", 1);
        expectedMap.put("Dodaj mi skrzydeł. Jak rozwijać u dzieci motywację wewnętrzną?", 1);
        expectedMap.put("Factfulness Dlaczego Świat Jest Lepszy Niż Myślimy Czyli Jak Stereotypy Zastąpić Realną Wiedzą", 1);
        expectedMap.put("Pułapki myślenia - o myśleniu szybkim i wolnym", 1);
        expectedMap.put("Indie games. Podręcznik niezależnego twórcy gier", 1);
        expectedMap.put("ĆWICZENIA 2-LATKA Z NAKLEJKAMI JUŻ TO WIEM", 1);
        expectedMap.put("Zniknięcie Annie Thorne", 1);
        expectedMap.put("Immunitet. Joanna Chyłka. Tom 4", 1);
        expectedMap.put("Przesilenie. Kwiat paproci", 1);
        expectedMap.put("Ta, która musi umrzeć", 1);
        expectedMap.put("Za zamkniętymi drzwiami", 1);
        expectedMap.put("Pokrzyk", 1);
        expectedMap.put("JAGA", 1);
        expectedMap.put("Teraz zaśniesz", 1);
        expectedMap.put("Siła nawyku. Dlaczego robimy to, co robimy i jak można to zmienić w życiu i biznesie", 1);
        expectedMap.put("Kontratyp. Joanna Chyłka. Tom 8", 1);
        expectedMap.put("SEROTONINA", 1);
        expectedMap.put("Coraz większy mrok", 1);

        return expectedMap;
    }
}
