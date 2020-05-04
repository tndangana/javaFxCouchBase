package zw.co.abn.covid;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<Main.StageReadyEvent> {

    @Value("classpath:/views/user/userList.fxml")
    private Resource resource;
    private final String stageTitle;


    private ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui.title}") String stageTitle,ApplicationContext applicationContext) {
        this.stageTitle = stageTitle;
        this.applicationContext = applicationContext;
    }

    /**
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(Main.StageReadyEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
            fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
            Parent parent = fxmlLoader.load();
            Stage stage = event.getStage();
            stage.setTitle(stageTitle);
            stage.setScene(new Scene(parent, 800, 800));
            stage.show();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
