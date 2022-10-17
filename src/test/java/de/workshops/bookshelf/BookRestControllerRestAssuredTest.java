package de.workshops.bookshelf;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookRestControllerRestAssuredTest {

    @LocalServerPort
    int port;

    @Test
    void shouldGetAllBooks() {
        RestAssured.given()
                .log().all()
                .when().get("http://localhost:" + port +"/book")
                .then()
                .statusCode(200);
    }
}