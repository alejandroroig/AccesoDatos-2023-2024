package org.testing.bookcollection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testing.entidades.BookCollection;
import org.testing.entidades.Book;


import java.util.List;

public class FindTest {
    @Test
    public void shouldFindABookByIsbn()
    {
        final String isbnToLocate = "un-isbn-2";
        BookCollection books = new BookCollection(List.of(new Book[]{
                new Book("un-isbn-1", "un titulo 1", "un autor 1", 100),
                new Book(isbnToLocate, "un titulo 2", "un autor 2", 200),
                new Book("un-isbn-3", "un titulo 3", "un autor 3", 300),
        }));
        books.find(isbnToLocate).forEach((Book book) -> assertEquals(isbnToLocate, book.isbn()));
    }
}
