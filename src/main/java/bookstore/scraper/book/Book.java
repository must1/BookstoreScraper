package bookstore.scraper.book;

import lombok.*;

import java.util.Objects;

@Value
@Builder
public class Book {

    public static final String PURIFIED_STRING_REGEX = "\\P{Alnum}+";
    private String title;
    private String author;
    private String productID;
    private String price;
    private String bookURL;

    public String getPurifiedTitle() {
        return title.replaceAll(PURIFIED_STRING_REGEX, "").toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
