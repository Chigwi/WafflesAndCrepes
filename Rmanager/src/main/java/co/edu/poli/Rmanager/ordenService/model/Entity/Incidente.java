package co.edu.poli.Rmanager.ordenService.model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "incidente")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Incidente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_incidente;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToOne
	@JoinColumn(name = "pedido")
	private Pedido pedido;

}
