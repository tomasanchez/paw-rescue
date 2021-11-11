package app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;
import spark.debug.DebugScreen;

import services.controller.ControllerService;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  private static final Integer PORT = 8080;
  private static final HandlebarsTemplateEngine ENGINE = new HandlebarsTemplateEngine();

  public static void main(String[] args) {
    new SetUp().bootStrap();
    DebugScreen.enableDebugScreen();
    run();
  }

  private static void run() {
    startServer();
    startRoutes();
  }

  public static HandlebarsTemplateEngine getEngine() {
    return ENGINE;
  }

  private static void startRoutes() {
    ControllerService controllerService = ControllerService.getInstance();
    
    // TODO: Home Page
    get("/", (req, res) -> {
      res.redirect(controllerService.getController("home").getPath());
      return null;
    });

    // !IMPORTANT: Todos los controladores deben añadire al controller service
    controllerService.getControllersList()
        .forEach(controller -> get(controller.getPath(), controller::getViewModel, ENGINE));


    System.out.println("Server Initialized!");
  }

  /**
   * Abre el puerto de escucha, inicializando el servidor.
   */
  private static void startServer() {
    System.out.println("Initializing server...");
    port(PORT);
    System.out.println("Listening to port " + PORT);
    staticFileLocation("/public");
  }

}
