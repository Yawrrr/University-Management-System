import java.util.ArrayList;

class Course {
    private String courseCode;
    private String title;
    private CourseLevel level;
    private ArrayList<Pupil> enrolledStudents;
    private Lecturer lecturer;

    public Course(String courseCode, String title, CourseLevel level) {
        this.courseCode = courseCode;
        this.title = title;
        this.level = level;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getDetails() {
        return "Course Code: " + courseCode + ", Title: " + title + ", Level: " + level + ", Lecturer: " + (lecturer != null ? lecturer.getName() : "None");
    }

    public void enrollStudent(Pupil student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            student.enrollInCourse(this);
        } else {
            throw new IllegalStateException("Student is already enrolled in this course");
        }
    }

    public void removeLecturer() {
        if (lecturer != null) {
            lecturer.removeCourseTaught(this);
            lecturer = null;
        }
    }

    public void assignLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
        lecturer.addCourseTaught(this);
    }

    public ArrayList<Pupil> getEnrolledStudents() {
        return enrolledStudents;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public CourseLevel getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }
}
