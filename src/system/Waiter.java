package system;

public class Waiter extends FloorStaff {

    // Member Variables
    private int tablesServed;
    private double tipsReceived;

    // Constructors
    public Waiter() {
        super();
        setWaiter(0, 0.0);
    }

    public Waiter(String firstName, String lastName, String password, char job, double hours, double overTime, int id,
                  int tablesServed, double tipsReceived){
        super(firstName, lastName, password, job, hours, overTime, id);
        setWaiter(tablesServed, tipsReceived);
    }

    // Member Methods

    public void setWaiter(int tablesServed, double tipsReceived) {
        setTablesServed(tablesServed);
        setTipsReceived(tipsReceived);
    }

    public void setTablesServed(int tablesServed) {
        this.tablesServed = tablesServed;
    }

    public int getTablesServed(){
        return tablesServed;
    }

    public void setTipsReceived(double tipsReceived) {
        this.tipsReceived = tipsReceived;
    }

    public double getTipsReceived(){
        return tipsReceived;
    }

    public String toString() {
        String str;
        str = super.toString() + String.format("%-8d %-8.2f %n", getTablesServed(), getTipsReceived());
        return str;
    }

    public static void main(String[] args) {
        Employee w1 = new Waiter("Ann", "Smith", "2455", 'W', 10.0,
                0, 104, 20, 20.00);
        System.out.println(w1.toString());

        Employee w2 = new Waiter();
        System.out.println(w2.toString());
    }//main
}
