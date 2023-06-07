package edu.imdadia.qurbani.controller;

import edu.imdadia.qurbani.Entity.UserEntity;
import edu.imdadia.qurbani.config.StageManager;
import edu.imdadia.qurbani.enumuration.FxmlView;
import edu.imdadia.qurbani.service.UserService;
import edu.imdadia.qurbani.util.JavaFXUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;


@Controller
public class LoginController implements Initializable {

    @FXML
    private TextField userNameField;

    @FXML
    private TextField passwordField;
    @FXML
    TextField plainPasswordField;

    private final UserService userService;
    private final StageManager stageManager;

    public LoginController(UserService userService, @Lazy final StageManager stageManager) {
        this.userService = userService;
        this.stageManager = stageManager;
    }

    @FXML
    public void loginButton() {
        if (StringUtils.isBlank(userNameField.getText())) {
            JavaFXUtils.showError("WRONG USER NAME");
            this.userNameField.requestFocus();
            return;
        }
        if (StringUtils.isBlank(passwordField.getText())) {
            JavaFXUtils.showError("WRONG PASSWORD");
            this.passwordField.requestFocus();
            return;
        }


        if (search(userNameField.getText()) != null) {
            UserEntity user = search(userNameField.getText());
            if (user.getUserPassword().equals(passwordField.getText())) {
                stageManager.switchSceneMax(FxmlView.MENU);
                UserInfoController.userInfo = user;
            } else {
                JavaFXUtils.showError("USER NAME OR PASSWORD IS INCORRECT");
            }
        } else {
            JavaFXUtils.showError("USER NAME OR PASSWORD IS INCORRECT");
        }
    }


    @FXML
    public void forgetPasswordButton() {
        UserEntity user = new UserEntity();
        if (search(userNameField.getText()) != null) {
            user = search(userNameField.getText());
            stageManager.switchScene(FxmlView.CHANGE_PASSWORD);
            ChangePasswordController.userName = userNameField.getText();
        } else {
            JavaFXUtils.showError("USER NAME IS INCORRECT");
        }
    }





    private UserEntity search(String userName) {
        return userService.searchByUserName(userName);
    }

    @FXML
    public void sighUpButton() {
        stageManager.switchScene(FxmlView.USER);
    }

    @FXML
    public void exitButton() {
        JavaFXUtils.showSuccessMessage("Your code ended successfully.");
        Platform.exit();
    }

    @FXML
    public void checkBox() {
        plainPasswordField.setVisible(!plainPasswordField.isVisible());
        passwordField.setVisible(!passwordField.isVisible());
        plainPasswordField.setText(passwordField.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        plainPasswordField.setVisible(false);
    }
}
