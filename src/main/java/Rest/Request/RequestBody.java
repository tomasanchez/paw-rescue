package Rest.Request;

public class RequestBody {
  private String email;
  
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }



  public RequestBody(String email) {
    this.email = email;
  }
}
