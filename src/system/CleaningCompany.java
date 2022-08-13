package system;

public class CleaningCompany {

    //class to be composed in janitor class

    // Member Variables =====================================================
    private String cleaningCompanyName;
    private int cleaningCompanyId;

    // Constructors =====================================================
    public CleaningCompany() {
        setCleaningCompany("NoCompany", 0);
    } //explicit default constructor

    public CleaningCompany(String cleaningCompanyName, int cleaningCompanyId) {
        setCleaningCompany(cleaningCompanyName, cleaningCompanyId);
    }

    // Member Methods =====================================================

    public void setCleaningCompany(String cleaningCompanyName,int cleaningCompanyId) {
        setCleaningCompanyName(cleaningCompanyName);
        setCleaningCompanyId(cleaningCompanyId);
    }

    public void setCleaningCompanyName(String cleaningCompanyName) {
        this.cleaningCompanyName = cleaningCompanyName;
    }

    public String getCleaningCompanyName(){
        return cleaningCompanyName;
    }

    // =====================================================================

    public void setCleaningCompanyId(int cleaningCompanyId) {
        this.cleaningCompanyId = cleaningCompanyId;
    }

    public int getCleaningCompanyId(){
        return cleaningCompanyId;
    }

    // ToString method =====================================================

    public String toString() {
        String str;
        str = String.format("%-8s %-8d %n", getCleaningCompanyName(), getCleaningCompanyId());
        return str;
    }

    public static void main(String[] args) {
        CleaningCompany c2 = new CleaningCompany("SAV", 2011);
        System.out.println(c2.toString());
    }
}
