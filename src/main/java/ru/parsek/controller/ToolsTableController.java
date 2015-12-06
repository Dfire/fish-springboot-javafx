package ru.parsek.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ru.parsek.entity.Manufacturer;
import ru.parsek.entity.Tool;
import ru.parsek.service.ToolService;

import javax.annotation.PostConstruct;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class ToolsTableController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(ToolsTableController.class);

    // Инъекции Spring
    @Autowired 
    private ToolService toolService;

    // Инъекции JavaFX
    @FXML private TableView<Tool> table;
    @FXML private TextField txtLabel;
    @FXML private TextField txtAlloy;
    @FXML private TextField txtManufacturer;
    
    // Variables
    private ObservableList<Tool> data;

    /**
     * Инициализация контроллера от JavaFX.
     * Метод вызывается после того как FXML загрузчик произвел инъекции полей.
     *
     * Обратите внимание, что имя метода <b>обязательно</b> должно быть "initialize",
     * в противном случае, метод не вызовется.
     *
     * Также на этом этапе еще отсутствуют бины спринга
     * и для инициализации лучше использовать метод,
     * описанный аннотацией @PostConstruct,
     * который вызовется спрингом, после того, как им будут произведены все инъекции.
     * {@link ToolsTableController#init()}
     */
    @FXML
    public void initialize() {
    }

    /**
     * На этом этапе уже произведены все возможные инъекции.
     */
    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        List<Tool> tools = toolService.findAll();
        data = FXCollections.observableArrayList(tools);

        // Столбцы таблицы
        TableColumn<Tool, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn<Tool, String> labelColumn = new TableColumn<>("Маркировка");
        labelColumn.setCellValueFactory(new PropertyValueFactory<>("label"));

        TableColumn<Tool, String> alloyColumn = new TableColumn<>("Сплав");
        alloyColumn.setCellValueFactory(new PropertyValueFactory<>("alloy"));
        
        TableColumn<Tool, Manufacturer> manufacturerColumn = new TableColumn<>("Производитель");
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

        table.getColumns().setAll(idColumn, labelColumn, alloyColumn, manufacturerColumn);

        // Данные таблицы
        table.setItems(data);
    }

    /**
     * Метод, вызываемый при нажатии на кнопку "Добавить".
     * Привязан к кнопке в FXML файле представления.
     */
    @FXML
    public void add() {
        Tool tool = new Tool(txtLabel.getText(), txtAlloy.getText(),txtManufacturer.getText());
        toolService.save(tool);
        data.add(tool);

        // чистим поля
        txtLabel.setText("");
        txtAlloy.setText("");
        txtManufacturer.setText("");
    }
    
    @FXML
    public void openDictionaries(){
    	stageController.setScene(SceneName.DICTIONARIES);
    }
}
