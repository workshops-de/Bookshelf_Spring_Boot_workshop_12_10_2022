package de.workshops.bookshelf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class BookViewController {

    private final BookService bookService;

    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    String getAllBooks(Model model) {
        final var books = bookService.getAllBooks();

        model.addAttribute("books", books);

        return "books";
    }
}
