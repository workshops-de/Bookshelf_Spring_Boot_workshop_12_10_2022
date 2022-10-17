package de.workshops.bookshelf;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookRestControllerMockitoTest {
    @MockBean
    BookService bookService;

    @Autowired
    BookRestController controller;

    @Captor
    ArgumentCaptor<String> isbnCaptor;

    @Test
    void shouldReturn_NO_CONTENT_whenNoBooksInShelf() {
        when(bookService.getAllBooks()).thenReturn(List.of());

        final var response = controller.getAllBooks();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void test() {
        when(bookService.getBookByIsbn(isbnCaptor.capture())).thenReturn(new Book());

        final var response = controller.getBookByIsbn("1234");

        final var value = isbnCaptor.getValue();
        assertThat(value).isEqualTo("1234");
    }


}