package kiosco;

class Validaciones {

    private String respuesta;
    private byte opcion;
    private int valor;
    private boolean estado;


    public Validaciones(byte opcion) {
        this.opcion = opcion;
    }

    public Validaciones(int valor) {
        this.valor = valor;
    }

    public Validaciones() {

    }

    public boolean ValidarRespuesta(String respuesta) {
        if (!(respuesta.equalsIgnoreCase("si")) && !(respuesta.equalsIgnoreCase("no"))) {
            System.out.println("Respuesta Erronea, Vuelva a Intentarlo!!");
            estado = false;
        } else {
            estado = true;
        }
        return estado;
    }

    public boolean ValidarValor() {
        if (valor < 1) {
            System.out.println("\nValor ingresado no es valido!!\n");

            estado = false;
        } else {
            estado = true;
        }
        return estado;
    }

    public boolean ValidarOpcion() {
        if (opcion < 1 || opcion > 8) {
            System.out.println("\nOpcion ingresada no es valido, Intentelo Nuevo!!\n");
            estado = false;
        } else {
            estado = true;
        }
        return estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public byte getOpcion() {
        return opcion;
    }

    public void setOpcion(byte opcion) {
        this.opcion = opcion;
    }

    public void imprimirMenu() {

        System.out.println("\n\t\t Menu de Ventas 'Mi Kiosco'");
        System.out.println("\t\t***************************");

        System.out.println("1.- Ingresar Producto.");
        System.out.println("2.- Mostrar Productos.");
        System.out.println("3.- Agregar Productos al Carrito.");
        System.out.println("4.- Eliminar Producto.");
        System.out.println("5.- Actualizar Producto");
        System.out.println("6.- Reporte de Ventas");
        System.out.println("7.- Cargar Productos");
        System.out.println("8.- Salir de Sistema.");
    }

}
