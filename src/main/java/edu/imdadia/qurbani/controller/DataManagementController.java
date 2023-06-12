package edu.imdadia.qurbani.controller;

import edu.imdadia.qurbani.Entity.DataManagementEntity;
import edu.imdadia.qurbani.config.StageManager;
import edu.imdadia.qurbani.enumuration.FxmlView;
import edu.imdadia.qurbani.service.DataManagementService;
import edu.imdadia.qurbani.util.JavaFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class DataManagementController implements Initializable {

    private final StageManager stageManager;
    private  final DataManagementService dataManagementService;
    @FXML
    private Integer orderIdField;
    @FXML
    private ChoiceBox<String> choiceAnimalBox;
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField mobNoField;
    @FXML
    private TextField paidItemField;
    @FXML
    private TextField unpaidItemField;

    public DataManagementController(@Lazy StageManager stageManager, DataManagementService dataManagementService) {
        this.stageManager = stageManager;
        this.dataManagementService = dataManagementService;

    }

    @FXML
    public void saveButton() {
        if (StringUtils.isBlank(firstNameField.getText())) {
            try {
                DataManagementEntity dataManagement = dataManagementService.findByFirstName(firstNameField.getText());
                if (dataManagement != null) {
                    JavaFXUtils.showWarningMessage("THIS NAME IS ALREADY");
                    firstNameField.requestFocus();
                    return;
                }
            } catch (Exception e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
            }
            DataManagementEntity dataManagement = new DataManagementEntity();
            setValue(dataManagement);
            JavaFXUtils.showSuccessMessage("DATA SAVE SUCCESSFULLY");
            clearButton();
        }else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER FIRSTNAME");
        }

    }

    @FXML
    public void updateButton() {

    }

    @FXML
    public void deleteById() {

    }

    @FXML
    public void deleteAllButton() {

    }

    @FXML
    public void clearButton() {
        firstNameField.clear();
        lastNameField.clear();
        mobNoField.clear();

    }

    @FXML

    public void backButton() {
        stageManager.switchScene(FxmlView.MENU);
    }
    private void setValue(DataManagementEntity dataManagementEntity) {
//        if (dataManagementEntity.getChoiceAnimalBox() != null) {
//            choiceAnimalBox.setValue(dataManagementEntity.getChoiceAnimalBox());
//        }
        if (dataManagementEntity.getPaidItem() != null) {
            paidItemField.setText(String.valueOf(dataManagementEntity.getPaidItem()));
        }
        if (dataManagementEntity.getUnpaidItem() != null) {
            unpaidItemField.setText(String.valueOf(dataManagementEntity.getPaidItem()));
        }
//        if (dataManagementEntity.getPaidItem() != null) {
//            paidItemField.setText(String.valueOf(dataManagementEntity.getPaidItem()));
//
//            if (dataManagementEntity.getPaidItem() != null) {
//                paidItemField.setText(String.valueOf(dataManagementEntity.getPaidItem()));
//        }


        lastNameField.setText(String.valueOf(dataManagementEntity.getLastName()));
        mobNoField.setText(String.valueOf(dataManagementEntity.getMobileNo()));
        firstNameField.setText(dataManagementEntity.getFirstName());
        choiceAnimalBox.setValue(String.valueOf(dataManagementEntity.getChoiceAnimalBox()));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

