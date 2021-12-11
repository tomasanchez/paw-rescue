package controller;

import core.mvc.controller.ControllerInitialization;
import model.mascota.caracteristica.Caracteristica;
import repositories.RepoCaracteristicas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class FeaturesController extends BaseController {


  /* =========================================================== */
  /* Overridables ---------------------------------------------- */
  /* =========================================================== */

  @Override
  protected ControllerInitialization getInitialization() {
    return ControllerInitialization.FULL_CRUD;
  }

  /* =========================================================== */
  /* Lifecycle methods ----------------------------------------- */
  /* =========================================================== */

  @Override
  protected void onInit() {
    // TODO Auto-generated method stub

  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {
    onRequirePrivileges(request, response);
  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    // TODO Auto-generated method stub

  }

  /* =========================================================== */
  /* Request Handling ------------------------------------------ */
  /* =========================================================== */

  @Override
  protected ModelAndView onPost(Request request, Response response) {

    onRequirePrivileges(request, response);
    onTransactionalOperation(response, () -> RepoCaracteristicas.getInstance()
        .createEntity(new Caracteristica(request.queryParams("valor"))));

    return super.onPost(request, response);
  }


  @Override
  protected Object onDeleteResponse(Request request, Response response) {
    Long id = Long.parseLong(request.params(":id"));
    onTransactionalOperation(response, () -> RepoCaracteristicas.getInstance().deleteEntity(id));
    return null;
  }

  @Override
  protected Object onPutResponse(Request request, Response response) {
    Long id = Long.parseLong(request.params(":id"));
    String valor = request.params(":valor");
    Caracteristica c = RepoCaracteristicas.getInstance().getEntity(id);
    c.setValor(valor);
    onTransactionalOperation(response, () -> RepoCaracteristicas.getInstance().updateEntity(c));
    return null;
  }

}
