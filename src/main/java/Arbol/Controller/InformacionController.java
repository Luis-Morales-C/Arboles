package Arbol.Controller;

import Arbol.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class InformacionController {


    @FXML
    private Tab TabInformacion1;

    @FXML
    private Tab TabInformacion11;

    @FXML
    private Tab TabInformacion12;

    @FXML
    private ImageView image;

    private MainApp main=new MainApp();
    @FXML
    void irArbolBinario(MouseEvent event) throws IOException {
        main.showMainStage();
    }

    @FXML
    void irGrafo(MouseEvent event) throws IOException {
        main.showArbolUserStage();
    }

}