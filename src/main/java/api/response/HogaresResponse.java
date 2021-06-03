package api.response;


import java.util.List;


public class HogaresResponse {

  private int total;
  private String offset;
  private List<Hogares> hogares;

  public HogaresResponse() {

  }

  public List<Hogares> getHogares() {
    return hogares;
  }

  public void setHogares(List<Hogares> hogares) {
    this.hogares = hogares;
  }


  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public String getOffset() {
    return offset;
  }

  public void setOffset(String offset) {
    this.offset = offset;
  }


}
