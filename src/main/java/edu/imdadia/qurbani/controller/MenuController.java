package edu.imdadia.qurbani.controller;

import edu.imdadia.qurbani.config.SpringFXMLLoader;
import edu.imdadia.qurbani.config.StageManager;
import edu.imdadia.qurbani.enumuration.FxmlView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MenuController implements Initializable {

    private final StageManager stageManager;

    private final SpringFXMLLoader springFXMLLoader;


    @FXML
    private BorderPane rootBorderPane;


    public MenuController(@Lazy StageManager stageManager, SpringFXMLLoader springFXMLLoader) {
        this.stageManager = stageManager;
        this.springFXMLLoader = springFXMLLoader;
    }

    @FXML
    public void helpButton() throws IOException {
        switchView(FxmlView.HELP);
    }


    public void dataManagement() throws Exception {
        switchView(FxmlView.dataManagement);
    }

    public void order() throws Exception {
        switchView(FxmlView.ORDER);
    }

    @FXML
    public void user() throws Exception {
        switchView(FxmlView.USER);
    }


    @FXML
    public void measurementTableButton() throws IOException{
        switchView(FxmlView.MEAUSREMENT_TABLE);
    }


    @FXML
    public void userInfoButton() throws IOException {
        switchView(FxmlView.USER_INFORMATION);
    }

    @FXML
    public void close() {
        Platform.exit();
    }


    @FXML
    private void logoutButton(){
        stageManager.switchScene(FxmlView.LOGIN);
    }

    private void switchView(final FxmlView fxmlView) throws IOException {
        final Parent view = springFXMLLoader.load(fxmlView.getFxmlFile());
        stageManager.getStage().setTitle(fxmlView.getTitle());
        rootBorderPane.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            switchView(FxmlView.HOME);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
