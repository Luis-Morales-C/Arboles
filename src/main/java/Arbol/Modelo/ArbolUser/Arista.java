package Arbol.Modelo.ArbolUser;

public class Arista {
    private Nodo origen;
    private Nodo destino;
    private double controlX;
    private double controlY;

    public Arista(Nodo origen, Nodo destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public Nodo getOrigen() {
        return origen;
    }

    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    public Nodo getDestino() {
        return destino;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    public double getControlX() {
        return controlX;
    }

    public void setControlX(double controlX) {
        this.controlX = controlX;
    }

    public double getControlY() {
        return controlY;
    }

    public void setControlY(double controlY) {
        this.controlY = controlY;
    }
}
