package de.workshops.bookshelf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ResourceLoader;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookRestController {
    private final ResourceLoader resourceLoader;
    private final ObjectMapper mapper;

    private List<Book> books;

    public BookRestController(ResourceLoader resourceLoader, ObjectMapper mapper) {
        this.resourceLoader = resourceLoader;
        this.mapper = mapper;
    }

    @PostConstruct
    void fillBookList () throws IOException {
        final var resource = resourceLoader.getResource("classpath:books.json");
        this.books = mapper.readValue(resource.getInputStream(), new TypeReference<>() {});
    }

    @GetMapping
    List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{isbn}")
    Book getBookByIsbn(@PathVariable @NotBlank @Size(min = 3) String isbn) {
        return books.stream()
                .filter(book -> isbn.equals(book.getIsbn()))
                .findFirst()
                .orElseThrow();
    }

    @GetMapping(params = "author")
    List<Book> getBooksByAuthor(@RequestParam String author) {
        return books.stream()
                .filter(book -> book.getAuthor().startsWith(author))
                .toList();
    }

    @PostMapping("/search")
    List<Book> searchBook (@RequestBody BookSearchRequest searchRequest) {
        return books.stream()
                .filter(book -> book.getAuthor().contains(searchRequest.getAuthorName())
                        || book.getIsbn().equals(searchRequest.getIsbn()))
                .toList();
    }
}
