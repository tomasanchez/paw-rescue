package Rest.Response;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TokenResponse {

  @JsonProperty("bearer_token")
  public String bearer_token;

  public TokenResponse(String bearer_token) {
    this.bearer_token = bearer_token;
  }

  public TokenResponse() {

  }

  public String getBearer_token() {
    return bearer_token;
  }

  public void setBearer_token(String bearer_token) {
    this.bearer_token = bearer_token;
  }


}
