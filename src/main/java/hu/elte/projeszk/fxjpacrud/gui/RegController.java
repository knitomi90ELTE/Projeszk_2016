package hu.elte.projeszk.fxjpacrud.gui;

import hu.elte.projeszk.fxjpacrud.LoginFX;
import hu.elte.projeszk.fxjpacrud.db.RegQuery;
import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class RegController implements Initializable {

    @FXML
    TextField nameField;
    @FXML
    TextField emailField;
    @FXML
    TextField pwd1Field;
    @FXML
    TextField pwd2Field;
    @FXML
    Label message;

    private final RegQuery query;

    public RegController() {
        query = new RegQuery();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void backToLogin(ActionEvent event) {
        LoginFX.getInstance().getChangeContent().replaceSceneContent("/view/Login.fxml");
    }

    public void regAction(ActionEvent event) {
        if (nameField.getText().isEmpty()) {
            message.setText("Hiányzó adat: név");
            return;
        }
        if (emailField.getText().isEmpty()) {
            message.setText("Hiányzó adat: email");
            return;
        }
        if (pwd1Field.getText().isEmpty()) {
            message.setText("Hiányzó adat: jelszó");
            return;
        }
        if (pwd2Field.getText().isEmpty()) {
            message.setText("Hiányzó adat: jelszó megint");
            return;
        }
        if (!pwd1Field.getText().equals(pwd2Field.getText())) {
            message.setText("Jelszavak nem egyeznek");
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
            e.printStackTrace();
        }

    }

    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

}
