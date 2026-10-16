package co.edu.poli.Rmanager.ordenService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.Rmanager.menuService.model.entity.Producto;
import co.edu.poli.Rmanager.ordenService.model.Entity.Incidente;
import co.edu.poli.Rmanager.ordenService.model.Entity.Mesa;
import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;
import co.edu.poli.Rmanager.ordenService.model.dto.MesaActivaDTO;
import co.edu.poli.Rmanager.ordenService.repository.IncidenteRepository;
import co.edu.poli.Rmanager.ordenService.repository.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private IncidenteRepository incidenteRepository;
	
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

	
}
