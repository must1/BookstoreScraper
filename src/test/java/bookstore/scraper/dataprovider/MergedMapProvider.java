package bookstore.scraper.dataprovider;

import bookstore.scraper.book.Book;
import bookstore.scraper.enums.Bookstore;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MergedMapProvider {

    public static Map<Bookstore, List<Book>> prepareExpectedMergedBestSellerMap() {
        Map<Bookstore, List<Book>> expectedMap = new EnumMap<>(Bookstore.class);

        List<Book> empikBestsellers = EmpikBookProvider.prepare5Bestsellers();
        List<Book> merlinBestsellers = MerlinBookProvider.prepare5Bestsellers();

        expectedMap.put(Bookstore.EMPIK, empikBestsellers);
        expectedMap.put(Bookstore.MERLIN, merlinBestsellers);

        return expectedMap;
    }

    public static Map<Bookstore, List<Book>> prepareCrimeBooksMap() {
        Map<Bookstore, List<Book>> expectedMap = new EnumMap<>(Bookstore.class);

        List<Book> crimeBooksEmpik = EmpikBookProvider.prepare15CrimeBooks();
        List<Book> crimeBooksMerlin = MerlinBookProvider.prepare15CrimeBooks();

        expectedMap.put(Bookstore.EMPIK, crimeBooksEmpik);
        expectedMap.put(Bookstore.MERLIN, crimeBooksMerlin);

        return expectedMap;
    }

    public static Map<Bookstore, Book> prepareExpectedMergedMostPreciseBookMap() {
        Map<Bookstore, Book> expectedMap = new EnumMap<>(Bookstore.class);

        expectedMap.put(Bookstore.EMPIK, EmpikBookProvider.prepareMostPreciseBook());
        expectedMap.put(Bookstore.MERLIN, MerlinBookProvider.prepareMostPreciseBook());

        return expectedMap;
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
