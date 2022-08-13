package system;

public class Chef extends FloorStaff {

    // Member Variables
    private int mainsServed;
    private int desertServed;
    private int dishesSentBack;

    // Constructors

    public Chef() {
        super();
        setChef(0, 0, 0);
    }

    public Chef(double hours, double overTime, int id) {
        super(hours, overTime, id);
        setChef(mainsServed, desertServed, dishesSentBack);
    }

    public Chef(String firstName, String lastName, String password, char job, double hours, double overTime, int id,
                int mainsServed, int desertServed, int dishesSentBack)
    {
        super(firstName, lastName, password, job, hours, overTime, id);
        setChef(mainsServed, desertServed, dishesSentBack);
    }

    public void setChef(int mainsServed, int desertServed, int dishesSentBack) {
        setMainsServed(mainsServed);
        setDesertServed(desertServed);
        setDishesSentBack(dishesSentBack);
    }

    public void setMainsServed(int mainsServed) {
        this.mainsServed = mainsServed;
    }

    public int getMainsServed(){
        return mainsServed;
    }

    public void setDesertServed(int mainsServed) {
        this.mainsServed = mainsServed;
    }

    public int getDesertServed(){
        return mainsServed;
    }

    public void setDishesSentBack(int dishesSentBack) {
        this.dishesSentBack = dishesSentBack;
    }

    public int getDishesSentBack(){
        return dishesSentBack;
    }

    public String toString() {
        String str;
        str = super.toString() + String.format("%-8d %-8d %-8d %n", getMainsServed(), getDesertServed(), getDishesSentBack());
        return str;
    }

    public static void main(String[] args) {
        Employee c1 = new Chef("Ann", "Smith", "2455", 'C', 10.0, 0, 104, 2, 20, 4);
        System.out.println(c1.toString());

        Chef c2 = new Chef();
        System.out.println(c2.toString());
    }
}

