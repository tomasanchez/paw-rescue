package services.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import controller.BaseController;
import controller.ContactController;
import controller.FeaturesController;
import controller.HomeController;
import controller.LoginController;
import controller.PetsController;
import controller.RegisterPetController;
import controller.RescueController;
import controller.SignUpController;



public class ControllerService {


  private static ControllerService instance;
  private Map<String, BaseController> controllers;

  private ControllerService() {
    controllers = new HashMap<String, BaseController>();
    initControllers();
  }

  public static ControllerService getInstance() {
    if (Objects.isNull(instance)) {
      instance = new ControllerService();
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
  public void addController(BaseController controller) {
    this.getControllers().put(controller.getControllerName(), controller);
  }

  public BaseController getController(String controllerName) {
    return this.getControllers().get(controllerName);
  }


  /**
   * Inicializar todos los controllers.
   */
  private void initControllers() {
    // ! Todos los controladores deben estar aquí
    // TODO: Agregar controllers
    addController(new HomeController());
    addController(new LoginController());
    addController(new SignUpController());
    addController(new PetsController());
    addController(new RegisterPetController());
    addController(new FeaturesController());
    addController(new RescueController());
    addController(new ContactController());
  }

}
