package i18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public class ResourceBundle {

  private static final String DEF_LANG = "en";
  private String lang;
  private Map<String, Object> model = new HashMap<>();
  private Properties properties;

  public ResourceBundle() {
    this(DEF_LANG);
  }

  public ResourceBundle(String lang) {
    this.setLang(lang);
  }

  /**
   * Obtiene el lenguaje default.
   * 
   * @return el fallback language
   */
  public static String defaultLanguage() {
    return DEF_LANG;
  }

  public Object getText(String key) {
    return this.getModel().get(key);
  }

  public String getLang() {
    return lang;
  }

  public Map<String, Object> getModel() {
    return this.model;
  }

  public ResourceBundle setModel(Map<String, Object> map) {
    this.model = map;
    return this;
  }


  public ResourceBundle setLang(String lang) {

    if (Objects.isNull(lang) || lang.isEmpty() || lang.length() < 2) {
      this.lang = DEF_LANG;
    } else {
      this.lang = lang.substring(0, 2);
    }

    return this.loadLanguage();
  }

  public Properties getProps() {
    return this.properties;
  }

  public Map<String, Object> updateMap(String lang, Map<String, Object> map) {

    if (!Objects.isNull(lang) && lang.startsWith(this.getLang())) {
      return map;
    }

    map.put("i18n", this.setLang(lang).getModel());

    return map;
  }

  /**
   * Actualiza las properties y el i18n model.
   * 
   * @return el resource boundle actualizado.
   */
  private ResourceBundle loadLanguage() {
    loadProperties();
    loadModel();
    return this;
  }

  /**
   * Genera el modelo con los textos del idioma cargado.
   * 
   * ? Las keys comienzan con el PREFIX. Ej: PREFIX = i18n>, i18n>menu.
   * 
   */
  private void loadModel() {
    this.setModel(this.getProps().entrySet().stream()
        .collect(Collectors.toMap(e -> String.valueOf(e.getKey()),
            e -> String.valueOf(e.getValue()), (prev, next) -> next, HashMap::new)));
  }

  /**
   * Carga las properties del archivo.
   * 
   * * Debe cumplirse la nomenclatura código de país ISO 639-1 véase el link
   * https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes.
   * 
   * ! IMPORTANT: los archivos deben llamarse i18-{código}.properties
   * 
   * ? De no existir el lenguaje, carga el DEF_LANG: i18n-en.properties
   * 
   */
  private void loadProperties() {
    this.properties = new Properties();

    String fileName = "locales/i18n-".concat(this.getLang()).concat(".properties");

    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

    if (Objects.isNull(inputStream)) {
      inputStream = getClass().getClassLoader()
          .getResourceAsStream(fileName.replace("-".concat(this.getLang()), "-".concat(DEF_LANG)));
      this.lang = defaultLanguage();
    }

    try {
      properties.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}


