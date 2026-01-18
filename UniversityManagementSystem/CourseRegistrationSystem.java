package UniversityManagementSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseRegistrationSystem {

    private Map<String, List<String>> registrations = new HashMap<>();

    public void registerCourse(String studentId,String course){
        registrations.putIfAbsent(studentId,new ArrayList<>());
        registrations.get(studentId).add(course);
        System.out.println("Course " + course + " registered for student " + studentId);
    }

    public void viewCourses(String studentId){
        List<String> courses = registrations.get(studentId);
        if (courses == null){
            System.out.println("No courses registered.");
        } else {
            System.out.println("Courses for " + studentId + ": " + courses);
        }
    }

    public Map<String, List<String>> getRegistrations(){
        return registrations;
    }
}
