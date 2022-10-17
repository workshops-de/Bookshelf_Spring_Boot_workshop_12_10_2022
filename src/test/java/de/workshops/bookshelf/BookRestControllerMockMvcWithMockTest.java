package de.workshops.bookshelf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
class BookRestControllerMockMvcWithMockTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    BookService bookService;

    @Test
    void shouldGetBookByIsbn() throws Exception {
        when(bookService.getBookByIsbn(anyString())).thenReturn(new Book());

        final var result = mockMvc.perform(get("/book/12345"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        final var jsonString = result.getResponse().getContentAsString();

        final var book = mapper.readValue(jsonString, new TypeReference<Book>() {});

        assertThat(book).isNotNull();
    }

    @Test
    void shouldPutNewBookInShelf() throws Exception {
        //language=JSON
        String bookJson = """
                {
                    "title": "Rest und HTTP",
                    "isbn": "978-3-89864-732-8",
                    "author": "Stefan Tilkov"
                }
                """;
        final var result = mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(bookJson))
                .andExpect(status().isOk())
                .andReturn();

        final var jsonString = result.getResponse().getContentAsString();
        assertThat(jsonString).isNotEmpty();
    }
}