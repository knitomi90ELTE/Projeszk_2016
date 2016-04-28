package hu.elte.projeszk.fxjpacrud.gui;

import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
import hu.elte.projeszk.fxjpacrud.helper.FormValidation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserPopupController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField pwdField1;
    @FXML
    private PasswordField pwdField2;
    
    private Stage dialogStage;
    private UserEntity currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void handleOk() {
        String errors = FormValidation.validateForm(nameField.getText(), emailField.getText(), pwdField1.getText(), pwdField2.getText());
        if (errors.length() > 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errors);
            alert.showAndWait();
        } else {
            currentUser = new UserEntity();
            currentUser.setName(nameField.getText());
            currentUser.setEmail(emailField.getText());
            currentUser.setPassword(pwdField1.getText());
            dialogStage.close();
        }
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public UserEntity getCurrentUser() {
        return currentUser;
    }

}
