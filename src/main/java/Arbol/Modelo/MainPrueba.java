package Arbol.Modelo;
public class MainPrueba {
    public static void main(String []args){
        ArbolBinario arbolBinario=new ArbolBinario();
        Nodo nodo1=new Nodo(3);
        Nodo nodo2=new Nodo(1);
        Nodo nodo3=new Nodo(2);
        Nodo nodo4=new Nodo(0);
        arbolBinario.insertar(nodo1);
        arbolBinario.insertar(nodo2);
        arbolBinario.insertar(nodo3);
        arbolBinario.insertar(nodo4);


    }
}