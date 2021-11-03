package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public abstract class BaseController {

  /**
   * View Model.
   */
  private Map<String, Object> model = new HashMap<String, Object>();

  private static volatile Map<String, Object> baseModel;

  /**
   * Obtiene el modelo compartido entre todos los controllers.
   * 
   * @return el modelo compartido
   */
  public static Map<String, Object> getBaseModel() {
    if (Objects.isNull(baseModel)) {
      baseModel = new HashMap<String, Object>();
    }
    return baseModel;
  }

  /**
   * Obtiene el nombre del controlador.
   * 
   * ? Ej: HomeController => Home
   * 
   * @return el nombre del controller
   */
  public String getControllerName() {
    return getClass().getSimpleName().toLowerCase().replace("controller", "");
  }

  /**
   * Obtiene el nombre del archivo de la vista.
   * 
   * ? Ej: HomeController => Home.hbs.html
   * 
   * @return el View Name
   */
  public String getViewName() {
    return this.getControllerName().concat(".html.hbs");
  }

  /**
   * Obtiene el HTTP path.
   * 
   * ? Ej: HomeController => /Home
   * 
   * @return
   */
  public String getPath() {
    return "/".concat(this.getClass().getSimpleName().toLowerCase().replace("controller", ""));
  }

  public Map<String, Object> getModel() {
    return model;
  }

  public void setModel(Map<String, Object> model) {
    this.model = model;
  }


  /**
   * Obtiene la ruta del viewmodel.
   * 
   * @param request la HTTP request
   * @param response la HTTP response
   * @return el ViewModel
   */
  public ModelAndView getViewModel(Request request, Response response) {
    getBaseModel().replace("loggedIn", isLogged(request));
    this.onInit(request, response);
    this.getModel().putAll(getBaseModel());
    return new ModelAndView(this.getModel(), this.getViewName());
  }

  /**
   * Redibuja un ModelAndView.
   * 
   * @return el nuevo model and view;
   */
  public ModelAndView getViewModel() {
    this.getModel().putAll(getBaseModel());
    return new ModelAndView(this.getModel(), this.getViewName());
  }

  /**
   * Funcion llamada antes de obtener el View Model.
   * 
   * @param request la HTTP request.
   * @param response la HTTP response.
   */
  protected abstract void onInit(Request request, Response response);


  public static void initBaseModel() {
    getBaseModel().put("loggedIn", false);
  }

  /**
   * Chequea si hay un usuario loggeado.
   * 
   * @param request
   * @return
   */
  protected boolean isLogged(Request request) {
    return !Objects.isNull(request.session().attribute("uid"));
  }

  protected boolean isLogged() {
    return (boolean) getBaseModel().get("loggedIn");
  }

}
