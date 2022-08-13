package system;

import java.util.Scanner;

public abstract class Employee {

    //Member Variables
    private String empFirstName;
    private String empLastName;
    private String empPassword;
    private char empjob;

    public static Scanner console = new Scanner(System.in);

// Constructors
    public Employee(){
        setEmployee("NoName", "NoName", "0000", 'N'); //default
    }

    public Employee(String firstName, String lastName, String password, char job){
        setEmployee(firstName, lastName, password, job);
    }

// Member Methods

    public void setEmployee(String firstName, String lastName, String password, char job){
        setEmpFirstName(firstName);
        setEmpLastName(lastName);
        setEmpPassword(password);
        setEmpjob(job);
    }

    public void setEmpFirstName(String empFirstName){
        this.empFirstName = empFirstName;
    }

    public String getEmpFirstName(){
        return empFirstName;
    }

    public void setEmpLastName(String empLastName){
        this.empLastName = empLastName;
    }

    public String getEmpLastName(){
        return empLastName;
    }

    public void setEmpPassword(String empPassword){
        this.empPassword = empPassword;
    }

    public String getEmpPassword(){
        return empPassword;
    }

    public void setEmpjob(char empjob){
        this.empjob = empjob;
    }

    public char getEmpjob(){
        return empjob;
    }

    public String toString()
    {
        String str;
        str = String.format("%-9s %-9s %-8s %-5c", getEmpFirstName(), getEmpLastName(), getEmpPassword(), getEmpjob());
        return str;
    }

    abstract public String printToFile();
}
