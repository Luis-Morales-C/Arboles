package Arbol.Controller;

import Arbol.Modelo.ArbolUser.Arista;
import Arbol.Modelo.ArbolUser.Grafo;
import Arbol.Modelo.ArbolUser.Nodo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArbolUserController {

    @FXML
    private Canvas canvas;


    @FXML
    private Canvas canvasArbol;


    @FXML
    private Button btnObtener;


    private Grafo grafo = new Grafo();

    private GraphicsContext gc;

    @FXML
    private Button btnCrearArista;

    @FXML
    void obtenerArbol(ActionEvent event) {
        dibujarArbol();

    }


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
            int contador = grafo.obtenerContadorAristas(arista);
            gc.setFill(Color.BLUE);
            gc.fillText(Integer.toString(contador), (arista.getOrigen().getX() + arista.getDestino().getX()) / 2, (arista.getOrigen().getY() + arista.getDestino().getY()) / 2);
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
                if (nodo == nodoSeleccionado1) {
                    nodoSeleccionado1 = null; // Deseleccionar el nodo si ya estaba seleccionado
                } else if (nodo == nodoSeleccionado2) {
                    nodoSeleccionado2 = null; // Deseleccionar el nodo si ya estaba seleccionado
                } else {
                    if (nodoSeleccionado1 == null) {
                        nodoSeleccionado1 = nodo;
                    } else if (nodoSeleccionado2 == null) {
                        nodoSeleccionado2 = nodo;
                    } else {
                        // Si ya hay dos nodos seleccionados, deseleccionarlos y seleccionar el actual
                        nodoSeleccionado1 = null;
                        nodoSeleccionado2 = null;
                    }
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

    @FXML
    void dibujarArbol() {
        GraphicsContext gc = canvasArbol.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasArbol.getWidth(), canvasArbol.getHeight());

        if (!grafo.esConexo()) {
            mostrarMensajeError("El grafo no es conexo.");
            return;
        }
        String circuito = grafo.encontrarCircuito();
        if (!circuito.equals("No existen circuitos")) {
            mostrarMensajeError("El grafo contiene un circuito: " + circuito);
            return;
        }


        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nombre del Nodo Raíz");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese el nombre del nodo raíz:");
        Optional<String> result = dialog.showAndWait();
        String nombreNodoRaiz = result.orElse("");

        Nodo nodoRaiz = null;
        for (Nodo nodo : grafo.getNodos()) {
            if (nodo.getNombre().equals(nombreNodoRaiz)) {
                nodoRaiz = nodo;
                break;
            }
        }

        if (nodoRaiz != null) {
            dibujarNodo(nodoRaiz, canvasArbol.getWidth() / 2, 50, canvasArbol.getWidth() / 4, gc);
        }
    }

    private void dibujarNodo(Nodo nodo, double x, double y, double offsetX, GraphicsContext gc) {
        if (nodo != null) {

            double radio = 22;

            Color colorRelleno = Color.BLACK;

            Color colorTexto = Color.CYAN;


            int tamanoTexto = 20;

            gc.setFill(colorRelleno);
            gc.fillOval(x - radio, y - radio, 2 * radio, 2 * radio);
            gc.setFill(colorTexto);
            gc.setFont(Font.font(tamanoTexto));
            gc.fillText(nodo.getNombre(), x - 5, y + 5);


            double offsetY = 70;
            double extensionLinea = 20;
            for (Arista arista : grafo.getAristas()) {
                if (arista.getOrigen() == nodo) {
                    Nodo hijo = arista.getDestino();
                    double xHijo = x + offsetX;
                    double yHijo = y + offsetY;
                    gc.strokeLine(x, y + radio, xHijo, yHijo - radio);
                    dibujarNodo(hijo, xHijo, yHijo, offsetX / 2, gc);
                }
            }
        }
    }
    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
