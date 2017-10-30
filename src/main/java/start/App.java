package start;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import start.access.DAOFactory;
import start.access.DataAcessFactory;
import start.api.UserTaskAPI;
import start.api.impl.UserTaskAPIImpl;
import start.resources.TaskResource;
import start.resources.UserResource;
import start.resources.UserTaskResource;

public class App extends Application<Config> {
  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void run(Config config, Environment environment) throws Exception {
    DAOFactory daoFactory = DataAcessFactory.getDAOFactory(config.getStoreName());
    UserTaskAPI userTaskAPI = new UserTaskAPIImpl(daoFactory);
    environment.jersey().register(new TaskResource(daoFactory));
    environment.jersey().register(new UserResource(daoFactory));
    environment.jersey().register(new UserTaskResource(userTaskAPI));
    environment.healthChecks().register("statusHealth", new HealthCheck() {
      @Override
      protected Result check() throws Exception {
        return Result.healthy();
      }
    });
  }
}
