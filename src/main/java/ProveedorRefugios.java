import Rest.Request.RequestBody;
import Rest.Response.TokenResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class ProveedorRefugios {

  Client client;
  private static final String API_REFUGIOS = "https://api.refugiosdds.com.ar/api";
  private static final String RESOURCE = "hogares";

  //Inicializacion del cliente.
  public ProveedorRefugios() {
    ClientConfig clientConfig = new DefaultClientConfig();
    clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
    client = Client.create(clientConfig);

  }

  public ClientResponse getRefugios(String filter, String value) {
    WebResource recurso = this.client.resource(API_REFUGIOS).path(RESOURCE);
    //WebResource recursoConParametros = recurso.queryParam("q",filter + ":" + value);
    WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);
    ClientResponse response = builder.get(ClientResponse.class);
    Refugio responseBody = response.getEntity(Refugio.class);
    return response;
  }

  public void loginRefugios() {
    WebResource recurso = this.client.resource(API_REFUGIOS).path("usuarios");
    WebResource.Builder builder = recurso.accept(MediaType.APPLICATION_JSON);

    RequestBody requestBody = new RequestBody("mailprueba1@gmail.com");
    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = "";
    try {
      jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody);
      ClientResponse response = builder.type("application/json").post(ClientResponse.class, jsonInString);
      TokenResponse responseBody = response.getEntity(TokenResponse.class);
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
  
  /*
  private Client client;
  private static final String API_GOOGLE = "https://www.googleapis.com/books/v1";
  private static final String RESOURCE = "volumes";

  //Inicializacion del cliente.
  public RequestService() {
    this.client = Client.create();
    //En la documentacion se puede ver como al cliente agregarle un ClientConfig
    //para agregarle filtros en las respuestas (por ejemplo, para loguear).
  }

  //Prueba de concepto de un parametro y los mensajes por separado para identificar los tipos de datos.
  public ClientResponse getBookByFilter(String filter, String value){
    WebResource recurso = this.client.resource(API_GOOGLE).path(RESOURCE);
    WebResource recursoConParametros = recurso.queryParam("q",filter + ":" + value);
    WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);
    ClientResponse response = builder.get(ClientResponse.class);
    return response;
  }

  //Prueba de concepto de envio de dos parametros, y el envio de mensajes juntos.
  public ClientResponse getBookByFilter(String filter, String value, String fields){
    ClientResponse response = this.client.resource(API_GOOGLE).path(RESOURCE)
      .queryParam("q",filter + ":" + value).queryParam("fields",fields)
      .accept(MediaType.APPLICATION_JSON)
      .get(ClientResponse.class);
    return response;
  }

  //Prueba de concepto del envio de un request con un header.
  public ClientResponse getBookAndSendHeader(String filter, String value, String header, String headerValue){
    ClientResponse response = this.client.resource(API_GOOGLE).path(RESOURCE)
      .queryParam("q",filter + ":" + value).header(header, headerValue)
      .accept(MediaType.APPLICATION_JSON)
      .get(ClientResponse.class);
    return response;
  }
   */
}
