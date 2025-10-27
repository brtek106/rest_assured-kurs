package pl.javastart.restassured.tests.pet;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.javastart.restassured.main.pojo.ApiResponse;
import pl.javastart.restassured.main.pojo.pet.Category;
import pl.javastart.restassured.main.pojo.pet.Pet;
import pl.javastart.restassured.main.pojo.pet.Tag;
import pl.javastart.restassured.main.properties.EnvironmentConfig;
import pl.javastart.restassured.main.test.data.pet.PetTestDataGenerator;
import pl.javastart.restassured.tests.testbases.SuiteTestBase;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatePetTests extends SuiteTestBase {

    private Pet pet;

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        pet = new PetTestDataGenerator().generatePet();

        Pet actualPet = given().body(pet).contentType("application/json")
                .when().post("pet")
                .then().statusCode(200).extract().as(Pet.class);

        assertEquals(actualPet.getId(), pet.getId(), "Pet id");
        assertEquals(actualPet.getName(), pet.getName(), "Pet name");
    }

    @AfterMethod
    public void cleanUpAfterTest() {
        ApiResponse apiResponse = given().contentType("application/json")
                .when().delete("pet/{petId}", pet.getId())
                .then().statusCode(200).extract().as(ApiResponse.class);

        assertEquals(apiResponse.getCode(), Integer.valueOf(200), "Code");
        assertEquals(apiResponse.getType(), "unknown", "Type");
        assertEquals(apiResponse.getMessage(), pet.getId().toString(), "Pet id");
    }
}
