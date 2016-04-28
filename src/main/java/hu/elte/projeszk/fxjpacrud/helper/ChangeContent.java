package hu.elte.projeszk.fxjpacrud.helper;

import hu.elte.projeszk.fxjpacrud.LoginFX;
import hu.elte.projeszk.fxjpacrud.entity.UserEntity;
import hu.elte.projeszk.fxjpacrud.gui.UserPopupController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChangeContent {

    private final LoginFX instance;
    private final Stage stage;

    public ChangeContent(LoginFX instance, Stage stage) {
        this.instance = instance;
        this.stage = stage;
    }

    public void replaceSceneContent(String fxml) {
        try {
            Parent page = (Parent) FXMLLoader.load(instance.getClass().getResource(fxml), null, new JavaFXBuilderFactory());
            Scene scene = stage.getScene();
            if (scene == null) {
                scene = new Scene(page);
                stage.setScene(scene);
            } else {
                stage.getScene().setRoot(page);
            }
            stage.sizeToScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserEntity displayPopupDialog() {
        UserEntity ue = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(instance.getClass().getResource("/view/UserPopup.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add User");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            UserPopupController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
            ue = controller.getCurrentUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ue;
    }
}
