package Arbol.Modelo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Nodo {
    Nodo nodoIzquierda;
    Nodo nodoDerecha;
    int valorNodo;

    private Circle figura;
    private int nivel;
    public Nodo(int valorNodo) {
        this.valorNodo = valorNodo;
        nodoIzquierda=null;
        nodoDerecha=null;
        this.figura = new Circle();
        this.nivel = 0;


    }

    public Nodo getNodoIzquierda() {
        return nodoIzquierda;
    }

    public void setNodoIzquierda(Nodo nodoIzquierda) {
        this.nodoIzquierda = nodoIzquierda;
    }

    public Nodo getNodoDerecha() {
        return nodoDerecha;
    }

    public void setNodoDerecha(Nodo nodoDerecha) {
        this.nodoDerecha = nodoDerecha;
    }

    public int getValorNodo() {
        return valorNodo;
    }
    public void setValorNodo(int valorNodo) {
        this.valorNodo = valorNodo;
    }
    public Circle getFigura() {
        return figura;
    }

    public void setFigura(Circle figura) {
        this.figura = figura;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
