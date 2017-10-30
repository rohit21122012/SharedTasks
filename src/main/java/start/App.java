package start;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import start.access.DAO;
import start.access.DAOFactory;
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
    DAO dao = DAOFactory.getDAO(config.getStoreName(), environment);
    environment.jersey().register(new TaskResource(dao.getTaskDAO()));
    environment.jersey().register(new UserResource(dao.getUserDAO()));
    environment.jersey().register(new UserTaskResource(new UserTaskAPIImpl(dao)));
    environment.healthChecks().register("statusHealth", new HealthCheck() {
      @Override
      protected Result check() throws Exception {
        return Result.healthy();
      }
    });
  }
}
