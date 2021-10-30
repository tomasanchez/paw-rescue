package app;

import services.controller.ControllerService;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  private static final Integer PORT = 8080;
  private static final HandlebarsTemplateEngine ENGINE = new HandlebarsTemplateEngine();

  public static void main(String[] args) {
    run();
  }

  private static void run() {
    startServer();
    startRoutes();
  }

  private static void startRoutes() {
    ControllerService controllerService = ControllerService.getInstance();

    // TODO: Home Page
    Spark.get("/", (req, res) -> {
      res.redirect(controllerService.getController("login").getPath());
      return null;
    });

    // !IMPORTANT: Todos los controladores deben añadire al controller service
    controllerService.getControllersList()
        .forEach(controller -> Spark.get(controller.getPath(), controller::getViewModel, ENGINE));

    System.out.println("Server Initialized!");
  }

  /**
   * Abre el puerto de escucha, inicializando el servidor.
   */
  private static void startServer() {
    System.out.println("Initializing server...");
    Spark.port(PORT);
    System.out.println("Listening to port " + PORT);
    Spark.staticFileLocation("/public");
  }

}
