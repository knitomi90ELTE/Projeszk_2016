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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;

/**
 * bejelentkezés vezérlő
 * @author 
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;
    @FXML
    private StackPane StackPane;
    
    private final LoginQuery query;

    /**
     * konstruktor
     */
    public LoginController() {
        query = new LoginQuery();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StackPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    loginAction(new ActionEvent());
                    keyEvent.consume();
                }
            }
        });
    }

    /**
     * bejelentkezés kezelő
     * @param event
     */
    public void loginAction(ActionEvent event) {
        System.out.println("Login event...");
        message.setText("Logging in...");
        if (email.getText().isEmpty()) {
            message.setText("Missing email!");
            return;
        }
        if (password.getText().isEmpty()) {
            message.setText("Missing password!");
            return;
        }

        UserEntity current = query.findUser(email.getText());
        if (current != null) {
            try {
                if (PasswordHash.validatePassword(password.getText(), current.getPassword())) {
                    message.setTextFill(Paint.valueOf("00ff00"));
                    System.out.println("Login successful...");
                    changeToAdminAction();
                    return;
                } else {
                    message.setText("Password is incorrect!");
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

            }
        } else {
            message.setText("Invalid email!");
        }
        message.setTextFill(Paint.valueOf("ff0000"));
        System.out.println("Login unsuccessful...");
    }
    
    /**
     * admininsztrátor felületre navigál
     */
    private void changeToAdminAction() {
        LoginFX.getInstance().getChangeContent().replaceSceneContent("/view/Admin.fxml");
    }

    /**
     * regisztráció felületre navigál
     * @param event
     */
    public void changeToRegAction(ActionEvent event) {
        LoginFX.getInstance().getChangeContent().replaceSceneContent("/view/Reg.fxml");
    }

}
