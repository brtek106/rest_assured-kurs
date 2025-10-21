package pl.javastart.restassured.test.testng.tasks;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;
import pl.javastart.restassured.test.tasks.TestBase;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class FactoryTaskTests extends TestBase {

    private String petStatus;
    private String name;

    public FactoryTaskTests(String name, String petStatus) {
        this.name = name;
        this.petStatus = petStatus;
    }

    @BeforeClass
    public void setupConfiguration() {
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType("application/json").build();
    }

    @Test
    public void givenPetStatusAndNameWhenPetIsCreatedThenPetWithPetStatusAndNameIsAvailableTest() {
        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(155);
        pet.setCategory(category);
        pet.setName(name);
        pet.setStatus(petStatus);
        pet.setPhotoUrls(Collections.singletonList("http://photos.com/dog1.jpg"));
        pet.setTags(Collections.singletonList(tag));

        Pet actualPet = given().body(pet)
                .when().post("pet")
                .then().statusCode(200).extract().as(Pet.class);

        assertEquals(actualPet.getStatus(), pet.getStatus(), "Pet status is not as expected");
        assertEquals(actualPet.getName(), pet.getName(), "Pet name is not as expected");
    }

    @Factory
    public static Object[] testData() {
        FactoryTaskTests firstTest = new FactoryTaskTests("Burek", "available");
        FactoryTaskTests secondTest = new FactoryTaskTests("Reksio", "pending");
        FactoryTaskTests thirdTest = new FactoryTaskTests("Azor", "sold");
        return new Object[]
                {firstTest, secondTest, thirdTest};
    }
}
