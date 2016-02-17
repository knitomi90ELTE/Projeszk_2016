/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.projeszk.fxjpacrud.gui;


import hu.elte.projeszk.fxjpacrud.LoginFX;
import hu.elte.projeszk.fxjpacrud.db.LoginQuery;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

/**
 *
 * @author capri
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;

    private final LoginQuery query = new LoginQuery();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void loginAction(ActionEvent event) {

        if (email.getText().isEmpty()) {
            message.setText("Nem adott meg emailt!");
            return;
        }
        if (password.getText().isEmpty()) {
            message.setText("Nem adott meg jelszót!");
            return;
        }

        UserEntity current = query.findUser(email.getText());
        if (current != null) {
            try {
                if (PasswordHash.validatePassword(password.getText(), current.getPassword())) {
                    message.setTextFill(Paint.valueOf("00ff00"));
                    message.setText("Jelszó megfelelő!");
                    changeToAdminAction();
                } else {
                    message.setText("Hibás jelszó!");
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        } else {
            message.setText("Email nem létezik!");
        }
        message.setTextFill(Paint.valueOf("ff0000"));

    }

    private void changeToAdminAction() {
        LoginFX.getInstance().getChangeContent().replaceSceneContent("gui/admin/Admin.fxml");
    }

    public void changeToRegAction(ActionEvent event) {
        LoginFX.getInstance().getChangeContent().replaceSceneContent("gui/reg/Reg.fxml");
    }

}
