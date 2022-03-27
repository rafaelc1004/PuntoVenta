package kiosco;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ventas {

    private LocalDateTime fecha = LocalDateTime.now();
    private ArrayList<DetallesVenta> detalleVenta = new ArrayList<>();

    //Contructores
    public Ventas() {

    }
    
    

    public Ventas(LocalDateTime fecha, ArrayList<DetallesVenta> detalle) {
        this.fecha = fecha;
        this.detalleVenta = detalle;
    }

    public void agregarDetalleVenta(DetallesVenta detalle) {
        this.detalleVenta.add(detalle);
    }

    public int calcularTotalVenta() {
        int total = 0;
        for (DetallesVenta detalleFor : detalleVenta) {
            total = total + detalleFor.calcularsubTotal();
        }
        return total;

    }

    //GETTER AND SETTERS
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public ArrayList<DetallesVenta> getLineaDetalle() {
        return detalleVenta;
    }

    public void setLineaDetalle(ArrayList<DetallesVenta> detalle) {
        this.detalleVenta = detalle;
    }

}
