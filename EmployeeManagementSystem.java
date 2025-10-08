import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Employee implements Serializable {
    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary);
    }
}

public class EmployeeManagementSystem {
    private static final String FILE_NAME = "employees.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addEmployee(Scanner sc) {
        try {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Designation: ");
            String designation = sc.nextLine();
            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            Employee emp = new Employee(id, name, designation, salary);

            ArrayList<Employee> employees = readEmployeeList();
            employees.add(emp);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(employees);
            oos.close();

            System.out.println("Employee added successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayEmployees() {
        ArrayList<Employee> employees = readEmployeeList();
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }
        System.out.println("\nEmployee Records:");
        for (Employee emp : employees) {
            emp.display();
        }
    }

    private static ArrayList<Employee> readEmployeeList() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return employees;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            employees = (ArrayList<Employee>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Error reading employee data: " + e.getMessage());
        }
        return employees;
    }
}
