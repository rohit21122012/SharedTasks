package start;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import start.resources.TaskResource;

public class App extends Application<Config> {
  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void run(Config config, Environment environment) throws Exception {
    environment.jersey().register(new TaskResource());
    environment.healthChecks().register("statusHealth", new HealthCheck() {
      @Override
      protected Result check() throws Exception {
        return Result.healthy();
      }
    });
  }
}
