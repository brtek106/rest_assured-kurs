package pl.javastart.restassured.main.test.data.pet;

import pl.javastart.restassured.main.pojo.pet.Category;
import pl.javastart.restassured.main.pojo.pet.Pet;
import pl.javastart.restassured.main.pojo.pet.Tag;
import pl.javastart.restassured.main.test.data.TestDataGenerator;

import java.util.Collections;
import java.util.Random;

public class PetTestDataGenerator extends TestDataGenerator {

    public Pet generatePet() {
        PetsCategory petsCategory = randomPetCategory();
        PetsTags petsTags = randomPetTag();

        Category category = new Category();
        category.setId(petsCategory.getId());
        category.setName(petsCategory.getCategoryName());

        Tag tag = new Tag();
        tag.setId(petsTags.getId());
        tag.setName(petsTags.getTagName());

        Pet pet = new Pet();
        pet.setName(faker().gameOfThrones().character());
        pet.setId(faker().number().numberBetween(1, 9999));
        pet.setCategory(category);
        pet.setPhotoUrls(Collections.singletonList(faker().internet().url()));
        pet.setTags(Collections.singletonList(tag));

        PetStatus petStatus = randomPetStatus();
        pet.setStatus(petStatus.getStatus());

        return pet;
    }

    public PetsCategory randomPetCategory() {
        int pick = new Random().nextInt(PetsCategory.values().length);
        return PetsCategory.values()[pick];
    }

    public PetsTags randomPetTag() {
        int pick = new Random().nextInt(PetsTags.values().length);
        return PetsTags.values()[pick];
    }

    public PetStatus randomPetStatus() {
        int pick = new Random().nextInt(PetStatus.values().length);
        return PetStatus.values()[pick];
    }
}
