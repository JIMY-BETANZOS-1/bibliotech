package com.bibliotech.repository;

import com.bibliotech.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookRepository {

    private final Map<Long, Book> books = new ConcurrentHashMap<>();

    public Book save(Book book) {
        books.put(book.getId(), book);
        return book;
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }
}
