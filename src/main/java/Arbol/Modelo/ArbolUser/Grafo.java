package Arbol.Modelo.ArbolUser;

import java.util.*;

public class Grafo {
    private List<Nodo> nodos;
    private List<Arista> aristas;
    private Map<String, Integer> contadorAristas;

    private Tipo tipo;

    public Grafo() {
        this.nodos = new ArrayList<>();
        this.aristas = new ArrayList<>();
        contadorAristas = new HashMap<>();
    }

    public List<Nodo> obtenerVecinos(Nodo nodo) {
        List<Nodo> vecinos = new ArrayList<>();

        // Iterar sobre todas las aristas del grafo
        for (Arista arista : getAristas()) {
            // Verificar si el nodo es el origen de la arista
            if (arista.getOrigen() == nodo) {
                vecinos.add(arista.getDestino());
            }
            // Verificar si el nodo es el destino de la arista
            else if (arista.getDestino() == nodo) {
                vecinos.add(arista.getOrigen());
            }
        }

        return vecinos;
    }


    public Nodo encontrarRaiz() {

        Set<Nodo> nodosDestino = new HashSet<>();

        for (Arista arista : aristas) {
            nodosDestino.add(arista.getDestino());
        }


        for (Nodo nodo : nodos) {
            if (!nodosDestino.contains(nodo)) {
                return nodo;
            }
        }

        return null;
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
        for (Arista arista : aristasAEliminar) {
            String clave = obtenerClave(arista);
            int contador = contadorAristas.get(clave);
            if (contador == 1) {
                contadorAristas.remove(clave);
            } else {
                contadorAristas.put(clave, contador - 1);
            }
        }
        aristas.removeAll(aristasAEliminar);
        nodos.remove(nodo);
    }

    private List<Arista> obtenerAristasDelNodo(Nodo nodo) {
        List<Arista> aristasNodo = new ArrayList<>();
        for (Arista arista : aristas) {
            if (arista.getOrigen().equals(nodo) || arista.getDestino().equals(nodo)) {
                aristasNodo.add(arista);
            }
        }
        return aristasNodo;
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
                for (Nodo n : camino) {
                    circuito.append(n.getNombre()).append(" -> ");
                }
                circuito.append(camino.get(0).getNombre());
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
                    // Encuentra un ciclo
                    return true;
                }
            }
        }

        camino.remove(camino.size() - 1);
        visitados.remove(actual);
        return false;
    }

    public List<Nodo> encontrarHojas() {
        List<Nodo> hojas = new ArrayList<>();
        for (Nodo nodo : nodos) {
            if (esHoja(nodo)) {
                hojas.add(nodo);
            }
        }
        return hojas;
    }

    private boolean esHoja(Nodo nodo) {
        int contadorVecinos = 0;
        for (Arista arista : aristas) {
            if (arista.getOrigen() == nodo) {
                contadorVecinos++;
            }
        }
        return contadorVecinos == 0;
    }


    public List<String> describirTopologia() {
        List<String> descripcion = new ArrayList<>();
        int gradoMaximo = 0;

        for (Nodo nodo : nodos) {
            int gradoNodo = obtenerGradoNodo(nodo);
            gradoMaximo = Math.max(gradoMaximo, gradoNodo);
        }
        if (gradoMaximo == 0) {
            descripcion.add("El grafo está vacío.");
        } else {
            descripcion.add("" + obtenerTipoArbol(gradoMaximo));
        }
        return descripcion;
    }

    private String obtenerTipoArbol(int gradoMaximo) {
        switch (gradoMaximo) {
            case 1:
                return Tipo.Arbol_binario.name();
            case 2:
                return Tipo.Arbol_binario.name();
            case 3:
                return Tipo.Arbol_ternario.name();
            case 4:
                return Tipo.Arbol_cuanternario.name();
            case 5:
                return Tipo.Arbol_quinario.name();
            case 6:
                return Tipo.Árbol_senario.name();
            case 7:
                return Tipo.Árbol_septenario.name();
            default:
                return "Tipo de árbol no definido para este grado.";
        }
    }

    private int obtenerGradoNodo(Nodo nodo) {
        int grado = 0;
        for (Arista arista : aristas) {
            if (arista.getOrigen() == nodo) {
                grado++;
            }
        }
        return grado;
    }


    public boolean esArbol() {
        return esConexo() && encontrarCircuito().equals("No existen circuitos");
    }

    public boolean estaVacio() {
        return nodos.isEmpty();
    }

    public boolean esMultigrafo() {
        Set<String> conexiones = new HashSet<>();
        Set<String> conexionesReversas = new HashSet<>();

        for (Arista arista : aristas) {
            String conexion1 = arista.getOrigen().getNombre() + "-" + arista.getDestino().getNombre();
            String conexion2 = arista.getDestino().getNombre() + "-" + arista.getOrigen().getNombre();

            if (conexiones.contains(conexion1) || conexionesReversas.contains(conexion2)) {
                return true;
            } else {
                conexiones.add(conexion1);
                conexionesReversas.add(conexion2);
            }
        }

        return false;
    }

    public int calcularNivelesGrafo(Nodo raiz) {
        int maxNivel = 0;
        Queue<Nodo> cola = new LinkedList<>();
        Map<Nodo, Integer> niveles = new HashMap<>();

        cola.offer(raiz);
        niveles.put(raiz, 0);

        while (!cola.isEmpty()) {
            Nodo nodoActual = cola.poll();
            int nivelActual = niveles.get(nodoActual);
            maxNivel = Math.max(maxNivel, nivelActual);

            for (Arista arista : aristas) {
                Nodo vecino = null;
                if (arista.getOrigen() == nodoActual) {
                    vecino = arista.getDestino();
                } else if (arista.getDestino() == nodoActual) {
                    vecino = arista.getOrigen();
                }
                if (vecino != null && !niveles.containsKey(vecino)) {
                    niveles.put(vecino, nivelActual + 1);
                    cola.offer(vecino);
                }
            }
        }

        return maxNivel;
    }

    public int calcularPesoArbol() {
        return nodos.size();
    }

    public List<Nodo> encontrarVerticesInternos(Nodo raiz) {
        List<Nodo> verticesInternos = new ArrayList<>();
        for (Nodo nodo : this.nodos) {
            if (!nodo.equals(raiz) && !esHoja(nodo)) {
                verticesInternos.add(nodo);
            }
        }
        return verticesInternos;
    }

    public Nodo encontrarNodoPorNombre(String nombre) {
        for (Nodo nodo : nodos) {
            if (nodo.getNombre().equals(nombre)) {
                return nodo;
            }
        }
        return null;
    }

    private Map<Nodo, Integer> calcularNivelesDesdeRaiz(Nodo raiz) {
        Map<Nodo, Integer> niveles = new HashMap<>();
        Queue<Nodo> cola = new LinkedList<>();

        cola.offer(raiz);
        niveles.put(raiz, 0);

        while (!cola.isEmpty()) {
            Nodo nodoActual = cola.poll();
            int nivelActual = niveles.get(nodoActual);

            for (Arista arista : aristas) {
                Nodo vecino = null;
                if (arista.getOrigen().equals(nodoActual)) {
                    vecino = arista.getDestino();
                } else if (arista.getDestino().equals(nodoActual)) {
                    vecino = arista.getOrigen();
                }
                if (vecino != null && !niveles.containsKey(vecino)) {
                    niveles.put(vecino, nivelActual + 1);
                    cola.offer(vecino);
                }
            }
        }

        return niveles;
    }

    public int obtenerNivelDeNodo(String nombreNodo) {
        Nodo raiz = encontrarRaiz();
        if (raiz == null) {
            throw new IllegalStateException("El grafo no tiene una raíz definida.");
        }

        Nodo nodo = encontrarNodoPorNombre(nombreNodo);
        if (nodo == null) {
            throw new IllegalArgumentException("Nodo con nombre " + nombreNodo + " no encontrado.");
        }

        Map<Nodo, Integer> niveles = calcularNivelesDesdeRaiz(raiz);
        return niveles.getOrDefault(nodo, -1);
    }

    public String determinarTipoNodo(String nombreNodo) {
        Nodo nodo = encontrarNodoPorNombre(nombreNodo);

        if (nodo == null) {
            throw new IllegalArgumentException("El nodo con nombre " + nombreNodo + " no fue encontrado.");
        }

        if (nodo.equals(encontrarRaiz())) {
            return "Raíz";
        } else if (esHoja(nodo)) {
            return "Hoja";
        } else {
            return "Vértice interno";
        }
    }

    public String encontrarAncestros(String nombreNodo) {
        StringBuilder ancestros = new StringBuilder();
        Nodo nodo = encontrarNodoPorNombre(nombreNodo);
        if (nodo == null) {
            throw new IllegalArgumentException("El nodo con nombre " + nombreNodo + " no fue encontrado.");
        }
        encontrarAncestrosRecursivo(nodo, ancestros);
        return ancestros.toString();
    }

    private void encontrarAncestrosRecursivo(Nodo nodo, StringBuilder ancestros) {
        Nodo raiz = encontrarRaiz();
        if (nodo.equals(raiz)) {
            ancestros.append("");
            return;
        }

        for (Arista arista : aristas) {
            Nodo padre = null;
            if (arista.getDestino().equals(nodo)) {
                padre = arista.getOrigen();
            } else if (arista.getOrigen().equals(nodo)) {
                padre = arista.getDestino();
            }
            if (padre != null) {
                ancestros.append(padre.getNombre()).append("  ");
                encontrarAncestrosRecursivo(padre, ancestros);
                return;
            }
        }
    }
    public String encontrarSucesores(String nombreNodo) {
        StringBuilder sucesores = new StringBuilder();
        Nodo nodo = encontrarNodoPorNombre(nombreNodo);
        if (nodo == null) {
            throw new IllegalArgumentException("El nodo con nombre " + nombreNodo + " no fue encontrado.");
        }
        encontrarSucesoresRecursivo(nodo, sucesores);
        return sucesores.toString();
    }

    private void encontrarSucesoresRecursivo(Nodo nodo, StringBuilder sucesores) {
        // Iterar sobre todas las aristas del grafo
        for (Arista arista : aristas) {
            // Verificar si el nodo es el origen de la arista
            if (arista.getOrigen().equals(nodo)) {
                Nodo sucesor = arista.getDestino();
                sucesores.append(sucesor.getNombre()).append("  ");
                encontrarSucesoresRecursivo(sucesor, sucesores);
            }
        }
    }

    public String encontrarPadre(String nombreNodo) {
        Nodo nodo = encontrarNodoPorNombre(nombreNodo);
        if (nodo == null) {
            throw new IllegalArgumentException("El nodo con nombre " + nombreNodo + " no fue encontrado.");
        }

        Nodo padre = encontrarPadreRecursivo(nodo);
        if (padre == null) {
            return  nombreNodo + " es la raíz";
        } else {
            return  padre.getNombre();
        }
    }

    private Nodo encontrarPadreRecursivo(Nodo nodo) {
        Nodo raiz = encontrarRaiz();
        if (nodo.equals(raiz)) {
            return null;
        }

        for (Arista arista : aristas) {
            Nodo padre = null;
            if (arista.getDestino().equals(nodo)) {
                padre = arista.getOrigen();
            } else if (arista.getOrigen().equals(nodo)) {
                padre = arista.getDestino();
            }
            if (padre != null) {
                return padre;
            }
        }

        return null;
    }

    public String encontrarHijos(String nombreNodo) {
        Nodo nodo = encontrarNodoPorNombre(nombreNodo);
        if (nodo == null) {
            throw new IllegalArgumentException("El nodo con nombre " + nombreNodo + " no fue encontrado.");
        }

        List<Nodo> hijos = encontrarHijosRecursivo(nodo);
        if (hijos.isEmpty()) {
            return "no tiene hijos.";
        } else {
            StringBuilder result = new StringBuilder();
            for (Nodo hijo : hijos) {
                result.append(hijo.getNombre()).append(", ");
            }
            result.delete(result.length() - 2, result.length());
            return result.toString();
        }
    }

    private List<Nodo> encontrarHijosRecursivo(Nodo nodo) {
        List<Nodo> hijos = new ArrayList<>();
        for (Arista arista : aristas) {
            Nodo hijo = null;
            if (arista.getOrigen().equals(nodo)) {
                hijo = arista.getDestino();
            }
            if (hijo != null) {
                hijos.add(hijo);
            }
        }
        return hijos;
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }
}
