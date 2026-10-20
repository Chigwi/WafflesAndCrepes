package co.edu.poli.Rmanager.ordenService.model.dto;

import co.edu.poli.Rmanager.ordenService.model.Entity.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoUpdateEstadoDTO {
	
	private Long id;
	
	private Long idEstado;
	
	private Long idEmpleado;

}
