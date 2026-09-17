import java.util.Scanner;

public class Main {
    private static EmployeeDatabase db = new EmployeeDatabase();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int empid = getIntInput("Enter Employee ID: ");
        System.out.print("Enter Password: ");
        String pass = in.nextLine();

        Employee emp = db.searchEmployee(empid);
        if (emp != null && emp.checkPassword(pass)) {
            printHomeScreenMenu();
            int choice;
            while (true) {
                choice = getIntInput("Select your Option from Home Screen: ");

                switch (choice) {
                    case 1:
                        handleOperationalMenu();
                        break;
                    case 2:
                        handleHRPanel();
                        break;
                    case 3:
                        handleZoneDivisionPanel();
                        break;
                    case 4:
                        handleBranchDepartmentPanel();
                        break;
                    case 5:
                        handleIndividualPanel();
                        break;
                    case 6:
                        handleGeneralReportHome();
                        break;
                    case 7:
                        handleLeaveSystemHome();
                        break;
                    case 8:
                        handleIncrementSystemHome();
                        break;
                    case 9:
                        handleQuardSystemHome();
                        break;
                    case 10:
                        handleSHBISAdjustment();
                        break;
                    case 11:
                        handleBenefitsSettlement();
                        break;
                    case 98:
                        printHomeScreenMenu();
                        break;
                    case 99:
                        System.out.println("Exiting Application... Thank you for using Islami Bank HRM System.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid Option! Please select a valid choice from Home Screen (1-11 or 99).");
                }
            }
        } else {
            System.out.println("Authentication Failed! Invalid ID or Password.");
        }
    }

    // ---------------- 0. Home Screen ----------------
    private static void printHomeScreenMenu() {
        System.out.println("\n=== ISLAMI BANK PLC - DAFFODIL SMART CITY SUB-BRANCH HRM ===");
        System.out.println("1. Operational Menu");
        System.out.println("2. HR Panel");
        System.out.println("3. Zone/Division Panel");
        System.out.println("4. Branch/Department Panel");
        System.out.println("5. Individual Panel");
        System.out.println("6. General Report Home");
        System.out.println("7. Leave System Home");
        System.out.println("8. Increment System Home");
        System.out.println("9. Quard System Home");
        System.out.println("10. SHBIS Adjustment");
        System.out.println("11. Benefits Settlement");
        System.out.println("98. Main Menu");
        System.out.println("99. Exit Application");
        System.out.println("=============================================================");
    }

    // ---------------- 1. Operational Menu ----------------
    private static void handleOperationalMenu() {
        System.out.println("\n--- Entering [1] Operational Menu ---");
        System.out.println("\n=== Core Operations Dashboard ===");

        int totalStaff = db.getAllEmployees().size(); // total number of employee variable a rakha holo
        System.out.println("Total Registered Employees : " + totalStaff);
        System.out.println("Today's Attendance Status  : Processed");

        // method call korar somoy totalStaff pass korbo
        System.out.println("Pending Leave Requests     : " + ReportingAnalytics.getPendingLeaveCount(totalStaff) + " Applications Pending");
    }

    // ---------------- 2. HR Panel (Admin Operations) ----------------
    private static void handleHRPanel() {
        System.out.println("\n--- Entering [2] HR Panel ---");
        System.out.print("Enter Admin/HR Password: ");
        String adminPass = in.nextLine();

        if (!adminPass.equals("admin123")) {
            System.out.println("Access Denied! Wrong HR Password.");
            return;
        }

        while (true) {
            System.out.println("\n>> HR Panel Sub-Options:");
            System.out.println("1. View All Employees"); //sob employee er information dekha jabe
            System.out.println("2. Add New Employee"); //notun employee add korbo
            System.out.println("3. Remove Employee"); // specific kono emplyee k delete kore dibo
            System.out.println("4. View Total Count"); //total number of employee
            System.out.println("5. Search Employee"); // employee k seach korbo
            System.out.println("98. Back to Home Screen"); // home screen a back korbo

            int choice = getIntInput("Select sub-option: "); // user er theke option nibo

            switch (choice) {
                case 1:
                    System.out.println("\n--- All Registered Employees ---");
                    for (Employee e : db.getAllEmployees()) {
                        System.out.println("ID: " + e.getId() + " | Name: " + e.getName() + " | Designation: " + e.getDesignation());
                    }
                    break;
                case 2:
                    handleAddNewEmployee();
                    break;
                case 3:
                    int removeId = getIntInput("Enter Employee ID to remove: ");
                    if (db.searchEmployee(removeId) != null) {
                        db.removeEmployee(removeId);
                        System.out.println("Employee removed successfully.");
                    } else {
                        System.out.println("Employee not found!");
                    }
                    break;
                case 4:
                    System.out.println("Total Employees: " + db.getAllEmployees().size());
                    break;
                case 5:
                    handleSearchEmployee();
                    break;
                case 98:
                    return;
                case 99:
                    System.out.println("Exiting Application... Thank you for using Islami Bank HRM System.");
                    System.exit(0);
                default:
                    System.out.println("Invalid Option!");
            }
        }
    }


    private static void handleSearchEmployee() {
        System.out.println("\n--- Search Employee ---");
        int searchID = getIntInput("Enter Employee ID to Search: ");

        Employee emp = db.searchEmployee(searchID);

        if (emp != null) {
            System.out.println("\n>>> Employee Found <<<");
            System.out.println("ID          : " + emp.getId());
            System.out.println("Name        : " + emp.getName());
            System.out.println("Designation : " + emp.getDesignation());
            System.out.println("Department  : " + emp.getDepartment());
            System.out.println("Mobile      : " + emp.getMobile());
            System.out.println("Basic Salary: " + emp.getBasicSalary() + " BDT");
        } else {
            System.out.println("Employee with ID " + searchID + " not found!");
        }
    }

    private static void handleAddNewEmployee() {
        System.out.println("\n--- Register New Employee ---");
        int newId = getIntInput("Enter ID: ");
        System.out.print("Enter Name: ");
        String name = in.nextLine();
        System.out.print("Enter Designation: ");
        String designation = in.nextLine();
        double basic = getDoubleInput("Enter Basic Salary: ");
        System.out.print("Set Password: ");
        String pass = in.nextLine();

        System.out.print("Enter Mobile: ");
        String mobile = in.nextLine();
        System.out.print("Enter Email: ");
        String email = in.nextLine();
        System.out.print("Enter Address: ");
        String address = in.nextLine();
        System.out.print("Enter Date of Birth (DD-MM-YYYY): ");
        String dob = in.nextLine();
        System.out.print("Enter Father's Name: ");
        String father = in.nextLine();
        System.out.print("Enter Mother's Name: ");
        String mother = in.nextLine();
        System.out.print("Enter NID Number: ");
        String nid = in.nextLine();
        System.out.print("Enter Date of Joining (DD-MM-YYYY): ");
        String doj = in.nextLine();
        System.out.print("Enter Department: ");
        String dept = in.nextLine();
        System.out.print("Enter Basic Salary: ");
        double basicSalary = in.nextDouble();

        Employee newEmp = new Employee(newId, name, designation, dept, basicSalary, pass,
                mobile, email, address, dob, father, mother, nid, doj);

        db.addEmployee(newEmp);
        System.out.println("Employee created successfully with all Profile parameters!");
    }

    // ---------------- 3. Zone/Division Panel ----------------
    private static void handleZoneDivisionPanel() {//zonal level-er bibhinno HR/business information manage kora jay
        System.out.println("\n--- Entering [3] Zone/Division Panel ---");
        System.out.println("--- 3. Zone/Division Panel (Islami Bank Zonal Office) ---");
        System.out.println("1. Zonal Investment & Mudaraba Targets");
        System.out.println("2. Shari'ah Inspection & Compliance Status");
        System.out.println("3. Zonal Employee Placement & Transfer");
        System.out.println("4. Branch Performance Dashboard under Zone");
        System.out.println("98. Home (Back to Main Screen)");
        System.out.println("99. Exit Application");
        System.out.print("Choose option: ");

        int subChoice = in.nextInt();
        in.nextLine(); // Clear buffer

        if (subChoice == 1) {// Option 1 select korle Investment & Mudaraba Target dekhabe
            System.out.println("\n--- Zonal Investment Targets ---");
            // Investment ebong GB department-er employee count korar jonno
            int investmentStaffCount = 0;// initially count 0 rakha hocche
            for (Employee emp : db.getAllEmployees()) { // database theke sob employee er upor loop chalano hocche
                if ("Investment".equalsIgnoreCase(emp.getDepartment()) || "GB".equalsIgnoreCase(emp.getDepartment())) {
                    investmentStaffCount++;
                }
            }

            // Ei object-er maddhome dynamic Mudaraba target calculate kora hobe
            ReportingAnalytics analytics = new ReportingAnalytics(db);
            double totalTarget = analytics.calculateTotalMudarabaTarget();// Total Mudaraba target calculate kora hocche

            System.out.println("Total Investment Officers in Branch: " + investmentStaffCount);// Total Investment/GB staff count display kora hocche
            System.out.printf("Allocated Mudaraba Target for this Month: BDT %.2f\n", totalTarget);// Total Mudaraba target display kora hocche

        } else if (subChoice == 2) {//Shari'ah Compliance information dekhabe
            System.out.println("\n--- Shari'ah Audit & Compliance ---");
            // Employee data theke Shari'ah compliance rating calculate korbe
            ReportingAnalytics analytics = new ReportingAnalytics(db);
            double complianceScore = analytics.calculateShariahRating();

            System.out.printf("Branch Shari'ah Compliance Rating: %.2f / 5.00\n", complianceScore);
            System.out.println("Status: Shari'ah Approved Banking Operations.");

        } else if (subChoice == 3) {//Employee Placement & Transfer section open hobe
            System.out.println("\n--- Zonal Employee Placement & Transfer ---");
            System.out.print("Enter Employee ID for Intra-Zone Transfer: ");
            int empId = in.nextInt();
            in.nextLine(); // Clear buffer

            Employee emp = db.searchEmployee(empId);
            if (emp != null) {// Employee paoa gele
                // Employee class a setDesignation  er use
                System.out.println("Employee ID " + empId + " (" + emp.getName() + ") successfully transferred to " + emp.getDesignation());
            } else {
                System.out.println("Error: Employee ID " + empId + " not found in Database.");
            }

        } else if (subChoice == 4) {
            System.out.println("\n--- Branch Performance Dashboard ---");
            // ReportingAnalytics object create kore zone report ber kora holo
            ReportingAnalytics analytics = new ReportingAnalytics(db);
            analytics.printHeader();

            String zoneReport = analytics.generateReport();
            System.out.println("Generated Zonal Report: " + zoneReport);
            System.out.println("Branch Operational Efficiency: High");

        } else if (subChoice == 98) {
            return;
        } else if (subChoice == 99) {
            System.out.println("Exiting Application... Thank you for using Islami Bank HRM System.");
            System.exit(0);
        } else {
            System.out.println("Invalid Option!");
        }
    }

    // ---------------- 4. Branch/Department Panel ----------------
    private static void handleBranchDepartmentPanel() {
        System.out.println("\n--- 4. Branch/Department Panel (Sub-Branch Level) ---");
        System.out.println("1. Attendance Report");
        System.out.println("2. Beneficiary Nomination");
        System.out.println("3. ID Card Number");
        System.out.println("4. Transfer Process Home");
        System.out.println("5. Personal Data");
        System.out.println("6. Casual Leave Report");
        System.out.println("98. Home");
        System.out.println("99. Exit");
        System.out.print("Choose option: ");

        int sub = in.nextInt();
        in.nextLine(); // Clear buffer

        if (sub == 1) {
            System.out.println("\n--- Attendance Report ---");
            System.out.println("Total Registered Employees: " + db.getAllEmployees().size());
            System.out.println("Status: All sub-branch employees are marked PRESENT today.");

        } else if (sub == 2) {
            System.out.println("\n--- Beneficiary Nomination ---");
            System.out.print("Enter Employee ID: ");
            int id = in.nextInt();
            in.nextLine(); // Clear buffer

            Employee emp = db.searchEmployee(id);
            if (emp != null) {
                System.out.print("Enter Nominee Name: ");
                String nominee = in.nextLine();
                System.out.print("Enter Relation with Employee: ");
                String relation = in.nextLine();

                System.out.println("Nominee (" + nominee + " - " + relation +
                        ") successfully updated for " + emp.getName());
            } else {
                System.out.println("Error: Employee ID " + id + " not found!");
            }

        } else if (sub == 3) {
            System.out.println("\n--- ID Card Number Verification ---");
            System.out.print("Enter Employee ID: ");
            int id = in.nextInt();
            in.nextLine(); // Clear buffer

            Employee emp = db.searchEmployee(id);
            if (emp != null) {
                System.out.println("Employee Name: " + emp.getName());
                System.out.println("Official Smart ID Card No: IBB-DSC-2026-" + emp.getId());
            } else {
                System.out.println("Error: Employee ID " + id + " not found!");
            }

        } else if (sub == 4) {
            System.out.println("\n--- Sub-Branch Transfer Process ---");
            System.out.print("Enter Employee ID for Transfer Request: ");
            int id = in.nextInt();
            in.nextLine(); // Clear buffer

            Employee emp = db.searchEmployee(id);
            if (emp != null) {
                System.out.print("Enter Target Branch Name: ");
                String targetBranch = in.nextLine();
                System.out.println("Transfer Application initiated for " + emp.getName() +
                        " to " + targetBranch + ". Sent to Zonal Office for approval.");
            } else {
                System.out.println("Error: Employee ID " + id + " not found!");
            }

        } else if (sub == 5) {
            System.out.println("\n--- Personal Data Verification ---");
            System.out.print("Enter Employee ID: ");
            int id = in.nextInt();
            in.nextLine(); // Clear buffer

            Employee emp = db.searchEmployee(id);
            if (emp != null) {
                System.out.println("ID: " + emp.getId());
                System.out.println("Name: " + emp.getName());
                System.out.println("Department: " + emp.getDepartment());
                System.out.println("Designation: " + emp.getDesignation());
                System.out.println("Basic Salary: BDT " + emp.getBasicSalary());
            } else {
                System.out.println("Error: Employee ID " + id + " not found!");
            }

        } else if (sub == 6) {
            System.out.println("\n--- Casual Leave (CL) Report ---");
            int totalStaff = db.getAllEmployees().size();
            int pendingLeaves = ReportingAnalytics.getPendingLeaveCount(totalStaff);

            System.out.println("Total Branch Staff: " + totalStaff);
            System.out.println("Pending Casual Leave Requests: " + pendingLeaves);
            System.out.println("Status: Departmental Leave Balance updated.");

        } else if (sub == 98) {
            return;
        } else if (sub == 99) {
            System.out.println("Exiting Application... Thank you for using Islami Bank HRM System.");
            System.exit(0);
        } else {
            System.out.println("Invalid Option!");
        }
    }
    // ---------------- 5. Individual Panel (Employee Profile View) ----------------
    private static void handleIndividualPanel() {
        System.out.println("\n--- Entering [5] Individual Panel ---");
        int id = getIntInput("Enter Employee ID: ");
        System.out.print("Enter Password: ");
        String password = in.nextLine();

        Employee emp = db.searchEmployee(id);
        if (emp != null && emp.checkPassword(password)) {
            while (true) {
                System.out.println("\n>> Welcome, " + emp.getName() + " (" + emp.getDesignation() + ")");
                System.out.println("1. View Personal Profile");
                System.out.println("2. View Financial Details (PF, Loan, Arrear)");
                System.out.println("3. Back to Home Screen");

                int choice = getIntInput("Select sub-option: ");
                if (choice == 3) break;

                switch (choice) {
                    case 1:
                        printPersonalProfile(emp);
                        break;
                    case 2:
                        printFinancialDetails(emp);
                        break;
                    default:
                        System.out.println("Invalid Option!");
                }
            }
        } else {
            System.out.println("Authentication Failed! Invalid ID or Password.");
        }
    }

    private static void printPersonalProfile(Employee emp) {
        System.out.println("\n--- [Employee Personal Profile] ---");
        System.out.println("Name            : " + emp.getName());
        System.out.println("NID Number      : " + emp.getNidNumber());
        System.out.println("Designation     : " + emp.getDesignation());
        System.out.println("Department      : " + emp.getDepartment());
        System.out.println("Date of Birth   : " + emp.getDateOfBirth());
        System.out.println("Father's Name   : " + emp.getFathersName());
        System.out.println("Mother's Name   : " + emp.getMothersName());
        System.out.println("Mobile No       : " + emp.getMobile());
        System.out.println("Email Address   : " + emp.getEmail());
        System.out.println("Present Address : " + emp.getAddress());
        System.out.println("Date of Joining : " + emp.getDateOfJoining());
        System.out.println("Basic Salary    : " + emp.getBasicSalary());
    }

    private static void printFinancialDetails(Employee emp) {
        System.out.println("\n--- [Employee Financial Statement] ---");
        System.out.println("Basic Salary             : " + emp.getBasicSalary() + " BDT");
        System.out.println("Accumulated PF Amount    : " + emp.getAccumulatedPF() + " BDT");
        System.out.println("Active SHBIS Loan        : " + emp.getActiveSHBISLoan() + " BDT");
        System.out.println("Monthly SHBIS Recovery   : " + emp.getShbisMonthlyRecovery() + " BDT");
        System.out.println("Current Arrear Amount    : " + emp.getCurrentArrear() + " BDT");
        System.out.println("Available Earned Leaves  : " + emp.getEarnedLeaveDays() + " Days");
    }

    // ---------------- 6. General Report Home ----------------
    private static void handleGeneralReportHome() {
        System.out.println("\n--- 6. General Report Home ---");
        System.out.println("1. Employee Details Report");
        System.out.println("2. Summary Report");
        System.out.println("3. PDC (Personal Data Card)");
        System.out.println("4. Increment Application Report");
        System.out.println("5. Quard System");
        System.out.println("98. Home");
        System.out.println("99. Exit");

        int sub = getIntInput("Choose option: ");

        if (sub == 1) {
            System.out.println("\n--- All Employees Details Report ---");
            for (Employee emp : db.getAllEmployees()) {
                System.out.println("ID: " + emp.getId() + " | Name: " + emp.getName() +
                        " | Dept: " + emp.getDepartment() + " | Design: " + emp.getDesignation() +
                        " | Basic: " + emp.getBasicSalary() + " BDT");
            }
        } else if (sub == 2) {
            System.out.println("\n--- System Summary Report ---");
            ReportingAnalytics analytics = new ReportingAnalytics(db);
            analytics.printHeader();
            System.out.println(analytics.generateReport());
            System.out.printf("Average Shariah Compliance Rating: %.2f / 5.00\n", analytics.calculateShariahRating());
        } else if (sub == 3) {
            System.out.println("\n--- Personal Data Card (PDC) ---");
            int id = getIntInput("Enter Employee ID: ");
            Employee emp = db.searchEmployee(id);
            if (emp != null) {
                printPersonalProfile(emp);
            } else {
                System.out.println("Employee ID " + id + " not found!");
            }
        } else if (sub == 4) {
            System.out.println("\n--- Increment Application Report ---");
            for (Employee emp : db.getAllEmployees()) {
                System.out.println("ID: " + emp.getId() + " | Name: " + emp.getName() +
                        " | Current Basic: " + emp.getBasicSalary() + " BDT");
            }
        } else if (sub == 5) {
            System.out.println("Redirecting to Quard System...");
            handleQuardSystemHome();
        } else if (sub == 98) {
            printHomeScreenMenu();
        } else if (sub == 99) {
            System.out.println("Exiting Application... Thank you for using Islami Bank HRM System.");
            System.exit(0);
        } else {
            System.out.println("Invalid Option!");
        }
    }

    // ---------------- 7. Leave System Home ----------------
    private static void handleLeaveSystemHome() {
        System.out.println("\n--- 7. Leave System Home ---");
        System.out.println("1. Apply Casual Leave");
        System.out.println("2. Remaining Leave");
        System.out.println("3. Pending Approvals");
        System.out.println("4. Attendance Register");
        System.out.println("5. Operational Menu Shortcut");
        System.out.println("98. Home (Back to Main Screen)");
        System.out.println("99. Exit Application");
        System.out.print("Choose option: ");

        int subChoice = in.nextInt();
        in.nextLine(); // Clear buffer

        if (subChoice == 1) {
            System.out.println("\n--- Casual Leave Application ---");
            System.out.print("Enter Employee ID: ");
            int empId = in.nextInt();
            in.nextLine();

            // ID চেক করা
            Employee emp = db.searchEmployee(empId);
            if (emp != null) {
                System.out.print("Enter Leave Start Date (DD-MM-YYYY): ");
                String startDate = in.nextLine();
                System.out.print("Enter Number of Days: ");
                int days = in.nextInt();
                in.nextLine();
                System.out.print("Reason for Leave: ");
                String reason = in.nextLine();

                System.out.println("\n✔ Leave Application Submitted Successfully for " + emp.getName());
                System.out.println("Status: Pending Manager's Approval.");
            } else {
                System.out.println(" Error: Employee ID not found in Daffodil Smart City Sub-Branch!");
            }

        } else if (subChoice == 2) {
            System.out.println("\n--- Remaining Leave ---");
            System.out.print("Enter Employee ID: ");
            int empId = in.nextInt();
            in.nextLine();

            Employee emp = db.searchEmployee(empId);
            if (emp != null) {
                int totalAllocated = 14;
                int leavesTaken = (emp.getId() % 5) + 2;
                int remainingBalance = totalAllocated - leavesTaken;

                System.out.println("Employee Name     : " + emp.getName());
                System.out.println("Designation       : " + emp.getDesignation());
                System.out.println("Total Casual Leave: " + totalAllocated + " Days");
                System.out.println("Leaves Consumed   : " + leavesTaken + " Days");
                System.out.println(" Remaining Leave: " + remainingBalance + " Days");
            } else {
                System.out.println("Error: Employee ID not found!");
            }

        } else if (subChoice == 3) {
            System.out.println("\n--- Pending Approvals Panel (Manager Only) ---");
            int pendingCount = ReportingAnalytics.getPendingLeaveCount(db.getAllEmployees().size());

            if (pendingCount > 0) {
                System.out.println("Found " + pendingCount + " pending leave application(s) for this Sub-Branch.");
                System.out.println("------------------------------------------------------------------");
                System.out.println("Application 01: Employee ID 103 (Tariqul Islam)");
                System.out.println("Requested: 3 Days (Sick Leave) | Applied On: 19-07-2026");
                System.out.print("Do you want to approve this application? (yes/no): ");
                String decision = in.nextLine();

                if ("yes".equalsIgnoreCase(decision)) {
                    System.out.println("✔ Application APPROVED. Notification sent to employee.");
                } else {
                    System.out.println("Application REJECTED/HELD.");
                }
            } else {
                System.out.println("No pending leave applications found at this moment.");
            }

        } else if (subChoice == 4) {
            System.out.println("\n--- Attendance Register Log ---");
            System.out.print("Enter Employee ID: ");
            int empId = in.nextInt();
            in.nextLine();

            Employee emp = db.searchEmployee(empId);
            if (emp != null) {
                System.out.println("Fetching attendance ledger from system...");
                System.out.println("------------------------------------------------------------------");
                System.out.println("Date: 20-07-2026 | ID: " + emp.getId() + " | Name: " + emp.getName() + " | Status: PRESENT");
                System.out.println("Date: 19-07-2026 | ID: " + emp.getId() + " | Name: " + emp.getName() + " | Status: PRESENT");
                System.out.println("Date: 18-07-2026 | ID: " + emp.getId() + " | Name: " + emp.getName() + " | Status: WEEKEND (Friday)");
                System.out.println("------------------------------------------------------------------");
            } else {
                System.out.println("Error: Employee ID not found!");
            }

        } else if (subChoice == 5) {
            System.out.println("\n[Redirecting] Transferring you to Operational Menu...");
            handleOperationalMenu();

        } else if (subChoice == 98) {
            printHomeScreenMenu();
        } else if (subChoice == 99) {
            System.out.println("Exiting Application... Thank you for using Islami Bank HRM System.");
            System.exit(0);
        } else System.out.println("Invalid Option!");
    }

    // ---------------- 8. Increment System Home (Pay Fixation) ----------------
    private static void handleIncrementSystemHome() {
        System.out.println("\n--- 8. Increment System Home ---");
        System.out.println("1. Pay Fixation");
        System.out.println("2. Salary & Reports");
        System.out.println("3. Bonus");
        System.out.println("4. Arrear");
        System.out.println("5. Income Tax");
        System.out.println("6. Provident Fund");
        System.out.println("7. Suspense");
        System.out.println("8. Settlement Home");
        System.out.println("98. Home");
        System.out.println("99. Exit");
        System.out.print("Choose option: ");
        int sub = in.nextInt();
        in.nextLine(); // Buffer clear

        if (sub >= 1 && sub <= 7) {
            System.out.print("Enter Employee ID: ");
            int empId = in.nextInt();
            in.nextLine();

            Employee emp = db.searchEmployee(empId);
            if (emp == null) {
                System.out.println("Error: Employee ID not found in Daffodil Smart City Sub-Branch!");
            } else if (sub == 1) {
                System.out.println("\n---  Pay Fixation Ledger ---");
                double currentBasic = emp.getBasicSalary();
                double nextScaleBasic = currentBasic + (currentBasic * 0.05);
                System.out.println("Employee: " + emp.getName());
                System.out.println("Current Basic Scale: BDT " + currentBasic);
                System.out.println("Next Fixed Basic Scale: BDT " + nextScaleBasic);
                System.out.println("Status: Approved by Sub-Branch In-Charge.");

            } else if (sub == 2) {
                System.out.println("\n---  Annual Salary & Increment Report ---");
                double monthlySalary = emp.getBasicSalary();
                double yearlyGross = monthlySalary * 12;
                System.out.println("Employee: " + emp.getName() + " | " + emp.getDesignation());
                System.out.println("Current Monthly Basic: BDT " + monthlySalary);
                System.out.println("Estimated Annual Gross: BDT " + yearlyGross);

            } else if (sub == 3) {
                System.out.println("\n--- Festival Bonus (Mudaraba Deposit Reward) ---");
                double bonus = emp.getBasicSalary();
                System.out.println("Employee: " + emp.getName());
                System.out.println("Calculated Festival Bonus (25% of Basic): BDT " + bonus*.25);
                System.out.println("Disbursement Status: Ready for Wallet Transfer.");

            } else if (sub == 4) {
                System.out.println("\n---  Arrear (বকেয়া) Calculation ---");
                double monthlyIncrement = emp.getBasicSalary() * 0.05;
                double totalArrear = monthlyIncrement * 2;
                System.out.println("Employee: " + emp.getName());
                System.out.println("Pending Arrear Period: 2 Months (Due to Scale Revision)");
                System.out.println("Total Payable Arrear: BDT " + totalArrear);

            } else if (sub == 5) {
                System.out.println("\n---  Halal Income Tax Assessment ---");
                double annualGross = emp.getBasicSalary() * 12;
                double tax = 0.0;
                if (annualGross > 350000) {
                    tax = (annualGross - 350000) * 0.05;
                }
                System.out.println("Employee: " + emp.getName());
                System.out.println("Estimated Yearly Taxable Income: BDT " + annualGross);
                System.out.println("Calculated Annual Income Tax: BDT " + tax);

            } else if (sub == 6) {
                System.out.println("\n---  Provident Fund (PF) Balance ---");
                double monthlyContribution = emp.getBasicSalary() * 0.10;
                double totalAccumulatedPF = monthlyContribution * 24;
                System.out.println("Employee: " + emp.getName());
                System.out.println("Monthly Employee Contribution (10%): BDT " + monthlyContribution);
                System.out.println("Total Accumulated PF Balance (with Bank Match): BDT " + (totalAccumulatedPF * 2));

            } else if (sub == 7) {
                System.out.println("\n---  Suspense Ledger Account ---");
                System.out.println("Employee: " + emp.getName());
                System.out.println("Unreconciled Allowances / Petty Cash Audits: BDT 0.00");
                System.out.println("Status: Clean Ledger. No Suspense Balance.");
            }
        } else if (sub == 8) {
            handleBenefitsSettlement();
        } else if (sub == 98) {
            printHomeScreenMenu();
        } else if (sub == 99) {
            System.out.println("Exiting Application... Thank you for using Islami Bank HRM System.");
            System.exit(0);
        } else System.out.println("Invalid Option!");
    }

    // ---------------- 9. Quard System Home ----------------
    private static void handleQuardSystemHome() {
        System.out.println("\n--- Entering [9] Quard System Home ---");
        System.out.println("1. Apply QPF");
        System.out.println("2. Status Check");
        System.out.println("3. Withdraw Application");
        System.out.println("4. Reports");
        System.out.println("5. Management Panel");
        System.out.println("98. Home");
        System.out.println("99. Exit");
        System.out.println("---------------------------------------");

        int sub = getIntInput("Select an option from Quard System: ");

        if (sub == 1) {
            handleApplyQPF();
        } else if (sub == 2) {
            handleQuardStatusCheck();
        } else if (sub == 3) {
            handleWithdrawQuardApplication();
        } else if (sub == 4) {
            handleQuardReports();
        } else if (sub == 5) {
            handleQuardManagementPanel();
        } else if (sub == 98) {
            printHomeScreenMenu();
        } else if (sub == 99) {
            System.out.println("Exiting Application... Thank you for using Islami Bank HRM System.");
            System.exit(0);
        } else System.out.println("Invalid Option!");
    }


// ---------------- Quard System Sub-Helper Methods ----------------

    private static void handleApplyQPF() {
        System.out.println("\n--- Apply for QPF (Quard-hasan against PF) ---");
        int id = getIntInput("Enter Employee ID: ");
        Employee emp = db.searchEmployee(id);

        if (emp != null) {// Employee পাওয়া গেলে
            double maxAllowed = emp.getAccumulatedPF() * 0.80;
            System.out.println("Employee Name         : " + emp.getName());
            System.out.println("Accumulated PF Amount : " + emp.getAccumulatedPF() + " BDT");
            System.out.println("Max Eligible QPF      : " + maxAllowed + " BDT");

            double amount = getDoubleInput("Enter Requested QPF Amount: ");
            if (amount > 0 && amount <= maxAllowed) {
                emp.setQuardStatus("Applied for " + amount + " BDT (Pending Approval)");
                System.out.println("QPF Application submitted successfully for review!");
            } else {
                System.out.println("Invalid Amount! Must be greater than 0 and within Max Eligible limit.");
            }
        } else {
            System.out.println("Employee with ID " + id + " not found!");
        }
    }

    private static void handleQuardStatusCheck() {
        System.out.println("\n--- Quard Status Check ---");
        int id = getIntInput("Enter Employee ID: ");
        Employee emp = db.searchEmployee(id);

        if (emp != null) {
            System.out.println("Employee Name : " + emp.getName());
            System.out.println("Quard Status  : " + emp.getQuardStatus());
        } else {
            System.out.println("Employee with ID " + id + " not found!");
        }
    }

    private static void handleWithdrawQuardApplication() {
        System.out.println("\n--- Withdraw Quard Application ---");
        int id = getIntInput("Enter Employee ID: ");
        Employee emp = db.searchEmployee(id);

        if (emp != null) {
            if (emp.getQuardStatus().contains("Pending")) {
                emp.setQuardStatus("No active approved Quard found.");
                System.out.println("Quard Application withdrawn successfully.");
            } else {
                System.out.println("No pending application available to withdraw.");
            }
        } else {
            System.out.println("Employee with ID " + id + " not found!");
        }
    }

    private static void handleQuardReports() {
        System.out.println("\n--- Quard System Reports ---");
        System.out.println("Generating overall Quard distribution & status report...");
        for (Employee e : db.getAllEmployees()) {
            System.out.println("ID: " + e.getId() + " | Name: " + e.getName() + " | Status: " + e.getQuardStatus());
        }
    }

    private static void handleQuardManagementPanel() {
        System.out.println("\n--- Quard Management Panel (Admin Approvals) ---");
        System.out.print("Enter Admin Password: ");
        String pass = in.nextLine();

        if (!pass.equals("admin123")) {
            System.out.println("Access Denied! Incorrect Admin Password.");
            return;
        }

        int id = getIntInput("Enter Employee ID to Approve/Reject Quard: ");
        Employee emp = db.searchEmployee(id);

        if (emp != null) {
            System.out.println("Current Status: " + emp.getQuardStatus());
            System.out.println("1. Approve Application");
            System.out.println("2. Reject Application");
            int decision = getIntInput("Select Decision: ");

            if (decision == 1) {
                emp.setQuardStatus("Approved and Active");
                System.out.println("Quard status updated to Approved.");
            } else if (decision == 2) {
                emp.setQuardStatus("Application Rejected");
                System.out.println("Quard status updated to Rejected.");
            } else {
                System.out.println("Invalid selection.");
            }
        } else {
            System.out.println("Employee with ID " + id + " not found!");
        }
    }

    // ---------------- 10. SHBIS Adjustment ----------------
    private static void handleSHBISAdjustment() {
        System.out.println("\n--- 10. SHBIS Adjustment ---"); //SHBIS -> Staff House Building Investment Scheme
        System.out.println("1. View SHBIS Investment Holders (Loan Information)y");
        System.out.println("2. Adjust Monthly Recovery");
        System.out.println("98. Home | 99. Exit");
        System.out.print("Choose option: ");
        int sub = in.nextInt();
        in.nextLine(); // Buffer clear

        if (sub == 1) {
            System.out.println("\n--- SHBIS Active Investment Holders List ---");
            boolean found = false;
            for (Employee emp : db.getAllEmployees()) {
                if (emp instanceof PermanentEmployee) {
                    System.out.println("ID: " + emp.getId() + " | Name: " + emp.getName() + " | Active SHBIS Loan: BDT 25,00,000");
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No Permanent Employees found with active SHBIS accounts.");
            }

        } else if (sub == 2) {
            System.out.println("\n--- Adjust Monthly SHBIS Recovery Amount ---");
            System.out.print("Enter Employee ID: ");
            int empId = in.nextInt();
            in.nextLine();

            Employee emp = db.searchEmployee(empId);
            if (emp == null) {
                System.out.println(" Error: Employee ID not found in Daffodil Smart City Sub-Branch!");
            }

            else if (!(emp instanceof PermanentEmployee)) {
                System.out.println("Access Denied: SHBIS scheme is only applicable for Permanent Staff.");
            } else {
                double defaultRecovery = 15000.0;
                System.out.println("Current Monthly SHBIS Deduction: BDT " + defaultRecovery);
                System.out.print("Enter New Recovery Amount to Adjust: BDT ");
                double newRecovery = in.nextDouble();
                in.nextLine();

                if (newRecovery < 5000) {
                    System.out.println(" Adjustment Failed: Minimum monthly recovery cannot be less than BDT 5,000.");
                } else {
                    System.out.println("✔ Recovery successfully updated to BDT " + newRecovery + " for " + emp.getName());
                    System.out.println("Note: Changes will reflect in the upcoming month's payroll ledger.");
                }
            }
        } else if (sub == 98) {
            printHomeScreenMenu();
        } else if (sub == 99) {
            System.out.println("Exiting Application... Thank you for using Islami Bank HRM System.");
            System.exit(0);
        } else System.out.println("Invalid Option!");
    }


    // ---------------- 11. Benefits Settlement ----------------
    private static void handleBenefitsSettlement() {
        System.out.println("\n--- 11. Benefits Settlement ---");
        System.out.println("1. Final Settlement Clearance");
        System.out.println("2. Encashment of Earned Leave");
        System.out.println("3. Discharge/Resignation");
        System.out.println("4. Increment System");
        System.out.println("98. Home");
        System.out.println("99. Exit");
        System.out.print("Choose option: ");
        int sub = in.nextInt();
        in.nextLine(); // Buffer clear

        if (sub == 1 || sub == 2 || sub == 3) {
            System.out.print("Enter Employee ID: ");
            int empId = in.nextInt();
            in.nextLine();

            Employee emp = db.searchEmployee(empId);
            if (emp == null) {
                System.out.println("Error: Employee ID not found in Daffodil Smart City Sub-Branch!");
            } else if (sub == 1) {
                System.out.println("\n--- Final Settlement Clearance Card ---");
                double pfBalance = (emp.getBasicSalary() * 0.10) * 24 * 2;
                double gratuity = emp.getBasicSalary() * 1.5;
                double totalSettlement = pfBalance + gratuity + emp.getBasicSalary();

                System.out.println("Employee: " + emp.getName() + " | " + emp.getDesignation());
                System.out.println("1. Accumulated PF (with Match) : BDT " + pfBalance);
                System.out.println("2. Gratuity Benefits           : BDT " + gratuity);
                System.out.println("3. Running Month Salary        : BDT " + emp.getBasicSalary());
                System.out.println("-------------------------------------------------------");
                System.out.println("Net Payable Settlement Amount: BDT " + totalSettlement);
                System.out.println("Status: Clearance Certificate Generated.");

            } else if (sub == 2) {
                System.out.println("\n--- Encashment of Earned Leave ---");
                int earnedLeaveDays = 20;
                double perDaySalary = emp.getBasicSalary() / 30;
                double encashmentAmount = perDaySalary * earnedLeaveDays;

                System.out.println("Employee: " + emp.getName());
                System.out.println("Available Earned Leave Balance: " + earnedLeaveDays + " Days");
                System.out.println("Calculated Per Day Basic Rate : BDT " + perDaySalary);
                System.out.println(" Total Leave Encashment Value: BDT " + encashmentAmount);
                System.out.println("Status: Approved for Payroll Credit.");

            } else if (sub == 3) {
                System.out.println("\n--- Processing Discharge / Resignation ---");
                System.out.print("Are you absolutely sure you want to remove " + emp.getName() + " from the system? (yes/no): ");
                String confirm = in.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    db.removeEmployee(empId);
                    System.out.println("✔ Success: Employee ID " + empId + " has been officially discharged from Daffodil Smart City Sub-Branch.");
                } else {
                    System.out.println("Discharge process cancelled.");
                }
            }
        } else if (sub == 4) {
            handleIncrementSystemHome();
        } else if (sub == 98) {
            printHomeScreenMenu();
        } else if (sub == 99) {
            System.out.println("Exiting Application... Thank you for using Islami Bank HRM System.");
            System.exit(0);
        } else System.out.println("Invalid Option!");

    }

    // ---------------- Input Validation Utilities ----------------
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!in.hasNextInt()) {
            System.out.println("Input Error: Please enter a valid integer number!");
            in.next();
            System.out.print(prompt);
        }
        int value = in.nextInt();
        in.nextLine();
        return value;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!in.hasNextDouble()) {
            System.out.println("Input Error: Please enter a valid decimal number!");
            in.next();
            System.out.print(prompt);
        }
        double value = in.nextDouble();
        in.nextLine();
        return value;
    }
}