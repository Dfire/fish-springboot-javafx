package ru.parsek.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DictionariesController extends BaseController{

	@FXML
	void openManufacturers(ActionEvent event){
		stageController.setScene(SceneName.MANUFACTURERS);
	}
	
	@FXML
    void openTools(ActionEvent event) {
		stageController.setScene(SceneName.TOOLS_TABLE);
    }
    
	@FXML
	void openMainMenu(ActionEvent event){
		stageController.setScene(SceneName.MAIN_MENU);
	}
}