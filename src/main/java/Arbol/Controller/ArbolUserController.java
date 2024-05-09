package Arbol.Controller;

import Arbol.Modelo.ArbolUser.Arista;
import Arbol.Modelo.ArbolUser.Grafo;
import Arbol.Modelo.ArbolUser.Nodo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArbolUserController {

    @FXML
    private Canvas canvas;

    private Grafo grafo = new Grafo();

    private GraphicsContext gc;

    @FXML
    private Button btnCrearArista;


    @FXML
    void crearArista(ActionEvent event) {
        if (nodoSeleccionado1 != null && nodoSeleccionado2 != null) {
            Arista arista = new Arista(nodoSeleccionado1, nodoSeleccionado2);
            grafo.agregarArista(arista);
            dibujar();
        }
    }
    @FXML
    public void initialize() {
        grafo = new Grafo();
        gc = canvas.getGraphicsContext2D();

        // Evento para crear un nodo al hacer clic
        canvas.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            crearNodo(x, y);
        });

        // Evento para seleccionar un nodo al hacer clic
        canvas.setOnMousePressed(event -> {
            double x = event.getX();
            double y = event.getY();
            seleccionarNodo(x, y);
        });
    }

    private void crearNodo(double x, double y) {

        boolean dentroDeNodoExistente = false;
        for (Nodo nodo : grafo.getNodos()) {
            double distancia = Math.sqrt(Math.pow(x - nodo.getX(), 2) + Math.pow(y - nodo.getY(), 2));
            if (distancia <= 15) {
                dentroDeNodoExistente = true;
                break;
            }
        }


        if (!dentroDeNodoExistente) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Nombre del Nodo");
            dialog.setHeaderText(null);
            dialog.setContentText("Ingrese el nombre del nodo:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(nombre -> {
                Nodo nodo = new Nodo(x, y, nombre);
                grafo.agregarNodo(nodo);
                dibujar();
            });
        }
    }


    private void dibujar() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Dibujar nodos
        for (Nodo nodo : grafo.getNodos()) {
            if (nodo == nodoSeleccionado1 || nodo == nodoSeleccionado2) {
                gc.setFill(Color.RED);
            } else {
                gc.setFill(Color.BLACK);
            }
            gc.fillOval(nodo.getX() - 15, nodo.getY() - 15, 25, 25);
            gc.setFill(Color.BLACK);
            gc.fillText(nodo.getNombre(), nodo.getX() - 10, nodo.getY() - 23);
        }

        // Dibujar aristas
        // Dibujar aristas
        for (Arista arista : grafo.getAristas()) {
            gc.setStroke(Color.BLACK);
            gc.strokeLine(arista.getOrigen().getX(), arista.getOrigen().getY(), arista.getDestino().getX(), arista.getDestino().getY());
        }


    }

    private Nodo nodoSeleccionado1 = null;
    private Nodo nodoSeleccionado2 = null;
    private double offsetX;
    private double offsetY;


    private void seleccionarNodo(double x, double y) {
        for (Nodo nodo : grafo.getNodos()) {
            double distancia = Math.sqrt(Math.pow(x - nodo.getX(), 2) + Math.pow(y - nodo.getY(), 2));
            if (distancia <= 15) { // Consideramos seleccionado si el clic está dentro del radio del nodo
                if (nodoSeleccionado1 == null) {
                    nodoSeleccionado1 = nodo;
                } else if (nodoSeleccionado2 == null) {
                    nodoSeleccionado2 = nodo;
                } else{
                    // Si ya hay dos nodos seleccionados, deseleccionarlos y seleccionar el actual
                    nodoSeleccionado1 = null;
                    nodoSeleccionado2 = null;
                }
                dibujar(); // Redibujar para actualizar la apariencia
                return; // Terminar el bucle ya que solo queremos seleccionar un nodo a la vez
            }
        }
    }



    private void moverNodo(double x, double y) {
        if (nodoSeleccionado1 != null) {
            nodoSeleccionado1.setX(x - offsetX);
            nodoSeleccionado1.setY(y - offsetY);
        }
        if (nodoSeleccionado2 != null) {
            nodoSeleccionado2.setX(x - offsetX);
            nodoSeleccionado2.setY(y - offsetY);
        }
    }

    @FXML
    private void onMousePressed(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        for (Nodo nodo : grafo.getNodos()) {
            if (nodo == nodoSeleccionado1 || nodo == nodoSeleccionado2) {
                offsetX = x - nodo.getX();
                offsetY = y - nodo.getY();
                return;
            }
        }

    }
    @FXML
    private void onMouseDragged(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        moverNodo(x, y);

        dibujar();
    }
}
