// Employee store ebong manage korar jonno ArrayList and List import kora hoyeche
import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabase {// EmployeeDatabase class-ta shob employee-er data manage kore
    private List<Employee> employeeList = new ArrayList<>();// Shob employee object store korar jonno initially ekta empty list create kora hocche

    public EmployeeDatabase() {
        // 7 jon emoloyee er data
        employeeList.add(new PermanentEmployee(101, "Anima Akter", "Senior/Principal Officer", "Management", 85000.0, "anima1234",
                "01711223344", "animakter@islamibank.com", "Dhaka, Bangladesh", "12-05-1985",
                "LAli Ahmed", "Morjina Begum", "1985269321456", "01-02-2010"));

        employeeList.add(new PermanentEmployee(102, "Monira Akter", "GB Officer", "General Banking", 60000.0, "monira1234",
                "01811223344", "moniraakter@islamibank.com", "Chittagong", "20-09-1990",
                "Abul Hashem", "Rokeya Begum", "1990269321457", "15-06-2015"));

        employeeList.add(new Employee(103, "Tasmin Arifa", "Cash Officer - 1", "Cash Section", 45000.0, "arifa1234",
                "01911223344", "tasminarifa@islamibank.com", "Sylhet", "10-01-1993",
                "Anwar Hossain", "Sufia Khatun", "1993269321458", "10-03-2018"));

        employeeList.add(new Employee(104, "Marium Rimi", "Cash Officer - 2", "Cash Section", 42000.0, "rimi1234",
                "01511223344", "mariumrimi@islamibank.com", "Khulna", "05-11-1995",
                "Amjad Ali", "Nasima Begum", "1995269321459", "01-01-2020"));

        employeeList.add(new Employee(105, "Mohammad Abdullah", "Customer Service", "HR & Retail", 35000.0, "abdullah1234",
                "01611223344", "mohammadabdullah@islamibank.com", "Rajshahi", "18-03-1996",
                "Fazlur Rahman", "Laila Banu", "1996269321460", "22-08-2021"));

        employeeList.add(new Employee(106, "Saifa Akter", "Office Assistant", "Operations", 22000.0, "saifa1234",
                "01311223344", "saifaakter@islamibank.com", "Barisal", "25-12-1998",
                "Sultan Khan", "Rasheda Khanam", "1998269321461", "15-05-2023"));

        employeeList.add(new Employee(107, "Abdur Rahman", "Security Guard", "Security", 18000.0, "rahaman1234",
                "01411223344", "abdurrahman@islamibank.com", "Rangpur", "01-01-1992",
                "Kari Miah", "Amina Khatun", "1992269321462", "01-12-2019"));
    }

    public void addEmployee(Employee emp) { // Notun employee database-e add korar method
        employeeList.add(emp);
    }

    public List<Employee> getAllEmployees() {// Database-er shob employee pawar method
        return employeeList;// Complete employee list return korche
    }

    public Employee searchEmployee(int id) {// ID diye specific employee search korar method
        for (Employee emp : employeeList) {// List-er prottek employee-ke ek ek kore check kora hocche
            if (emp.getId() == id) {
                return emp;// ID match korle oi employee object return korche
            }
        }
        return null;// Kono employee-er ID match na korle null return korbe
    }

    public void removeEmployee(int id) {// ID diye employee remove korar method
        employeeList.removeIf(emp -> emp.getId() == id); // Je employee-er ID user-er deya ID-er sathe match korbe,take list theke remove kora hobe
    }
}