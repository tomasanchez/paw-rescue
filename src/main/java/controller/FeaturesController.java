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
    this.getModel().put("showToast", false);
  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {
    requiereSession(request, response);
    requireAdmin(request, response);
    updateFeaturesSet();
    this.getModel().put("toastStatus", "bg-success");
    this.getModel().put("toastMessage", getResourceBundle().getText("featureSuccess"));
  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    this.getModel().put("showToast", false);
  }

  private void updateFeaturesSet() {
    this.getModel().remove("caracteristicas");
    this.getModel().put("caracteristicas", RepoCaracteristicas.getInstance().getEntitySet());
  }

  private void onSetPost() {
    Spark.post(this.getPath().concat("/:id"), (req, res) -> this.onMerge(req, res),
        Router.getEngine());

    Spark.post(this.getPath(), (req, res) -> this.onPost(req, res), Router.getEngine());
  }

  private ModelAndView onPost(Request req, Response res) {
    requiereSession(req, res);

    this.getModel().put("showToast", true);

    try {
      withTransaction(() -> RepoCaracteristicas.getInstance()
          .createEntity(new Caracteristica(req.queryParams("valor"))));
      res.status(201);
    } catch (RuntimeException e) {
      res.status(500);
      this.getModel().put("toastStatus", "bg-error");
      this.getModel().put("toastMessage", getResourceBundle().getText("featureError"));
    }

    return this.getViewModel(req, res);
  }

  private ModelAndView onMerge(Request req, Response res) {
    requiereSession(req, res);
    Long id = Long.parseLong(req.params(":id"));
    Boolean del = Boolean.parseBoolean(req.queryParams("delete"));

    try {
      if (del) {
        this.onDeleteFeature(id, res);
      } else {
        this.onUpdateFeature(id, req.queryParams("valor"), res);
      }
      res.status(200);
    } catch (RuntimeException e) {
      this.getModel().put("toastStatus", "bg-error");
      this.getModel().put("toastMessage", getResourceBundle().getText("featureError"));
      res.status(500);
    }

    this.getModel().put("showToast", true);
    res.redirect(this.getPath());
    return null;
  }

  private void onUpdateFeature(Long id, String valor, Response res) {

    Caracteristica c = RepoCaracteristicas.getInstance().getEntity(id);
    c.setValor(valor);
    withTransaction(() -> RepoCaracteristicas.getInstance().updateEntity(c));
    res.status(200);

  }

  private void onDeleteFeature(Long id, Response res) {
    withTransaction(() -> RepoCaracteristicas.getInstance().deleteEntity(id));
    res.status(200);
  }

}
