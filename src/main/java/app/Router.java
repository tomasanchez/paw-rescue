package app;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;
import static spark.debug.DebugScreen.enableDebugScreen;

import core.mvc.controller.Controller;
import core.services.ControllerLoaderService;
import spark.TemplateEngine;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

  private static Integer PORT = 7070;
  private static final TemplateEngine ENGINE = new HandlebarsTemplateEngine();

  public static void main(String[] args) {
    // new SetUp().bootStrap();
    // ! DISABLE DEBUGGER SCREEB WHEN IN PRODUCTION
    enableDebugScreen();
    server();
    RescatePatitas.main(args);
  }

  private static void server() {
    System.out.println("Initializing server...");
    PORT = getHerokuAssignedPort();
    port(PORT);
    System.out.println(
        "\u001B[32mServer started at http://localhost:".concat(PORT.toString()).concat("/\033[0m"));
    staticFileLocation("/public");
    serveRoutes();
  }

  private static void serveRoutes() {
    // ! INJECT Template Engine into controller class
    Controller.setEngine(ENGINE);
    ControllerLoaderService.getService().findAll();
  }

  private static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
  }

}
