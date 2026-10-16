package co.edu.poli.Rmanager.ordenService.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.Rmanager.menuService.model.entity.Detalle_producto;
import co.edu.poli.Rmanager.menuService.model.entity.Producto;
import co.edu.poli.Rmanager.menuService.repository.DetalleProductoRepository;
import co.edu.poli.Rmanager.menuService.repository.ProductoRepository;
import co.edu.poli.Rmanager.ordenService.enums.Estados;
import co.edu.poli.Rmanager.ordenService.model.Entity.Estado;
import co.edu.poli.Rmanager.ordenService.model.Entity.Historial_estado;
import co.edu.poli.Rmanager.ordenService.model.Entity.Incidente;
import co.edu.poli.Rmanager.ordenService.model.Entity.Mesa;
import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;
import co.edu.poli.Rmanager.ordenService.model.dto.EstadoDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.MesaActivaDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoInsertDTO;
import co.edu.poli.Rmanager.ordenService.repository.EstadoRepository;
import co.edu.poli.Rmanager.ordenService.repository.Historial_estadoRepository;
import co.edu.poli.Rmanager.ordenService.repository.IncidenteRepository;
import co.edu.poli.Rmanager.ordenService.repository.MesaRepository;
import co.edu.poli.Rmanager.ordenService.repository.PedidoRepository;
import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;
import co.edu.poli.Rmanager.usuarioService.repository.EmpleadoRepository;
import jakarta.transaction.Transactional;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private IncidenteRepository incidenteRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private Historial_estadoRepository historialEstadoRepository;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	private MesaRepository mesaRepository;
	
	@Autowired
	private DetalleProductoRepository detalleProductoRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	public PedidoService(PedidoRepository pedidoRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
	}
	
	//create pedido
	
	public Pedido createPedido(PedidoInsertDTO pedidoDTO) {
		
		LocalDateTime creacion = LocalDateTime.now();
		
		Pedido pedido = new Pedido();
		
		//se asegura que el estado del pedido al crear sea pendiente
		Estado nuevo = new Estado();
				
		nuevo.setId_estado(Estados.PENDIENTE.getId());
		
		nuevo.setNombre(Estados.PENDIENTE.getEstado());
		
		pedido.setEstado(nuevo);
		
		//trae al empleado que creó el pedido
		
		Empleado meseroEncargado;
		if(empleadoRepository.existsById(pedidoDTO.getId_mesero())) {
			meseroEncargado = bringEmpleado(pedidoDTO.getId_mesero());
		}else {
			meseroEncargado = null;
		}
		
		//asigna el empleado al pedido
		
		pedido.setMesero(meseroEncargado);
		
		
		//trae la mesa asignada al pedido
		
		Mesa mesaAsignada;
		if(mesaRepository.existsById(pedidoDTO.getId_mesa())) {
			mesaAsignada = bringMesa(pedidoDTO.getId_mesa());
		}else {
			mesaAsignada = null;
		}
		
		//asigna la mesa al pedido
		
		pedido.setMesa(mesaAsignada);

		//se asignan la hora de creacion y ultima actualización
		
		pedido.setFecha_creacion(creacion);
		
		pedido.setFecha_ultima_actualizacion(creacion);
		
		//guarda pedido en la base de datos y retorna
		
		return pedidoRepository.save(pedido);
		
	}

	//read all pedidos
	
	public List<Pedido> getAllPedidos() {
		
		return pedidoRepository.findAll();
		
	}
	
	//read pedido by id
	
	public Optional<Pedido> getPedidoById(Long id){
	
		return pedidoRepository.findById(id);
		
	}
	
	//delete pedido
	
	public boolean deletePedido(Long id) {
	    if (pedidoRepository.existsById(id)) {
	        pedidoRepository.deleteById(id);
	        return true;
	    }
	    return false;  // Returns false if ID not found
	}
	
	//update pedido 
	
	// ELIMINAR UPDATE PEDIDO Y REMPLAZAR POR METODOS GRANULARES DE ACTUALIZACION POR ATRIBUTO
	
	public Pedido updatePedido(Pedido newPedido) {
		
		return pedidoRepository.save(newPedido);
		
	}
	
	//get estado de un pedido
	
	public EstadoDTO getEstadoOfPedido(Long idPedido) {
		Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
		
		if(pedido.isPresent()) {
			
			Pedido currentPedido = pedido.get();
			
			EstadoDTO currentEstado = new EstadoDTO();
			
			currentEstado.setPedido_id(idPedido);
			
			currentEstado.setEstado(currentPedido.getEstado().getNombre());
			
			return currentEstado;
		}else {
			return null;
		}
	}
	
	
	//get pedidos by estado
	
	public List<Optional<Pedido>> getPedidoByEstado(String estado){
    	return pedidoRepository.findAllByEstado(estado);
    }
	
	//get pedidos with estado "disponible"
	
	public List<Optional<Pedido>> getPedidosPendientes(){
    	return pedidoRepository.findAllByEstado("pendiente");
    }
	
	//get the mesas assigned to an empleado
	public List<MesaActivaDTO>getMesasByEmpleado(Long idEmpleado){
		return pedidoRepository.findMesasActivaByEmpleado(idEmpleado);
	}
	
	//get all the mesas currently active
	public List<MesaActivaDTO>getAllMesasActivas(){
		return pedidoRepository.findAllMesasActivas();
	}
	
	//change pedido's mesa
	public boolean updateMesaFromPedido (Long pedidoId, Mesa mesa) {
		
		Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);
		
		if(optionalPedido.isPresent()) {
			
			Pedido pedido = optionalPedido.get();
			
			pedido.setMesa(mesa);
			
			pedidoRepository.save(pedido);
			
			return true;
		}
		
		return false;
	}
	
	//add incidente to pedido
	public boolean addIncidenteToPedido(Long pedidoId, String descripcion) {
		
		Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);
		
		if(optionalPedido.isPresent()) {
			
			Pedido pedido = optionalPedido.get();
			
			Incidente incidente = new Incidente();
			
			incidente.setPedido(pedido);
			
			incidente.setDescripcion(descripcion);
			
			incidenteRepository.save(incidente);
			
			return true;
		}
		
		return false;
	}

	//actualizar estado de un pedido
	public EstadoDTO updateEstadoOfPedido (Long idPedido, Long idEstado, Long idEmpleado) {
		
		//trae pedido de base de datos
		Optional<Pedido> optionalPedido = pedidoRepository.findById(idPedido);
		
		//verifica existencia del pedido
		if(optionalPedido.isPresent()) {

			Pedido pedido = optionalPedido.get();
			
			//trae estado actual del pedido
			Long current = pedido.getEstado().getId_estado();
			
			//verifica validez del cambio del estado (unicamente orden ascendente)
			if(current < idEstado && idEstado < 4 ) {
				
				//trae el nuevo estado desde la base de datos con su id
				Estado newEstado = bringEstado(idEstado);
				
				//se cambia el estado en el pedido
				pedido.setEstado(newEstado);
				
				//se trae al empleado que hizo el cambio de estado
				Empleado empleadoActual = bringEmpleado(idEmpleado);
				
				LocalDateTime horaCambio = LocalDateTime.now();
				
				//se crea el historial para guardar el cambio de estado
				Historial_estado estadoChange = new Historial_estado();
				
				estadoChange.setId_pedido(pedido);
				
				estadoChange.setEstado(newEstado);
				
				estadoChange.setFecha(horaCambio);
				
				estadoChange.setEmpleado_que_relevo(empleadoActual);
				
				//se guarda el cambio de estado
				historialEstadoRepository.save(estadoChange);
				
				//se cambia la fecha de actualización del pedido
				pedido.setFecha_ultima_actualizacion(horaCambio);
				
				pedidoRepository.save(pedido);
				
				return getEstadoOfPedido(idPedido);
				
				
			}else {
				
				return getEstadoOfPedido(idPedido);
				
			}
			
			
		}else {
			return null;
		}
	}
	
	//add producto(S) to pedido
	@Transactional
	public Pedido addProductosToPedido(List<Detalle_producto> productos, Long idPedido) {
		
		Pedido current = bringPedido(idPedido);
		
		Double total = current.getPrecio_total() != null ? current.getPrecio_total() : 0.0;
		
		for (Detalle_producto detalle_producto : productos) {
			
			Producto actual = bringProducto(detalle_producto.getProducto().getId_producto());
			
			detalle_producto.setProducto(actual);
			
			detalle_producto.setPedido(current);
			
			current.getProductos().add(detalle_producto);
			
			total += actual.getPrecio();
			
		}
		
		current.setPrecio_total(total);
		
		return pedidoRepository.save(current);
		
	}
	
	//remove producto(S) from pedido
	@Transactional
	public Pedido removeProductosFromPedido(List<Long> idsDetalle, Long idPedido) {

	    Pedido current = bringPedido(idPedido);

	    current.getProductos().removeIf(d -> idsDetalle.contains(d.getId_detalle_producto()));

	    double total = current.getProductos().stream()
	            .mapToDouble(d -> d.getProducto().getPrecio())
	            .sum();

	    current.setPrecio_total(total);
	    return pedidoRepository.save(current);
	}

	
	//*****************
	// METODOS PRIVADOS
	//*****************
	
	//Trae una mesa de la base de datos
	private Empleado bringEmpleado (Long idEmpleado) {
		
		Optional<Empleado> optionalEmpleado = empleadoRepository.findById(idEmpleado);
		
		if(optionalEmpleado.isPresent()) {
			
			Empleado empleado = optionalEmpleado.get();
			
			return empleado;
		}else {
			
			return null;
			
		}
	}
	
	//trae una mesa de la base de datos
	private Mesa bringMesa (Long idMesa) {
		
		Optional<Mesa> optionalMesa = mesaRepository.findById(idMesa);
		
		if(optionalMesa.isPresent()) {
			
			Mesa mesa = optionalMesa.get();
			
			return mesa;
		}else {
			
			return null;
			
		}
	}
	
	//trae un estado de la base de datos
	private Estado bringEstado (Long idEstado) {
		
		Optional<Estado> optionalEstado = estadoRepository.findById(idEstado);
		
		if(optionalEstado.isPresent()) {
			
			Estado estado = optionalEstado.get();
			
			return estado;
			
		}else {
			
			return null;
			
		}
	}
	
	//trae un pedido de la base de datos
	private Pedido bringPedido (Long idPedido) {
		
		if(pedidoRepository.existsById(idPedido)) {
			
			Optional <Pedido> optionalPedido = pedidoRepository.findById(idPedido);
			
			if(optionalPedido.isPresent()) {
				
				Pedido pedido = optionalPedido.get();
				
				return pedido;
				
			}else {
				return null;
			}
			
		}else {
			return null;
		}
	}
	
	//trae un producto de la base de datos
	private Producto bringProducto (Long idProducto) {
		
		if(productoRepository.existsById(idProducto)) {
			
			Optional<Producto> optionalProducto = productoRepository.findById(idProducto);
			
			if(optionalProducto.isPresent()) {
			
				Producto producto = optionalProducto.get();
				
				return producto;
				
			}else {
				
				return null;
				
			}
			
		}else {
			
			return null;
			
		}
	}
}

