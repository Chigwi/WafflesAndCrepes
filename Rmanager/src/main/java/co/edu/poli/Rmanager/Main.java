package co.edu.poli.Rmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.edu.poli.Rmanager.menuService.model.entity.Categoria;
import co.edu.poli.Rmanager.menuService.model.entity.Detalle_producto;
import co.edu.poli.Rmanager.menuService.model.entity.Producto;
import co.edu.poli.Rmanager.menuService.service.ProductosService;
import co.edu.poli.Rmanager.ordenService.model.Entity.Detalle_pedido;
import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;
import co.edu.poli.Rmanager.usuarioService.model.entity.Rol;
import co.edu.poli.Rmanager.usuarioService.service.EmpleadoService;

import java.util.ArrayList;


@SpringBootApplication
public class Main implements CommandLineRunner {

    private final EmpleadoService empleadoService;
    private final ProductosService productoService;

    // Spring will inject these automatically, no "new" needed
    public Main(EmpleadoService empleadoService, ProductosService productoService) {
        this.empleadoService = empleadoService;
        this.productoService = productoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }	
	
	public void run(String... args)throws Exception{

		//pruebas empleado
		
		Rol mesero = new Rol((long) 1, "Mesero", null);
		
		Empleado e1 = new Empleado(null, "Sam", "Arce", "12345678", "Sam@correo", mesero, null);
		
		Empleado e2 = new Empleado(null, "Allie", "Velandia", "109315345", "Allie@correo", mesero, null);
		
		Empleado e3 = new Empleado(null, "Salomé", "Dorado", "11361646", "Salo@correo", mesero, null);
		
		empleadoService.createEmpleado(e1);
		
		empleadoService.createEmpleado(e2);
		
		empleadoService.createEmpleado(e3);
		
		
		System.out.println(empleadoService.getAllEmpleado().toString());
		
		Empleado e4 = new Empleado((long) 2, "Allyson", "Velandia", "109315345", "Allie@correo", mesero, null);
		
		System.out.println(empleadoService.getUsuarioById((long)2));
		

		
		//pruebas producto
		ArrayList<Producto> pl = new ArrayList<Producto>();
		
		Categoria comidaRapida = new Categoria((long) 1, "Comida Rapida", pl);
		
		
		Producto p1 = new Producto(null, "Perro Caliente", "Salchicha dentro de un pan", (double) 15.0, comidaRapida, true);
		
		Producto p2 = new Producto(null, "Pizza", "Pizza italiana tradiconal ig", (double) 15.0, comidaRapida, true);

		Producto p3 = new Producto(null, "Hamburguesa", "Carne molida dentro de un pan", (double) 15.0, comidaRapida, true);

		productoService.createProducto(p1);
		
		productoService.createProducto(p2);
		
		productoService.createProducto(p3);
		
		System.out.println(productoService.getAllProducto().toString());
		
		Producto p4 = new Producto((long) 3, "Salchipapa", "papas y salchichas", (double) 15.0, comidaRapida, true);
		
		System.out.println(productoService.getProductoById((long)3).toString());
		
	
		
		
		//pruebas detalle_producto
		
		Detalle_pedido sp = new Detalle_pedido();
		
		Detalle_producto pd1 = new Detalle_producto(null,p1,"sin cebolla",sp);
		
		Detalle_producto pd2 = new Detalle_producto(null,p2,"con adicion de queso",sp);
		
		Detalle_producto pd3 = new Detalle_producto(null,p3,"con adicion de papas a la francesa",sp);
		
		productoService.createDetalleProducto(pd1);
		productoService.createDetalleProducto(pd2);
		productoService.createDetalleProducto(pd3);
		
		System.out.println(productoService.getAllDetalleProducto().toString());
		
		Detalle_producto pd4 = new Detalle_producto((long)1, p1, "sin cebolla y con limonada",sp);
		
		System.out.println(productoService.getDetalleProductonById((long)1).toString());
		
		
		//deletes
		
		//empleado
		empleadoService.deleteEmpleado((long)1);
		empleadoService.deleteEmpleado((long)2);
		empleadoService.deleteEmpleado((long)3);
		
		//producto
		productoService.deleteProducto((long)1);
		productoService.deleteProducto((long)2);
		productoService.deleteProducto((long)3);
		
		//detalle prpodcuto
		productoService.deleteDetalle_producto((long)1);
		productoService.deleteDetalle_producto((long)2);
		productoService.deleteDetalle_producto((long)3);
	}

}
