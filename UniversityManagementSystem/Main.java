package UniversityManagementSystem;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        AdmissionSystem admission = new AdmissionSystem();
        AttendanceSystem attendance = new AttendanceSystem();
        CourseRegistrationSystem courseRegistration = new CourseRegistrationSystem();

        List<Student> loadedStudents = FileHandling.loadData("students.dat");
        for (Student student : loadedStudents){
            admission.getStudents().add(student);
        }

        while (true){
            System.out.println("\n--- University Management System ---");
            System.out.println("1.Add student");
            System.out.println("2.Admit student");
            System.out.println("3.List student");
            System.out.println("4.Mark attendance");
            System.out.println("5.View attendance");
            System.out.println("6.Register course");
            System.out.println("7.View courses");
            System.out.println("8.Reports");
            System.out.println("9.Save & Exit");
            System.out.println("Choose your option:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.println("Enter Name:");
                    String name = scanner.nextLine();
                    admission.addStudent(id, name);
                    break;
                case 2:
                    System.out.println("Enter Student ID: ");
                     id = scanner.nextLine();
                    System.out.println("Enter Course: ");
                    String course = scanner.nextLine();
                    admission.admitStudent(id, course);
                    break;
                case 3:
                    admission.listStudents();
                    break;
                case 4:
                    System.out.println("Enter Student ID: ");
                    id = scanner.nextLine();
                    System.out.println("Enter Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    attendance.markAttendance(id, date);
                    break;
                case 5:
                    System.out.println("Enter Student ID: ");
                    id = scanner.nextLine();
                    attendance.viewAttendance(id);
                    break;
                case 6:
                    System.out.println("Enter Student ID: ");
                    id = scanner.nextLine();
                    System.out.println("Enter Course: ");
                    course = scanner.nextLine();
                    courseRegistration.registerCourse(id, course);
                    break;
                case 7 :
                    System.out.println("Enter Student ID: ");
                    id = scanner.nextLine();
                    courseRegistration.viewCourses(id);
                    break;
                case 8:
                    System.out.println("\n--- Reports ---");
                    for (Student student : admission.getStudents()){
                        System.out.println("Student: " + student.getId() + " - " + student.getName());
                        System.out.println("Admitted: " + student.isAdmitted() +
                                " | Course: " + (student.getCourse() == null ? "None" : student.getCourse()));
                        System.out.println("Registered Courses: " + courseRegistration.getRegistrations().getOrDefault(student.getId(), List.of()));
                        System.out.println("Attendance: " + attendance.getAttendance().getOrDefault(student.getId(), List.of()));
                    }
                case 9:
                    FileHandling.saveData(admission.getStudents(), "students.dat");
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice");


            }

        }
    }
}
