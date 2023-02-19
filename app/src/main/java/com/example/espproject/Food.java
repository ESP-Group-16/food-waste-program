import java.util.Dictionary;
import java.util.ArrayList;

public class Food { // TODO: check access modifiers.
    private int foodId;
    private String name;
    private ArrayList<String> dietaryInfo;
    private int unit;
    private Dictionary<String, Integer> nutritional_info;
    private double carbonFootprint;
    private StorageLocation storageLocation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDietaryInfo() {
        return dietaryInfo;
    }

    public void setDietaryInfo(ArrayList<String> dietaryInfo) {
        this.dietaryInfo = dietaryInfo;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public Dictionary<String, Integer> getNutritional_info() {
        return nutritional_info;
    }

    public void setNutritional_info(Dictionary<String, Integer> nutritional_info) {
        this.nutritional_info = nutritional_info;
    }

    public double getCarbonFootprint() {
        return carbonFootprint;
    }

    public void setCarbonFootprint(double carbonFootprint) {
        this.carbonFootprint = carbonFootprint;
    }

    public StorageLocation getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(StorageLocation storageLocation) {
        this.storageLocation = storageLocation;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public Food(){
        // TODO: (verify the need for)/ edit constructor
    }
}
