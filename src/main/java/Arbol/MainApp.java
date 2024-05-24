package Arbol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        showInfoStage();

    }

    public void showMainStage() throws IOException {
        Stage infoStage = new Stage();
        FXMLLoader fxmlLoaderInfo = new FXMLLoader(getClass().getResource("arbolBinario.fxml"));
        Scene scene = new Scene(fxmlLoaderInfo.load(), 1280, 800);
        infoStage.setScene(scene);
        infoStage.show();
        infoStage.setResizable(false);
        infoStage.centerOnScreen();
    }


    public void showInfoStage() throws IOException {
        Stage infoStage = new Stage();
        FXMLLoader fxmlLoaderInfo = new FXMLLoader(getClass().getResource("informacion.fxml"));
        Scene scene = new Scene(fxmlLoaderInfo.load(), 1143, 698);
        infoStage.setTitle("Informacion");
        infoStage.setScene(scene);
        //infoStage.setFullScreen(true);  // Establece la pantalla completa
        infoStage.show();
        infoStage.setResizable(false);
        infoStage.centerOnScreen();
    }

    public void showArbolUserStage() throws IOException {
        Stage infoStage = new Stage();
        FXMLLoader fxmlLoaderInfo = new FXMLLoader(getClass().getResource("ArbolUser.fxml"));
        Scene scene = new Scene(fxmlLoaderInfo.load(), 1464, 700);
        infoStage.setTitle("Arbol user");
        infoStage.setScene(scene);
        infoStage.show();
        infoStage.setResizable(false);
        infoStage.centerOnScreen();
    }


    public static void main(String[] args) {
        launch();
    }
}
