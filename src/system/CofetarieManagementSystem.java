package system;

import java.io.*;
import java.util.*;

public class CofetarieManagementSystem implements CleaningExpense, Bonus{

    public static Scanner console = new Scanner(System.in);

    public static void main(String[] args) throws IOException
    {
        final char SENTINEL = 'Q'; //sentinel to exit overall system


        String loginFirstName;
        String loginLastName;
        String loginPassword;
        char loginJob;


        int choice;
        String firstName, lastName, password, findFirstName, findLastName, findPassword;
        char job;
        boolean found;

        choice = showLoginSignUp(); // Initial read to show menu

        while (choice != SENTINEL) // Continue until Q/Exit sentinel encountered
        {
            switch (choice)
            {
                case 'L':
                    //reads in login file created in create selection
                    Scanner inFileLogin = new Scanner(new FileReader("login.dat"));

                    System.out.println("\nEmployee Login ======================\n");

                    //read data to create employee login
                    System.out.println("Please enter your First Name:");
                    findFirstName = console.next();

                    System.out.println("Please enter your Last Name:");
                    findLastName = console.next();

                    System.out.println("Please enter your password:");
                    findPassword = console.next();

                    found = false;

                    // Main while loop file Input until EOF
                    while (inFileLogin.hasNext())
                    {
                        loginFirstName = inFileLogin.next();
                        loginLastName = inFileLogin.next();
                        loginPassword = inFileLogin.next();
                        loginJob = inFileLogin.next().charAt(0);

                        if (findFirstName.equalsIgnoreCase(loginFirstName) && loginLastName.equalsIgnoreCase(findLastName)
                                &&  findPassword.equalsIgnoreCase(loginPassword)) //checks if variables matches file
                        {
                            System.out.println("\nHello " + findFirstName); //greeting message
                            found = true;

                            switch (loginJob)
                            {
                                case 'M':
                                    managerSelection(loginFirstName, loginLastName, loginPassword, loginJob); //calls method based on job selected in create
                                    break;
                                case 'B':
                                    bartenderSelection(loginFirstName, loginLastName, loginPassword, loginJob);
                                    break;
                                case 'C':
                                    chefSelection(loginFirstName, loginLastName, loginPassword, loginJob);
                                    break;
                                case 'J':
                                    janitorSelection(loginFirstName, loginLastName, loginPassword, loginJob);
                                    break;
                                case 'W':
                                    waiterSelection(loginFirstName, loginLastName, loginPassword, loginJob);
                                    break;
                                default:
                                    System.out.println("Invalid job\n");
                            } // job selection switch
                        }
                    }

                    if (found == false) {
                        System.out.println("Employee not found, please try again.");
                    }

                    inFileLogin.close();
                    break;
                case 'C':
                    //create employee section

                    System.out.println("\nCreate Employee Login Details ======================\n");

                    //read data to create employee login
                    System.out.println("What is your first name?");
                    firstName = console.next();

                    System.out.println("What is your last name?");
                    lastName = console.next();

                    do
                    {
                        System.out.println("Set your password (Must be four characters or more): ");
                        password = console.next();

                    }while (!(password.length() >= 4));//checks to see if password more than 4 characters

                    do
                    {
                        System.out.println("What job positon do you hold: Manager(M), Bartender(B), Chef(C), Janitor(J) or Waiter(W)");
                        job = console.next().charAt(0);
                        job = Character.toUpperCase(job);
                    }while (!(job == 'M')&&(!(job == 'B'))&&(!(job == 'C'))&&(!(job == 'J'))&&(!(job == 'W')));


                    Employee e1 = new Employee(firstName, lastName, password, job) {
                        @Override
                        public String printToFile() {
                            String str;
                            str = String.format("%-9s %-9s %-8s %-5c %n", getEmpFirstName(), getEmpLastName(), getEmpPassword(), getEmpjob());
                            return str;
                        }
                    };

                    //show entered data to user
                    System.out.println("\nYour details:\n" + e1.toString());

                    //writes and append login file
                    Writer createOutput;
                    createOutput = new BufferedWriter(new FileWriter("login.dat", true));
                    createOutput.append(e1.printToFile());
                    createOutput.close();
                    break;
                default:
                    System.out.println("Invalid choice, Must be either L, C or Q)\n" );
            }

            System.out.println();
            choice = showLoginSignUp();
        }

    }


    public static char showLoginSignUp()
    {
        char choice;

        System.out.println("====================  Restaurant Managment System  ======================");
        System.out.println("\nPlease enter 'L' if you wish to login, 'C' if you wish to create login details or 'Q' to Quit\n");

        System.out.print("Enter option: ");
        choice = console.next().charAt(0);
        choice = Character.toUpperCase(choice);
        return choice;
    }


    public static int showManagerMenu()
    {
        int menuInput;

        //Manager menu options
        do
        {
            System.out.println("Options Menu ========================");
            System.out.println(" 1: Enter Weekly Wage			2: Enter number of customers today");
            System.out.println(" 3: Enter number of complaints		4: Add Bonus");
            System.out.println(" 5: View Manager details		6: View all employee details		0: Exit");
            menuInput = console.nextInt();

            if (menuInput > 6 || menuInput < 0) {
                System.out.println("Invalid Selction, please try again");
            }
        }while (!(menuInput == 0)&&!(menuInput == 1)&&!(menuInput == 2)&&(!(menuInput == 3))&&
                (!(menuInput == 4))&&(!(menuInput == 5)&&(!(menuInput == 6))));

        return menuInput;
    }

    // job specific selection methods: manager, chef etc.

    public static void managerSelection(String loginFirstName, String loginLastName, String loginPassword, char loginJob) throws FileNotFoundException
    {

        PrintWriter outFileM = new PrintWriter("manager.dat");
        PrintWriter outFileMC = new PrintWriter("complaints.dat");

        final int MENU_SENTINEL = 0;

        //manager variables
        double weeklyWage = 0;
        int numOfCustomers, numOfComplaints, menuInput;
        char bonusInput;
        String complaint, allComplaints = "";
        boolean bonusReceived = false;

        //create a new manager object using passed login details
        Manager m1 = new Manager(loginFirstName, loginLastName, loginPassword, loginJob);

        System.out.println("\nManager System ======================\n");

        menuInput = showManagerMenu();

        while (menuInput != MENU_SENTINEL) // Continue until 0/Exit sentinel encountered
        {
            switch (menuInput)
            {
                case 1:
                    System.out.println("What is your weekly wage: ");
                    weeklyWage = console.nextDouble();

                    if (bonusReceived == true) { //if bonus has been already set add to wage
                        weeklyWage += BONUS;
                        bonusReceived = false;
                    }
                    m1.setWeeklyWage(weeklyWage);
                    break;
                case 2:
                    System.out.print("How many customers today: ");
                    numOfCustomers = console.nextInt();
                    m1.setNumOfCustomers(numOfCustomers);
                    break;
                case 3:
                    System.out.print("How many complaints received today: ");
                    numOfComplaints = console.nextInt();
                    console.nextLine();

                    for(int i = 1; i <= numOfComplaints; i++) {
                        System.out.println("Log complaint: " + i);

                        complaint = console.nextLine();

                        allComplaints += complaint + "\n";
                    }

                    m1.setNumOfComplaints(numOfComplaints);

                    outFileMC.println(allComplaints);
                    outFileMC.close();
                    break;
                case 4:
                    do
                    {
                        System.out.print("Did you recieve a bonus this week (Yes(Y)/No(N)) ");
                        bonusInput = console.next().charAt(0);
                        bonusInput = Character.toUpperCase(bonusInput); //allows for lower/uppercase input
                    }while (!(bonusInput == 'Y')&&(!(bonusInput == 'N')));//checks for correct input Y/N

                    //exception if weekly wage is 0
                    try {
                        if (m1.getWeeklyWage() == 0)
                        {
                            throw new BonusException("\nWeekly wage is 0, please enter weekly wage first."); //throws bonus exception if wage is 0
                        }

                        if (bonusInput == 'Y') {
                            //add bonus to weekly wage
                            System.out.println("\nYour total weekly wage with the added bonus of " + BONUS + " is: " + m1.addBonus(weeklyWage, BONUS) + "\n");
                            bonusReceived = true;
                        }
                    }
                    catch (BonusException e) {
                        System.err.println(e.getMessage()); //print out exception message
                        System.out.println();
                    }
                    break;
                case 5:
                    System.out.println(m1.toString() + "\n"); //print out manager details
                    break;
                case 6:
                    viewAllEmployees(); //view all employees
                    break;
                default:
                    System.out.println("Invalid input\n");
            } // job selection switch

            menuInput = showManagerMenu(); //subsequent read
        } //sentinel while to exit when 0 encountered

        //print manager data to file
        outFileM.print (m1.getClass().getName() + "  ");
        outFileM.println (m1.printToFile());
        outFileM.close();
    }


    public static void bartenderSelection(String loginFirstName, String loginLastName, String loginPassword, char loginJob) throws FileNotFoundException
    {
        //print Bartender employee file
        PrintWriter outFileB = new PrintWriter("barTender.dat");

        //bartender variables
        double floorStaffHours, floorStaffOverTime, drinkCost = 0;
        int floorStaffId, barTenderDrinksServed, barTenderKegsUsed, menuInput;
        char barTenderTapsCleaned;
        String drinkName = "";

        System.out.println("\nBartender System ======================\n");

        //get and enter employee floorstaff data
        FloorStaff f1 = FloorStaff.setFloorStaffDetails();
        floorStaffHours = f1.getEmpHours();
        floorStaffOverTime = f1.getEmpOverTime();
        floorStaffId = f1.getEmpId();

        //input bartender specific elements
        System.out.print("How many drinks served today: ");
        barTenderDrinksServed = console.nextInt();

        //menu system to log drinks sold if greater than 0
        if (barTenderDrinksServed > 0)
        {
            ArrayList<Drinks> drinksList = new ArrayList<>();

            for(int i = 1; i <= barTenderDrinksServed; i++)
            {
                do
                {
                    System.out.println("Add Drink " + i + " ========================");
                    System.out.println(" 1: Spirits	   2: Beers");
                    System.out.println(" 3: Cocktails	   4: Soft Drinks");
                    menuInput = console.nextInt();

                    if (menuInput > 4 || menuInput < 0) {
                        System.out.println("Invalid Selection, please try again");
                    }
                }while (!(menuInput == 0)&&!(menuInput == 1)&&!(menuInput == 2)&&(!(menuInput == 3))&&(!(menuInput == 4)));

                //sets drink values depending on input
                switch (menuInput)
                {
                    case 1:
                        drinkName = "Spirit";
                        drinkCost = 8.00;
                        break;
                    case 2:
                        drinkName = "Beer";
                        drinkCost = 5.50;
                        break;
                    case 3:
                        drinkName = "Cocktail";
                        drinkCost = 10.00;
                        break;
                    case 4:
                        drinkName = "SoftDrink";
                        drinkCost = 2.50;
                        break;
                    default:
                        System.out.println("Invalid drink\n");
                }

                drinksList.add(new Drinks(drinkName, drinkCost)); //add drink details to array list using Drinks object
            }

            for(Drinks list : drinksList){
                try {
                    Writer drinksOutput = new BufferedWriter(new FileWriter("drinks.dat", true));
                    drinksOutput.append(list.getDrinkName() + " " + list.getDrinkCost() + "\n");
                    drinksOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.print("\nHow many kegs used today: ");
        barTenderKegsUsed = console.nextInt();

        do
        {
            System.out.print("Were the bar taps cleaned today? (Yes(Y)/No(N)) ");
            barTenderTapsCleaned = console.next().charAt(0);
            barTenderTapsCleaned = Character.toUpperCase(barTenderTapsCleaned);
        }while (!(barTenderTapsCleaned == 'Y')&&(!(barTenderTapsCleaned == 'N')));//checks for correct input

        //call method to calculate staff wages
        calculateFloorStaffWages(floorStaffHours, floorStaffOverTime);

        //create bartender object using data and save to file
        BarTender b1 = new BarTender(loginFirstName, loginLastName, loginPassword, loginJob, floorStaffHours, floorStaffOverTime,
                floorStaffId, barTenderDrinksServed, barTenderKegsUsed, barTenderTapsCleaned);

        //show data input to user
        System.out.println("\nYour details:\n" + b1.toString());

        outFileB.print (b1.getClass().getName() + "   ");
        outFileB.println (b1.toString());
        outFileB.close();

        //print and append data to all employees file
        try {
            Writer bartenderOutput = new BufferedWriter(new FileWriter("allEmployees.dat", true));
            bartenderOutput.append(b1.toString());
            bartenderOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void chefSelection(String loginFirstName, String loginLastName, String loginPassword, char loginJob) throws FileNotFoundException
    {
        //print Chef employee file
        PrintWriter outFileC = new PrintWriter("chef.dat");

        double floorStaffHours, floorStaffOverTime;
        int floorStaffId, mainsServed, desertServed, dishesSentBack;

        System.out.println("\nChef System ======================\n");

        //get and enter employee Floorstaff data
        FloorStaff f1 = FloorStaff.setFloorStaffDetails();
        floorStaffHours = f1.getEmpHours();
        floorStaffOverTime = f1.getEmpOverTime();
        floorStaffId = f1.getEmpId();

        //input chef specific elements
        System.out.print("How many mains served today: ");
        mainsServed = console.nextInt();

        System.out.print("How many deserts served: ");
        desertServed = console.nextInt();

        System.out.print("How many dishes sent back: ");
        dishesSentBack = console.nextInt();

        //call method to calculate staff wages
        calculateFloorStaffWages(floorStaffHours, floorStaffOverTime);

        //create chef object using data and save to file
        Chef c1 = new Chef(loginFirstName, loginLastName, loginPassword, loginJob, floorStaffHours, floorStaffOverTime,
                floorStaffId, mainsServed, desertServed, dishesSentBack);

        //show data input to user
        System.out.println("\nYour details:\n" + c1.toString());

        outFileC.print (c1.getClass().getName() + "   ");
        outFileC.println (c1.toString());
        outFileC.close();

        //print and append data to all employees file
        try {
            Writer chefOutput = new BufferedWriter(new FileWriter("allEmployees.dat", true));
            chefOutput.append(c1.toString());
            chefOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void janitorSelection(String loginFirstName, String loginLastName, String loginPassword, char loginJob) throws FileNotFoundException
    {
        //print Janitor employee file
        PrintWriter outFileJ = new PrintWriter("janitor.dat");

        double floorStaffHours, floorStaffOverTime;
        int floorStaffId, roomsCleared, cleaningCompanyId;
        String cleaningCompanyName;
        char cleaningCostInput;

        System.out.println("\nJanitor System ======================\n");

        //get and enter employee floorstaff data
        FloorStaff f1 = FloorStaff.setFloorStaffDetails();
        floorStaffHours = f1.getEmpHours();
        floorStaffOverTime = f1.getEmpOverTime();
        floorStaffId = f1.getEmpId();

        //input janitor specific elements
        System.out.print("How many rooms cleaned today: ");
        roomsCleared = console.nextInt();

        //composition inputs
        System.out.print("Cleaning Company name and ID: ");
        cleaningCompanyName = console.next();
        cleaningCompanyId = console.nextInt();

        //call method to calculate staff wages
        calculateFloorStaffWages(floorStaffHours, floorStaffOverTime);

        do
        {
            System.out.print("View the total cleaning cost (Yes(Y)/No(N)) ");
            cleaningCostInput = console.next().charAt(0);
            cleaningCostInput = Character.toUpperCase(cleaningCostInput);
        }while (!(cleaningCostInput == 'Y')&&(!(cleaningCostInput == 'N')));//checks for correct input


        //create janitor object using data and save to file
        Janitor j1 = new Janitor (loginFirstName, loginLastName, loginPassword, loginJob, floorStaffHours, floorStaffOverTime,
                floorStaffId, roomsCleared, cleaningCompanyName, cleaningCompanyId);

        //implemented interface method
        if (cleaningCostInput == 'Y') {
            System.out.println("The Total cleaning cost for today is " + j1.calculateCleaningCost(roomsCleared, COST) + "\n");
        }

        //show data input to user
        System.out.println("Your details:\n" + j1.toString());

        outFileJ.print (j1.getClass().getName() + "   ");
        outFileJ.println (j1.toString());
        outFileJ.close();

        //print and append data to all employees file
        try {
            Writer janitorOutput = new BufferedWriter(new FileWriter("allEmployees.dat", true));
            janitorOutput.append(j1.toString());
            janitorOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void waiterSelection(String loginFirstName, String loginLastName, String loginPassword, char loginJob) throws FileNotFoundException
    {
        //print Waiter employee file
        PrintWriter outFileW = new PrintWriter("waiter.dat");

        double floorStaffHours, floorStaffOverTime, tipsReceived;
        int floorStaffId, tablesServed;

        System.out.println("\nWaiter System ======================\n");

        //get and enter employee floorstaff data
        FloorStaff f1 = FloorStaff.setFloorStaffDetails();
        floorStaffHours = f1.getEmpHours();
        floorStaffOverTime = f1.getEmpOverTime();
        floorStaffId = f1.getEmpId();

        //input bartender specific elements
        System.out.print("How many tables served today: ");
        tablesServed = console.nextInt();

        System.out.print("How much in tips recieved today: ");
        tipsReceived = console.nextDouble();

        //call method to calculate staff wages
        calculateFloorStaffWages(floorStaffHours, floorStaffOverTime);

        //create bartender object using data and save to file
        Waiter w1 = new Waiter(loginFirstName, loginLastName, loginPassword, loginJob, floorStaffHours, floorStaffOverTime,
                floorStaffId, tablesServed, tipsReceived);

        //show data input to user
        System.out.println("\nYour details:\n" + w1.toString());

        outFileW.print (w1.getClass().getName() + "   ");
        outFileW.println (w1.toString());
        outFileW.close();

        //print and append data to all employees file
        try {
            Writer waiterOutput = new BufferedWriter(new FileWriter("allEmployees.dat", true));
            waiterOutput.append(w1.toString());
            waiterOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //class specific methods for manager (viewAllEmployees) and FloorStaff (calculateWages)

    public static void viewAllEmployees() throws FileNotFoundException
    {
        // Record Layout to input from employee file
        String empFirstName;
        String empLastName;
        String empPassword;
        char empJob;
        double floorStaffHours;
        double floorStaffOverTime;
        int floorStaffId;

        Scanner inFileEmp = new Scanner(new FileReader("allEmployees.dat")); //read in employee file

        System.out.println("\nName		    Pw      Job    Details");

        while (inFileEmp.hasNext())
        {
            empFirstName = inFileEmp.next();
            empLastName = inFileEmp.next();
            empPassword = inFileEmp.next();
            empJob = inFileEmp.next().charAt(0);
            floorStaffHours = inFileEmp.nextDouble();
            floorStaffOverTime = inFileEmp.nextDouble();
            floorStaffId = inFileEmp.nextInt();

            StringBuffer str = new StringBuffer(); //string buffer

            str.append(String.format("%-9s %-9s %-8s %-5c %-8.2f %-8.2f %-8d", empFirstName, empLastName, empPassword, empJob, floorStaffHours,
                    floorStaffOverTime, floorStaffId));

            //file input based on job
            switch (empJob)
            {
                case 'B':
                    int bDrinksServed = inFileEmp.nextInt();
                    int bKegsUsed = inFileEmp.nextInt();
                    char bTapsCleaned = inFileEmp.next().charAt(0);
                    str.append(String.format("%-8d %-8d %-6c", bDrinksServed, bKegsUsed, bTapsCleaned)); //add to string buffer
                    break;
                case 'C':
                    int cMainsServed = inFileEmp.nextInt();
                    int cDesertServed = inFileEmp.nextInt();
                    int cDishesSentBack = inFileEmp.nextInt();
                    str.append(String.format("%-8d %-8d %-8d", cMainsServed, cDesertServed, cDishesSentBack));
                    break;
                case 'J':
                    int jRoomsCleared = inFileEmp.nextInt();
                    String cleaningCompanyName = inFileEmp.next();
                    int cleaningCompanyId = inFileEmp.nextInt();
                    str.append(String.format("%-8d %-8s %-8d", jRoomsCleared, cleaningCompanyName,cleaningCompanyId));
                    break;
                case 'W':
                    int wTablesServed = inFileEmp.nextInt();
                    double wTipsReceived = inFileEmp.nextDouble();
                    str.append(String.format("%-8d %-8.2f", wTablesServed, wTipsReceived));
                    break;
                default:
                    System.out.println("Invalid job\n");
            } // job selection switch

            System.out.println(str); //print out string buffer to user
        }
        System.out.println();
        inFileEmp.close();
    }


    public static void calculateFloorStaffWages(double hours, double overTime)
    {
        double totWage;
        char wagesInput = 0;

        do
        {
            System.out.print("Would you like to view your total wage for today? (Yes(Y)/No(N)) ");
            wagesInput = console.next().charAt(0);
            wagesInput = Character.toUpperCase(wagesInput);
        }while (!(wagesInput == 'Y')&&(!(wagesInput == 'N')));//checks for correct input

        if(wagesInput == 'Y') {
            WageCalculation wages = (empHours, empOverTime) ->
            {
                double totalWage;
                if(overTime > 0) {
                    totalWage = (hours * 10.00) + (overTime * 15.00); //time and a half for overtime
                }
                else {
                    totalWage = hours * 10.00;
                }
                return totalWage;
            };


            totWage = wages.calculateWage(hours, overTime);
            System.out.print("Your wage received today is: " + totWage + "\n");
        }
    }


    @Override
    public double calculateCleaningCost(int roomsCleared, double COST_PER_ROOM) {
        double cleaningCost;
        cleaningCost = roomsCleared * COST_PER_ROOM;
        return cleaningCost;
    }

    //add bonus to manager
    @Override
    public double addBonus(double weeklyWage, double BONUS) {
        weeklyWage += BONUS;
        return weeklyWage;
    }
}
