import java.util.*;

public class Course {
  private String courseName;
  private String  courseCode;
  private int credit ;
  private ArrayList<Student> studentList;
  private ArrayList<Staff> staffList;

  public Course(String courseName, String courseCode, int credit){
    this.courseName = courseName;
    this.courseCode = courseCode;
    this.credit = credit;
  }

  public void addStaff(Staff staff){
    staffList.add(staff);
  }

  public void addStudent(Student student){
    studentList.add(student);
  }

  public String getCourseName(){
    return courseName;
  }
  
  public String getCourseCode(){
    return courseCode;
  }

  public void removeStaff(Staff staff){
    staffList.remove(staff);
  }

  public void removeStudent(Student student){
    studentList.remove(student);
  }

  public void print(){
    System.out.println("Course Name: " + courseName);
    System.out.println("Course Code: " + courseCode);
    System.out.println("Credit: " + credit);
    System.out.println("Staffs: ");
    for (Staff staff : staffList){
      System.out.println(staff.getName());
    }
    System.out.println("Students: ");
    for (Student student : studentList){
      System.out.println(student.getName());
    }
  }

  public void readCourses(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter Course Name: ");
    courseName = sc.nextLine();
    System.out.println("Enter Course Code: ");
    courseCode = sc.nextLine();
    System.out.println("Enter Credit: ");
    credit = sc.nextInt();
  }
}
