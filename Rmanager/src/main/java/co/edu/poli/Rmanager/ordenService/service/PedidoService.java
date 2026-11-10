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
import co.edu.poli.Rmanager.ordenService.enums.MetodosDePago;
import co.edu.poli.Rmanager.ordenService.model.Entity.Estado;
import co.edu.poli.Rmanager.ordenService.model.Entity.Historial_estado;
import co.edu.poli.Rmanager.ordenService.model.Entity.Incidente;
import co.edu.poli.Rmanager.ordenService.model.Entity.Mesa;
import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;
import co.edu.poli.Rmanager.ordenService.model.dto.EstadoDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.MesaActivaDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoInsertDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoPrintMetodoDePagoDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoUpdateEstadoDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoUpdateIncidenteDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoUpdateMesaDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.PedidoUpdateMetodoDePagoDTO;
import co.edu.poli.Rmanager.ordenService.repository.EstadoRepository;
import co.edu.poli.Rmanager.ordenService.repository.Historial_estadoRepository;
import co.edu.poli.Rmanager.ordenService.repository.IncidenteRepository;
import co.edu.poli.Rmanager.ordenService.repository.MesaRepository;
import co.edu.poli.Rmanager.ordenService.repository.PedidoRepository;
import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;
import co.edu.poli.Rmanager.usuarioService.repository.EmpleadoRepository;
import jakarta.persistence.EntityNotFoundException;
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
	
	@Transactional
	public Pedido createPedido(PedidoInsertDTO dto) {
	    LocalDateTime now = LocalDateTime.now();

	    Mesa mesa = bringMesa(dto.getId_mesa());          // throws if missing
	    if (Boolean.TRUE.equals(mesa.getOcupada())) {
	        throw new IllegalStateException("La mesa " + mesa.getId() + " ya está ocupada");
	    }

	    Pedido pedido = new Pedido();
	    pedido.setEstado(bringEstado(Estados.PENDIENTE.getId()));
	    pedido.setMesero(bringEmpleado(dto.getId_mesero()));  // throws if missing
	    pedido.setMesa(mesa);
	    pedido.setFecha_creacion(now);
	    pedido.setFecha_ultima_actualizacion(now);

	    mesa.setOcupada(true);
	    mesaRepository.save(mesa);

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
	
	/*public Pedido updatePedido(Pedido newPedido) {
		
		return pedidoRepository.save(newPedido);
		
	}*/
	
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
    	return pedidoRepository.findAllByEstado("Pendiente");
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
	@Transactional
	public boolean updateMesaFromPedido(PedidoUpdateMesaDTO uPedido) {

	    Pedido pedido = bringPedido(uPedido.getId());       // throws if missing
	    Mesa nuevaMesa = bringMesa(uPedido.getIdmesa());    // throws if missing
	    Mesa mesaAnterior = pedido.getMesa();

	    // same table: nothing to do
	    if (mesaAnterior != null && mesaAnterior.getId().equals(nuevaMesa.getId())) {
	        return true;
	    }

	    if (Boolean.TRUE.equals(nuevaMesa.getOcupada())) {
	        throw new IllegalStateException("La mesa " + nuevaMesa.getId() + " ya está ocupada");
	    }

	    // free the old table
	    if (mesaAnterior != null) {
	        mesaAnterior.setOcupada(false);
	        mesaRepository.save(mesaAnterior);
	    }

	    // occupy the new one
	    nuevaMesa.setOcupada(true);
	    mesaRepository.save(nuevaMesa);

	    pedido.setMesa(nuevaMesa);
	    pedido.setFecha_ultima_actualizacion(LocalDateTime.now());
	    pedidoRepository.save(pedido);

	    return true;
	}
	
	//add incidente to pedido
	public boolean addIncidenteToPedido(PedidoUpdateIncidenteDTO uPedido) {
		
		Optional<Pedido> optionalPedido = pedidoRepository.findById(uPedido.getId());
		
		if(optionalPedido.isPresent()) {
			
			Pedido pedido = optionalPedido.get();
			
			Incidente incidente = new Incidente();
			
			incidente.setPedido(pedido);
			
			incidente.setDescripcion(uPedido.getIncidente());
			
			incidenteRepository.save(incidente);
			
			return true;
		}
		
		return false;
	}

	//actualizar estado de un pedido
	public EstadoDTO updateEstadoOfPedido (PedidoUpdateEstadoDTO uPedido) {
		
		Long idPedido = uPedido.getId();
		
		Long idEstado = uPedido.getIdEstado();
		
		Long idEmpleado = uPedido.getIdEmpleado();
		
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
	
	//metodo de pago functionality
	public PedidoPrintMetodoDePagoDTO updateMetodoDePago (PedidoUpdateMetodoDePagoDTO uPedido) {
		
		Pedido pedido = bringPedido(uPedido.getIdPedido());
		
		String metodoDePago = "";
		
		PedidoPrintMetodoDePagoDTO saved = new PedidoPrintMetodoDePagoDTO();
		
		saved.setIdPedido(uPedido.getIdPedido());
		
		LocalDateTime now = LocalDateTime.now();
		
		byte key = uPedido.getIdMetodo();
		switch (key) {
			case 1: {
				
				metodoDePago = MetodosDePago.EFECTIVO.getMetodo();
				
				pedido.setMetodo_de_pago(metodoDePago);
				
				saved.setMetodoDePago(metodoDePago);
				
				break;
			}
			case 2:{
				
				metodoDePago = MetodosDePago.TARJETA.getMetodo();
				
				pedido.setMetodo_de_pago(metodoDePago);
				
				saved.setMetodoDePago(metodoDePago);
				
				break;
			}
			case 3:{
				
				metodoDePago = MetodosDePago.BREB.getMetodo();
				
				pedido.setMetodo_de_pago(metodoDePago);
				
				saved.setMetodoDePago(metodoDePago);
				
				break;
			}
			default:
				throw new IllegalArgumentException("Metodo de pago invalido");
			}
		
		pedido.setFecha_ultima_actualizacion(now);
		
		pedidoRepository.save(pedido);
		
		return saved;
	}

	
	public List<Pedido> getPedidoByEmpleado(Long idEmpleado) {
		return pedidoRepository.findPedidosByEmpleado(idEmpleado);
	}
	
	
	@Transactional
	public EstadoDTO checkout(Long idPedido, Long idEmpleado) {
	    Pedido pedido = bringPedido(idPedido);

	    if (pedido.getMetodo_de_pago() == null || pedido.getMetodo_de_pago().isBlank()) {
	        throw new IllegalStateException("No se puede finalizar: falta el método de pago");
	    }
	    if (pedido.getEstado().getId_estado().equals(Estados.FINALIZADO.getId())) {
	        throw new IllegalStateException("El pedido ya está finalizado");
	    }
	    
	    if (!pedido.getEstado().getId_estado().equals(Estados.ENTREGADO.getId())) {
	        throw new IllegalStateException("El pedido aún no ha sido entregado");
	    }

	    LocalDateTime now = LocalDateTime.now();
	    Estado finalizado = bringEstado(Estados.FINALIZADO.getId());

	    pedido.setEstado(finalizado);
	    pedido.setFecha_ultima_actualizacion(now);

	    // liberar la mesa
	    Mesa mesa = pedido.getMesa();
	    mesa.setOcupada(false);
	    mesaRepository.save(mesa);

	    // registrar el cambio en el historial
	    Historial_estado h = new Historial_estado();
	    h.setId_pedido(pedido);
	    h.setEstado(finalizado);
	    h.setFecha(now);
	    h.setEmpleado_que_relevo(bringEmpleado(idEmpleado));
	    historialEstadoRepository.save(h);

	    pedidoRepository.save(pedido);
	    return getEstadoOfPedido(idPedido);
	}
	
	
	//bring the incidente of pedido
	@Transactional
	public PedidoUpdateIncidenteDTO getIncidenteOfPedido(Long idPedido) {

	    // fails with a clear 404 if the order doesn't exist
	    bringPedido(idPedido);

	    PedidoUpdateIncidenteDTO dto = new PedidoUpdateIncidenteDTO();
	    dto.setId(idPedido);
	    dto.setIncidente(
	        incidenteRepository.findDescripcionByPedidoId(idPedido).orElse(null));

	    return dto;
	}
	
	//*****************
	// METODOS PRIVADOS
	//*****************
	
	private Empleado bringEmpleado(Long idEmpleado) {
	    return empleadoRepository.findById(idEmpleado)
	            .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado: " + idEmpleado));
	}

	private Mesa bringMesa(Long idMesa) {
	    return mesaRepository.findById(idMesa)
	            .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada: " + idMesa));
	}

	private Estado bringEstado(Long idEstado) {
	    return estadoRepository.findById(idEstado)
	            .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado: " + idEstado));
	}

	private Pedido bringPedido(Long idPedido) {
	    return pedidoRepository.findById(idPedido)
	            .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado: " + idPedido));
	}

	private Producto bringProducto(Long idProducto) {
	    return productoRepository.findById(idProducto)
	            .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + idProducto));
	}
}

