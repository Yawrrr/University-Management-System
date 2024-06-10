import java.util.*;

public class Department {
  private String departmentName;
  private ArrayList<Course> courseList;
  private ArrayList<Staff> staffList;

  public Department(String departmentName){
    this.departmentName = departmentName;
  }

  public void addCourse(Course course){
    courseList.add(course);
  }

  public void addStaff(Staff staff){
    staffList.add(staff);
  }

  public String getDepartmentName(){
    return departmentName;
  }

  public Course getCourse(String courseCode){
    for (Course course : courseList){
      if (course.getCourseCode().equals(courseCode)){
        return course;
      }
    }
    return null;
  }
}
