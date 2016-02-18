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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

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
    
    private Stage loginStage;
    
    private LoginQuery query;

    public LoginController() {
    }
    
    
    
    public LoginController(Stage stage) {
        try{
            loginStage = new Stage();
            query = new LoginQuery();
            /*FXMLLoader loader = new FXMLLoader(
                    LoginFX.getInstance().getClass().getResource(
                            "/view/Login.fxml"
                    )
            );*/
            //Parent page = (Parent) FXMLLoader.load(LoginFX.getInstance().getClass().getResource("/view/Login.fxml"), null, new JavaFXBuilderFactory());
            
            FXMLLoader loader = new FXMLLoader(LoginFX.getInstance().getClass().getResource("/view/Login.fxml"));
            
            //loader.setController(this);
            //loginStage = new Stage();
            //Scene s = new Scene(loader.load());
            //loginStage.setScene();
            //loginStage.show();
            //stage.setScene(new Scene((Pane) loader.load()));
            //stage.show();
            Scene scene = loginStage.getScene();
            if (scene == null) {
                scene = new Scene(loader.load());
                loginStage.setScene(scene);
            } else {
                loginStage.getScene().setRoot(loader.load());
            }
            loginStage.show();
            loginStage.sizeToScene();
            
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
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
        LoginFX.getInstance().getChangeContent().replaceSceneContent("gui/Admin.fxml");
    }

    public void changeToRegAction(ActionEvent event) {
        LoginFX.getInstance().getChangeContent().replaceSceneContent("gui/Reg.fxml");
    }

}
