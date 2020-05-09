package zw.co.abn.covid.alert;



import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;


public class AlertInformation {




    public static void showSuccesMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText(title);
        alert.setContentText(content);
        styleAlert(alert);
        alert.showAndWait();
    }

    public static void showErrorMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(content);
        styleAlert(alert);
        alert.showAndWait();
    }


    private static void styleAlert(Alert alert) {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(AlertInformation.class.getResource("/dark-theme.css").toExternalForm());
        dialogPane.getStyleClass().add("custom-alert");
    }


}
