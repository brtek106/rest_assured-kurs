package pl.javastart.restassured.test.problems;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class InvalidCertTest {

    @Test
    public void untrustedRootTest() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setRelaxedHTTPSValidation();
        RequestSpecification requestSpecification = requestSpecBuilder.build();
        given().spec(requestSpecification).when().get("https://untrusted-root.badssl.com/").then().statusCode(200);
    }
}
