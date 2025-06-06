package org.example.springbootweb.repository;

import java.util.List;
import java.util.Optional;
import org.example.springbootweb.model.Book;

public interface BookRepository {
    Book createBook(Book book);

    Optional<Book> getBookById(Long id);

    List<Book> getAll();
}
