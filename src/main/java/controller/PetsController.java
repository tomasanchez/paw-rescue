package controller;

import static spark.Spark.delete;

import app.Router;
import repositories.RepoMascotas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PetsController extends BaseController {

  @Override
  protected void onInit() {
    getModel().put("showToast", false);
    onSetEndPoints();
  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {
    requiereSession(request, response);
    onRefreshUser();
  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    onInitToast();
  }

  private void onSetEndPoints() {
    delete(getPath().concat("/:id"), (req, res) -> this.onDelete(req, res), Router.getEngine());
  }

  private ModelAndView onDelete(Request req, Response res) {
    requiereSession(req, res);
    Long id = Long.parseLong(req.params(":id"));

    try {
      withTransaction(() -> new RepoMascotas().deleteEntity(id));
      onSwitchToast(true);
      res.status(200);
    } catch (RuntimeException e) {
      onSwitchToast(false);
      res.status(500);
    }

    return getViewModel();
  }
}
