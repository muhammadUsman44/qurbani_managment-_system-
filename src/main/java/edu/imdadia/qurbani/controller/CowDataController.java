package edu.imdadia.qurbani.controller;


import edu.imdadia.qurbani.Entity.CowDataEntity;
import edu.imdadia.qurbani.config.StageManager;
import edu.imdadia.qurbani.enumuration.FxmlView;
import edu.imdadia.qurbani.service.CowDataService;
import edu.imdadia.qurbani.util.JavaFXUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class CowDataController implements Initializable {


    @FXML
    private TableView<CowDataEntity> tableView;

    @FXML
    private TableColumn<CowDataEntity, String> idColumn;

    @FXML
    private TableColumn<CowDataEntity, String> nameColumn;

    @FXML
    private TableColumn<CowDataEntity, String> priceColumn;

    @FXML
    private TableColumn<CowDataEntity, String> totalPriceColumn;


    @FXML
    private TextField orderIdField;

    @FXML
    private TextField cowNumberField;
    @FXML
    private TextField cowPriceField;
    @FXML
    private TextField perHeadPriceField;

    private final StageManager stageManager;

    private final CowDataService cowDataService;

    public CowDataController(@Lazy StageManager stageManager, CowDataService cowDataService) {

        this.stageManager = stageManager;
        this.cowDataService = cowDataService;
    }

    @FXML
    public void saveButton() {

        if (!StringUtils.isBlank(orderIdField.getText())) {
            try {
                Optional<CowDataEntity> o = cowDataService.findById(Integer.valueOf(orderIdField.getText()));
                CowDataEntity cowDataEntity = o.orElse(null);
                if (cowDataEntity == null) {
                    saveRecord();
                    JavaFXUtils.showSuccessMessage("DATA SAVED SUCCESSFULLY");
                    setUpTable();
                    clear();
                } else {
                    JavaFXUtils.showWarningMessage("THIS ID ALREADY ENTERED");
                }
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER INTEGER NUMBER");
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER ID");
        }
    }


    private void saveRecord() {
        CowDataEntity cowDataEntity = new CowDataEntity();
        if (!StringUtils.isBlank(orderIdField.getText())) {
            try {
                cowDataEntity.setOrderId(Integer.valueOf(orderIdField.getText()));
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER INTEGER NUMBER");
                orderIdField.requestFocus();
                return;
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER ID");
            orderIdField.requestFocus();
            return;
        }
        if (!StringUtils.isBlank(cowNumberField.getText())) {
            try {
                cowDataEntity.setCowNumberField(cowNumberField.getText());
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER STRING NUMBER");
                cowNumberField.requestFocus();
                return;
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER COW NAME");
            cowNumberField.requestFocus();
            return;
        }
        if (!StringUtils.isBlank(cowPriceField.getText())) {
            try {
                cowDataEntity.setCowPrice(Integer.valueOf(cowPriceField.getText()));
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER INTEGER NUMBER");
                cowPriceField.requestFocus();
                return;
            }

        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER COW PRICE");
            cowPriceField.requestFocus();
            return;
        }
        setPerHeadPriceKey();
        if (!StringUtils.isBlank(perHeadPriceField.getText())) {
            try {
                cowDataEntity.setPerHeadPrice(Integer.valueOf(perHeadPriceField.getText()));
            } catch (NumberFormatException e) {
                JavaFXUtils.showWarningMessage("1");
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER INTEGER NUMBER");
                return;
            }
        }
        cowDataService.save(cowDataEntity);
    }

    @FXML
    public void searchByIdButton() {
        CowDataEntity cowDataEntity = cowDataService.searchByRegNo(Integer.valueOf(orderIdField.getText()));
        if (cowDataEntity != null) {
            if (cowDataEntity.getOrderId() != null) {
                orderIdField.setText(String.valueOf(cowDataEntity.getOrderId()));
            }
            if (cowDataEntity.getCowPrice() != null) {
                cowPriceField.setText(String.valueOf(cowDataEntity.getCowPrice()));
            }
            if (cowDataEntity.getCowNumberField() != null) {
                cowNumberField.setText(cowDataEntity.getCowNumberField());
            }
            if (cowDataEntity.getPerHeadPrice() != null) {
                perHeadPriceField.setText(String.valueOf(cowDataEntity.getPerHeadPrice()));
            }

    }}

    @FXML
    public void delete() {
        if (StringUtils.isBlank(orderIdField.getText())) {
            JavaFXUtils.showError("PLEASE ENTER ID");
        } else {
            cowDataService.deleteById(Integer.valueOf(orderIdField.getText()));
            JavaFXUtils.showSuccessMessage("DATA DELETE SUCCESSFULLY");
        }
        setUpTable();
        clear();
    }

    @FXML
    public void UpdateButton() {
        if (!StringUtils.isBlank(orderIdField.getText())) {
            try {
                Optional<CowDataEntity> o = cowDataService.findById(Integer.valueOf(orderIdField.getText()));
                CowDataEntity cowDataEntity = o.orElse(null);
                if (cowDataEntity != null) {
                    saveRecord();
                    setUpTable();
                    clear();
                    JavaFXUtils.showSuccessMessage("DATA SAVE SUCCESSFULLY");
                } else {
                    JavaFXUtils.showWarningMessage("ALREADY DATA SAVED WITH THIS ID");
                }
            } catch (NumberFormatException e) {
                JavaFXUtils.showWarningMessage(e.getMessage());
            }
        } else {
            JavaFXUtils.showWarningMessage("ID FIELD IS BLANK");
        }

    }

    @FXML
    public void deleteAllButton() {
        cowDataService.deleteAll();
        setUpTable();
    }
//
    @FXML
    public void clearFormButton() {
        clear();
    }


    private void clear() {
        orderIdField.clear();
        cowNumberField.clear();
        cowPriceField.clear();
        perHeadPriceField.clear();
//        deliveryDateDatePicker.getEditor().clear();
    }


    private void setUpTable() {

        List<CowDataEntity> orderEntities = new ArrayList<>();
        try {
            orderEntities = cowDataService.getAll();
        } catch (Exception e) {
            JavaFXUtils.showError("Exception FOR ENTER DATA IN TABLE");
        }
        tableView.setItems(FXCollections.observableArrayList(orderEntities));
        this.idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getOrderId())));
        this.nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCowNumberField()));
        this.priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPerHeadPrice())));
        this.totalPriceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCowPrice())));
        this.tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
               setTableRecord(newSelection);
            }
        });
    }

    private void setTableRecord(CowDataEntity cowDataEntity) {
        orderIdField.setText(String.valueOf(cowDataEntity.getOrderId()));
        cowNumberField.setText(cowDataEntity.getCowNumberField());
        cowPriceField.setText(String.valueOf(cowDataEntity.getCowPrice()));
        perHeadPriceField.setText(String.valueOf(cowDataEntity.getPerHeadPrice()));
    }

    @FXML
    public void backButton() {
        stageManager.switchScene(FxmlView.MENU);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpTable();
    }

    @FXML
    public void setPerHeadPriceKey() {
        if (!StringUtils.isBlank(cowPriceField.getText())){
            perHeadPriceField.setText(String.valueOf(Integer.valueOf(cowPriceField.getText()) /7));
        }
    }


}



