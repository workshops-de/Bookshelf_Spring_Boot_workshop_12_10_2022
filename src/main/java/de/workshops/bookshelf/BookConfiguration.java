package de.workshops.bookshelf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BookConfiguration {

    @Bean
    @Primary
    Book aNovel() {
        final var novel = new Book();
        novel.setDescription("This is a novel");
        return novel;
    }

    @Bean(name = "cookbookBook")
    Book aCookbook() {
        final var cookbook = new Book();
        cookbook.setDescription("This is a cookbook");
        return cookbook;
    }
}
