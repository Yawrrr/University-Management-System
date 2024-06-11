import java.util.ArrayList;

public class Pupil extends UTMMember{
    private ArrayList<Course> enrolledCourses;

    public Pupil(String memberId, String name, String email) {
        super(memberId, name, email);
        this.enrolledCourses = new ArrayList<>();
    }

    public void enrollInCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        } else {
            throw new IllegalStateException("Student is already enrolled in this course");
        }
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    @Override
    public String getDetails() {
        return "Student ID: " + memberId + ", Name: " + name + ", Email: " + email;
    }

    @Override
    public ArrayList<Course> getCoursesTaught(){
        ArrayList<Course> coursesTaught = new ArrayList<>();
        return coursesTaught;
    }
}