package model.pregunta.publicaciones;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PregPubKey implements Serializable {

  @Column(name = "preg_id")
  Long pregId;

  @Column(name = "pub_id")
  Long pubId;

  public Long getPregId() {
    return pregId;
  }

  public void setPregId(Long pregId) {
    this.pregId = pregId;
  }

  public Long getPubId() {
    return pubId;
  }

  public void setPubId(Long pubId) {
    this.pubId = pubId;
  }

}
