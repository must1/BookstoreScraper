package bookstore.scraper.enums;

import java.util.InputMismatchException;

public enum CategoryType {
    BIOGRAPHY, CRIME, GUIDES, FANTASY, ROMANCES,MOST_PRECISE_BOOK,BESTSELLER;

    private static final CategoryType[] copyOfValues = values();

    public static CategoryType forName(CategoryType name) {
        for (CategoryType value : copyOfValues) {
            if (value.name().equals(String.valueOf(name))) {
                return value;
            }
        }
        throw new InputMismatchException("Given category type is bad!");
    }
}
