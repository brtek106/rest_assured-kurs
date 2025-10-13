package pl.javastart.restassured.test.ssl;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BadSslTest {

    @Test
    public void sslCertExpiredTest() {
        given().relaxedHTTPSValidation().when().get("https://expired.badssl.com/").then().statusCode(200);
    }
}
