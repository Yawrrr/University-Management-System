import java.util.*;

public class Person {
  private String name;

  Person(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }
}

class Staff extends Person{
  private String staffID;
  private String subject;

  public Staff(String name, String subject){
    super(name);
    this.subject = subject;
  }

  public String getSubject(){
    return subject;
  }
}

class Student extends Person{
  private String studentID;
  private ArrayList<Course> enrolledCourses;

  public Student(String name, String studentID){
    super(name);
    this.studentID = studentID;
  }

  public String getStudentID(){
    return studentID;
  }

  public void enrollCourse(Course course){
    enrolledCourses.add(course);
  }

  public void dropCourse(Course course){
    enrolledCourses.remove(course);
  }

  public void printDetails (){
    System.out.println("Student ID: " + studentID);
    System.out.println("Name: " + getName());
    System.out.println("Enrolled Courses: ");
    for (Course course : enrolledCourses){
      System.out.println(course.getCourseName());
    }
  }


}