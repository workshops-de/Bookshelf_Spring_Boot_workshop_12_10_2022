package de.workshops.bookshelf;

import io.restassured.RestAssured;
import lombok.With;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookRestControllerRestAssuredTest {

    @LocalServerPort
    int port;

    @Test
    void shouldGetAllBooks() {
        RestAssured.given()
                .auth().basic("dbUser", "geheim")
                .log().all()
                .when().get("http://localhost:" + port +"/book")
                .then()
                .statusCode(200);
    }
}