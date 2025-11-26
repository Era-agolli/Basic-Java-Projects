package StudentManagementSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();
    private int subjectNumber;

    public StudentManager(int subjectNumber) {
        this.subjectNumber = subjectNumber;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public Student searchStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }

        } return null;

}

public boolean deleteStudent(String name){
        Student student = searchStudent(name);
        if (student != null){
            students.remove(student);
            return true;
        } return false;
}

public boolean updateStudentMarks(String name,double[] newMarks){
        Student student = searchStudent(name);
        if(student == null ) {
            return false;
        }
        for(int i=0; i< newMarks.length;i++){
            student.setMark(i,newMarks[i]);
        }
        student.calculateAverage();
        student.Grade();
        return true;
}

public void sortByName(){
    Collections.sort(students, Comparator.comparing(Student::getName));
}
public void sortByAverage(){
        Collections.sort(students,(student1,student2)->Double.compare(student2.getAverage(),student1.getAverage()));
}
public void showAllStudents(){
        if (students.isEmpty()){
            System.out.println("Student list is empty");
        }else {
            for (Student student : students){
                student.studentReport();
                System.out.println("------------------");
            }
        }

}

public void showStatistics(){
        if (students.isEmpty()){
            System.out.println("There are no Students found.");
            return;
        }
        double highest = students.get(0).getAverage();
        double lowest = students.get(0).getAverage();

        for (Student student: students){
            if(student.getAverage()> highest){
                highest = student.getAverage();
            }
            if (student.getAverage()< lowest){
                lowest = student.getAverage();
            }
        }

        System.out.println("----Class Statistics----");
        System.out.println("Highest Average:" + highest);
        System.out.println("Lowest Average" + lowest);
        System.out.println("------------------------");

}



}

