package Arbol.Modelo.ArbolUser;

public class Arista {
    private Nodo origen;
    private Nodo destino;

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
}
