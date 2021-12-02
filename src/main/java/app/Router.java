package app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import spark.debug.DebugScreen;

import services.controller.ControllerService;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  private static Integer PORT = 8080;
  private static final HandlebarsTemplateEngine ENGINE = new HandlebarsTemplateEngine();

  public static void main(String[] args) {
    // new SetUp().bootStrap();
    DebugScreen.enableDebugScreen();
    run();
    RescatePatitas.main(args);
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
    PORT = getHerokuAssignedPort();
    port(PORT);
    System.out.println("Listening to port " + PORT);
    staticFileLocation("/public");
  }

  private static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
  }
}
