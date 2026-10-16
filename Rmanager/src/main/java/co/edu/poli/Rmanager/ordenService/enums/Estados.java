package co.edu.poli.Rmanager.ordenService.enums;
public enum Estados {

    PENDIENTE(1L, "Pendiente"),
    EN_PREPARACION(2L, "En preparacion"),
    ENTREGADO(3L, "Entregado");

    private final Long id;
    private final String metodo;

    Estados(Long id, String estado) {
        this.id = id;
        this.metodo = estado;
    }

    public Long getId() {
        return id;
    }

    public String getEstado() {
        return metodo;
    }
}