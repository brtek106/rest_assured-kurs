package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCreationTests {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {
        given().log().all().contentType("application/json").body(
        """
          {
           "id": 445,
           "username": "firstuser",
           "firstName": "Krzysztof",
           "lastName": "Kowalski",
           "email": "krzysztof@test.com",
           "password": "password",
           "phone": "+123456789",
           "userStatus": 1
         }
         """)
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/user")
                .then().log().all().statusCode(200);

        given().log().all().pathParam("username", "firstuser")
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}")
                .then().log().all().statusCode(200);
    }
}
