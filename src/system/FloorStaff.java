package system;

public class FloorStaff extends Employee{

    //member variables
    private double empHours;
    private double empOverTime;
    private int empId;

// Constructors

    public FloorStaff() {
        super();
        setFloorStaff(0.0, 0.0, 0);
    } //explicit default constructor

    public FloorStaff(double hours, double overTime, int id){
        setFloorStaff(hours, overTime, id);
    }

    public FloorStaff(String firstName, String lastName, String password, char job, double hours, double overTime, int id){
        super(firstName, lastName, password, job);
        setFloorStaff(hours, overTime, id);
    }

// member methods

    public void setFloorStaff(double hours, double overTime, int id){
        setEmpHours(hours);
        setEmpOverTime(overTime);
        setEmpId(id);
    }

    public static FloorStaff setFloorStaffDetails() {

        double floorStaffHours, floorStaffOverTime;
        int floorStaffId;

        System.out.print("How many hours did you work today: ");
        floorStaffHours = console.nextDouble();

        System.out.print("How many hours of overtime did you work today: ");
        floorStaffOverTime = console.nextDouble();

        System.out.print("What is your employee ID: ");
        floorStaffId = console.nextInt();

        return new FloorStaff(floorStaffHours, floorStaffOverTime, floorStaffId);
    }

    public void setEmpHours(double empHours) {
        this.empHours = empHours;
    }

    public double getEmpHours(){
        return empHours;
    }

    public void setEmpOverTime(double empOverTime) {
        this.empOverTime = empOverTime;
    }

    public double getEmpOverTime(){
        return empOverTime;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getEmpId(){
        return empId;
    }


    public String toString()
    {
        String str;
        str = super.toString() + String.format("%-8.2f %-8.2f %-8d", getEmpHours(), getEmpOverTime(), getEmpId());
        return str;
    }

    //overriden abstract method
    @Override
    public String printToFile() {
        String str;
        str = super.toString() + String.format("%-8.2f %-8.2f %-8d", getEmpHours(), getEmpOverTime(), getEmpId());
        return str;
    }

    public static void main(String[] args) {
        //test data
        FloorStaff f1 = new FloorStaff("Matthew", "Sloyan", "1234", 'F', 10, 2, 101);
        System.out.println(f1.toString());
    }
}

