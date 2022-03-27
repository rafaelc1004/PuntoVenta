package kiosco;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestClass {

    private static byte opcion;
    private static boolean estado;
    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Productos> arrayProdu = new ArrayList<Productos>();
    private static ArrayList<Ventas> ventas = new ArrayList<Ventas>();
    private static Productos producto;
    private static Validaciones valida;
    private static String respuesta;
    private static String codigo;

    public static void main(String arg[]) throws Exception {

        mostrarMenu();
    }

    //Creacion de metodo de menu
    private static void mostrarMenu() throws Exception {

        do {
            valida = new Validaciones();
            valida.imprimirMenu();
            System.out.print("\nElija una opcion :");
            opcion = Byte.parseByte(bf.readLine());
            //Se verifica que el numero ingresado este entre el numero de las opciones
            valida = new Validaciones(opcion);
            estado = valida.ValidarOpcion();
        } while (estado == false);
        opcionMenu(opcion);
    }

    //se crea un switch quien deribara a los diferentes metodos para cada opcion del menu
    private static void opcionMenu(int opcion) throws Exception {

        switch (opcion) {
            case 1:
                ingresarProducto();
                break;
            case 2:
                mostrarProductos();
                break;
            case 3:
                agregarProductoCarrito();
                break;
            case 4:
                eliminarProducto();
                break;
            case 5:
                actualizarProducto();
                break;
            case 6:
                reporteVentas();
                break;
            case 7:
                cargarProductos();
                break;
            default:
                System.out.println("\t\tFin de Aplicacion");
                System.exit(0);
        }
    }

    private static void cargarProductos() throws Exception {
        arrayProdu.add(new Productos("aaa", "Pila", 150));
        arrayProdu.add(new Productos("bbb", "Chicle", 100));
        arrayProdu.add(new Productos("ccc", "Cocacola 1/2", 500));
        arrayProdu.add(new Productos("ddd", "confort", 300));

        System.out.println("Carga exitosa de productos!!");
        mostrarMenu();
    }

    //Se crea este metodo para que se pueda hacer registro de de un nuevo producto
    private static void ingresarProducto() throws Exception {
        do {
            String codigo, nombre;
            int precio;
            System.out.print("\nIngrese Codigo de producto :");
            codigo = bf.readLine();
            System.out.print("Ingrese nombre de Producto :");
            nombre = bf.readLine();
            do {
                System.out.print("ingrese precio de Producto :$");
                precio = Integer.parseInt(bf.readLine());
                valida = new Validaciones(precio);
                estado = valida.ValidarValor();
            } while (estado == false);

            // se crea el nuevo objeto de tipo producto y en la linea siguiente se guarda en el arrayList de tipo Productos
            producto = new Productos(codigo, nombre, precio);
            arrayProdu.add(producto);
            System.out.println("\nProducto registrado Correctamente!!");
            TimeUnit.SECONDS.sleep(1);

            // se pregunta si quiere registrar un nuevo producto y se valida respuesta que sea si o no
            do {

                System.out.print("Desea registrar otro producto si/no :");
                respuesta = bf.readLine();
                valida = new Validaciones();
                estado = valida.ValidarRespuesta(respuesta);
            } while (estado == false);
        } while (respuesta.equalsIgnoreCase("si"));
        mostrarMenu();
    }

    // muestra todos los productos registrados en sistema
    private static void listarProductos() throws Exception {
        System.out.println("\n\t Codigo de Producto |Nombre producto\t|Precio \t ");
        System.out.println("\t ----------------------------------------------------------------------");
        for (Productos produ : arrayProdu) {

            System.out.println("\t\t" + produ.getCodigo() + "\t\t" + produ.getNombre() + "\t\t" + produ.getPrecio());

        }

    }

    //Permite buscar algun producto en especifico, ya sea por codigo o nombre de producto
    private static Productos buscarProducto(String codigo) {

        for (Productos produ : arrayProdu) {
            if (produ.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("\n\t Codigo de Producto |Nombre producto \t|Precio \t ");
                System.out.println("\t ----------------------------------------------------------------------");
                System.out.println("\t\t" + produ.getCodigo() + "\t\t" + produ.getNombre() + "\t\t" + produ.getPrecio());
                return produ;
            }
        }
        return null;
    }

    //permite actualizar un registro encontrado segun su codigo de producto
    private static void actualizarProducto() throws Exception {

        do {
            String nuevoNombre;
            int nuevoPrecio;
            listarProductos();
            System.out.print("\nIngrese el codigo del Producto a actualizar :");
            codigo = bf.readLine();

            Productos produ = buscarProducto(codigo);
            if (produ != null) {

                System.out.print("\nIngrese nuevo nombre de Producto :");
                nuevoNombre = bf.readLine();
                do {
                    System.out.print("ingrese nuevo precio de Producto :$");
                    nuevoPrecio = Integer.parseInt(bf.readLine());
                    valida = new Validaciones(nuevoPrecio);
                    valida.ValidarValor();
                    estado = valida.ValidarValor();
                } while (estado == false);
                produ.setNombre(nuevoNombre);
                produ.setPrecio(nuevoPrecio);

                System.out.println("\nProducto actualizado Correctamente!!");

            } else {

                System.out.println("Producto no existe!!");
            }
            do {
                System.out.print("Desea actualizar otro producto si/no :");
                respuesta = bf.readLine();
                estado = valida.ValidarRespuesta(respuesta);
            } while (estado == false);

        } while (respuesta.equalsIgnoreCase("si"));
        mostrarMenu();
    }

    private static void eliminarProducto() throws Exception {
        do {
            listarProductos();
            System.out.print("Ingrese el codigo del Producto a eliminar :");
            codigo = bf.readLine();
            Productos produ = buscarProducto(codigo);
            if (produ != null) {
                arrayProdu.remove(produ);
                System.out.println("El producto se ha eliminado Correctamente!!");
            } else {

                System.out.println("El producto no Existe!!");
            }
            do {
                System.out.print("Desea Eliminar otro Producto otro producto si/no :");
                respuesta = bf.readLine();
                estado = valida.ValidarRespuesta(respuesta);
            } while (estado == false);

        } while (respuesta.equalsIgnoreCase("si"));
        mostrarMenu();
    }

    private static void agregarProductoCarrito() throws Exception {
        Ventas venta = new Ventas();

        do {

            listarProductos();
            System.out.print("\nIngrese el codigo del producto para agregar al carro :");
            codigo = bf.readLine();
            //busca producto por codigo para agregar al carro
            Productos produ = buscarProducto(codigo);
            if (produ != null) {
                System.out.print("\nCantidad a comprar :");
                int cantidad = Integer.parseInt(bf.readLine());
                DetallesVenta detalle = new DetallesVenta(cantidad, produ);
                
                venta.agregarDetalleVenta(detalle);

            } else {

                System.out.println("Producto no existe!!");
            }
            do {
                System.out.print("Desea agregar otro producto al carrito de compras si/no :");
                respuesta = bf.readLine();
                estado = valida.ValidarRespuesta(respuesta);
            } while (estado == false);

        } while (respuesta.equalsIgnoreCase("si"));
        do {
            System.out.println("La Venta fue pagada si/no");
            respuesta = bf.readLine();
            estado = valida.ValidarRespuesta(respuesta);
        } while (estado == false);
        if (respuesta.equalsIgnoreCase("si")) {
            ventas.add(venta);

        }
        mostrarMenu();
    }

    private static void reporteVentas() throws Exception {

        System.out.println("Ventas\n********\n");
        System.out.println("Fecha \t Monto");
        for (Ventas venta : ventas) {
            System.out.printf("%td %tB %tY %tH: %tM \t %d %n",
                     venta.getFecha(),
                     venta.getFecha(),
                     venta.getFecha(),
                     venta.getFecha(),
                     venta.getFecha(),
                     venta.calcularTotalVenta());
        }
        System.out.printf("%n%n");
        mostrarMenu();
    }

    private static void mostrarProductos() throws Exception {
        listarProductos();
        mostrarMenu();
    }

}
