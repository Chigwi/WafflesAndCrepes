package co.edu.poli.Rmanager.ordenService.model.Entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.poli.Rmanager.menuService.model.entity.Detalle_producto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Detalle_pedido")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Detalle_pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_detalle_pedido;
	
	@OneToOne
	@JoinColumn(name = "pedido")
	private Pedido pedido;
	
    @OneToMany(mappedBy = "detalle_pedido", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Detalle_producto> productos;

	
	@Column(name = "precio_total")
	private Double precio_total;
	
	@Column(name = "metodo_de_pago")
	private String metodo_de_pago;
	
}
