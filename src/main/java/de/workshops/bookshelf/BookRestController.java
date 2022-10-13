package de.workshops.bookshelf;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    ResponseEntity<List<Book>> getAllBooks() {
        final var books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{isbn}")
    ResponseEntity<Book> getBookByIsbn(@PathVariable @NotBlank @Size(min = 3) String isbn) {
        final var book = bookService.getBookByIsbn(isbn);
        if (book == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping(params = "author")
    ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author) {
        final var booksByAuthor = bookService.getBooksByAuthor(author);
        if (booksByAuthor.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(booksByAuthor);
    }

    @PostMapping("/search")
    ResponseEntity<List<Book>> searchBook (@RequestBody BookSearchRequest searchRequest) {
        final var books = bookService.searchBooks(searchRequest);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> contraintViolation() {
        return ResponseEntity.badRequest().body("Something went wrong");
    }

}
