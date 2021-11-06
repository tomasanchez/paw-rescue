package controller;

import app.Router;
import model.mascota.caracteristica.Caracteristica;
import repositories.RepoCaracteristicas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public class FeaturesController extends BaseController {

  @Override
  protected void onInit() {
    onSetPost();
  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {
    requiereSession(request, response);
    updateFeaturesSet();
  }

  private void updateFeaturesSet() {
    this.getModel().put("caracteristicas", RepoCaracteristicas.getInstance().getEntitySet());
  }

  private void onSetPost() {
    Spark.post(this.getPath().concat("/:id"), (req, res) -> this.onMerge(req, res),
        Router.getEngine());

    Spark.post(this.getPath(), (req, res) -> this.onPost(req, res), Router.getEngine());
  }

  private ModelAndView onPost(Request req, Response res) {
    requiereSession(req, res);
    try {
      withTransaction(() -> RepoCaracteristicas.getInstance()
          .createEntity(new Caracteristica(req.queryParams("valor"))));
      res.status(201);
    } catch (RuntimeException e) {
      res.status(500);
    }

    return this.getViewModel(req, res);
  }

  private ModelAndView onMerge(Request req, Response res) {
    requiereSession(req, res);
    Long id = Long.parseLong(req.params(":id"));
    Boolean del = Boolean.parseBoolean(req.queryParams("delete"));

    if (del) {
      this.onDeleteFeature(id, res);
    } else {
      this.onUpdateFeature(id, req.queryParams("valor"), res);
    }

    res.redirect(this.getPath());

    return null;
  }

  private void onUpdateFeature(Long id, String valor, Response res) {

    try {
      Caracteristica c = RepoCaracteristicas.getInstance().getEntity(id);
      c.setValor(valor);
      withTransaction(() -> RepoCaracteristicas.getInstance().updateEntity(c));
      res.status(200);
    } catch (RuntimeException e) {
      res.status(500);
    }

  }

  private void onDeleteFeature(Long id, Response res) {
    try {
      withTransaction(() -> RepoCaracteristicas.getInstance().deleteEntity(id));
      res.status(200);
    } catch (RuntimeException e) {
      res.status(500);
    }
  }

}
