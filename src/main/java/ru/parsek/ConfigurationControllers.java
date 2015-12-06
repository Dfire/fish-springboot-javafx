package ru.parsek;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.parsek.controller.BaseController;
import ru.parsek.controller.ManufacturersTableController;
import ru.parsek.controller.SceneName;
import ru.parsek.controller.StageController;
import ru.parsek.controller.ToolsTableController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ConfigurationControllers{

	private AppRef ref = new AppRef();
	
	@Bean(name = "main_menu_view")
    public View getMainMenuView() throws IOException {
        return loadView("fxml/menu.fxml");
    }
    
    @Bean(name = "dictionaries_view")
    public View getDictionariesView() throws IOException {
        return loadView("fxml/dictionaries.fxml");
    }
    
    @Bean(name = "table_tools_view")
    public View getTableToolsView() throws IOException {
        return loadView("fxml/table_tools.fxml");
    }
    
    @Bean(name = "table_manufacturers_view")
    public View getTableManufacturersView() throws IOException {
        return loadView("fxml/table_manufacturers.fxml");
    }

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его сделать произвести все необходимые инъекции.
     */
    @Bean
    public ToolsTableController getTableToolsController() throws IOException {
        return (ToolsTableController) getTableToolsView().getController();
    }
    
    @Bean
    public ManufacturersTableController getTableManufacturersController() throws IOException {
        return (ManufacturersTableController) getTableManufacturersView().getController();
    }
    
    public void setApplication(Application app){
    	ref.app = app;
    }
    
    /**
     * Самый обыкновенный способ использовать FXML загрузчик.
     * Как раз-таки на этом этапе будет создан объект-контроллер,
     * произведены все FXML инъекции и вызван метод инициализации контроллера.
     */
    protected View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            BaseController controller = loader.getController();
            controller.setStageController(ref);
            return new View(loader.getRoot(), controller);
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }
    
    public class AppRef implements StageController{
    	private Application app;

		@Override
		public void setScene(SceneName scene) {
			app.setScene(scene);
		}
    }

    /**
     * Класс - оболочка: контроллер мы обязаны указать в качестве бина,
     * а view - представление, нам предстоит использовать в точке входа {@link Application}.
     */
    public class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }
}
