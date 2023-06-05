package edu.imdadia.tailor.controller;

import edu.imdadia.tailor.Entity.UserEntity;
import edu.imdadia.tailor.config.StageManager;
import edu.imdadia.tailor.enumuration.FxmlView;
import edu.imdadia.tailor.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class UserInfoController implements Initializable {

    private final StageManager stageManager;

    private final UserService userService;
    protected static UserEntity userInfo;
    @FXML
    private Label addressField;


    @FXML
    private Label mobileNoField;

    @FXML
    private Label userNameField;

    @FXML
    private Label nameField;


    public UserInfoController(@Lazy StageManager stageManager, UserService userService) {
        this.stageManager = stageManager;
        this.userService = userService;
    }

    @FXML
    public void backButton(){
        stageManager.switchSceneMax(FxmlView.MENU);
    }

    @FXML
    public void deleteButton(){
        try {
            userService.delete(userService.searchByUserName(userInfo.getUserName()));
            stageManager.switchScene(FxmlView.LOGIN);
        }catch (Exception e){

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addressField.setText(userInfo.getAddress());
        nameField.setText(userInfo.getFirstName()+" "+userInfo.getLastName());
        userNameField.setText(userInfo.getUserName());
        mobileNoField.setText(userInfo.getMobileNo());
    }
}
