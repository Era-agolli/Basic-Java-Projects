package UniversityManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandling {

    public static void saveData(List<Student> students,String filename){
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(students);
            System.out.println("Data saved to file.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<Student> loadData(String filename){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))){
            return (List<Student>) ois.readObject();
        } catch (Exception e){
            System.out.println("No previous data found.");
            return new ArrayList<>();
        }
    }
}
