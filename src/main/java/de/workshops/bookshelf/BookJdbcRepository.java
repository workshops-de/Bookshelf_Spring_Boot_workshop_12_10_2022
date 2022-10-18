package de.workshops.bookshelf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookJdbcRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookJdbcRepository.class);
    @Autowired
    JdbcTemplate template;

    @Autowired
    NamedParameterJdbcTemplate namedTemplate;

    public List<Book> findAll() {
        String sql = "SELECT * FROM book";
        return template.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO book (title, description, author, isbn) VALUES (?, ?, ?, ?)";
        template.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Set parameters
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getIsbn());

            return preparedStatement;
        }, generatedKeyHolder);

        LOGGER.info("Generated Id: {}", generatedKeyHolder.getKey());
    }

    public void saveNamed(Book book) {
        String sql = "INSERT INTO book (title, description, author, isbn) VALUES (:title, :description, :author, :isbn)";
        Map<String, Object> params = new HashMap<>();
        params.put("title", book.getTitle());
        params.put("description", book.getDescription());
        params.put("author", book.getAuthor());
        params.put("isbn", book.getIsbn());
        namedTemplate.update(sql, params);
    }
}
