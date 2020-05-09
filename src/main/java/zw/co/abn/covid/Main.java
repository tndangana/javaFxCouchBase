package zw.co.abn.covid;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class Main extends Application {


    private ConfigurableApplicationContext applicationContext;


    @Override
    public void init()  {
       applicationContext = new SpringApplicationBuilder(CovidApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) {
        applicationContext.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop()  {
        applicationContext.stop();
        Platform.exit();
    }


    //spring security will be used
    //for the purpose of login use
//    username ===>admin
//    password ===> admin


     static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage primaryStage) {
            super(primaryStage);
        }

         public Stage getStage(){
             return (Stage) getSource();
         }
    }



}
