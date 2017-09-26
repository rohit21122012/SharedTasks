package start;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import start.api.impl.InMemoryAPI;
import start.resources.TaskResource;
import start.resources.UserResource;

public class App extends Application<Config> {
  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void run(Config config, Environment environment) throws Exception {
    InMemoryAPI inMemoryAPI = new InMemoryAPI();
    environment.jersey().register(new TaskResource(inMemoryAPI));
    environment.jersey().register(new UserResource(inMemoryAPI));
    environment.healthChecks().register("statusHealth", new HealthCheck() {
      @Override
      protected Result check() throws Exception {
        return Result.healthy();
      }
    });
  }
}
