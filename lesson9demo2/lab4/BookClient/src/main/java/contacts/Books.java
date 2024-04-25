package contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Books {
    private Collection<Book> books;

    public Books() {
    }

    public Books(List<Book> books) {
        this.books = books;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Books{" +
                "books=" + books +
                '}';
    }
}
