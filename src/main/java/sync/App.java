package sync;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import sync.access.DAO;
import sync.access.DAOFactory;
import sync.api.impl.UserTaskAPIImpl;
import sync.resources.TaskResource;
import sync.resources.UserResource;
import sync.resources.UserTaskResource;

public class App extends Application<Config> {
  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void run(Config config, Environment environment) throws Exception {
    DAO dao = DAOFactory.getDAO(config.getDBConfig(), environment);
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
