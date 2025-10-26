package pl.javastart.restassured.main.test.data.pet;

public enum PetsCategory {
    DOGS(1, "Dogs"),
    CATS(2, "Cats"),
    OTHER(3, "Other");

    private int id;
    private String categoryName;

    PetsCategory(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getId() {
        return id;
    }
}
