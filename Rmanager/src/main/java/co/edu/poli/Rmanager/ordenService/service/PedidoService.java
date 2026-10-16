package co.edu.poli.Rmanager.ordenService.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.Rmanager.menuService.model.entity.Producto;
import co.edu.poli.Rmanager.ordenService.enums.Estados;
import co.edu.poli.Rmanager.ordenService.model.Entity.Estado;
import co.edu.poli.Rmanager.ordenService.model.Entity.Historial_estado;
import co.edu.poli.Rmanager.ordenService.model.Entity.Incidente;
import co.edu.poli.Rmanager.ordenService.model.Entity.Mesa;
import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;
import co.edu.poli.Rmanager.ordenService.model.dto.EstadoDTO;
import co.edu.poli.Rmanager.ordenService.model.dto.MesaActivaDTO;
import co.edu.poli.Rmanager.ordenService.repository.EstadoRepository;
import co.edu.poli.Rmanager.ordenService.repository.Historial_estadoRepository;
import co.edu.poli.Rmanager.ordenService.repository.IncidenteRepository;
import co.edu.poli.Rmanager.ordenService.repository.PedidoRepository;
import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;
import co.edu.poli.Rmanager.usuarioService.repository.EmpleadoRepository;


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
	
	//create pedido
	
	
	public PedidoService(PedidoRepository pedidoRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
	}
	
	
	
	public Pedido createPedido(Pedido pedido) {
		
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
				Estado newEstado = estadoRepository.findById(idEstado).get();
				
				//se cambia el estado en el pedido
				pedido.setEstado(newEstado);
				
				//se trae al empleado que hizo el cambio de estado
				Empleado empleadoActual = empleadoRepository.findById(idEmpleado).get();
				
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
}

