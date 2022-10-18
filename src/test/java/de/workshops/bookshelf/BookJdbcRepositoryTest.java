package de.workshops.bookshelf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BookJdbcRepository.class)
class BookJdbcRepositoryTest {

    @Autowired
    BookJdbcRepository repository;

    @Autowired
    JdbcTemplate template;

    @BeforeEach
    void initDb () {
        String sql = "INSERT INTO BOOK (id, title, author, description, isbn) VALUES (1, 'title', 'author', 'description', 'isbn')";
        template.update(sql);
    }

    @Test
    void shouldFindAllBooks() {
        final var bookList = repository.findAll();

        assertThat(bookList)
                .hasSize(1)
                .first()
                .hasFieldOrPropertyWithValue("title", "title")
                .hasFieldOrPropertyWithValue("description", "description")
                .hasFieldOrPropertyWithValue("author", "author")
                .hasFieldOrPropertyWithValue("isbn", "isbn");
    }
}