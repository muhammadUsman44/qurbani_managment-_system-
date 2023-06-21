package edu.imdadia.qurbani.controller;

import edu.imdadia.qurbani.Entity.DataManagementEntity;
import edu.imdadia.qurbani.Entity.CowDataEntity;
import edu.imdadia.qurbani.config.StageManager;
import edu.imdadia.qurbani.enumuration.FxmlView;
import edu.imdadia.qurbani.service.DataManagementService;
import edu.imdadia.qurbani.util.JavaFXUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


@Controller
public class DataManagementController implements Initializable {

    @FXML
    private TableView<DataManagementEntity> tableView;

    @FXML
    private TableColumn<DataManagementEntity, String> choiceBoxColum;

    @FXML
    private TableColumn<DataManagementEntity, String> phoneNoColum;

    @FXML
    private TableColumn<DataManagementEntity, String> lastNameColum;

    @FXML
    private TableColumn<DataManagementEntity, String> firstNameColum;
    @FXML
    private TableColumn<CowDataEntity, String> dateColum;
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

    @FXML
    private DatePicker deliveryDateDatePicker;

    @FXML
    private TextField searchField;

    private final StageManager stageManager;
    private final DataManagementService dataManagementService;

    public DataManagementController(@Lazy StageManager stageManager, DataManagementService dataManagementService) {
        this.stageManager = stageManager;
        this.dataManagementService = dataManagementService;
    }

    @FXML
    public void saveButton() {

        if (!StringUtils.isBlank(firstNameField.getText())) {
            try {
                    dataManagementService.save(saveRecord(new DataManagementEntity()));
                    JavaFXUtils.showSuccessMessage("DATA SAVED SUCCESSFULLY");
                    setUpTable();
                    clear();


            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER INTEGER NUMBER");
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER ID");
        }
    }


    private DataManagementEntity saveRecord(DataManagementEntity dataManagement) {

        if (!StringUtils.isBlank(firstNameField.getText())) {
            try {
                dataManagement.setFirstName(firstNameField.getText());
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER STRING NUMBER");
                firstNameField.requestFocus();
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER FIRST NAME");
            firstNameField.requestFocus();
        }

        if (!StringUtils.isBlank(lastNameField.getText())) {
            try {
                dataManagement.setLastName(lastNameField.getText());
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER STRING Number");
                lastNameField.requestFocus();
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER LAST NAME");
            lastNameField.requestFocus();
        }
        if (!StringUtils.isBlank(mobNoField.getText())) {
            try {
                dataManagement.setMobileNo(mobNoField.getText());
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER MOBILE NUMBER");
                mobNoField.requestFocus();
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER MOBILE NUMBER");
            firstNameField.requestFocus();
        }

        if (!StringUtils.isBlank(mobNoField.getText())) {
            try {

                dataManagement.setChoiceAnimalBox(choiceAnimalBox.getValue());
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER value CHOICE BOX");
                mobNoField.requestFocus();
            }
        } else {
        JavaFXUtils.showWarningMessage("PLEASE ENTER MOBILE NUMBER");
        firstNameField.requestFocus();
    }
        if (!StringUtils.isBlank(choiceAnimalBox.getValue())) {
            try {

                dataManagement.setChoiceAnimalBox(choiceAnimalBox.getValue());
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER value CHOICE BOX");
                mobNoField.requestFocus();
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER MOBILE NUMBER");
            firstNameField.requestFocus();
        }
        if (!StringUtils.isBlank(paidItemField.getText())) {
            try {

                dataManagement.setPaidItem(Integer.valueOf(paidItemField.getText()));
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER PADE ITEM");
                mobNoField.requestFocus();
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER PADE ITEM");
            firstNameField.requestFocus();
        }
        if (!StringUtils.isBlank(unpaidItemField.getText())) {
            try {

                dataManagement.setUnpaidItem(Integer.valueOf(unpaidItemField.getText()));
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER UNPADE ITEM");
                mobNoField.requestFocus();
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER UNPADE ITEM");
            firstNameField.requestFocus();
        }

        if (!StringUtils.isBlank(String.valueOf(deliveryDateDatePicker.getValue()))){
        try {

            dataManagement.setDeliveryDate(deliveryDateDatePicker.getValue());
        } catch (NumberFormatException e) {
            JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER date");
            mobNoField.requestFocus();
        }
    } else {
        JavaFXUtils.showWarningMessage("PLEASE ENTER date ITEM");
        firstNameField.requestFocus();
    }

       return dataManagementService.save(dataManagement);
    }


//    @FXML
//    public void search() {
//        DataManagementEntity dataManagement;
//        try {
//            dataManagement = dataManagementService.findByFirstName(searchField.getText());
//            if (dataManagement != null) {
//                saveRecord();
//                return;
//            }
//        } catch (RuntimeException e) {
//            JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
//            return;
//        }
//        try {
//            dataManagement = dataManagementService.findByLastName(searchField.getText());
//            if (dataManagement != null) {
//                saveRecord();
//                return;
//            }
//        } catch (RuntimeException e) {
//            JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
//            return;
//        }
//
//        try {
//            dataManagement = dataManagementService.findByMob(searchField.getText());
//            if (dataManagement != null) {
//                saveRecord();
//                return;
//            }
//        } catch (RuntimeException e) {
//            JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
//            return;
//        }
////        try {
////            dataManagement = dataManagementService.f(searchField.getText());
////            if (dataManagement != null) {
////                saveRecord();
////                return;
////            }
////        } catch (RuntimeException e) {
////            JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
////            return;
////        }
//        if (dataManagement == null) {
//            JavaFXUtils.showWarningMessage("THIS RECORD IN NOT ENTERED");
//        }
//    }

    @FXML
    public void delete() {
        if (StringUtils.isBlank(firstNameField.getText())) {
            JavaFXUtils.showError("PLEASE ENTER ID");
        } else {
            dataManagementService.deleteByFirstName(firstNameField.getText());
            JavaFXUtils.showSuccessMessage("DATA DELETE SUCCESSFULLY");
        }
        setUpTable();
        clear();
    }
//
    @FXML
    public void updateButton() {
//        if (!StringUtils.isBlank(firstNameField.getText())) {
//            try {
//                Optional<DataManagementEntity> o = dataManagementService.findByFirstName(firstNameField.getText());
//                OrderEntity orderEntity = o.orElse(null);
//                if (orderEntity != null) {
//                    saveRecord();
//                    setUpTable();
//                    clear();
//                    JavaFXUtils.showSuccessMessage("DATA SAVE SUCCESSFULLY");
//                } else {
//                    JavaFXUtils.showWarningMessage("ALREADY DATA SAVED WITH THIS ID");
//                }
//            } catch (NumberFormatException e) {
//                JavaFXUtils.showWarningMessage(e.getMessage());
//            }
//        } else {
//            JavaFXUtils.showWarningMessage("ID FIELD IS BLANK");
//        }

    }

    @FXML
    public void deleteAllButton() {
        dataManagementService.deleteAll();
        setUpTable();
    }

    @FXML
    public void clearFormButton() {
        clear();
    }

    @FXML
    private void clear() {
        choiceAnimalBox.setValue("");
        firstNameField.clear();
        lastNameField.clear();
        mobNoField.clear();
        paidItemField.clear();
        unpaidItemField.clear();
        deliveryDateDatePicker.getEditor().clear();

    }


    private void setUpTable() {

        List<DataManagementEntity> dataManagementEntities = new ArrayList<>();
        try {
            dataManagementEntities = dataManagementService.getAll();
        } catch (Exception e) {
            JavaFXUtils.showError("Exception FOR ENTER DATA IN TABLE");
        }
        tableView.setItems(FXCollections.observableArrayList(dataManagementEntities));
        this.firstNameColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        this.lastNameColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        this.phoneNoColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMobileNo()));
        this.choiceBoxColum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getChoiceAnimalBox()));
//        this.dateColum.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDeliveryDate())));


        this.tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                saveTableRecord(newSelection);
            }
        });
    }

    public void saveTableRecord(DataManagementEntity dataManagement) {
        firstNameField.setText(dataManagement.getFirstName());
        lastNameField.setText(dataManagement.getLastName());
        mobNoField.setText(dataManagement.getMobileNo());
        deliveryDateDatePicker.setValue(dataManagement.getDeliveryDate());
        choiceAnimalBox.setValue(dataManagement.getChoiceAnimalBox());
    }


    @FXML
    public void backButton() {
        stageManager.switchScene(FxmlView.MENU);
    }
        private List<String>getChoiceBoxC(){

            final  List<String> list = new ArrayList<>();
//     final  List<OrderEntity> list = new ArrayList<>();usmans
//            size = list.size();
//            choiceAnimalBox.setValue(choiceAnimalBox.f);
            list.add("fffff");
     list.add("ddddddd");
     list.add("ededdede");
     list.add("hhhhhh");
     list.add("ggggg");
//    DataManagementEntity dataManagement = new DataManagementEntity();
//        DataManagementService dataManagementService1;
            return list;
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setUpTable();
        } catch (Exception e) {

        }
        choiceAnimalBox.getItems().addAll(getChoiceBoxC());
    }
}


