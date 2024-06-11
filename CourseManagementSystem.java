import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseManagementSystem {

    private static final String DELIMITER = ",";

    private static University university = new University("UTM");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();
        while (true) {
            clearConsole();
            System.out.println("Welcome to the University Faculty Course Management System");
            System.out.println("1. Enter as Admin");
            System.out.println("2. Enter as Lecturer");
            System.out.println("3. Enter as Pupil");
            System.out.println("4. Exit");
            System.out.print("Choose your option: ");
            String userInput = scanner.nextLine();
            int choice = Integer.parseInt(userInput);
            
            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    lecturerMenu();
                    break;
                case 3:
                    pupilMenu();
                    break;
                case 4:
                    saveToFile();
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            clearConsole();
            System.out.println("Admin Menu");
            System.out.println("1. Manage Courses");
            System.out.println("2. Manage Members");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose your option: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine(); 
    
            switch (choice) {
                case 1:
                    manageCourses();
                    break;
                case 2:
                    manageMembers();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageMembers() {
        while (true) {
            clearConsole();
            System.out.println("Manage Members");
            System.out.println("Member Details:");
            for (UTMMember member : university.getMembers()) {
                System.out.println(member.getDetails());
            }
            System.out.println("\nOptions:");
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Edit Member Information");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Choose your option: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine();  
    
            switch (choice) {
                case 1:
                    addMember();
                    break;
                case 2:
                    removeMember();
                    break;
                case 3:
                    editMemberInfo();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    private static void manageCourses() {
        while (true) {
            clearConsole();
            System.out.println("Manage Courses");
            System.out.println("Course Details:");
            for (Course course : university.getCourses()) {
                System.out.println(course.getDetails());
            }
            System.out.println("\nOptions:");
            System.out.println("1. Add Course");
            System.out.println("2. Remove Course");
            System.out.println("3. Assign Lecturer to Course");
            System.out.println("4. Remove Lecturer from Course");
            System.out.println("5. Assign Pupil to Course");
            System.out.println("6. Remove Pupil from Course");
            System.out.println("7. Back to Admin Menu");
            System.out.print("Choose your option: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine(); 
    
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    removeCourse();
                    break;
                case 3:
                    assignLecturerToCourse();
                    break;
                case 4:
                    removeLecturerFromCourse();
                    break;
                case 5:
                    assignPupilToCourse();
                    break;
                case 6:
                    removePupilFromCourse();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void lecturerMenu() {
        Lecturer lecturer = null;
        while (true) {
            if (lecturer == null) {
                clearConsole();
                System.out.print("Enter Lecturer ID: ");
                String lecturerId = scanner.nextLine();
    
                for (UTMMember member : university.getMembers()) {
                    if (member instanceof Lecturer && member.memberId.equals(lecturerId)) {
                        lecturer = (Lecturer) member;
                        break;
                    }
                }
    
                if (lecturer == null) {
                    System.out.println("Lecturer not found.");
                    return;
                }
            }
    
            clearConsole();
            System.out.println("Welcome, " + lecturer.getName());
            System.out.println("Lecturer Menu");
            System.out.println("1. View Courses Taught");
            System.out.println("2. Back to Main Menu");
            System.out.print("Choose your option: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine();  
    
            switch (choice) {
                case 1:
                    viewCoursesTaught(lecturer);
                    break;
                case 2:
                    lecturer = null; 
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void pupilMenu() {
        Pupil pupil = null;
        while (true) {
            if (pupil == null) {
                clearConsole();
                System.out.print("Enter Pupil ID: ");
                String pupilId = scanner.nextLine();
    
                for (UTMMember member : university.getMembers()) {
                    if (member instanceof Pupil && member.memberId.equals(pupilId)) {
                        pupil = (Pupil) member;
                        break;
                    }
                }
    
                if (pupil == null) {
                    System.out.println("Pupil not found.");
                    return;
                }
                
            }

            clearConsole();
            System.out.println("Welcome, " + pupil.getName());
            System.out.println("Pupil Menu");
            System.out.println("1. View Enrolled Courses");
            System.out.println("2. Enroll in Course");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose your option: ");
    
            int choice = scanner.nextInt();
            scanner.nextLine();  
    
            switch (choice) {
                case 1:
                    viewEnrolledCourses(pupil);
                    break;
                case 2:
                    enrollInCourse(pupil);
                    break;
                case 3:
                    pupil = null; 
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    private static void enrollInCourse(Pupil pupil) {
        clearConsole();
        System.out.println("Available Courses:");
        ArrayList<Course> courses = university.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getDetails());
        }
        System.out.print("Choose a course to enroll in (Enter course number): ");
        int courseChoice = scanner.nextInt();
        scanner.nextLine();
    
        if (courseChoice < 1 || courseChoice > courses.size()) {
            System.out.println("Invalid course choice.");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            return;
        }
    
        Course selectedCourse = courses.get(courseChoice - 1);
        try {
            pupil.enrollInCourse(selectedCourse);
            System.out.println("Enrolled in course successfully.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to enroll in course.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void viewCoursesTaught(Lecturer lecturer) {
        ArrayList<Course> coursesTaught = lecturer.getCoursesTaught();
        if (coursesTaught.isEmpty()) {
            System.out.println("You are not teaching any courses.");
        } else {
            for (Course course : coursesTaught) {
                System.out.println(course.getDetails());
            }
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void viewEnrolledCourses(Pupil pupil) {
        ArrayList<Course> enrolledCourses = pupil.getEnrolledCourses();
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
        } else {
            for (Course course : enrolledCourses) {
                System.out.println(course.getDetails());
            }
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void addCourse() {
        clearConsole();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Course Level (UNDERGRADUATE/POSTGRADUATE): ");
        String levelStr = scanner.nextLine();
        CourseLevel level = CourseLevel.valueOf(levelStr.toUpperCase());

        Course course = new Course(courseCode, title, level);
        university.addCourse(course);
        System.out.println("Course added successfully. Press Enter to continue...");
        scanner.nextLine();
    }

    private static void addMember() {
        clearConsole();
        System.out.print("Enter Member Type (Pupil/Lecturer): ");
        String memberType = scanner.nextLine();
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        if (memberType.equalsIgnoreCase("Pupil")) {
            Pupil pupil = new Pupil(memberId, name, email);
            university.addMember(pupil);
            System.out.println("Pupil added successfully. Press Enter to continue...");
        } else if (memberType.equalsIgnoreCase("Lecturer")) {
            Lecturer lecturer = new Lecturer(memberId, name, email);
            university.addMember(lecturer);
            System.out.println("Lecturer added successfully. Press Enter to continue...");
        } else {
            System.out.println("Invalid member type. Press Enter to continue...");
        }
        scanner.nextLine();
    }

    private static void viewAllCourses() {
        clearConsole();
        for (Course course : university.getCourses()) {
            System.out.println(course.getDetails());
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private static void viewAllMembers() {
        clearConsole();
        for (UTMMember member : university.getMembers()) {
            System.out.println(member.getDetails());
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    
    private static void removeCourse() {
        clearConsole();
        System.out.println("Available Courses:");
        for (Course course : university.getCourses()) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle());
        }
        System.out.print("Enter Course Code to remove: ");
        String courseCode = scanner.nextLine();
    
        Course courseToRemove = null;
        for (Course course : university.getCourses()) {
            if (course.getCourseCode().equals(courseCode)) {
                courseToRemove = course;
                break;
            }
        }
    
        if (courseToRemove != null) {
            university.getCourses().remove(courseToRemove);
            System.out.println("Course removed successfully.");
        } else {
            System.out.println("Course not found.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void assignLecturerToCourse() {
        clearConsole();
        System.out.println("Available Courses:");
        for (Course course : university.getCourses()) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle());
        }
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
    
        Course courseToAssignTo = null;
        for (Course course : university.getCourses()) {
            if (course.getCourseCode().equals(courseCode)) {
                courseToAssignTo = course;
                break;
            }
        }
    
        if (courseToAssignTo != null) {
            System.out.println("Available Lecturers:");
            for (UTMMember member : university.getMembers()) {
                if (member instanceof Lecturer) {
                    System.out.println(member.getMemberId() + " - " + member.getName());
                }
            }
            System.out.print("Enter Lecturer ID to assign: ");
            String lecturerId = scanner.nextLine();
    
            Lecturer lecturerToAssign = null;
            for (UTMMember member : university.getMembers()) {
                if (member instanceof Lecturer && member.getMemberId().equalsIgnoreCase(lecturerId)) {
                    lecturerToAssign = (Lecturer) member;
                    break;
                }
            }
    
            if (lecturerToAssign != null) {
                courseToAssignTo.assignLecturer(lecturerToAssign);
                System.out.println("Lecturer assigned to course successfully.");
            } else {
                System.out.println("Lecturer not found.");
            }
        } else {
            System.out.println("Course not found.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void removeLecturerFromCourse() {
        clearConsole();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
    
        Course course = null;
        
        for (Course c : university.getCourses()) {
            if (c instanceof Course && c.getCourseCode().equals(courseCode)) {
                course = c;
                break;
            }
        }
        if (course != null) {
            if (course.getLecturer() != null) {
                course.getLecturer().getCoursesTaught().remove(course);
                course.removeLecturer();
                System.out.println("Lecturer removed from course successfully.");
            } else {
                System.out.println("No lecturer assigned to this course.");
            }
        } else {
            System.out.println("Course not found.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void assignPupilToCourse() {
        clearConsole();
        System.out.println("Available Courses:");
        for (Course course : university.getCourses()) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle());
        }
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
    
        Course courseToAssignTo = null;
        for (Course course : university.getCourses()) {
            if (course.getCourseCode().equals(courseCode)) {
                courseToAssignTo = course;
                break;
            }
        }
    
        if (courseToAssignTo != null) {
            System.out.println("Available Pupils:");
            for (UTMMember member : university.getMembers()) {
                if (member instanceof Pupil) {
                    System.out.println(member.getMemberId() + " - " + member.getName());
                }
            }
            System.out.print("Enter Pupil ID to assign: ");
            String pupilId = scanner.nextLine();
    
            Pupil pupilToAssign = null;
            for (UTMMember member : university.getMembers()) {
                if (member instanceof Pupil && member.getMemberId().equalsIgnoreCase(pupilId)) {
                    pupilToAssign = (Pupil) member;
                    break;
                }
            }
    
            if (pupilToAssign != null) {
                courseToAssignTo.enrollStudent(pupilToAssign);
                System.out.println("Pupil assigned to course successfully.");
            } else {
                System.out.println("Pupil not found.");
            }
        } else {
            System.out.println("Course not found.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void removePupilFromCourse() {
        clearConsole();
        System.out.println("Available Courses:");
        for (Course course : university.getCourses()) {
            System.out.println(course.getCourseCode() + " - " + course.getTitle());
        }
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
    
        Course courseToRemoveFrom = null;
        for (Course course : university.getCourses()) {
            if (course.getCourseCode().equals(courseCode)) {
                courseToRemoveFrom = course;
                break;
            }
        }
    
        if (courseToRemoveFrom != null) {
            System.out.println("Available Pupils:");
            for (UTMMember member : university.getMembers()) {
                if (member instanceof Pupil) {
                    System.out.println(member.getMemberId() + " - " + member.getName());
                }
            }
            System.out.print("Enter Pupil ID to remove: ");
            String pupilId = scanner.nextLine();
    
            Pupil pupilToRemove = null;
            for (UTMMember member : courseToRemoveFrom.getEnrolledStudents()) {
                if (member instanceof Pupil && member.getMemberId().equalsIgnoreCase(pupilId)) {
                    pupilToRemove = (Pupil) member;
                    break;
                }
            }
    
            if (pupilToRemove != null) {
                courseToRemoveFrom.getEnrolledStudents().remove(pupilToRemove);
                System.out.println("Pupil removed from course successfully.");
            } else {
                System.out.println("Pupil not found in the course.");
            }
        } else {
            System.out.println("Course not found.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void removeMember() {
        clearConsole();
        System.out.print("Enter Member ID to remove: ");
        String memberId = scanner.nextLine();
    
        UTMMember memberToRemove = null;
        for (UTMMember member : university.getMembers()) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                memberToRemove = member;
                break;
            }
        }
    
        if (memberToRemove != null) {
            university.getMembers().remove(memberToRemove);
            System.out.println("Member removed successfully.");
        } else {
            System.out.println("Member not found.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
    
    private static void editMemberInfo() {
        clearConsole();
        System.out.println("Available Members:");
        for (UTMMember member : university.getMembers()) {
            System.out.println(member.getMemberId() + " - " + member.getName());
        }
        System.out.print("Enter Member ID to edit: ");
        String memberId = scanner.nextLine();
    
        UTMMember memberToEdit = null;
        for (UTMMember member : university.getMembers()) {
            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                memberToEdit = member;
                break;
            }
        }
    
        if (memberToEdit != null) {
            System.out.println("Editing Member: " + memberToEdit.getName());
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Email");
            System.out.println("3. Cancel");
    
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  
    
            switch (choice) {
                case 1:
                    System.out.println("Current name: " + memberToEdit.getName());
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    memberToEdit.setName(newName);
                    System.out.println("Name updated successfully.");
                    break;
                case 2:
                    System.out.println("Current email: " + memberToEdit.getEmail());
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    memberToEdit.setEmail(newEmail);
                    System.out.println("Email updated successfully.");
                    break;
                case 3:
                    System.out.println("Canceling operation.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } else {
            System.out.println("Member not found.");
        }
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    public static UTMMember findMember(String memberId) {
        for (UTMMember member : university.getMembers()) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private static void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("university_data.txt"))) {
            String line;
            Course courses = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts[0].equalsIgnoreCase("course")) {
                    String courseCode = parts[1];
                    String title = parts[2];
                    CourseLevel level = CourseLevel.valueOf(parts[3].toUpperCase());
                    String lecturerId = parts[4];
                    Course course = new Course(courseCode, title, level);
                    university.addCourse(course);
                    if (!lecturerId.equalsIgnoreCase("None")) {
                        assignLecturerToCourse(course, lecturerId);
                    }
                } else if (parts[0].equalsIgnoreCase("member")) {
                    String memberType = parts[1];
                    String memberId = parts[2];
                    String name = parts[3];
                    String email = parts[4];
                    if (memberType.equalsIgnoreCase("Pupil")) {
                        Pupil pupil = new Pupil(memberId, name, email);
                        university.addMember(pupil);
                        // Load enrolled courses
                        for (int i = 5; i < parts.length; i++) {
                            String courseCode = parts[i];
                                    
                        for (Course c : university.getCourses()) {
                            if (c instanceof Course && c.getCourseCode().equals(courseCode)) {
                                courses = c;
                                break;
                            }
                        }
                            if (courses != null) {
                                pupil.enrollInCourse(courses);
                            }
                        }
                    } else if (memberType.equalsIgnoreCase("Lecturer")) {
                        Lecturer lecturer = new Lecturer(memberId, name, email);
                        university.addMember(lecturer);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void assignLecturerToCourse(Course course, String lecturerId) {
        for (UTMMember member : university.getMembers()) {
            if (member instanceof Lecturer && member.getMemberId().equals(lecturerId)) {
                Lecturer lecturer = (Lecturer) member;
                course.assignLecturer(lecturer);
                return;
            }
        }
        System.out.println("Lecturer with ID " + lecturerId + " not found for course " + course.getCourseCode());
    }

    private static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("university_data.txt"))) {
            for (Course course : university.getCourses()) {
                String line = "course" + DELIMITER + course.getCourseCode() + DELIMITER +
                            course.getTitle() + DELIMITER + course.getLevel() + DELIMITER +
                            (course.getLecturer() != null ? course.getLecturer().getMemberId() : "None");
                writer.write(line);
                writer.newLine();
            }
            for (UTMMember member : university.getMembers()) {
                String line = "member" + DELIMITER + (member instanceof Pupil ? "Pupil" : "Lecturer") +
                            DELIMITER + member.getMemberId() + DELIMITER +
                            member.getName() + DELIMITER + member.getEmail();
                if (member instanceof Pupil) {
                    for (Course course : ((Pupil) member).getEnrolledCourses()) {
                        line += DELIMITER + course.getCourseCode();
                    }
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
