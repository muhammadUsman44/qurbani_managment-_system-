package edu.imdadia.tailor.controller;

import edu.imdadia.tailor.Entity.MeausrementEntity;
import edu.imdadia.tailor.config.StageManager;
import edu.imdadia.tailor.enumuration.FxmlView;
import edu.imdadia.tailor.service.MeausrementService;
import edu.imdadia.tailor.util.JavaFXUtils;
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
public class MeausrementController implements Initializable {


    private final StageManager stageManager;
    private final MeausrementService meausrementService;


    @FXML
    private ChoiceBox<String> frontPocketChoiceBox;
    @FXML
    private ChoiceBox<String> pocketTypeChoiceBox;
    @FXML
    private ChoiceBox<String> sidePocketChoiceBox;
    @FXML
    private ChoiceBox<String> qamizFrontStripTypeChoiceBox;
    @FXML
    private ChoiceBox<String> threadChoiceBox;
    @FXML
    private ChoiceBox<String> zipChoiceBox;
    @FXML
    private ChoiceBox<String> qamizBottomTypeChoiceBox;
    @FXML
    private ChoiceBox<String> calfTypeChoiceBox;

    @FXML
    private ChoiceBox<String> collarTypeChoiceBox;


    @FXML
    private TextField idField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField mobNoField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField cnicField;


    @FXML
    public TextField qamizLengthField;
    @FXML
    private TextField trouserLengthField;
    @FXML
    private TextField trouserBottomField;
    @FXML
    private TextField trouserThaiField;
    @FXML
    private TextField shalwarBottomField;
    @FXML
    private TextField calfSizeField;
    @FXML
    private TextField shalwarLengthField;
    @FXML
    private TextField qamizFrontStripField;
    @FXML
    private TextField qamizBottomField;
    @FXML
    private TextField waistLengthField;
    @FXML
    private TextField chestLengthField;
    @FXML
    private TextField neckLengthField;
    @FXML
    private TextField shoulderLengthField;
    @FXML
    private TextField sleevsLengthField;
    @FXML
    private TextField trouserHipField;

    @FXML
    private TextField searchField;


    public MeausrementController(@Lazy StageManager stageManager, MeausrementService meausrementService) {
        this.stageManager = stageManager;
        this.meausrementService = meausrementService;

    }


    public void search() {
        MeausrementEntity meausrement;
        try {
            meausrement = meausrementService.findById(Integer.valueOf(searchField.getText()));
            if (meausrement != null) {
                setValue(meausrement);
                return;
            }

        } catch (NumberFormatException e) {

        }
        try {
            meausrement = meausrementService.findByFirstName(searchField.getText());
            if (meausrement != null) {
                setValue(meausrement);
                return;
            }

        } catch (RuntimeException e) {
            JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
            return;
        }

        try {
            meausrement = meausrementService.findByLast(searchField.getText());
            if (meausrement != null) {
                setValue(meausrement);
                return;
            }
        } catch (RuntimeException e) {
            JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
            return;
        }
        try {

            meausrement = meausrementService.findByMobileNo(searchField.getText());
            if (meausrement != null) {
                setValue(meausrement);
            }
        }  catch (RuntimeException e) {
            JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
            return;
        }
        if (meausrement == null) {
            JavaFXUtils.showWarningMessage("THIS RECORD IN NOT ENTERED");
        }
    }


    @FXML
    public void saveButton() {

        if (!StringUtils.isBlank(idField.getText())) {
            try {
                MeausrementEntity meausrementEntity = meausrementService.findById(Integer.valueOf(idField.getText()));
                if (meausrementEntity != null) {
                    JavaFXUtils.showWarningMessage("THIS ID ALREADY");
                    idField.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
            }
            MeausrementEntity meausrement = new MeausrementEntity();
            saveValue(meausrement);
            JavaFXUtils.showSuccessMessage("DATA SAVED SUCCESSFULLY");
            clear();
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER ID");
        }
    }

    public void deleteById() {
        final String id = idField.getText();
        meausrementService.deleteById(Integer.valueOf(idField.getText()));
        JavaFXUtils.showSuccessMessage("Item with Id " + id + " deleted successfully");
        clear();


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setValue(meausrementTable);
        }catch (Exception e){

        }
        frontPocketChoiceBox.getItems().addAll(getPocketType());
        sidePocketChoiceBox.getItems().addAll(getPocketType());
        threadChoiceBox.getItems().addAll(getThread());
        zipChoiceBox.getItems().addAll(getZipType());
        collarTypeChoiceBox.getItems().addAll(getRoundOrStraight());
        qamizBottomTypeChoiceBox.getItems().addAll(getRoundOrStraight());
        calfTypeChoiceBox.getItems().addAll(getRoundOrStraight());
        pocketTypeChoiceBox.getItems().addAll(getRoundOrStraight());
        qamizFrontStripTypeChoiceBox.getItems().addAll(getRoundOrStraight());
    }

    private List<String> getPocketType() {
        final List<String> list = new ArrayList<>();
        list.add("Single");
        list.add("Double");
        list.add("No Pocket");
        return list;
    }


    private List<String> getZipType() {
        final List<String> list = new ArrayList<>();
        list.add("yes");
        list.add("no");
        return list;
    }


    private List<String> getRoundOrStraight() {
        final List<String> list = new ArrayList<>();
        list.add("Round");
        list.add("Straight");
        return list;
    }

    private List<String> getThread() {
        final List<String> list = new ArrayList<>();
        list.add("single");
        list.add("double");
        return list;
    }

    @FXML
    public void updateButton() {
        if (!StringUtils.isBlank(idField.getText())) {
            try {
                MeausrementEntity meausrementEntity = meausrementService.findById(Integer.valueOf(idField.getText()));
                if (meausrementEntity != null) {
                    saveValue(meausrementEntity);
                    JavaFXUtils.showSuccessMessage("DATA UPDATE SUCCESSFULLY");
                    clear();
                } else {
                    JavaFXUtils.showWarningMessage("THIS ID NOT ENTERED");
                }

            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, e.getClass().getName(), e.getMessage());
            }
        } else {
            JavaFXUtils.showWarningMessage("PLEASE ENTER ID");
        }
    }

    @FXML
    public void backButton(){
        stageManager.switchScene(FxmlView.MENU);
    }


    @FXML
    private void deleteAllButton() {
        meausrementService.deleteAll();
    }

    @FXML
    private void clearButton() {
        clear();
    }

  protected static   MeausrementEntity meausrementTable;

    private void setValue(MeausrementEntity meausrement) {
        if (meausrement.getQamizLength() != null) {
            qamizLengthField.setText(String.valueOf(meausrement.getQamizLength()));
        }
        if (meausrement.getShoulder() != null) {
            shoulderLengthField.setText(String.valueOf(meausrement.getShoulder()));
        }
        if (meausrement.getNack() != null) {
            neckLengthField.setText(String.valueOf(meausrement.getNack()));
        }
        if (meausrement.getTrouserBottom() != null) {
            trouserBottomField.setText(String.valueOf(meausrement.getTrouserBottom()));
        }
        if (meausrement.getTrouserHip() != null) {
            trouserHipField.setText(String.valueOf(meausrement.getTrouserHip()));
        }
        if (meausrement.getChest() != null) {
            chestLengthField.setText(String.valueOf(meausrement.getChest()));
        }

        if (meausrement.getCalfSize() != null) {
            calfSizeField.setText(String.valueOf(meausrement.getCalfSize()));
        }
        if (meausrement.getTrouserThai() != null) {
            trouserThaiField.setText(String.valueOf(meausrement.getTrouserThai()));
        }
        if (meausrement.getShalwarBottom() != null) {
            shalwarBottomField.setText(String.valueOf(meausrement.getShalwarBottom()));
        }
        if (meausrement.getWaist() != null) {
            waistLengthField.setText(String.valueOf(meausrement.getWaist()));
        }
        if (meausrement.getShalwarLength() != null) {
            shalwarLengthField.setText(String.valueOf(meausrement.getShalwarLength()));
        }
        if (meausrement.getQamizBottom() != null) {
            qamizBottomField.setText(String.valueOf(meausrement.getQamizBottom()));
        }
        if (meausrement.getSleevs() != null) {
            sleevsLengthField.setText(String.valueOf(meausrement.getSleevs()));
        }
        if (meausrement.getTrouserLength() != null) {
            trouserLengthField.setText(String.valueOf(meausrement.getTrouserLength()));
        }
        if (meausrement.getQamizFrontStrip() != null) {
            qamizFrontStripField.setText(String.valueOf(meausrement.getQamizFrontStrip()));
        }

        idField.setText(String.valueOf(meausrement.getMeausrementId()));
        cnicField.setText(meausrement.getCnicNo());
        lastNameField.setText(meausrement.getLastName());
        mobNoField.setText(String.valueOf(meausrement.getMobileNo()));
        genderField.setText(meausrement.getGender());
        firstNameField.setText(meausrement.getFirstName());
        frontPocketChoiceBox.setValue(meausrement.getFrontPocket());
        pocketTypeChoiceBox.setValue(meausrement.getPocketType());
        sidePocketChoiceBox.setValue(meausrement.getPocketType());
        calfTypeChoiceBox.setValue(meausrement.getCalfType());
        zipChoiceBox.setValue(meausrement.getZip());
        qamizFrontStripTypeChoiceBox.setValue(meausrement.getQamizFrontStripType());
        qamizBottomTypeChoiceBox.setValue(meausrement.getQamizBottomType());
        collarTypeChoiceBox.setValue(meausrement.getCollarType());
        threadChoiceBox.setValue(meausrement.getThread());
    }

    private void saveValue(MeausrementEntity meausrement) {
        if (!StringUtils.isBlank(idField.getText())) {
            try {
                meausrement.setMeausrementId(Integer.valueOf(idField.getText()));
            } catch (NumberFormatException e) {
                JavaFXUtils.showAlert(Alert.AlertType.WARNING, "NumberFormatException", "PLEASE ENTER INTEGER NUMBER");
            }
        }


        if (!StringUtils.isBlank(sleevsLengthField.getText())) {
            meausrement.setSleevs(Double.valueOf(sleevsLengthField.getText()));
        }
        if (!StringUtils.isBlank(shalwarBottomField.getText())) {
            meausrement.setShalwarBottom(Double.valueOf(shalwarBottomField.getText()));
        }


        if (!StringUtils.isBlank(qamizBottomField.getText())) {
            meausrement.setQamizBottom(Double.valueOf(qamizBottomField.getText()));
        }
        if (!StringUtils.isBlank(qamizFrontStripField.getText())) {
            meausrement.setQamizFrontStrip(Double.valueOf(qamizFrontStripField.getText()));
        }
        if (!StringUtils.isBlank(shalwarLengthField.getText())) {
            meausrement.setShalwarLength((Double.valueOf(shalwarLengthField.getText())));
        }


        if (!StringUtils.isBlank(trouserHipField.getText())) {
            meausrement.setTrouserHip(Double.valueOf(trouserHipField.getText()));
        }
        if (!StringUtils.isBlank(genderField.getText())) {
            meausrement.setGender(genderField.getText());
        }
        if (!StringUtils.isBlank(neckLengthField.getText())) {
            meausrement.setNack(Double.valueOf(neckLengthField.getText()));
        }
        if (!StringUtils.isBlank(chestLengthField.getText())) {
            meausrement.setChest(Double.valueOf(chestLengthField.getText()));
        }
        if (!StringUtils.isBlank(calfSizeField.getText())) {
            meausrement.setCalfSize(Double.valueOf(calfSizeField.getText()));
        }
        if (!StringUtils.isBlank(trouserLengthField.getText())) {
            meausrement.setTrouserLength(Double.valueOf(trouserLengthField.getText()));
        }

        if (!StringUtils.isBlank(waistLengthField.getText())) {
            meausrement.setWaist(Double.valueOf(waistLengthField.getText()));
        }
        if (!StringUtils.isBlank(trouserThaiField.getText())) {
            meausrement.setTrouserThai(Double.valueOf(trouserThaiField.getText()));
        }
        if (!StringUtils.isBlank(trouserBottomField.getText())) {
            meausrement.setTrouserBottom(Double.valueOf(trouserBottomField.getText()));
        }
        if (!StringUtils.isBlank(shoulderLengthField.getText())) {
            meausrement.setShoulder(Double.valueOf(shoulderLengthField.getText()));
        }
        if (!StringUtils.isBlank(qamizLengthField.getText())) {
            meausrement.setQamizLength(Double.valueOf(qamizLengthField.getText()));
        }
        meausrement.setCnicNo(cnicField.getText());
        meausrement.setLastName(lastNameField.getText());
        meausrement.setFirstName(firstNameField.getText());
        meausrement.setThread(threadChoiceBox.getValue());
        meausrement.setQamizBottomType(qamizBottomTypeChoiceBox.getValue());
        meausrement.setCalfType(calfTypeChoiceBox.getValue());
        meausrement.setFrontPocket(frontPocketChoiceBox.getValue());
        meausrement.setCollarType(collarTypeChoiceBox.getValue());
        meausrement.setSidePocket(sidePocketChoiceBox.getValue());
        meausrement.setPocketType(pocketTypeChoiceBox.getValue());
        meausrement.setZip(zipChoiceBox.getValue());
        meausrement.setMobileNo(mobNoField.getText());
        meausrement.setQamizFrontStripType(qamizFrontStripTypeChoiceBox.getValue());

        meausrementService.save(meausrement);
    }

    private void clear() {
        frontPocketChoiceBox.setValue("");
        pocketTypeChoiceBox.setValue("");
        sidePocketChoiceBox.setValue("");
        qamizFrontStripTypeChoiceBox.setValue("");
        threadChoiceBox.setValue("");
        zipChoiceBox.setValue("");
        qamizBottomTypeChoiceBox.setValue("");
        calfTypeChoiceBox.setValue("");
        collarTypeChoiceBox.setValue("");
        idField.clear();
        firstNameField.clear();
        lastNameField.clear();
        mobNoField.clear();
        genderField.clear();
        cnicField.clear();
        qamizLengthField.clear();
        trouserLengthField.clear();
        trouserBottomField.clear();
        trouserThaiField.clear();
        shalwarBottomField.clear();
        calfSizeField.clear();
        shalwarLengthField.clear();
        qamizFrontStripField.clear();
        qamizBottomField.clear();
        waistLengthField.clear();
        chestLengthField.clear();
        neckLengthField.clear();
        shoulderLengthField.clear();
        sleevsLengthField.clear();
        trouserHipField.clear();

    }

}

