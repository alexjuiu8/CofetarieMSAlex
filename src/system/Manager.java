package system;

public final class Manager extends Employee implements Bonus{

    // Member Variables
    private double weeklyWage;
    private int numOfCustomers;
    private int numOfComplaints;

    // Constructors
    public Manager() {
        super();
        setManager(0.0, 0, 0);
    }//explicit default constructor

    public Manager(String firstName, String lastName, String password, char job)
    {
        super(firstName, lastName, password, job);
    }

    public Manager(String firstName, String lastName, String password, char job,
                   double weeklyWage, int numOfCustomers, int numOfComplaints)
    {
        super(firstName, lastName, password, job);
        setManager(weeklyWage, numOfCustomers, numOfComplaints);
    }

    // Member Methods

    public void setManager(double weeklyWage, int numOfCustomers, int numOfComplaints) {
        setWeeklyWage(weeklyWage);
        setNumOfCustomers(numOfCustomers);
        setNumOfComplaints(numOfComplaints);
    }

    public void setWeeklyWage(double weeklyWage) {
        this.weeklyWage = weeklyWage;
    }

    public double getWeeklyWage(){
        return weeklyWage;
    }

    public void setNumOfCustomers(int numOfCustomers) {
        this.numOfCustomers = numOfCustomers;
    }

    public int getNumOfCustomers(){
        return numOfCustomers;
    }

    public void setNumOfComplaints(int numOfComplaints) {
        this.numOfComplaints = numOfComplaints;
    }

    public int getNumOfComplaints(){
        return numOfComplaints;
    }

    @Override
    public double addBonus(double weeklywage, double BONUS) {
        return weeklyWage += BONUS;
    }

    @Override //Override the interface default method
    public void displayBonus(){
        System.out.println("The Bonus for a Manager is " + BONUS);
    }

    public String toString() {
        String str;
        System.out.println("\nName		    Pw      Job   Wage	   Cust	    Complaints");
        str = super.toString() + String.format("%-8.2f %-8d %-8d %n", getWeeklyWage(), getNumOfCustomers(), getNumOfComplaints());
        return str;
    }

    @Override
    public String printToFile() {
        String str;
        str = super.toString() + String.format("%-8.2f %-8d %-8d %n", getWeeklyWage(), getNumOfCustomers(), getNumOfComplaints());
        return str;
    }

    public static void main(String[] args) {
        Manager m1 = new Manager("Andrew", "Smith", "1267", 'M', 700.00, 121, 2);
        System.out.println(m1.toString());
        m1.addBonus(700.00, BONUS);
        System.out.println(m1.toString());

        m1.displayBonus();

        Manager m2 = new Manager();
        System.out.println(m2.toString());
    }
}
