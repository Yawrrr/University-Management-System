import java.util.ArrayList;

abstract class UTMMember {
  protected String memberId;
  protected String name;
  protected String email;

  public UTMMember(String memberId, String name, String email) {
      this.memberId = memberId;
      this.name = name;
      this.email = email;
  }

  public String getName() {
      return name;
  }

  public String getEmail() {
      return email;
  }

  public String getMemberId() {
      return memberId;
  }

  public abstract String getDetails();

  protected abstract ArrayList<Course> getCoursesTaught();

  public void setName(String name) {
      this.name = name;
  }

    public void setEmail(String email) {
        this.email = email;
    }
}