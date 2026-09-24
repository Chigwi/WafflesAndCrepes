package co.edu.poli.Rmanager.menuService.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table (name="Detalle_producto")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Detalle_producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_detalle_producto;
	
	@OneToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;
	
	@Column(name = "notas")
	private String notas;
	
	@ManyToOne
	@JoinColumn(name = "detalle_pedido")
	private Long detalle_pedido;
	
	
	
	
	

}
