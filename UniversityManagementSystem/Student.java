package UniversityManagementSystem;

import java.io.Serializable;

public class Student implements Serializable {

    private String id;
    private String name;
    private String course;
    private boolean admitted;

    public Student(String id,String name){
        this.id = id;
        this.name = name;
        this.admitted = false;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getCourse(){
        return course;
    }

    public boolean isAdmitted(){
        return admitted;
    }

    public void admit(String course){
        this.admitted = true;
        this.course = course;
    }

    @Override
    public String toString(){
        return id + " - " + name + " | Course: " + (course == null ? "None" : course) + " | Admitted: " + admitted;
    }
}
