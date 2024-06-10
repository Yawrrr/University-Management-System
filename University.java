import java.util.*;

public class University {
  private String name;
  private ArrayList<Department> departmentList;

  public University(){
    departmentList = new ArrayList<Department>();
  }
  
  public University(String name){
    this.name = name;
  }

  public void addDepartment(Department department){
    departmentList.add(department);
  }

  public ArrayList<Department> getDepartments(){
    return departmentList;
  }
}

enum UniversityType {
  PUBLIC, PRIVATE
}