package es.rdajila.clientcourses.util;

public class PaginatorItem {
    private int numero;
    private boolean actual;

    public PaginatorItem(int numero, boolean actual) {
        this.numero = numero;
        this.actual = actual;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
