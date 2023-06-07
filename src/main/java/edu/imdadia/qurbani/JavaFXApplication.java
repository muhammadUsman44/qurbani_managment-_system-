package edu.imdadia.qurbani;

import edu.imdadia.qurbani.config.StageManager;
import edu.imdadia.qurbani.enumuration.FxmlView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFXApplication extends Application {

  protected ConfigurableApplicationContext springContext;
  protected StageManager stageManager;

  @Override
  public void init() {
    springContext = springBootApplicationContext();
  }

  @Override
  public void start(final Stage stage) {
    stageManager = springContext.getBean(StageManager.class, stage);
    displayInitialScene();
  }

  @Override
  public void stop() {
    springContext.close();
  }

  /**
   * Useful to override this method by sub-classes wishing to change the first Scene to be displayed
   * on startup. Example: Functional tests on main window.
   */
  protected void displayInitialScene() {
    stageManager.switchScene(FxmlView.LOGIN);
  }


  private ConfigurableApplicationContext springBootApplicationContext() {
    final SpringApplicationBuilder builder = new SpringApplicationBuilder(
        TheQurbaniShowApplication.class);
    final String[] args = getParameters().getRaw().stream().toArray(String[]::new);
    return builder.run(args);
  }
}
