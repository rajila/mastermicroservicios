package es.rdajila.cursos.model;

public class Curso {
    private String nombre;
    private int duracion;
    private String profesor;
    private double precio;

    public Curso() {}

    public Curso(String eNombre, int eDuracion, String eProfesor, double ePrecio) {
        this.nombre = eNombre;
        this.duracion = eDuracion;
        this.profesor = eProfesor;
        this.precio = ePrecio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
