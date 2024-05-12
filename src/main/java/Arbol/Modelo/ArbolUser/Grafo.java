package Arbol.Modelo.ArbolUser;

import java.util.*;

public class Grafo {
    private List<Nodo> nodos;
    private List<Arista> aristas;
    private Map<String, Integer> contadorAristas;

    public Grafo() {
        this.nodos = new ArrayList<>();
        this.aristas = new ArrayList<>();
        contadorAristas = new HashMap<>();
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
        String clave = obtenerClave(arista);
        contadorAristas.put(clave, contadorAristas.getOrDefault(clave, 0) + 1);
    }
    public void eliminarNodo(Nodo nodo) {
        List<Arista> aristasAEliminar = new ArrayList<>();
        for (Arista arista : aristas) {
            if (arista.getOrigen() == nodo || arista.getDestino() == nodo) {
                aristasAEliminar.add(arista);
            }
        }
        aristas.removeAll(aristasAEliminar);

        nodos.remove(nodo);
    }


    public int obtenerContadorAristas(Arista arista) {
        String clave = obtenerClave(arista);
        return contadorAristas.getOrDefault(clave, 0);
    }
    public boolean esConexo() {
        if (nodos.isEmpty()) {
            return false;
        }

        Nodo primerNodo = nodos.get(0);
        Set<Nodo> visitados = new HashSet<>();
        Deque<Nodo> cola = new ArrayDeque<>();
        cola.offer(primerNodo);

        while (!cola.isEmpty()) {
            Nodo nodoActual = cola.poll();
            visitados.add(nodoActual);
            for (Arista arista : aristas) {
                if (arista.getOrigen() == nodoActual || arista.getDestino() == nodoActual) {
                    Nodo vecino = arista.getOrigen() == nodoActual ? arista.getDestino() : arista.getOrigen();
                    if (!visitados.contains(vecino)) {
                        cola.offer(vecino);
                    }
                }
            }
        }

        return visitados.size() == nodos.size();
    }


    private String obtenerClave(Arista arista) {
        String nombreOrigen = arista.getOrigen().getNombre();
        String nombreDestino = arista.getDestino().getNombre();
        if (nombreOrigen.compareTo(nombreDestino) < 0) {
            return nombreOrigen + "-" + nombreDestino;
        } else {
            return nombreDestino + "-" + nombreOrigen;
        }
    }

    public String encontrarCircuito() {
        Set<Nodo> visitados = new HashSet<>();
        List<Nodo> camino = new ArrayList<>();
        for (Nodo nodo : nodos) {
            if (!visitados.contains(nodo) && dfs(nodo, null, visitados, camino)) {

                StringBuilder circuito = new StringBuilder();
                circuito.append(camino.get(camino.size() - 1).getNombre());
                for (int i = camino.size() - 2; i >= 0; i--) {
                    circuito.append(" -> ").append(camino.get(i).getNombre());
                    if (camino.get(i) == camino.get(camino.size() - 1)) {
                        break;
                    }
                }
                return circuito.toString();
            }
        }
        return "No existen circuitos";
    }

    private boolean dfs(Nodo actual, Nodo padre, Set<Nodo> visitados, List<Nodo> camino) {
        visitados.add(actual);
        camino.add(actual);

        for (Arista arista : aristas) {
            Nodo vecino = null;
            if (arista.getOrigen() == actual) {
                vecino = arista.getDestino();
            } else if (arista.getDestino() == actual) {
                vecino = arista.getOrigen();
            }
            if (vecino != null) {
                if (!visitados.contains(vecino)) {
                    if (dfs(vecino, actual, visitados, camino)) {
                        return true;
                    }
                } else if (!vecino.equals(padre)) {

                    return true;
                }
            }
        }

        camino.remove(camino.size() - 1);
        return false;
    }
    public boolean esArbol() {
        return esConexo() && encontrarCircuito().equals("No existen circuitos");
    }

}
