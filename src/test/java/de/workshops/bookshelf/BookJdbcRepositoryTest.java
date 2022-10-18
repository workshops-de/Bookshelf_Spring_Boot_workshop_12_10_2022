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

    @Test
    void shouldFindAllBooks() {
        final var bookList = repository.findAll();

        assertThat(bookList).hasSize(3);
    }
}