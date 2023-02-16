import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Pantry { // TODO: check access modifiers.
    public static final String fireStoreCollectionName = "pantries";
    private int pantryId; // Changed from id to pantryId (per UML diagram)
    private String name;
    // New added attributes
    private List<User> userList;
    private List<Stock> stocks;

    public Pantry(int id, String name) {
        this.pantryId = id;
        this.name = name;
    }

    public Pantry() {

    }

    public Pantry retrievePantry(FirebaseFirestore db) {
        //TODO: move this to the interface method for future use
        //TODO: get a user in the future

        // pass the pantry back to pantry activity
        db.collection(fireStoreCollectionName).document("PsFFlfX7gicMjIyBay5H").get();

        return null;
    }

    public int getId() {
        return pantryId;
    }

    public void setId(int id) {
        this.pantryId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // New Attribute Getters and Setters

    public List<User> getUsers() { // Name altered from getUserList() --> getUsers() (per UML diagram)
        return userList;
    }

    public void setUsers(List<User> userList) { // setUserList() --> setUsers()
        this.userList = userList;
    }

    public List<Stock> getStock() { // Name altered from getStocks() --> getStock() (per UML diagram)
        return stocks;
    }

    public void setStock(List<Stock> stocks) { // setStocks() --> setStock()
        this.stocks = stocks;
    }


}
