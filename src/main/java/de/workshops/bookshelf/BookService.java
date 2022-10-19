package de.workshops.bookshelf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    private final BookJdbcRepository repository;
    private final Book novel;
    private final Book cookbook;

    BookService(BookJdbcRepository repository, Book novel, @Qualifier("cookbookBook") Book cookbook) {
        this.repository = repository;
        this.novel = novel;
        this.cookbook = cookbook;

        LOGGER.info("Novel: {}", novel.getDescription());
        LOGGER.info("Cookbook: {}", cookbook.getDescription());
    }

    List<Book> getAllBooks() {
        return repository.findAll();
    }

    Book getBookByIsbn(String isbn) {
        final var book = repository.findAll().stream()
                .filter(b -> isbn.equals(b.getIsbn()))
                .findFirst();
        return book.orElse(null);
    }

    List<Book> getBooksByAuthor(String author) {
        return repository.findAll().stream()
                .filter(book -> book.getAuthor().startsWith(author))
                .toList();
    }

    List<Book> searchBooks(BookSearchRequest searchRequest) {
        return repository.findAll().stream()
                .filter(book -> book.getAuthor().contains(searchRequest.getAuthorName())
                        || book.getIsbn().equals(searchRequest.getIsbn()))
                .toList();
    }

    public void save(Book book) {
        repository.save(book);
    }
}
