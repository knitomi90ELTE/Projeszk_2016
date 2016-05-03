package hu.elte.projeszk.fxjpacrud;

import hu.elte.projeszk.fxjpacrud.helper.ChangeContent;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Az alkalmazás fő osztálya.
 * 
 * @author Gergő, Zoltán
 */
public class LoginFX extends Application {

    private static LoginFX instance;
    private ChangeContent changeContent;

    /**
     * A konsruktor beállítja az osztályszintű példányt az objektumra.
     */
    public LoginFX() {
        instance = this;
    }

    /**
     * Visszatér az osztály pédányával-
     * 
     * @return instance osztály példánya
     */
    public static LoginFX getInstance() {
        return instance;
    }

    /**
     * Visszatér az aktuálisan megjelő tartalomra való referenciával.
     * 
     * @return changeContent aktuálisan megjelő tartalom
     */
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
     * Az alkalmazás fő függvénye.
     * 
     * @param args parancssori argumentumok
     */
    public static void main(String[] args) {
        launch(args);
    }

}
