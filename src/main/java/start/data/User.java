package start.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class User {
  private int id;
  @Length(max = 140)
  private String name;
  private String password;

  User() {

  }

  @JsonProperty
  public int getId() {
    return id;
  }

  @JsonProperty
  public void setId(int id) {
    this.id = id;
  }

  @JsonProperty
  public String getName() {
    return name;
  }

  @JsonProperty
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty
  public String getPassword() {
    return password;
  }

  @JsonProperty
  public void setPassword(String password) {
    this.password = password;
  }
}
