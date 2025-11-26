package StudentManagementSystem;

public class Student {

    private String name;
    private double[] marks;
    private double average;
    private String grade;

    public Student (String name,int subjectNumber){
        this.name = name;
        this.marks = new double[subjectNumber];
    }

    public String getName(){
        return name;
    }

    public double[] getMarks(){
        return marks;
    }

    public double getAverage(){
        return average;
    }

    public String getGrade(){
        return grade;
    }

    public void setMark(int index, double value){
        this.marks[index]=value;
    }

    public void calculateAverage(){
        double sum = 0;
        for(int i=0;i< marks.length; i++){
            sum += marks[i];
        }
        this.average = sum / marks.length;

    }

    public void Grade(){
        if(average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C" ;
        }else if (average >= 60){
            grade = "D";
        }else{
            grade = "F";
        }

    }


    public void studentReport(){
        System.out.println("----Student Report---");
        System.out.println("Name: " + name);
        System.out.println("Marks: ");
            for(int i = 0; i< marks.length;i++){
                System.out.print(marks[i] + " ");
        System.out.println("Average: " + average);
        System.out.println("Grade: " + grade);
        }

    }
}
