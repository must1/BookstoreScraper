package bookstore.scraper.book;

import lombok.*;

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
                ", productID='" + productID + '\'' +
                ", actualPrice='" + price + '\'' +
                '}';
    }
}
