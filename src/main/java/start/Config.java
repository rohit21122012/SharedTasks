package start;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

class Config extends Configuration {
  @NotEmpty
  private String storeName;

  @JsonProperty
  String getStoreName() {
    return storeName;
  }

  @JsonProperty
  void setStoreName(String storeName) {
    this.storeName = storeName;
  }
}
