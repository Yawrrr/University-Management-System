import java.util.ArrayList;

class University {
    private String name;
    private ArrayList<Course> courses;
    private ArrayList<UTMMember> members;

    public University(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addMember(UTMMember member) {
        members.add(member);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<UTMMember> getMembers() {
        return members;
    }
}
