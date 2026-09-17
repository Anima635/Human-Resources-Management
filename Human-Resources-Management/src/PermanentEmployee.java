// Inheritance implementation
public class PermanentEmployee extends Employee {
    private double bonus;// Permanent employee-r bonus amount store korar jonno variable

    public PermanentEmployee(int id, String name, String designation, String department, double basicSalary, String password,
                             String mobile, String email, String address, String dateOfBirth,
                             String fathersName, String mothersName, String nidNumber, String dateOfJoining) {
        super(id, name, designation, department, basicSalary, password, mobile, email, address, dateOfBirth, fathersName, mothersName, nidNumber, dateOfJoining);
        this.bonus = 0.0;
    }

    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    @Override
    public void displayDetails() { // Polymorphism
        super.displayDetails();
        System.out.println("Employment Type: Permanent | SHBIS Eligible: Yes");// Permanent employee SHBIS-er jonno eligible
    }
}