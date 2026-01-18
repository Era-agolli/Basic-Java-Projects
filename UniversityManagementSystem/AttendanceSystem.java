package UniversityManagementSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceSystem {

    private Map<String, List<String>> attendance = new HashMap<>();

    public void markAttendance(String studentId, String date){
        attendance.putIfAbsent(studentId, new ArrayList<>());
        attendance.get(studentId).add(date);
        System.out.println("Attendance marked for " + studentId + " on " + date);
    }

    public void viewAttendance(String studentId){
        List<String> dates = attendance.get(studentId);
        if (dates == null){
            System.out.println("No attendance records found.");
        } else {
            System.out.println("Attendance for " + studentId + ": " + dates);
        }
    }

    public Map<String, List<String>> getAttendance(){
        return attendance;
    }

}
