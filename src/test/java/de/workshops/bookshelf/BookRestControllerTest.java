package de.workshops.bookshelf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(properties = "my.name=Kratz")
class BookRestControllerTest {

    @Autowired
    BookRestController bookRestController;

    @Test
    void shouldGetAllBooks() {
        final var allBooks = bookRestController.getAllBooks();

        assertThat(allBooks.getBody())
                .isNotNull()
                .hasSize(3);
    }
}