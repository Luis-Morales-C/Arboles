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

    public String recorridoInOrden() {
        StringBuilder resultado = new StringBuilder();
        recorridoInOrdenRecursivo(nodoRaiz, resultado);
        return resultado.toString();
    }

    private void recorridoInOrdenRecursivo(Nodo nodo, StringBuilder resultado) {
        if (nodo != null) {
            recorridoInOrdenRecursivo(nodo.getNodoIzquierda(), resultado);
            resultado.append(nodo.getValorNodo()).append(" ");
            recorridoInOrdenRecursivo(nodo.getNodoDerecha(), resultado);
        }
    }
    public String recorridoPreOrden() {
        StringBuilder resultado = new StringBuilder();
        recorridoPreOrdenRecursivo(nodoRaiz, resultado);
        return resultado.toString();
    }

    private void recorridoPreOrdenRecursivo(Nodo nodo, StringBuilder resultado) {
        if (nodo != null) {
            resultado.append(nodo.getValorNodo()).append(" ");
            recorridoPreOrdenRecursivo(nodo.getNodoIzquierda(), resultado);
            recorridoPreOrdenRecursivo(nodo.getNodoDerecha(), resultado);
        }
    }
    public String recorridoPostOrden() {
        StringBuilder resultado = new StringBuilder();
        recorridoPostOrdenRecursivo(nodoRaiz, resultado);
        return resultado.toString();
    }

    private void recorridoPostOrdenRecursivo(Nodo nodo, StringBuilder resultado) {
        if (nodo != null) {
            recorridoPostOrdenRecursivo(nodo.getNodoIzquierda(), resultado);
            recorridoPostOrdenRecursivo(nodo.getNodoDerecha(), resultado);
            resultado.append(nodo.getValorNodo()).append(" ");
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
    public int calcularPeso() {
        return calcularPesoRecursivo(nodoRaiz);
    }

    private int calcularPesoRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        } else {
            // Sumar 1 por el nodo actual y los nodos en los subárboles izquierdo y derecho
            return 1 + calcularPesoRecursivo(nodo.getNodoIzquierda()) + calcularPesoRecursivo(nodo.getNodoDerecha());
        }
    }
    public int calcularNivel() {
        return calcularNivelRecursivo(nodoRaiz);
    }

    private int calcularNivelRecursivo(Nodo nodo) {
        if (nodo == null) {
            return -1; // No hay nodos, por lo tanto, el nivel es -1
        } else {
            // Calcular la profundidad máxima de los subárboles izquierdo y derecho
            int nivelIzquierdo = calcularNivelRecursivo(nodo.getNodoIzquierda());
            int nivelDerecho = calcularNivelRecursivo(nodo.getNodoDerecha());

            // Retornar el nivel máximo de los subárboles izquierdo y derecho, más uno (para el nodo actual)
            return Math.max(nivelIzquierdo, nivelDerecho) + 1;
        }
    }


    public Nodo getNodoRaiz() {
        return nodoRaiz;
    }

    public void setNodoRaiz(Nodo nodoRaiz) {
        this.nodoRaiz = nodoRaiz;
    }

}
