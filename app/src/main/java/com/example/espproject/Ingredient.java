public class Ingredient { // TODO: check access modifiers.
    private Food food;
    private double quantity;

    public Ingredient(){
        // TODO: (verify the need for)/ edit constructor
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
