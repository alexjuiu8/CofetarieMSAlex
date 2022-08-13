package system;

public class Janitor extends FloorStaff implements CleaningExpense{

    // Member Variables
    private int roomsCleared;
    private double cleaningCost;

    //cleaning company composition
    CleaningCompany company;

    // Constructors
    public Janitor() {
        super();
        setRoomsCleared(0);
    }

    public Janitor(String firstName, String lastName, String password, char job, double hours, double overTime, int id, int roomsCleared){
        super(firstName, lastName, password, job, hours, overTime, id);
        setRoomsCleared(roomsCleared);
    }

    //Constructors with composition
    public Janitor(String firstName, String lastName, String password, char job, double hours, double overTime, int id, int roomsCleared,
                   String cleaningCompanyName, int cleaningCompanyId)
    {
        super(firstName, lastName, password, job, hours, overTime, id);
        setRoomsCleared(roomsCleared);
        company = new CleaningCompany(cleaningCompanyName, cleaningCompanyId);
    }

    // Member Methods

    public void setRoomsCleared(int roomsCleared) {
        this.roomsCleared = roomsCleared;
    }

    public int getRoomsCleared(){
        return roomsCleared;
    }

    // Interface method

    public double getCleaningCost(){
        return cleaningCost;
    }

    @Override
    public double calculateCleaningCost(int roomsCleared, double COST_PER_ROOM) {
        return cleaningCost = getRoomsCleared() * COST_PER_ROOM;
    }

    // ToString method

    public String toString() {
        String str;
        str = super.toString() + String.format("%-8d %-8s %-8d %n", getRoomsCleared(), company.getCleaningCompanyName(), company.getCleaningCompanyId());
        return str;
    }

    public static void main(String[] args) {
        Janitor c1 = new Janitor("John", "Smith", "1345", 'C', 12.0,
                3.0, 103, 12, "Sav", 102);
        System.out.println(c1.toString());

        //c1.calculateCleaningCost(3, COST_PER_ROOM);
        //System.out.println(c1.getCleaningCost());
    }
}
