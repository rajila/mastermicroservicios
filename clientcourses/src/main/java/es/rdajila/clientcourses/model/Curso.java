package es.rdajila.clientcourses.model;

public class Curso {
    private Integer id;
    private String nombre;
    private Integer duracion;
    private String profesor;
    private Double precio;
    private String categoria;

    public Curso() {}

    public Curso(String categoria, Integer duracion, Integer id, String nombre, Double precio, String profesor) {
        this.categoria = categoria;
        this.duracion = duracion;
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.profesor = profesor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
