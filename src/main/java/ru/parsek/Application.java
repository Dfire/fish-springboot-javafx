package ru.parsek;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.parsek.controller.BaseController;
import ru.parsek.controller.SceneName;
import ru.parsek.controller.StageController;
import scala.annotation.meta.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport 
                         implements StageController{

    @Value("${ui.title:JavaFX приложение}")//
    private String windowTitle;

    @Qualifier("main_menu_view")
    @Autowired
    private ConfigurationControllers.View mainMenuView;
    
    @Qualifier("dictionaries_view")
    @Autowired
    private ConfigurationControllers.View dictionariesView;
    
    @Qualifier("table_tools_view")
    @Autowired
    private ConfigurationControllers.View tableToolsView;
    
    @Qualifier("table_manufacturers_view")
    @Autowired
    private ConfigurationControllers.View tableManufacturersView;
    
    @Autowired
    private ConfigurationControllers config;
    
    private Stage stage;

	private Scene mainMenu;
	private Scene dictionaries;
	private Scene tableTools;
	private Scene tableManufacturers;
	
    public void setStage(Stage stage){
    	this.stage = stage;
    };
	
    @Override
	public void setScene(SceneName name){
    	try{
			switch (name) {
			case MAIN_MENU:
				if(mainMenu == null){
					mainMenu = new Scene(mainMenuView.getView());
				}
				stage.setScene(mainMenu);
				break;
				
			case DICTIONARIES:
				if(dictionaries == null){
					dictionaries = new Scene(dictionariesView.getView());
				}
				stage.setScene(dictionaries);
				break;
				
			case TOOLS_TABLE:
				if(tableTools == null){
					tableTools = new Scene(tableToolsView.getView());
				}
				stage.setScene(tableTools);
				break;
					
			case MANUFACTURERS:
				if(tableManufacturers == null){
					tableManufacturers = new Scene(tableManufacturersView.getView());
				}
				stage.setScene(tableManufacturers);
				break;
	
			default:
				break;
			}
    	}catch (Exception e){
			stage.close();
		}
	}

    @Override
    public void start(Stage stage) throws Exception {
    	
    	setStage(stage);
    	config.setApplication(this);
    	
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(mainMenuView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }
    
    public static void main(String[] args) {
        launchApp(Application.class, args);
    }
    
    
    
    
    	
    	
}
