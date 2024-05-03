package Arbol.Controller;

import Arbol.Modelo.ArbolBinario;
import Arbol.Modelo.Nodo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ArbolBinarioController {
    private ArbolBinario arbolBinario= new ArbolBinario();

    private Nodo nodo;

    @FXML
    private Canvas canvas;

    @FXML
    private Button btEliminarNodoBinario;

    @FXML
    private Button btnCrearNodoBinario;

    @FXML
    private TextField labelValorNodo;


    @FXML
    void CrearNodo(ActionEvent event) {
        // Obtener el valor del TextField
        int valor = Integer.parseInt(labelValorNodo.getText());

        // Crear un nuevo nodo con el valor proporcionado
        Nodo nuevoNodo = new Nodo(valor);

        // Insertar el nuevo nodo en el árbol binario
        arbolBinario.insertar(nuevoNodo);

        dibujarArbol();


    }
    @FXML
    void EliminarNodo(ActionEvent event) {
        int valor = Integer.parseInt(labelValorNodo.getText());

        Nodo nodoEliminar = new Nodo(valor);

        arbolBinario.eliminar(nodoEliminar);

        dibujarArbol();
    }

    @FXML
    void dibujarArbol() {
        // Limpiar el canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Dibujar el árbol
        dibujarNodo(arbolBinario.getNodoRaiz(), canvas.getWidth() / 2, 50, canvas.getWidth() / 4, gc);
    }

    private void dibujarNodo(Nodo nodo, double x, double y, double offsetX, GraphicsContext gc) {
        if (nodo != null) {
            // Dibujar el nodo
            gc.setFill(Color.RED);
            gc.setStroke(Color.BLACK);
            gc.strokeOval(x - 15, y - 15, 30, 30);
            gc.fillText(String.valueOf(nodo.getValorNodo()), x - 5, y + 5);

            // Dibujar las conexiones a los nodos hijos
            double offsetY = 50;
            if (nodo.getNodoIzquierda() != null) {
                double xIzquierda = x - offsetX;
                double yIzquierda = y + offsetY;
                gc.strokeLine(x, y + 15, xIzquierda, yIzquierda - 15);
                dibujarNodo(nodo.getNodoIzquierda(), xIzquierda, yIzquierda, offsetX / 2, gc);
            }
            if (nodo.getNodoDerecha() != null) {
                double xDerecha = x + offsetX;
                double yDerecha = y + offsetY;
                gc.strokeLine(x, y + 15, xDerecha, yDerecha - 15);
                dibujarNodo(nodo.getNodoDerecha(), xDerecha, yDerecha, offsetX / 2, gc);
            }
        }
    }
}