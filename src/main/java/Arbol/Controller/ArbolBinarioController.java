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
        panaInformacion.getChildren().clear();

        Nodo raiz = arbolBinario.getNodoRaiz();

        double posY = 20; // Posición inicial en Y

        // Etiqueta para la raíz del árbol
        Label labelRaiz = new Label("Raíz del árbol: " + raiz.getValorNodo());
        labelRaiz.setLayoutY(posY);
        panaInformacion.getChildren().add(labelRaiz);
        posY += 27;

        // Obtener la lista de hojas del árbol binario
        List<Integer> hojas = arbolBinario.encontrarHojas();

        // Etiqueta para las hojas del árbol
        Label labelHojas = new Label("Hojas del árbol:");
        labelHojas.setLayoutY(posY);
        panaInformacion.getChildren().add(labelHojas);
        posY += 27;

        // Mostrar cada hoja en una etiqueta separada
        for (int hoja : hojas) {
            Label hojaLabel = new Label(String.valueOf(hoja));
            hojaLabel.setLayoutY(posY);
            panaInformacion.getChildren().add(hojaLabel);
            posY += 17; // Incrementar la posición Y para la próxima etiqueta
        }

        // Obtener la lista de vértices internos del árbol binario
        List<Integer> verticesInternos = arbolBinario.encontrarVerticesInternos();

        // Etiqueta para los vértices internos del árbol
        Label labelVertices = new Label("Vértices internos del árbol:");
        labelVertices.setLayoutY(posY);
        panaInformacion.getChildren().add(labelVertices);
        posY += 17;

        // Mostrar los vértices internos del árbol
        for (int vertice : verticesInternos) {
            Label verticeLabel = new Label(String.valueOf(vertice));
            verticeLabel.setLayoutY(posY);
            panaInformacion.getChildren().add(verticeLabel);
            posY += 17;
        }

        // Etiqueta para el peso del árbol
        int peso = arbolBinario.calcularPeso();
        Label labelPeso = new Label("Peso: " + peso);
        labelPeso.setLayoutY(posY);
        panaInformacion.getChildren().add(labelPeso);
        posY += 27;

        // Etiqueta para el nivel del árbol
        int nivel = arbolBinario.calcularNivel();
        Label labelNivel = new Label("Nivel: " + nivel);
        labelNivel.setLayoutY(posY);
        panaInformacion.getChildren().add(labelNivel);
        posY += 27;

        // Obtener el recorrido inorden del árbol binario
        String recorridoInOrden = arbolBinario.recorridoInOrden();

        // Etiqueta para el recorrido inorden del árbol
        Label labelRecorridoInOrden = new Label("Recorrido Inorden: " + recorridoInOrden);
        labelRecorridoInOrden.setLayoutY(posY);
        panaInformacion.getChildren().add(labelRecorridoInOrden);
        posY += 27;

        // Obtener el recorrido preorden del árbol binario
        String recorridoPreOrden = arbolBinario.recorridoPreOrden();

        // Etiqueta para el recorrido preorden del árbol
        Label labelRecorridoPreOrden = new Label("Recorrido Preorden: " + recorridoPreOrden);
        labelRecorridoPreOrden.setLayoutY(posY);
        panaInformacion.getChildren().add(labelRecorridoPreOrden);
        posY += 27;

        // Obtener el recorrido postorden del árbol binario
        String recorridoPostOrden = arbolBinario.recorridoPostOrden();

        // Etiqueta para el recorrido postorden del árbol
        Label labelRecorridoPostOrden = new Label("Recorrido Postorden: " + recorridoPostOrden);
        labelRecorridoPostOrden.setLayoutY(posY);
        panaInformacion.getChildren().add(labelRecorridoPostOrden);
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
