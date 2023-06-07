package edu.imdadia.qurbani.controller;

import edu.imdadia.qurbani.Entity.MeausrementEntity;
import edu.imdadia.qurbani.config.StageManager;
import edu.imdadia.qurbani.enumuration.FxmlView;
import edu.imdadia.qurbani.service.MeausrementService;
import edu.imdadia.qurbani.util.JavaFXUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class MeausrementTableController implements Initializable {

    private final MeausrementService meausrementService;

    private final StageManager stageManager;

    @FXML
    private TextField searchTable;
    @FXML
    private TableView<MeausrementEntity> customerDataTable;

    @FXML
    private TableColumn<MeausrementEntity, String> idTableColumn;


    @FXML
    private TableColumn<MeausrementEntity, String> firstNameTableColumn;

    @FXML
    private TableColumn<MeausrementEntity, String> lastNameTableColumn;

    @FXML
    private TableColumn<MeausrementEntity, String> mobNoTableColumn;

    @FXML
    private TableColumn<MeausrementEntity, String> genderTableColumn;

    @FXML
    private TableColumn<MeausrementEntity, String> cnicTableColumn;


    @FXML
    public void showMeausrementButton() {
        stageManager.switchScene(FxmlView.MEAUSREMENT);
    }


    public MeausrementTableController(MeausrementService meausrementService, @Lazy StageManager stageManager) {
        this.meausrementService = meausrementService;
        this.stageManager = stageManager;
    }


    private void setUpTable() {

        List<MeausrementEntity> meausrementEntities = new ArrayList<>();
        try {
            meausrementEntities = meausrementService.findAllByOrderByMeausrementIdAsc();
        } catch (Exception e) {
            JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
        }
        customerDataTable.setItems(FXCollections.observableArrayList(meausrementEntities));

        this.idTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMeausrementId()))
        );
        this.firstNameTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        this.lastNameTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getLastName())));
        this.genderTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getGender())));
        this.cnicTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCnicNo())));
        this.mobNoTableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMobileNo())));

        this.customerDataTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                MeausrementController.meausrementTable = meausrementService.findById(newSelection.getMeausrementId());
            }
        });
    }

    public void tableFilter() {
        final List<MeausrementEntity> itemList = meausrementService.findAllByOrderByMeausrementIdAsc();
        FilteredList<MeausrementEntity> filteredData = new FilteredList<>(FXCollections.observableArrayList(itemList), p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchTable.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(MeausrementEntity -> {
            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            // Compare first name and last name of every person with filter text. yhn jis jis colon mn lgana hy filter usy get likhy first name , last name , number , id, cnic
            String lowerCaseFilter = newValue.toLowerCase();
            if (MeausrementEntity.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches first name.
            } else if (MeausrementEntity.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            } else if (String.valueOf(MeausrementEntity.getMobileNo()).toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            } else if (String.valueOf(MeausrementEntity.getCnicNo()).toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            } else
                return String.valueOf(MeausrementEntity.getMeausrementId()).toLowerCase().contains(lowerCaseFilter); // Filter matches last name.
// Does not match.
        }));
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<MeausrementEntity> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(customerDataTable.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        customerDataTable.setItems(sortedData);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpTable();
        tableFilter();
    }
}
