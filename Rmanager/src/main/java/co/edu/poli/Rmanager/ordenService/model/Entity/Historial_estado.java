package co.edu.poli.Rmanager.ordenService.model.Entity;


import java.sql.Date;

import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Historial_Estado")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Historial_estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_historial_estado;
	
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido id_pedido;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Estado estado;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "empleado_que_relevo")
	private Empleado empleado_que_relevo;
	
}
