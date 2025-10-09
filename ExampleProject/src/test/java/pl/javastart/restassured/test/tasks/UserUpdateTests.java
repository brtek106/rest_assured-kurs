package pl.javastart.restassured.test.tasks;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.user.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserUpdateTests extends TestBase {

    @Test
    public void givenCorrectUserDataWhenFirstNameLastNameAreUpdatedThenUserDataIsUpdatedTest() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType("application/json");
        RequestSpecification defaultSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder postResponseSpecBuilder = new ResponseSpecBuilder();
        postResponseSpecBuilder
                .expectBody("code", equalTo(200))
                .expectBody("type", equalTo("unknown"))
                .expectBody("message", equalTo("445"))
                .expectStatusCode(200);
        ResponseSpecification userCreationResponseSpecification = postResponseSpecBuilder.build();

        User user = new User();
        user.setId(445);
        user.setUsername("firstuser");
        user.setFirstName("Krzysztof");
        user.setLastName("Kowalski");
        user.setEmail("krzysztof@test.com");
        user.setPassword("password");
        user.setPhone("+123456789");
        user.setUserStatus(1);

        given().spec(defaultSpecification)
                .body(user)
                .when().post("user")
                .then().assertThat().spec(userCreationResponseSpecification);

        user.setFirstName("Marian");
        user.setLastName("Paździoch");

        given().spec(defaultSpecification)
                .pathParam("username", user.getUsername())
                .body(user)
                .when().put("user/{username}")
                .then().assertThat().spec(userCreationResponseSpecification);

        ResponseSpecBuilder updateResponseSpecBuilder = new ResponseSpecBuilder();
        updateResponseSpecBuilder
                .expectBody("id", equalTo(445))
                .expectBody("username", equalTo("firstuser"))
                .expectBody("firstName", equalTo("Marian"))
                .expectBody("lastName", equalTo("Paździoch"))
                .expectBody("email", equalTo("krzysztof@test.com"))
                .expectBody("password", equalTo("password"))
                .expectBody("phone", equalTo("+123456789"))
                .expectBody("userStatus", equalTo(1))
                .expectStatusCode(200);
        ResponseSpecification getResponseSpecification = updateResponseSpecBuilder.build();

        given().spec(defaultSpecification)
                .pathParam("username", user.getUsername())
                .when().get("user/{username}")
                .then().assertThat().spec(getResponseSpecification);
    }
}
