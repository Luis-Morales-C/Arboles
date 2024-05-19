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
        Scene scene = new Scene(fxmlLoaderInfo.load(), 950, 650);
        infoStage.setScene(scene);
        infoStage.show();
    }


    public void showInfoStage() throws IOException {
        Stage infoStage = new Stage();
        FXMLLoader fxmlLoaderInfo = new FXMLLoader(getClass().getResource("informacion.fxml"));
        Scene scene = new Scene(fxmlLoaderInfo.load(), 950, 650);
        infoStage.setTitle("Informacion");
        infoStage.setScene(scene);
        //infoStage.setFullScreen(true);  // Establece la pantalla completa
        infoStage.show();
    }

    public void showArbolUserStage() throws IOException {
        Stage infoStage = new Stage();
        FXMLLoader fxmlLoaderInfo = new FXMLLoader(getClass().getResource("ArbolUser.fxml"));
        Scene scene = new Scene(fxmlLoaderInfo.load(), 950, 650);
        infoStage.setTitle("Arbol user");
        infoStage.setScene(scene);
        infoStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
