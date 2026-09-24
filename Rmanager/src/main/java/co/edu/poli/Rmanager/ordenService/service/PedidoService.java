package co.edu.poli.Rmanager.ordenService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;
import co.edu.poli.Rmanager.ordenService.repository.PedidoRepository;

public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	//create pedido
	
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

}
