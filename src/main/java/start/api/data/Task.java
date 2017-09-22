package start.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;


public class Task {
  private int id;
  @Length(max = 140)
  private String title;
  @Length(max = 1400)
  private String description;

  Task() {
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
  public String getTitle() {
    return title;
  }

  @JsonProperty
  public void setTitle(String title) {
    this.title = title;
  }

  @JsonProperty
  public String getDescription() {
    return description;
  }

  @JsonProperty
  public void setDescription(String description) {
    this.description = description;
  }
}
