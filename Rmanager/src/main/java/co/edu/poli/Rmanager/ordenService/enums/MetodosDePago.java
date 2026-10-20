package co.edu.poli.Rmanager.ordenService.enums;

public enum MetodosDePago {

    EFECTIVO("Efectivo"),
    TARJETA("Tarjeta"),
    BREB("Bre-b");

    
    private final String metodo;

    MetodosDePago(String estado) {
        this.metodo = estado;
    }

    public String getEstado() {
        return metodo;
    }
}