public class ContractEmployee extends Employee { //Employee class-er properties and methods ei class-e use kora jabe
    // Contract employee-r contract duration store korar jonno variable
    // private use koray eta directly baire theke access kora jabe na
    private int durationMonths; //Contract employee kotomash-er jonno kaj korbe

    public ContractEmployee(int id, String name, String designation, String department, double basicSalary, String password,
                            String mobile, String email, String address, String dateOfBirth,
                            String fathersName, String mothersName, String nidNumber, String dateOfJoining, int durationMonths) {
        super(id, name, designation, department, basicSalary, password, mobile, email,address,dateOfBirth,fathersName,mothersName,nidNumber,dateOfJoining);
        this.durationMonths = durationMonths;
    }

    @Override
    public void displayDetails() { // Polymorphism
        super.displayDetails(); // Parent class-er displayDetails() method call kora hocche
        System.out.println("Employment Type: Contractual (" + durationMonths + " Months) | SHBIS Eligible: No");
    }
}