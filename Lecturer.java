import java.util.ArrayList;

public class Lecturer extends UTMMember {
    private ArrayList<Course> coursesTaught;

    public Lecturer(String memberId, String name, String email) {
        super(memberId, name, email);
        this.coursesTaught = new ArrayList<>();
    }

    public void addCourseTaught(Course course) {
        if (!coursesTaught.contains(course)) {
            coursesTaught.add(course);
        }
    }

    public ArrayList<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void removeCourseTaught(Course course) {
        if (coursesTaught.contains(course)) {
            coursesTaught.remove(course);
        }
    }

    @Override
    public String getDetails() {
        return "Lecturer ID: " + memberId + ", Name: " + name + ", Email: " + email;
    }
}
