package pl.javastart.restassured.main.test.data.pet;

public enum PetsTags {
    YOUNG_PET(1, "young-pet"),
    ADULT_PET(2, "adult-pet");

    private int id;
    private String tagName;

    PetsTags(int id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public int getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }
}
