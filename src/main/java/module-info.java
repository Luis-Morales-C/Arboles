module arboles.arboles {
    requires javafx.controls;
    requires javafx.fxml;


    opens Arbol to javafx.fxml;
    exports Arbol;
    exports Arbol.Controller;
    opens Arbol.Controller to javafx.fxml;
}