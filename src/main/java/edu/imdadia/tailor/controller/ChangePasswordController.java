package edu.imdadia.tailor.controller;

import edu.imdadia.tailor.Entity.UserEntity;
import edu.imdadia.tailor.config.StageManager;
import edu.imdadia.tailor.enumuration.FxmlView;
import edu.imdadia.tailor.service.UserService;
import edu.imdadia.tailor.util.JavaFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;


@Controller
public class ChangePasswordController {

    private final StageManager stageManager;
    private final UserService userService;
    @FXML
    private TextField reEnterPasswordField;


    @FXML
    private TextField mobNoField;


    @FXML
    private TextField newPasswordField;

    protected static String userName;

    public ChangePasswordController(@Lazy StageManager stageManager, UserService userService) {
        this.stageManager = stageManager;
        this.userService = userService;
    }


    @FXML
    public void backButton() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    @FXML
    public void changePasswordButton() {
        UserEntity user = userService.searchByUserName(userName);
        if (user != null) {
            if (user.getMobileNo().equals(mobNoField.getText())) {
                if (StringUtils.isBlank(newPasswordField.getText())) {
                    JavaFXUtils.showError("PLEASE ENTER THE PASSWORD");
                    newPasswordField.requestFocus();
                    return;
                }
                if (StringUtils.isBlank(reEnterPasswordField.getText())) {
                    JavaFXUtils.showError("PLEASE ENTER THE PASSWORD");
                    reEnterPasswordField.requestFocus();
                    return;
                }
                if (newPasswordField.getText().equals(reEnterPasswordField.getText())) {
                    user.setUserPassword(newPasswordField.getText());
                    userService.save(user);
                    JavaFXUtils.showSuccessMessage("PASSWORD CHANGE SUCCESSFULLY");
                } else {
                    JavaFXUtils.showError("PASSWORD ARE NOT SIMILAR");
                }
            } else {
                JavaFXUtils.showError("PLEASE  ENTER THE CORRECT NUMBER");
            }
        }
    }
}
