package Arbol.Modelo;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinario {
    private Nodo nodoRaiz;


    public ArbolBinario() {
        this.nodoRaiz = null;
    }
    public void insertar(Nodo nodo) {
        nodoRaiz = insertarRecursivo(nodoRaiz, nodo);
    }

    private Nodo insertarRecursivo(Nodo nodoActual, Nodo nodoNuevo) {
        if (nodoActual == null) {
            return nodoNuevo;
        }
        if (nodoNuevo.getValorNodo() < nodoActual.getValorNodo()) {
            nodoActual.setNodoIzquierda(insertarRecursivo(nodoActual.getNodoIzquierda(), nodoNuevo));
        } else if (nodoNuevo.getValorNodo() > nodoActual.getValorNodo()) {
            nodoActual.setNodoDerecha(insertarRecursivo(nodoActual.getNodoDerecha(), nodoNuevo));
        }
        return nodoActual;
    }

    public void eliminar(Nodo nodo) {
        nodoRaiz = eliminarRecursivo(nodoRaiz, nodo);
    }

    private Nodo eliminarRecursivo(Nodo nodoActual, Nodo nodoEliminar) {
        if (nodoActual == null) {
            return null;
        }
        if (nodoEliminar.getValorNodo() < nodoActual.getValorNodo()) {
            nodoActual.setNodoIzquierda(eliminarRecursivo(nodoActual.getNodoIzquierda(), nodoEliminar));
        } else if (nodoEliminar.getValorNodo() > nodoActual.getValorNodo()) {
            nodoActual.setNodoDerecha(eliminarRecursivo(nodoActual.getNodoDerecha(), nodoEliminar));
        } else {
            if (nodoActual.getNodoIzquierda() == null) {
                return nodoActual.getNodoDerecha();
            } else if (nodoActual.getNodoDerecha() == null) {
                return nodoActual.getNodoIzquierda();
            } else {
                nodoActual.setValorNodo(encontrarMenorValor(nodoActual.getNodoDerecha()));
                nodoActual.setNodoDerecha(eliminarRecursivo(nodoActual.getNodoDerecha(), new Nodo(nodoActual.getValorNodo())));
            }
        }
        return nodoActual;
    }

    private int encontrarMenorValor(Nodo nodo) {
        return nodo.getNodoIzquierda() == null ? nodo.getValorNodo() : encontrarMenorValor(nodo.getNodoIzquierda());
    }

    private int obtenerNivelRecursivo(Nodo nodoActual, Nodo nodoBuscado, int nivel) {
        if (nodoActual == null) {
            return -1;
        }
        if (nodoActual == nodoBuscado) {
            return nivel;
        }
        int nivelIzquierdo = obtenerNivelRecursivo(nodoActual.getNodoIzquierda(), nodoBuscado, nivel + 1);
        int nivelDerecho = obtenerNivelRecursivo(nodoActual.getNodoDerecha(), nodoBuscado, nivel + 1);

        if (nivelIzquierdo != -1) {
            return nivelIzquierdo;
        }
        if (nivelDerecho != -1) {
            return nivelDerecho;
        }
        return -1;
    }
    public List<Integer> encontrarHojas() {
        List<Integer> hojas = new ArrayList<>();
        encontrarHojasRecursivo(nodoRaiz, hojas);
        return hojas;
    }

    private void encontrarHojasRecursivo(Nodo nodoActual, List<Integer> hojas) {
        if (nodoActual != null) {
            if (nodoActual.getNodoIzquierda() == null && nodoActual.getNodoDerecha() == null) {
                hojas.add(nodoActual.getValorNodo());
            } else {
                encontrarHojasRecursivo(nodoActual.getNodoIzquierda(), hojas);
                encontrarHojasRecursivo(nodoActual.getNodoDerecha(), hojas);
            }
        }
    }
    public List<Integer> encontrarVerticesInternos() {
        List<Integer> verticesInternos = new ArrayList<>();
        encontrarVerticesInternosRecursivo(nodoRaiz, verticesInternos);
        return verticesInternos;
    }

    private void encontrarVerticesInternosRecursivo(Nodo nodoActual, List<Integer> verticesInternos) {
        if (nodoActual != null) {

            if (nodoActual != nodoRaiz && (nodoActual.getNodoIzquierda() != null || nodoActual.getNodoDerecha() != null)) {
                verticesInternos.add(nodoActual.getValorNodo());
            }

            encontrarVerticesInternosRecursivo(nodoActual.getNodoIzquierda(), verticesInternos);
            encontrarVerticesInternosRecursivo(nodoActual.getNodoDerecha(), verticesInternos);
        }
    }


    public void imprimirEnOrden() {
        imprimirEnOrdenRecursivo(nodoRaiz);
    }

    private void imprimirEnOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            imprimirEnOrdenRecursivo(nodo.getNodoIzquierda());
            System.out.print(nodo.getValorNodo() + " ");
            imprimirEnOrdenRecursivo(nodo.getNodoDerecha());
        }
    }

    public Nodo getNodoRaiz() {
        return nodoRaiz;
    }

    public void setNodoRaiz(Nodo nodoRaiz) {
        this.nodoRaiz = nodoRaiz;
    }

}
