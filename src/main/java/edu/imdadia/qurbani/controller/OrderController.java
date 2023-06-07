package edu.imdadia.qurbani.controller;


import edu.imdadia.qurbani.Entity.OrderEntity;
import edu.imdadia.qurbani.config.StageManager;
import edu.imdadia.qurbani.enumuration.FxmlView;
import edu.imdadia.qurbani.service.OrderService;
import edu.imdadia.qurbani.util.JavaFXUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
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
public class OrderController implements Initializable {


    @FXML
    private TableView<OrderEntity> tableView;

    @FXML
    private TableColumn<OrderEntity, String> idColumn;

    @FXML
    private TableColumn<OrderEntity, String> nameColumn;

    @FXML
    private TableColumn<OrderEntity, String> priceColumn;

    @FXML
    private TableColumn<OrderEntity, String> totalPriceColumn;

    @FXML
    private TableColumn<OrderEntity, String> quantityColumn;

    @FXML
    private TableColumn<OrderEntity, String> dateColumn;

    @FXML
    private DatePicker deliveryDateDatePicker;


    @FXML
    private TextField orderIdField;

    @FXML
    private TextField productNameField;
    @FXML
    private TextField productPriceField;
    @FXML
    private TextField productQuantityField;
    @FXML
    private TextField totalPriceField;
    private final StageManager stageManager;

    private final OrderService orderService;

    public OrderController(@Lazy StageManager stageManager, OrderService orderService) {

        this.stageManager = stageManager;
        this.orderService = orderService;
    }

    @FXML
    public void saveButton() {

        if (!StringUtils.isBlank(orderIdField.getText())) {
            try {
                Optional<OrderEntity> o = orderService.findById(Integer.valueOf(orderIdField.getText()));
                OrderEntity orderEntity = o.orElse(null);
                if (orderEntity == null) {
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
        OrderEntity orderEntity = new OrderEntity();
        if (!StringUtils.isBlank(orderIdField.getText())) {
            try {
                orderEntity.setOrderId(Integer.valueOf(orderIdField.getText()));
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
        if (!StringUtils.isBlank(productNameField.getText())) {
            try {
                orderEntity.setProductName(productNameField.getText());
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER INTEGER NUMBER");
                productNameField.requestFocus();
                return;
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER PRODUCT NAME");
            productNameField.requestFocus();
            return;
        }
        if (!StringUtils.isBlank(productPriceField.getText())) {
            try {
                orderEntity.setProductPrice(Integer.valueOf(productPriceField.getText()));
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER INTEGER NUMBER");
                productPriceField.requestFocus();
                return;
            }

        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER PRODUCT PRICE");
            productPriceField.requestFocus();
            return;
        }
        if (!StringUtils.isBlank(productQuantityField.getText())) {
            try {
                orderEntity.setProductQuantity(Integer.valueOf(productQuantityField.getText()));
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER INTEGER NUMBER");
                productQuantityField.requestFocus();
                return;
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER PRODUCT QUANTITY");
            productQuantityField.requestFocus();
            return;
        }
        if (!StringUtils.isBlank(String.valueOf(deliveryDateDatePicker.getValue()))) {
            try {
                orderEntity.setDeliveryDate(deliveryDateDatePicker.getValue());
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER INTEGER NUMBER");
                deliveryDateDatePicker.requestFocus();
                return;
            }
        }
        totalPriceKey();
        if (!StringUtils.isBlank(totalPriceField.getText())) {
            try {
                orderEntity.setTotalPrice(Integer.valueOf(totalPriceField.getText()));
            } catch (NumberFormatException e) {
                JavaFXUtils.showWarningMessage("1");
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "Number Format Exception", "PLEASE ENTER INTEGER NUMBER");
                return;
            }
        }
        orderService.save(orderEntity);
    }

    @FXML
    public void searchByIdButton() {
        OrderEntity orderEntity = orderService.searchByRegNo(Integer.valueOf(orderIdField.getText()));
        if (orderEntity != null) {
            if (orderEntity.getOrderId() != null) {
                orderIdField.setText(String.valueOf(orderEntity.getOrderId()));
            }
            if (orderEntity.getProductPrice() != null) {
                productPriceField.setText(String.valueOf(orderEntity.getProductPrice()));
            }
            if (orderEntity.getProductName() != null) {
                productNameField.setText(orderEntity.getProductName());
            }
            if (orderEntity.getProductQuantity() != null) {
                productQuantityField.setText(String.valueOf(orderEntity.getProductQuantity()));
            }
            if (orderEntity.getTotalPrice() != null) {
                totalPriceField.setText(String.valueOf(orderEntity.getTotalPrice()));
            }
            if (orderEntity.getDeliveryDate() != null) {
                deliveryDateDatePicker.setValue(orderEntity.getDeliveryDate());
            }
        } else {
            JavaFXUtils.showError("productId with " + orderIdField.getText() + " not registered");
        }
    }

    @FXML
    public void delete() {
        if (StringUtils.isBlank(orderIdField.getText())) {
            JavaFXUtils.showError("PLEASE ENTER ID");
        } else {
            orderService.deleteById(Integer.valueOf(orderIdField.getText()));
            JavaFXUtils.showSuccessMessage("DATA DELETE SUCCESSFULLY");
        }
        setUpTable();
        clear();
    }

    @FXML
    public void UpdateButton() {
        if (!StringUtils.isBlank(orderIdField.getText())) {
            try {
                Optional<OrderEntity> o = orderService.findById(Integer.valueOf(orderIdField.getText()));
                OrderEntity orderEntity = o.orElse(null);
                if (orderEntity != null) {
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
        orderService.deleteAll();
        setUpTable();
    }

    @FXML
    public void clearFormButton() {
        clear();
    }


    private void clear() {
        orderIdField.clear();
        productNameField.clear();
        productPriceField.clear();
        productQuantityField.clear();
        deliveryDateDatePicker.getEditor().clear();
        totalPriceField.clear();
    }


    private void setUpTable() {

        List<OrderEntity> orderEntities = new ArrayList<>();
        try {
            orderEntities = orderService.getAll();
        } catch (Exception e) {
            JavaFXUtils.showError("Exception FOR ENTER DATA IN TABLE");
        }
        tableView.setItems(FXCollections.observableArrayList(orderEntities));
        this.idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getOrderId())));
        this.nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
        this.priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getProductPrice())));
        this.totalPriceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTotalPrice())));
        this.dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDeliveryDate())));
        this.quantityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getProductQuantity())));

        this.tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateRecord(newSelection);
            }
        });
    }

    private void populateRecord(OrderEntity orderEntity) {
        orderIdField.setText(String.valueOf(orderEntity.getOrderId()));
        productNameField.setText(orderEntity.getProductName());
        productPriceField.setText(String.valueOf(orderEntity.getProductPrice()));
        totalPriceField.setText(String.valueOf(orderEntity.getTotalPrice()));
        productQuantityField.setText(String.valueOf(orderEntity.getProductQuantity()));
        deliveryDateDatePicker.setValue(orderEntity.getDeliveryDate());
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
    public void totalPriceKey() {
        if (!StringUtils.isBlank(productPriceField.getText()) && !StringUtils.isBlank(productQuantityField.getText())) {
            totalPriceField.setText(String.valueOf(Integer.valueOf(productPriceField.getText()) * Integer.valueOf(productQuantityField.getText())));
        }
    }


}



