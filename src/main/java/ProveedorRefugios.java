import Rest.JsonFactory;
import Rest.Request.RequestBody;
import Rest.Response.TokenResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import exeptions.LogueoSinEmailException;
import exeptions.UsuarioLogueadoException;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


public class ProveedorRefugios {

  Client client;
  private static final String API_REFUGIOS = "https://api.refugiosdds.com.ar/api";
  private static final String RESOURCE = "hogares";
  private static String TOKEN = "";

  //Inicializacion del cliente.
  public ProveedorRefugios() {
    ClientConfig clientConfig = new DefaultClientConfig();
    clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
    client = Client.create(clientConfig);

  }

  public ClientResponse getRefugios(String filter, String value) {
    ClientResponse response = this.client.resource(API_REFUGIOS).path(RESOURCE)
      .queryParam("offset", "2").header("Authorization", "Bearer " + TOKEN)
      .accept(MediaType.APPLICATION_JSON)
      .get(ClientResponse.class);

    JsonFactory jsonFactory = new JsonFactory();
    String responseBody = response.getEntity(String.class);
    List<Refugio> a = new ArrayList<>();
    List<Refugio> tokenResponse = jsonFactory.fromJson(responseBody, a.getClass());
    return response;
  }

  public void loginRefugios() {
    WebResource recurso = this.client.resource(API_REFUGIOS).path("usuarios");
    WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);

    RequestBody requestBody = new RequestBody("mailprueba23@gmail.com");
    JsonFactory jsonFactory = new JsonFactory();
    ClientResponse response = builder.type("application/json").post(ClientResponse.class, jsonFactory.toJson(requestBody));
    if (response.getStatus() == 409) {
      throw new UsuarioLogueadoException();
    }
    if (response.getStatus() == 422) {
      throw new LogueoSinEmailException();
    }
    String responseBody = response.getEntity(String.class);
    TokenResponse tokenResponse = jsonFactory.fromJson(responseBody, TokenResponse.class);
    TOKEN = tokenResponse.getBearer_token();
    System.out.println(tokenResponse.getBearer_token());


  }
  
}
