package Arbol.Modelo.ArbolUser;

public class Nodo {
    private double x;
    private double y;

    private String nombre;

    public Nodo(double x, double y, String nombre) {
        this.x = x;
        this.y = y;
        this.nombre=nombre;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}