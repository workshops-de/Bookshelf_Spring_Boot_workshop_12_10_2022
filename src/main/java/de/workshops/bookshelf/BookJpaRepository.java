package de.workshops.bookshelf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {

    @Query("select count(b) from Book b")
    int countBooks();

    @Query("select b from Book as b")
    List<Book> allBooks();
}
