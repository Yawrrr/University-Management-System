import java.io.*;
import java.util.*;

public class UniversitySystem {
  public static void manageDepartment(University u){
    boolean continueManage= true, continueManageCourse, continueManageStaff;
    try {
      File file = new File("Department.txt");
      Scanner readFile = new Scanner(file);
      while (readFile.hasNextLine()){
        String departname = readFile.nextLine();
        Department d = new Department(departname);
        u.addDepartment(d);
      }
    } catch (FileNotFoundException e) {
      System.out.println("Error: File not found");
    }
    System.out.println("Department List");
    ArrayList<Department> departmentList = u.getDepartments();
    for(int i = 0; i<departmentList.size(); i++){
      System.out.println((i+1) + ". " + departmentList.get(i).getDepartmentName());
    }
    System.out.println("Select Department: ");
    Scanner sc = new Scanner(System.in);
    int choice = sc.nextInt();
    Department selectedDepartment = departmentList.get(choice-1);
    System.out.println("--------------------");
    System.out.println("1. Add Course");
    System.out.println("2. Add Staff");
    System.out.println("3. Exit");
    System.out.println("--------------------");
    System.out.print("Select Option: ");
    
  }

  public static void manageCourse(){
    try {
        
    } catch (Exception e) {
    }
  }

  public static void enrollStudent(){
    try {
        
    } catch (Exception e) {
    }
  }

  public static void main(String[] args) {
    University u = new University("University of Technology, Malaysia");
    int choice;
    boolean Continue = true;

    while (Continue){
      System.out.println("--------------------");
      System.out.println("1. Manage Department");
      System.out.println("2. Manage Course");
      System.out.println("3. Enroll Student");
      System.out.println("4. Exit");

      Scanner sc = new Scanner(System.in);
      choice = sc.nextInt();

      switch (choice) {
          case 1:
            manageDepartment(u);
             break;
          case 2:
            manageCourse();    
            break;

          case 3:
            break;
          case 4:
            Continue = false;
            break;
          default:
              throw new AssertionError();
      }
    }
  }
}
