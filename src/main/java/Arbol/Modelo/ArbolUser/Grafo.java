package Arbol.Modelo.ArbolUser;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Nodo> nodos;
    private List<Arista> aristas;

    public Grafo() {
        this.nodos = new ArrayList<>();
        this.aristas = new ArrayList<>();
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public void agregarNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    public void agregarArista(Arista arista) {
        aristas.add(arista);
    }
}
