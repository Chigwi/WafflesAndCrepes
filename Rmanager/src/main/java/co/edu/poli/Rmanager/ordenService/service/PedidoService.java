package co.edu.poli.Rmanager.ordenService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.Rmanager.ordenService.model.Entity.Pedido;
import co.edu.poli.Rmanager.ordenService.repository.PedidoRepository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import co.edu.poli.Rmanager.ordenService.model.Entity.Estado;
import co.edu.poli.Rmanager.ordenService.model.Entity.Mesa;
import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	//create pedido
	
	
	public PedidoService(PedidoRepository pedidoRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
	}
	
	
	
	@Transactional
	public Pedido createPedido(Pedido pedido) {
		attachReferences(pedido);
		return pedidoRepository.save(pedido);
	}

	@Transactional
	public Pedido updatePedido(Pedido newPedido) {
		attachReferences(newPedido);
		return pedidoRepository.save(newPedido);
	}

	private void attachReferences(Pedido pedido) {
		if (pedido.getEstado() != null && pedido.getEstado().getId_estado() != null) {
			pedido.setEstado(em.getReference(Estado.class, pedido.getEstado().getId_estado()));
		}

		if (pedido.getMesa() != null && pedido.getMesa().getId() != null) {
			pedido.setMesa(em.getReference(Mesa.class, pedido.getMesa().getId()));
		}
		if (pedido.getMesero() != null && pedido.getMesero().getId_empleado() != null) {
		     pedido.setMesero(em.getReference(Empleado.class, pedido.getMesero().getId_empleado()));
		}
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
	/*
	public Pedido updatePedido(Pedido newPedido) {
		
		return pedidoRepository.save(newPedido);
		
	}*/

}
