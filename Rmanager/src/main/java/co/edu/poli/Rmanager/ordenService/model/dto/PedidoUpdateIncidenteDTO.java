package co.edu.poli.Rmanager.ordenService.model.dto;

import co.edu.poli.Rmanager.ordenService.model.Entity.Incidente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoUpdateIncidenteDTO {
	
	private Long id;
	
	private Incidente incidente;

}
