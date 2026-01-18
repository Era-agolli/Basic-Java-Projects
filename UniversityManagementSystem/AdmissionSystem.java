package UniversityManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class AdmissionSystem {

    private List<Student> students = new ArrayList<>();

    public void addStudent(String id,String name){
        students.add(new Student(id,name));
        System.out.println("Student added successfully");
    }

    public void admitStudent(String id, String course) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.admit(course);
                System.out.println("Student admitted to " + course);
                return;
            }
        }
        System.out.println("Student not found!");
    }



    public void listStudents() {
        for (Student student : students){
            System.out.println(student);
        }
    }

    public List<Student> getStudents(){
        return students;
    }
}
