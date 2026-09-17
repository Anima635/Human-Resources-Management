public class Employee { // Employee-er basic, personal ebong financial information ekhane rakha hoyeche
    private int id;
    private String name;
    private String designation;
    private double basicSalary;
    private String password;

    private String mobile;
    private String email;
    private String address;
    private String dateOfBirth;
    private String fathersName;
    private String mothersName;
    private String nidNumber;
    private String dateOfJoining;
    private String department;

    private double accumulatedPF;
    private double activeSHBISLoan;
    private double shbisMonthlyRecovery;
    private int earnedLeaveDays;
    private double currentArrear;
    private double activeQuardAmount;
    private String quardStatus;

    public Employee(int id, String name, String designation, String department, double basicSalary, String password,
                    String mobile, String email, String address, String dateOfBirth,
                    String fathersName, String mothersName, String nidNumber, String dateOfJoining) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.basicSalary = basicSalary;
        this.password = password;

        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.fathersName = fathersName;
        this.mothersName = mothersName;
        this.nidNumber = nidNumber;
        this.dateOfJoining = dateOfJoining;

        this.accumulatedPF = (basicSalary * 0.10) * 24 * 2; // Ekhane basic salary-er 10% PF hisebe dhora hoyeche
        this.activeSHBISLoan = designation.contains("Officer") ? 2500000.0 : 0.0; //designation: "Officer" hole, SHBIS loan amount 2500000 set kora hocche
        this.shbisMonthlyRecovery = designation.contains("Officer") ? 15000.0 : 0.0; //Officer hole monthly SHBIS recovery 15000 set kora hocche
        this.earnedLeaveDays = 20; // Employee-er earned leave initially 20 days set kora hocche
        this.currentArrear = basicSalary * 0.05 * 2;// Basic salary-er 5% kore 2 period-er arrear calculate kora hocche
        this.activeQuardAmount = 0.0;// Initially kono active Quard amount nei
        this.quardStatus = "No active approved Quard found."; // Initially kono approved Quard nei
    }

    // গেটার এবং সেটার মেথডসমূহ
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDesignation() { return designation; }
    public double getBasicSalary() { return basicSalary; }
    public boolean checkPassword(String pass) { return this.password.equals(pass); }
    public String getDepartment() {
        return department;
    }

    public String getMobile() { return mobile; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getFathersName() { return fathersName; }
    public String getMothersName() { return mothersName; }
    public String getNidNumber() { return nidNumber; }
    public String getDateOfJoining() { return dateOfJoining; }

    public double getAccumulatedPF() { return accumulatedPF; }
    public void setAccumulatedPF(double accumulatedPF) { this.accumulatedPF = accumulatedPF; }
    public double getActiveSHBISLoan() { return activeSHBISLoan; }
    public void setActiveSHBISLoan(double activeSHBISLoan) { this.activeSHBISLoan = activeSHBISLoan; }
    public double getShbisMonthlyRecovery() { return shbisMonthlyRecovery; }
    public void setShbisMonthlyRecovery(double shbisMonthlyRecovery) { this.shbisMonthlyRecovery = shbisMonthlyRecovery; }
    public int getEarnedLeaveDays() { return earnedLeaveDays; }
    public void setEarnedLeaveDays(int earnedLeaveDays) { this.earnedLeaveDays = earnedLeaveDays; }
    public double getCurrentArrear() { return currentArrear; }
    public void setCurrentArrear(double currentArrear) { this.currentArrear = currentArrear; }
    public double getActiveQuardAmount() { return activeQuardAmount; }
    public void setActiveQuardAmount(double activeQuardAmount) { this.activeQuardAmount = activeQuardAmount; }
    public String getQuardStatus() { return quardStatus; }
    public void setQuardStatus(String quardStatus) { this.quardStatus = quardStatus; }

    public void applyPayFixation() { //Pay fixation apply korar method: Basic salary 5% increase kora hocche
        this.basicSalary += (this.basicSalary * 0.05);
    }

    public void displayDetails() {
        System.out.println("\n--- Employee Details ---");
        System.out.println("ID: " + id + " | Name: " + name);
        System.out.println("Designation: " + designation + " | Department: " + department);
        System.out.println("Mobile: " + mobile + " | Email: " + email);
        System.out.println("NID: " + nidNumber + " | Joining Date: " + dateOfJoining);
    }
}