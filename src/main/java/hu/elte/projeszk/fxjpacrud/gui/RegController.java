/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * FXML Controller class
 *
 * @author capri
 */
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

    public void loadRegScene() {
        FXMLLoader loader = new FXMLLoader(LoginFX.getInstance().getClass().getResource("/view/Reg.fxml"));
        Stage regStage = new Stage();
        Scene scene = regStage.getScene();
        try {
            if (scene == null) {
                scene = new Scene(loader.load());
                regStage.setScene(scene);
            } else {
                regStage.getScene().setRoot(loader.load());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        regStage.show();
        regStage.sizeToScene();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void backToLogin(ActionEvent event) {
        LoginController loginController = new LoginController();
        loginController.loadLoginScene();
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
            LoginFX.getInstance().getChangeContent().replaceSceneContent("gui/Admin.fxml");
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
