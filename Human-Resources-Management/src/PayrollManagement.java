public class PayrollManagement {// Employee-er salary calculation & payroll manage korar jonno ei class use kora hoyeche
    private Employee employee;// Je employee-er payroll calculate hobe, tar object store kora hocche
    private double medicalAllowance;
    private double houseRentAllowance;
    private double providentFundDeduction;// Provident Fund-er deduction store korar variable
    private double taxDeduction;

    // Constructor
    public PayrollManagement(Employee employee, double medicalAllowance, double houseRentAllowance) {
        this.employee = employee;
        this.medicalAllowance = medicalAllowance;
        this.houseRentAllowance = houseRentAllowance;
        calculateDeductions();
    }


    // Net Salary = Gross Salary - PF - Tax
    private void calculateDeductions() {
        double basic = employee.getBasicSalary();
        this.providentFundDeduction = basic * 0.10;

        // basic salary er upor 5% tax
        if (basic > 50000) {
            this.taxDeduction = basic * 0.05;
        } else {
            this.taxDeduction = 0.0;
        }
    }

    // (Gross Salary)
    public double calculateGrossSalary() {
        return employee.getBasicSalary() + medicalAllowance + houseRentAllowance;
    }

    // (Net Payable Salary)
    public double calculateNetSalary() {
        return calculateGrossSalary() - (providentFundDeduction + taxDeduction);
    }

    // pay slip card print
    public void printPayslipCard() {
        System.out.println("Employee ID     : " + employee.getId());
        System.out.println("Employee Name   : " + employee.getName());
        System.out.println("Designation     : " + employee.getDesignation());
        System.out.println("Department      : " + employee.getDepartment());
        System.out.println("-------------------------------------------------------");
        System.out.println("(+) Basic Salary             : BDT " + employee.getBasicSalary());
        System.out.println("(+) House Rent Allowance     : BDT " + houseRentAllowance);
        System.out.println("(+) Medical Allowance        : BDT " + medicalAllowance);
        System.out.println("-------------------------------------------------------");
        System.out.println("Gross Salary                 : BDT " + calculateGrossSalary());
        System.out.println("-------------------------------------------------------");
        System.out.println("(-) Provident Fund (10%)     : BDT " + providentFundDeduction);
        System.out.println("(-) Income Tax Deduction     : BDT " + taxDeduction);
        System.out.println("-------------------------------------------------------");
        System.out.println("👉 NET PAYABLE SALARY        : BDT " + calculateNetSalary());

        //prmanent Employee hole, Festival Bonus Status pabe
        if (employee instanceof PermanentEmployee) {//employee object-ta PermanentEmployee type er hole 1 return korbe
            PermanentEmployee pe = (PermanentEmployee) employee;//Type Casting
            System.out.println("Festival Bonus Status        : Processed (With Next Eid Payroll)");
        }
    }

    // Getters and Setters for Encapsulation
    public Employee getEmployee() { return employee; }
    public double getMedicalAllowance() { return medicalAllowance; }
    public double getHouseRentAllowance() { return houseRentAllowance; }
    public double getProvidentFundDeduction() { return providentFundDeduction; }
    public double getTaxDeduction() { return taxDeduction; }
}