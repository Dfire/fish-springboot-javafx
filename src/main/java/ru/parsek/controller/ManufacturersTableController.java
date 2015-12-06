package ru.parsek.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.parsek.entity.Manufacturer;
import ru.parsek.service.ManufacturerService;

public class ManufacturersTableController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(ManufacturersTableController.class);
	
	@Autowired
	private ManufacturerService manufacturerService;
	
    @FXML
    private TextField txtManufacturer;

    @FXML
	private TableView<Manufacturer> table;
    
    // Variables
    private ObservableList<Manufacturer> data;

    @PostConstruct
    public void init(){
    	List<Manufacturer> manufacturers = manufacturerService.findAll();
    	data = FXCollections.observableArrayList(manufacturers);
    	
    	// Столбцы таблицы
        TableColumn<Manufacturer, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn<Manufacturer, String> nameColumn = new TableColumn<>("Название");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        table.getColumns().setAll(idColumn, nameColumn);

        // Данные таблицы
        table.setItems(data);
    }
    
	@FXML
	void add(ActionEvent event) {
		
		Manufacturer manufacturer = new Manufacturer(txtManufacturer.getText());
		manufacturerService.save(manufacturer);
		data.add(manufacturer);
		
		txtManufacturer.setText("");
		
	}
	
	@FXML
	void openDictionaries(ActionEvent event) {
		stageController.setScene(SceneName.DICTIONARIES);
	}


}
