package main.java.com.example;
import java.util.Scanner;

public class AttendanceTracker {
    private int[] employeeId = new int[10];
    private String[] employeeName = new String[10];
    private String[] attendanceStatus = new String[10];
    private int count = 0;

    public void recordAttendance(int id, String name, String status) {
        if (count < 10) {
            employeeId[count] = id;
            employeeName[count] = capitalizeName(name);
            attendanceStatus[count] = status;
            count++;
        } else {
            System.out.println("Attendance records are full.");
        }
    }
    public void displayAttendance() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID\tName\t\tAttendance\n");
        for (int i = 0; i < count; i++) {
            sb.append(employeeId[i]).append("\t").append(employeeName[i]).append("\t").append(attendanceStatus[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
    public void calculateAttendanceSummary(){
        int present = 0;
        int absent = 0;
        for (int i = 0; i < count; i++) {
            if (attendanceStatus[i].equals("Present")) {
                present++;
            } else {
                absent++;
            }
        }
        System.out.println("Total Present: " + present);
        System.out.println("Total Absent: " + absent);
    }
    public void searchEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employeeId[i] == id) {
                System.out.println("ID" + employeeId[i] + ", Name: " + employeeName[i] + ", Attendance: " + attendanceStatus[i]);
                return;
            }
        }
        System.out.println("Employee not found.");

    }
    private String capitalizeName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

public static void main(String[] args) {
    AttendanceTracker tracker = new AttendanceTracker();
    Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println("1. Record Attendance");
        System.out.println("2. Display Attendance");
        System.out.println("3. Calculate Attendance Summary");
        System.out.println("4. Search Employee");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter Employee ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // consume newline
                System.out.print("Enter Employee Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Attendance Status (Present/Absent): ");
                String status = scanner.nextLine();
                tracker.recordAttendance(id, name, status);
                break;
            case 2:
                tracker.displayAttendance();
                break;
            case 3:
                tracker.calculateAttendanceSummary();
                break;
            case 4:
                System.out.print("Enter Employee ID to search: ");
                int searchId = scanner.nextInt();
                tracker.searchEmployee(searchId);
                break;
            case 5:
                System.out.println("Exiting...");
                scanner.close();
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
}