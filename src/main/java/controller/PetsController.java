package controller;

import spark.Request;
import spark.Response;

public class PetsController extends BaseController {

  @Override
  protected void onInit() {
    // TODO Auto-generated method stub
    getModel().put("showToast", false);
  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {
    requiereSession(request, response);
  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    // TODO Auto-generated method stub
  }

}
