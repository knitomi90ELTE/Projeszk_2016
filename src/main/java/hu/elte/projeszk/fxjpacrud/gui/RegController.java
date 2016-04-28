package hu.elte.projeszk.fxjpacrud.gui;

import hu.elte.projeszk.fxjpacrud.LoginFX;
import hu.elte.projeszk.fxjpacrud.db.RegQuery;
import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
import hu.elte.projeszk.fxjpacrud.helper.FormValidation;
import hu.elte.projeszk.fxjpacrud.security.PasswordHash;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField pwd1Field;
    @FXML
    private TextField pwd2Field;
    @FXML
    private Label message;

    private final RegQuery query;

    public RegController() {
        query = new RegQuery();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void backToLogin(ActionEvent event) {
        LoginFX.getInstance().getChangeContent().replaceSceneContent("/view/Login.fxml");
    }

    public void regAction(ActionEvent event) {
        String errors = FormValidation.validateForm(nameField.getText(), emailField.getText(), pwd1Field.getText(), pwd2Field.getText());
        if (errors.length() > 0) {
            message.setText(errors);
            return;
        }
        RegQuery r = new RegQuery();
        UserEntity u = new UserEntity();
        u.setName(nameField.getText());
        u.setEmail(emailField.getText());
        try {
            String pwd = PasswordHash.createHash(pwd1Field.getText());
            u.setPassword(pwd);
            r.addUser(u);
            LoginFX.getInstance().getChangeContent().replaceSceneContent("/view/Admin.fxml");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

        }
    }

}
