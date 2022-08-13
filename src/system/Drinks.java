package system;

public class Drinks {

    // Member Variables
    private String drinkName;
    private double drinkCost;


    public Drinks(String drinkName, double drinkCost) {
        setDrinkName(drinkName);
        setDrinkCost(drinkCost);
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkName(){
        return drinkName;
    }

    public void setDrinkCost(double drinkCost) {
        this.drinkCost = drinkCost;
    }

    public double getDrinkCost(){
        return drinkCost;
    }
}

