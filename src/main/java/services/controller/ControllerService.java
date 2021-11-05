package services.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import controller.BaseController;
import controller.FeaturesController;
import controller.HomeController;
import controller.LoginController;

public class ControllerService {


  private static ControllerService instance;
  private Map<String, BaseController> controllers;

  private ControllerService() {
    controllers = new HashMap<String, BaseController>();
  }

  public static ControllerService getInstance() {
    if (Objects.isNull(instance)) {
      instance = new ControllerService();
      initControllers();
    }
    return instance;
  }

  public Map<String, BaseController> getControllers() {
    return controllers;
  }

  public List<BaseController> getControllersList() {
    return controllers.values().stream().collect(Collectors.toList());
  }

  /**
   * Añade un controller a la lista.
   * 
   * @param controller un nuevo controller a agregar
   */
  public static void addController(BaseController controller) {
    getInstance().getControllers().put(controller.getControllerName(), controller);
  }

  public BaseController getController(String controllerName) {
    return getInstance().getControllers().get(controllerName);
  }


  /**
   * Inicializar todos los controllers.
   */
  private static void initControllers() {
    // * Inicializo el modelo compartido entre los controllers
    BaseController.initBaseModel();

    // ! Todos los controladores deben estar aquí
    // TODO: Agregar controllers
    addController(new LoginController());
    addController(new HomeController());
    addController(new FeaturesController());
  }

}
