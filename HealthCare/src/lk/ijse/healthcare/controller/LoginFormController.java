package lk.ijse.healthcare.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane loginPaneContext;
    public JFXTextField txtUsername;
    public JFXTextField txtPassword;

    public void openDashboardFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void openSignUpFormOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignupForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage= (Stage) loginPaneContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();

    }
}
