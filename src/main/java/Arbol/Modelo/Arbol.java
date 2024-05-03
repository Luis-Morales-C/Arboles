package Arbol.Modelo;

public class Arbol {
    private Nodo nodoRaiz;
    private Nodo nodoIzquierda;
    private Nodo nodoDerecha;

    private int tamano;

    public class ArbolBinario{


    }


    public boolean estaVacio(){
        if(tamano==0){
            return true;
        }
        return false;
    }
}
