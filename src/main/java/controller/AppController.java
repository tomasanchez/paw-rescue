package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import i18n.ResourceBundle;
import spark.Request;

public class AppController {

  private volatile Map<String, Object> appModel = new HashMap<>();
  private volatile ResourceBundle resourceBundle = new ResourceBundle();

  AppController() {
    getAppModel().put("loggedIn", false);
    getAppModel().put("user", null);
    getAppModel().put("isAdmin", false);
    getAppModel().put("isVoluntario", false);
    getAppModel().put("userPrivilege", 0);
    getAppModel().put("language", null);
    getAppModel().put("i18n", resourceBundle.getModel());
    getAppModel().put("navigation", new HashMap<String, Object>());
    getAppModel().put("hrefs", new HashMap<String, Object>());

  }

  public Map<String, Object> getAppModel() {
    return appModel;
  }

  public void setAppModel(Map<String, Object> appModel) {
    this.appModel = appModel;
  }

  @SuppressWarnings("unchecked")
  public Map<String, Object> getNavModel() {
    return (Map<String, Object>) getAppModel().get("navigation");
  }

  public ResourceBundle getResourceBundle() {
    return resourceBundle;
  }

  public void setResourceBundle(ResourceBundle resourceBundle) {
    this.resourceBundle = resourceBundle;
  }

  public void updateLanguage(Request request) {
    if (Objects.isNull(getAppModel().get("language"))) {
      getResourceBundle().updateMap(request.headers("Accept-Language"), getAppModel());
    } else {
      getResourceBundle().updateMap(String.valueOf(getAppModel().get("language")), getAppModel());
    }
  }

  public void updateNavigationModel(String currentView) {
    getNavModel().replaceAll((k, v) -> v = k.equals(currentView) ? "active" : "");
  }

}
