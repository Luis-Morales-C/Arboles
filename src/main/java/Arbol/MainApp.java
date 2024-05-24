package Arbol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {

    private Stage infoStage;
    private Stage mainStage;
    private Stage arbolUserStage;

    @Override
    public void start(Stage stage) throws IOException {
        showInfoStage();
    }

    public void showMainStage() throws IOException {
        if (mainStage == null) {
            mainStage = new Stage();
            FXMLLoader fxmlLoaderInfo = new FXMLLoader(getClass().getResource("arbolBinario.fxml"));
            Scene scene = new Scene(fxmlLoaderInfo.load(), 1336, 680);
            mainStage.setScene(scene);
            mainStage.setResizable(false);
            mainStage.centerOnScreen();
        }
        mainStage.show();
    }

    public void showInfoStage() throws IOException {
        if (infoStage == null) {
            infoStage = new Stage();
            FXMLLoader fxmlLoaderInfo = new FXMLLoader(getClass().getResource("informacion.fxml"));
            Scene scene = new Scene(fxmlLoaderInfo.load(), 1143, 698);
            infoStage.setTitle("Informacion");
            infoStage.setScene(scene);
            infoStage.setResizable(false);
            infoStage.centerOnScreen();
        }
        infoStage.show();
    }

    public void showArbolUserStage() throws IOException {
        if (arbolUserStage == null) {
            arbolUserStage = new Stage();
            FXMLLoader fxmlLoaderInfo = new FXMLLoader(getClass().getResource("ArbolUser.fxml"));
            Scene scene = new Scene(fxmlLoaderInfo.load(), 1400, 700);
            arbolUserStage.setTitle("Arbol user");
            arbolUserStage.setScene(scene);
            arbolUserStage.setResizable(false);
            arbolUserStage.centerOnScreen();
        }
        arbolUserStage.show();
    }

    public void closeInfoStage() {
        if (infoStage != null) {
            infoStage.close();
        }
    }

    public void closeMainStage() {
        if (mainStage != null) {
            mainStage.close();
        }
    }

    public void closeArbolUserStage() {
        if (arbolUserStage != null) {
            arbolUserStage.close();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}
