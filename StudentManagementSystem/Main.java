package StudentManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = null; // not initialized yet
        int subjectNumber = 0; // store number of subjects once entered

        while (true) {
            System.out.println("----- MENU -----");
            System.out.println("1. Add Student");
            System.out.println("2. Show All Students");
            System.out.println("3. Show Class Statistics");
            System.out.println("4. Search Student");
            System.out.println("5. Edit Student Marks");
            System.out.println("6. Delete Student");
            System.out.println("7. Sort by Name");
            System.out.println("8. Sort by Average");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // ask for number of subjects only if manager is not created yet


                    System.out.println("Enter the name of the student:");
                    String name = scanner.nextLine();

                    if (manager == null) {
                        System.out.println("Enter number of subjects:");
                        subjectNumber = scanner.nextInt();
                        scanner.nextLine();
                        manager = new StudentManager(subjectNumber);
                    }

                    Student student = new Student(name, subjectNumber);

                    System.out.println("Enter marks for " + subjectNumber + " subjects:");
                    for (int i = 0; i < subjectNumber; i++) {
                        System.out.print("Subject " + (i + 1) + ": ");
                        student.setMark(i, scanner.nextDouble());
                    }
                    scanner.nextLine();

                    student.calculateAverage();
                    student.Grade();

                    System.out.println("Assigning grade for student...");
                    System.out.println("Average: " + student.getAverage());
                    System.out.println("Grade: " + student.getGrade());

                    manager.addStudent(student);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    if (manager != null) {
                        manager.showAllStudents();
                    } else {
                        System.out.println("No students yet.");
                    }
                    break;

                case 3:
                    if (manager != null) {
                        manager.showStatistics();
                    } else {
                        System.out.println("No students yet.");
                    }
                    break;

                case 4:
                    if (manager != null) {
                        System.out.print("Enter student name to search: ");
                        String searchName = scanner.nextLine();
                        Student found = manager.searchStudent(searchName);
                        if (found != null) {
                            found.studentReport();
                        } else {
                            System.out.println("Student not found.");
                        }
                    } else {
                        System.out.println("No students yet.");
                    }
                    break;

                case 5:
                    if (manager != null) {
                        System.out.print("Enter student name to edit marks: ");
                        String editName = scanner.nextLine();
                        Student editStudent = manager.searchStudent(editName);
                        if (editStudent != null) {
                            double[] newMarks = new double[subjectNumber];
                            System.out.println("Enter new marks:");
                            for (int i = 0; i < subjectNumber; i++) {
                                System.out.print("Subject " + (i + 1) + ": ");
                                newMarks[i] = scanner.nextDouble();
                            }
                            scanner.nextLine();
                            manager.updateStudentMarks(editName, newMarks);
                            System.out.println("Marks updated!");
                        } else {
                            System.out.println("Student not found.");
                        }
                    } else {
                        System.out.println("No students yet.");
                    }
                    break;

                case 6:
                    if (manager != null) {
                        System.out.print("Enter student name to delete: ");
                        String delName = scanner.nextLine();
                        if (manager.deleteStudent(delName)) {
                            System.out.println("Student deleted.");
                        } else {
                            System.out.println("Student not found.");
                        }
                    } else {
                        System.out.println("No students yet.");
                    }
                    break;

                case 7:
                    if (manager != null) {
                        manager.sortByName();
                        System.out.println("Students sorted by name:");
                        manager.showAllStudents();
                    } else {
                        System.out.println("No students yet.");
                    }
                    break;

                case 8:
                    if (manager != null) {
                        manager.sortByAverage();
                        System.out.println("Students sorted by average:");
                        manager.showAllStudents();
                    } else {
                        System.out.println("No students yet.");
                    }
                    break;

                case 9:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
