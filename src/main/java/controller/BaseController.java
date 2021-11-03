package controller;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public abstract class BaseController {

  /**
   * View Model.
   */
  private Map<String, Object> model = new HashMap<String, Object>();

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
    this.onInit(request, response);
    return new ModelAndView(this.getModel(), this.getViewName());
  }

  /**
   * Redibuja un ModelAndView.
   * 
   * @return el nuevo model and view;
   */
  public ModelAndView getViewModel() {
    return new ModelAndView(this.getModel(), this.getViewName());
  }

  /**
   * Funcion llamada antes de obtener el View Model.
   * 
   * @param request la HTTP request.
   * @param response la HTTP response.
   */
  protected abstract void onInit(Request request, Response response);

}
