package main.java.com.example;

import java.util.Scanner;

public class WorkHourCalculator {
    public static double[] calculateWorkHours(int startTime, int endTime, int breakTime) {
        int totalMinutes = 0;
        if (endTime < startTime) {
            totalMinutes = ((24 - startTime) + endTime) * 60 - breakTime;
        } else {
            totalMinutes = (endTime - startTime) * 60 - breakTime;
        }
        double hours = totalMinutes / 60.0;
        double regularHours = Math.min(hours, 8);
        double overtime = Math.max(0, hours - 8);
        return new double[]{regularHours, overtime};
    }

    // Generates a weekly report for the employee with the given ID.
    public static void generateWeeklyReport(int employeeId) {
        Scanner scanner = new Scanner(System.in);
        double[] dailyRegular = new double[7];
        double[] dailyOvertime = new double[7];

        System.out.println("Generating Weekly Report for Employee ID: " + employeeId);
        for (int i = 0; i < 7; i++) {
            System.out.println("Day " + (i + 1) + " - Enter start time (0-23):");
            int start = scanner.nextInt();
            System.out.println("Day " + (i + 1) + " - Enter end time (0-23):");
            int end = scanner.nextInt();
            System.out.println("Day " + (i + 1) + " - Enter break time in minutes:");
            int breakTime = scanner.nextInt();

            double[] hours = calculateWorkHours(start, end, breakTime);
            dailyRegular[i] = hours[0];
            dailyOvertime[i] = hours[1];
        }

        double totalRegular = 0, totalOvertime = 0;
        StringBuilder report = new StringBuilder("Weekly Work Hours Report for Employee ID: " + employeeId + "\n\n");

        for (int i = 0; i < 7; i++) {
            report.append("Day ").append(i + 1).append(": Regular Hours = ").append(dailyRegular[i])
                  .append(", Overtime Hours = ").append(dailyOvertime[i]).append("\n");
            totalRegular += dailyRegular[i];
            totalOvertime += dailyOvertime[i];
        }

        report.append("\nTotal Regular Hours: ").append(totalRegular)
              .append("\nTotal Overtime Hours: ").append(totalOvertime).append("\n");

        System.out.println(report.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Calculate Work Hours (Single Day)");
            System.out.println("2. Generate Weekly Report");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int employeeId = scanner.nextInt();
                    System.out.print("Enter start time (0-23): ");
                    int start = scanner.nextInt();
                    System.out.print("Enter end time (0-23): ");
                    int end = scanner.nextInt();
                    System.out.print("Enter break time in minutes: ");
                    int breakTime = scanner.nextInt();
                    double[] result = calculateWorkHours(start, end, breakTime);
                    System.out.println("\nEmployee ID: " + employeeId);
                    System.out.println("Regular Hours: " + result[0] + " hours");
                    System.out.println("Overtime Hours: " + result[1] + " hours");
                    break;
                case 2:
                    System.out.print("Enter Employee ID for weekly report: ");
                    int id = scanner.nextInt();
                    generateWeeklyReport(id);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
