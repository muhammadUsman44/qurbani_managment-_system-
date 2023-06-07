package edu.imdadia.qurbani.controller;


import edu.imdadia.qurbani.Entity.UserEntity;
import edu.imdadia.qurbani.config.StageManager;

import edu.imdadia.qurbani.enumuration.FxmlView;
import edu.imdadia.qurbani.service.UserService;
import edu.imdadia.qurbani.util.JavaFXUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final StageManager stageManager;

    private final UserService userService;
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField userPasswordField;

    @FXML
    private TextField mobNoField;

    @FXML
    private TextField address;


    public UserController(@Lazy final StageManager stageManager, UserService userService) {
        this.stageManager = stageManager;

        this.userService = userService;
    }

    @FXML
    public void saveButton() {
        if (StringUtils.isBlank(firstNameField.getText())) {
            JavaFXUtils.showError("FIRST NAME IS REQUIRED");
            firstNameField.requestFocus();
            return;
        }

        if (StringUtils.isBlank(lastNameField.getText())) {
            JavaFXUtils.showError("LAST NAME IS REQUIRED");
            lastNameField.requestFocus();
            return;
        }

        if (StringUtils.isBlank(userNameField.getText())) {
            JavaFXUtils.showError("USER NAME IS REQUIRED");
            userNameField.requestFocus();
            return;
        }

        if (StringUtils.isBlank(userPasswordField.getText())) {
            JavaFXUtils.showError("PASSWORD IS REQUIRED");
            userPasswordField.requestFocus();
            return;
        }

        if (StringUtils.isBlank(address.getText())) {
            JavaFXUtils.showError("ADDRESS IS REQUIRED");
            address.requestFocus();
            return;
        }


        if (StringUtils.isBlank(mobNoField.getText())) {
            JavaFXUtils.showError("MOBILE NO IS REQUIRED");
            mobNoField.requestFocus();
            return;
        }


        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userNameField.getText());
        userEntity.setUserPassword(userPasswordField.getText());
        userEntity.setFirstName(firstNameField.getText());
        userEntity.setLastName(lastNameField.getText());
        userEntity.setMobileNo(mobNoField.getText());
        userEntity.setAddress(address.getText());
        if (search(userNameField.getText()) == null) {
            userService.save(userEntity);
            JavaFXUtils.showSuccessMessage("USER SAVED SUCCESSFULLY");
            clearButton();
        }else {
            JavaFXUtils.showError("THIS USER NAME IS ALREADY USE");
        }
    }

    private UserEntity search(String userName) {
        return userService.searchByUserName(userName);
    }

    @FXML
    public void clearButton(){
        firstNameField.clear();
        lastNameField.clear();
        userNameField.clear();
        userPasswordField.clear();
        address.clear();
        mobNoField.clear();
    }
    @FXML
    public void backButton(){
        stageManager.switchScene(FxmlView.LOGIN);
    }

}
