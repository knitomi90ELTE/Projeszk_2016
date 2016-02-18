/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.projeszk.fxjpacrud;


import hu.elte.projeszk.fxjpacrud.gui.LoginController;
import hu.elte.projeszk.fxjpacrud.helper.ChangeContent;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author capri
 */
public class LoginFX extends Application {
    
    private static LoginFX instance;
    private ChangeContent changeContent;
    
    public LoginFX() {
        instance = this;
    }

    public static LoginFX getInstance() {
        return instance;
    }

    public ChangeContent getChangeContent() {
        return changeContent;
    }
    
    @Override
    public void start(Stage stage) {
        changeContent = new ChangeContent(instance, stage);
        changeContent.replaceSceneContent("/view/Login.fxml");
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
