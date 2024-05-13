package Arbol.Controller;

import Arbol.Modelo.ArbolBinario;
import Arbol.Modelo.Nodo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.List;

public class ArbolBinarioController {
    private ArbolBinario arbolBinario = new ArbolBinario();

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
    private Pane panaInformacion;

    @FXML
    private Button btnObtener;




    @FXML
    void ObtenerDetalle(ActionEvent event) {
        // Limpiar el contenido anterior en el Pane
        panaInformacion.getChildren().clear();

        // Obtener la lista de hojas del árbol binario
        List<Integer> hojas = arbolBinario.encontrarHojas();
        // Obtener la lista de vértices internos del árbol binario
        List<Integer> verticesInternos = arbolBinario.encontrarVerticesInternos();


        // Construir una representación de texto de la lista de hojas
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hojas del árbol:\n");
        for (int hoja : hojas) {
            stringBuilder.append(hoja).append("\n");
        }
        String hojasTexto = stringBuilder.toString();

        // Mostrar la representación de texto en el Pane
        Label labelHojas = new Label(hojasTexto);
        panaInformacion.getChildren().add(labelHojas);

        // Construir una representación de texto de la lista de vértices internos
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder.append("Vértices internos del árbol:\n");
        for (int vertice : verticesInternos) {
            stringBuilder.append(vertice).append("\n");
        }
        String verticesTexto = stringBuilder.toString();

        // Mostrar la representación de texto en el Pane
        Label labelVertices = new Label(verticesTexto);
        panaInformacion.getChildren().add(labelVertices);
    }

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
            // Tamaño del nodo
            double radio = 22; // El radio determina el tamaño del nodo

            // Color del relleno del nodo
            Color colorRelleno = Color.BLACK; // Cambia este color según tus preferencias

            // Color del texto del nodo
            Color colorTexto = Color.CYAN; // Cambia este color según tus preferencias

            // Tamaño del texto
            int tamanoTexto = 20; // Cambia este valor según tus preferencias

            // Dibujar el nodo
            gc.setFill(colorRelleno); // Establecer el color del relleno
            gc.fillOval(x - radio, y - radio, 2 * radio, 2 * radio); // Rellenar el óvalo con el color especificado

            gc.setFill(colorTexto); // Establecer el color del texto
            gc.setFont(Font.font(tamanoTexto)); // Establecer el tamaño del texto
            gc.fillText(String.valueOf(nodo.getValorNodo()), x - 5, y + 5);

            // Dibujar las conexiones a los nodos hijos
            double offsetY = 70;
            double extensionLinea = 20; // Ajusta la longitud de las líneas de conexión
            if (nodo.getNodoIzquierda() != null) {
                double xIzquierda = x - offsetX;
                double yIzquierda = y + offsetY;
                gc.strokeLine(x, y + radio, xIzquierda, yIzquierda - radio);
                dibujarNodo(nodo.getNodoIzquierda(), xIzquierda, yIzquierda, offsetX / 2, gc);
            }
            if (nodo.getNodoDerecha() != null) {
                double xDerecha = x + offsetX;
                double yDerecha = y + offsetY;
                gc.strokeLine(x, y + radio, xDerecha, yDerecha - radio);
                dibujarNodo(nodo.getNodoDerecha(), xDerecha, yDerecha, offsetX / 2, gc);
            }
        }
    }
}
