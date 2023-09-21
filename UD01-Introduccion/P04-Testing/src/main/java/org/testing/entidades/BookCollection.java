package org.testing.entidades;

import java.util.ArrayList;
import java.util.List;

public record BookCollection(List<Book> books) {
    public List<Book> find(String textToFind) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : this.books) {
            if (book.isbn().equals(textToFind) ||
                    book.title().contains(textToFind) ||
                    book.author().contains(textToFind)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
}
