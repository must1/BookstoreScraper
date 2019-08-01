package bookstore.scraper.book;

import lombok.*;

import java.util.Objects;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class Book {

    private String title;
    private String author;
    private String productID;
    private String price;
    private String bookURL;

    public String getPurifiedTitle() {
        return title.replaceAll("\\P{Alnum}+", "").toLowerCase();
    }

    public static class BookBuilder {

        private String title;
        private String author;
        private String productID;
        private String price;
        private String bookURL;

        public BookBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder withBookUrl(String bookURL) {
            this.bookURL = bookURL;
            return this;
        }

        public BookBuilder withPrice(String price) {
            this.price = price;
            return this;
        }

        public BookBuilder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder withProductID(String productID) {
            this.productID = productID;
            return this;
        }

        public Book build() {
            Book book = new Book();
            book.price = this.price;
            book.author = this.author;
            book.title = this.title;
            book.productID = this.productID;
            book.bookURL = this.bookURL;
            return book;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
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

}
