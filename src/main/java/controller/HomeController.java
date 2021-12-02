package controller;

import spark.Request;
import spark.Response;

public class HomeController extends BaseController {

  @Override
  public String getPath() {
    return "/";
  }

  @Override
  protected void onInit() {
    // TODO Auto-generated method stub

  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {
    // TODO Auto-generated method stub
  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    // TODO Auto-generated method stub

  }

}
