package co.edu.poli.Rmanager.ordenService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import co.edu.poli.Rmanager.ordenService.model.Entity.Mesa;
import co.edu.poli.Rmanager.ordenService.repository.MesaRepository;




public class MesaServise {
	
	@Autowired
	private MesaRepository mesaRepository;
	

	
	public MesaServise(MesaRepository mesaRepository) {
		super();
		this.mesaRepository = mesaRepository;
	}
	
	//crear mesa
	
	public Mesa createMesa(Mesa mesa) {
		
		return mesaRepository.save(mesa);
		
	}
	
	//read all mesas
	
		public List<Mesa> getAllMesas() {
			
			return mesaRepository.findAll();
			
		}
		
		//read mesa by id
		
		public Optional<Mesa> getPedidoById(Long id){
		
			return mesaRepository.findById(id);
			
		}
		
		//delete mesa
		
		public boolean deleteMesa(Long id) {
		    if (mesaRepository.existsById(id)) {
		        mesaRepository.deleteById(id);
		        return true;
		    }
		    return false;  // Returns false if ID not found
		}
		
		//update mesa
		
		public Mesa updateMesa(Mesa newMesa) {
			
			return mesaRepository.save(newMesa);
			
		}

}
