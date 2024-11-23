package es.rdajila.clientcourses.util;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PaginatorRender<T> {
    private String url;
    private Page<T> page;
    private int totalPaginas;
    private int numElementosPorPagina;
    private int paginaActual;
    private List<PaginatorItem> paginas;

    public PaginatorRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<PaginatorItem>();
        numElementosPorPagina = 5;
        totalPaginas = page.getTotalPages();
        paginaActual = page.getNumber() + 1;
        int desde, hasta;
        if (totalPaginas <= numElementosPorPagina) {
            desde = 1;
            hasta = totalPaginas;
        } else {
            if (paginaActual <= numElementosPorPagina / 2) {
                desde = 1;
                hasta = numElementosPorPagina;
            } else if (paginaActual >= totalPaginas - numElementosPorPagina / 2) {
                desde = totalPaginas - numElementosPorPagina + 1;
                hasta = numElementosPorPagina;
            } else {
                desde = paginaActual - numElementosPorPagina / 2;
                hasta = numElementosPorPagina;
            }
        }
        for (int i = 0; i < hasta; i++) {
            paginas.add(new PaginatorItem(desde + i, paginaActual == desde + i));
        }
    }

    public String getUrl() {
        return url;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public List<PaginatorItem> getPaginas() {
        return paginas;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public boolean isFirst() {
        return page.isFirst();
    }
    public boolean isLast() {
        return page.isLast();
    }
    public boolean isHasNext() {
        return page.hasNext();
    }
    public boolean isHasPrevious() {
        return page.hasPrevious();
    }
}
