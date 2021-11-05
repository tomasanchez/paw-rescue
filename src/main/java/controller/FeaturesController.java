package controller;

import spark.Request;
import spark.Response;

public class FeaturesController extends BaseController {

  @Override
  protected void onInit(Request request, Response response) {
    requiereSession(request, response);
  }

}
