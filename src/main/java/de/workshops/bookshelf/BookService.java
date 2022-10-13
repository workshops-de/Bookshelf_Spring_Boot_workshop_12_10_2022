package de.workshops.bookshelf;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
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

    public List<Book> searchBooks(BookSearchRequest searchRequest) {
        return repository.findAll().stream()
                .filter(book -> book.getAuthor().contains(searchRequest.getAuthorName())
                        || book.getIsbn().equals(searchRequest.getIsbn()))
                .toList();
    }
}
