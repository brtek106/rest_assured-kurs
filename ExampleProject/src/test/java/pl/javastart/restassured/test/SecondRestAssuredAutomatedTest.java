package pl.javastart.restassured.test;


import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SecondRestAssuredAutomatedTest {


    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
                given()
                    .log().uri().log().method()
                .when()
                    .get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/1")
                .then()
                    .log().all().statusCode(200);


    }
}
