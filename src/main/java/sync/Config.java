package sync;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class Config extends Configuration {
  private DBConfig dbConfig;

  @JsonProperty
  DBConfig getDBConfig() {
    return dbConfig;
  }

  @JsonProperty
  void setDbConfig(DBConfig dbConfig) {
    this.dbConfig = dbConfig;
  }

  public class DBConfig {
    @NotEmpty
    private String dbName;

    private Curator zk;

    @JsonProperty
    public Curator getZk() {
      return zk;
    }

    @JsonProperty
    public void setZk(Curator zk) {
      this.zk = zk;
    }

    public String getDbName() {
      return dbName;
    }

    public void setDbName(String dbName) {
      this.dbName = dbName;
    }

    public class Curator {
      private String connectString;
      private int baseSleepTimeMs;
      private int maxRetries;

      @JsonProperty
      public String getConnectString() {
        return connectString;
      }

      @JsonProperty
      public void setConnectString(String connectString) {
        this.connectString = connectString;
      }

      @JsonProperty
      public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
      }

      @JsonProperty
      public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
      }

      @JsonProperty
      public int getMaxRetries() {
        return maxRetries;
      }

      @JsonProperty
      public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
      }
    }
  }
}
