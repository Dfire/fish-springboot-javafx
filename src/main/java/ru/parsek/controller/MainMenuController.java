package ru.parsek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController extends BaseController{

    @FXML
    public void openDictionaries(ActionEvent event) {
    	stageController.setScene(SceneName.DICTIONARIES);
    }

}
