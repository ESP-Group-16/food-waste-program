import java.util.List;

public class User {
    private int userId;
    private String userName;
    private String password;
    private List<Food> dislikes;
    private boolean hasDoneTutorial;
    private List<Food> allergies;
    private List<Recipe> favourites; // in UML diagram is listed as List<Recipes>; changed this to <Recipe>.
    private int socialCreditScore;
    private List<Ingredient> shoppingList;

    public static void signUp() { // TODO: Is this the same as login() - seen below?

    }

    public User(int userId, String username) {
        this.userId = userId;
        this.userName = username;
    }

    public User() {

    }

    // Requested Methods (from UML)

    public List<Recipe> getRecipes(){
        List<Recipe> foo = null; // TODO: exchange placeholder.
        return foo;
    }

    // getFavouriteRecipes() same functionality as 'getter' getfavourites()
    public List<Recipe> getFavouriteRecipes() {
        return favourites;
    }

    // getAllergies in New Getters and Setters

    // TODO: !! I kinda had to guess what we're returning here
    public List<String> getPreferences(){
        List<String> foo = null; // TODO: exchange placeholder.
        return foo;
    }

    public void login(){

    }

    public void logout(){

    }


    // Old Getters and Setters (before template created)

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    // New Getters and Setters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Food> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<Food> dislikes) {
        this.dislikes = dislikes;
    }

    public boolean isHasDoneTutorial() {
        return hasDoneTutorial;
    }

    public void setHasDoneTutorial(boolean hasDoneTutorial) {
        this.hasDoneTutorial = hasDoneTutorial;
    }

    public List<Food> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Food> allergies) {
        this.allergies = allergies;
    }

    // getFavourites would go here.

    public void setFavourites(List<Recipe> favourites) {
        this.favourites = favourites;
    }

    public int getSocialCreditScore() {
        return socialCreditScore;
    }

    public void setSocialCreditScore(int socialCreditScore) {
        this.socialCreditScore = socialCreditScore;
    }

    public List<Ingredient> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<Ingredient> shoppingList) {
        this.shoppingList = shoppingList;
    }
}
