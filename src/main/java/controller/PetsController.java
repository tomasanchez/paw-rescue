package controller;

import static spark.Spark.post;

import app.Router;
import repositories.RepoMascotas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PetsController extends BaseController {

  @Override
  protected void onInit() {
    getModel().put("showToast", false);
    onSetPost();
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

  private void onSetPost() {
    post(getPath().concat("/:id"), (req, res) -> this.onMerge(req, res), Router.getEngine());
  }

  private ModelAndView onMerge(Request req, Response res) {
    requiereSession(req, res);

    Long id = Long.parseLong(req.params(":id"));
    Boolean del = Boolean.parseBoolean(req.queryParams("delete"));


    try {
      if (del) {
        onDelete(id);
      } else {
        onUpdate(id, req);
      }
      onSwitchToast(true);
      res.status(200);
    } catch (RuntimeException e) {
      onSwitchToast(false);
      res.status(500);
    }

    return this.getViewModel();
  }


  private void onDelete(Long id) {
    withTransaction(() -> new RepoMascotas().deleteEntity(id));
  }

  private void onUpdate(Long id, Request req) {

  }

}
