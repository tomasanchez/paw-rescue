package i18n;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResourceBundleTest {

  @Test
  void i18nUp() {
    Assertions.assertNotNull(new ResourceBundle().getText("test"));
  }

  @Test
  void i18nReplaces() {
    ResourceBundle bundle = new ResourceBundle();
    Map<String, Object> map = new HashMap<>();
    bundle.updateMap(null, map);
    Assertions.assertFalse(map.isEmpty());
  }

  @Test
  void i18nMultipleLanguages() {
    ResourceBundle bundleEN = new ResourceBundle();
    ResourceBundle bundleFR = new ResourceBundle("fr");
    Assertions.assertNotEquals(bundleEN.getText("test"), bundleFR.getText("test"));
  }
}
