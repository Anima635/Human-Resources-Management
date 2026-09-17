public class ReportingAnalytics extends AbstractReport {
    private EmployeeDatabase db;// EmployeeDatabase-er object store korar jonno variable

    public ReportingAnalytics(EmployeeDatabase db) {
        super("IBB PLC - Daffodil Smart City Sub-Branch Performance Report");
        this.db = db;
    }

    @Override
    public void printHeader() {
        System.out.println("\n=======================================================");
        System.out.println("   ISLAMI BANK BANGLADESH PLC - DAFFODIL SMART CITY SUB-BRANCH   ");
        System.out.println("====== " + reportTitle + " ======");
        System.out.println("=======================================================");
    }

    // Eligible officer-der upor based kore total Mudaraba target calculate kore
    public double calculateTotalMudarabaTarget() {
        int eligibleOfficers = 0;
        for (Employee emp : db.getAllEmployees()) {
            if ("Investment".equalsIgnoreCase(emp.getDepartment()) || "GB".equalsIgnoreCase(emp.getDepartment()) || "General Banking".equalsIgnoreCase(emp.getDepartment())) {
                eligibleOfficers++;
            }
        }
        double targetPerOfficer = 2500000.0; // Prottek eligible officer-er jonno target 25 lakh taka dhora hoyeche
        return eligibleOfficers * targetPerOfficer;
    }

    // Employee-der average performance-er upor based kore Shariah compliance rating calculate korar method
    public double calculateShariahRating() {
        if (db.getAllEmployees().isEmpty()) return 0.0;

        double totalRating = 0.0;
        int count = 0;

        for (Employee emp : db.getAllEmployees()) {// Prottek employee-er rating calculate kora hocche
            // Employee ID-er last digit-er upor based kore, example/demo purpose-e ekta Shariah score generate kora hocche
            double employeeShariahScore = 4.0 + (emp.getId() % 10) * 0.1;
            if (employeeShariahScore > 5.0) employeeShariahScore = 5.0; // Rating maximum 5.0 er beshi hote parbe na

            totalRating += employeeShariahScore;// Employee-er rating total-er sathe add kora hocche
            count++;// Employee count 1 kore barano hocche
        }
        return totalRating / count;// Shob employee-er average rating return kora hocche
    }

    // Total staff-er upor based kore pending leave request-er number calculate kore
    public static int getPendingLeaveCount(int totalStaff) {
        // Staff 3-er kom hole kono pending leave request dhora hocche na
        if (totalStaff < 3) return 0;
        return totalStaff / 3; // Prottek 3 jon staff-er jonno 1 ta pending leave request dhora hocche
    }


    @Override
    public String generateReport() {// AbstractReport-er maddhome ekhane implement kora hocche
        // Branch code, total staff ebong total Mudaraba target, ekta String report hisebe return kora hocche
        return "Branch Code: DSC-09 | Active Sub-Branch Staff: " + db.getAllEmployees().size() +
                " | Total Mudaraba Target Assigned: BDT " + calculateTotalMudarabaTarget();
    }
}