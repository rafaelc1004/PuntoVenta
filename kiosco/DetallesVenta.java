package kiosco;

/**
 *
 * @author Rafaelito
 */
public class DetallesVenta {

    private int cantidad;
    private Productos producto;

    public DetallesVenta(int cantidad, Productos producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int calcularsubTotal() {
        return cantidad * producto.getPrecio();
    }

}
