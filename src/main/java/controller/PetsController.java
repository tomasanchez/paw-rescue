package controller;

import core.mvc.controller.ControllerInitialization;
import model.usuario.Usuario;
import repositories.RepoMascotas;
import repositories.RepoUsers;
import spark.Request;
import spark.Response;

public class PetsController extends BaseController {

  private RepoUsers usersRepository = new RepoUsers();
  private RepoMascotas petRepository = new RepoMascotas();

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
    onRequireSession(request, response);
    onRefreshUser();
  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    // TODO Auto-generated method stub
  }

  /* =========================================================== */
  /* Request Handling ------------------------------------------ */
  /* =========================================================== */

  @Override
  protected Object onDeleteResponse(Request request, Response response) {
    onRequireSession(request, response);

    Long id = Long.parseLong(request.params(":id"));
    Usuario user = onRefreshUser();

    onTransactionalOperation(response, () -> {
      usersRepository.updateEntity(user.deleteMascota(id));
      petRepository.deleteEntity(id);
    });

    return super.onDeleteResponse(request, response);
  }

}
