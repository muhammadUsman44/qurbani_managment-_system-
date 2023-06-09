package edu.imdadia.qurbani.controller;

import edu.imdadia.qurbani.config.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
@Controller
public class DataManagementController {

        private final StageManager stageManager;
        @FXML
        private TextField firstNameField;

        @FXML
        private TextField lastNameField;

        @FXML
        private TextField mobNoField;

        public DataManagementController(@Lazy StageManager stageManager) {
            this.stageManager = stageManager;
        }

        @FXML
        public void saveButton(){

        }
        @FXML
        public void updateButton(){

        }
        @FXML
        public void deleteById(){

        }
        @FXML
        public void deleteAllButton(){

        }
        @FXML
        public void clearButton(){
        }
    @FXML
    public void backButton(){

    }
    }

