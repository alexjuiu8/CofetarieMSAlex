package system;


public class BarTender extends FloorStaff {

    // Member Variables
    private int drinksServed;
    private int kegsUsed;
    private char tapsCleaned;

    // Constructors

    public BarTender() {
        super();
        setBarTender(0, 0, 'N');
    }

    public BarTender(String firstName, String lastName, String password, char job, double hours, double overTime, int id,
                     int drinksServed, int kegsUsed, char tapsCleaned)
    {
        super(firstName, lastName, password, job, hours, overTime, id);
        setBarTender(drinksServed, kegsUsed, tapsCleaned);
    }


    public void setBarTender(int drinksServed, int kegsUsed, char tapsCleaned) {
        setDrinksServed(drinksServed);
        setKegsUsed(kegsUsed);
        setTapsCleaned(tapsCleaned);
    }

    public void setDrinksServed(int drinksServed) {
        this.drinksServed = drinksServed;
    }

    public int getDrinksServed(){
        return drinksServed;
    }


    public void setKegsUsed(int kegsUsed) {
        this.kegsUsed = kegsUsed;
    }

    public int getKegsUsed(){
        return kegsUsed;
    }


    public void setTapsCleaned(char tapsCleaned) {
        this.tapsCleaned = tapsCleaned;
    }

    public char getTapsCleaned(){
        return tapsCleaned;
    }


    public String toString() {
        String str;
        str = super.toString() + String.format("%-8d %-8d %-6c %n", getDrinksServed(), getKegsUsed(), getTapsCleaned());
        return str;
    }

    public static void main(String[] args) {
        //test data
        Employee b1 = new BarTender("John", "Smith", "1345", 'B', 12.0,
                3.0, 102, 12, 2, 'Y');
        System.out.println(b1.toString());

        BarTender b2 = new BarTender();
        System.out.println(b2.toString());
    }

}

