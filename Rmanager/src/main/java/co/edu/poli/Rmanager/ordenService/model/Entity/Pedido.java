package co.edu.poli.Rmanager.ordenService.model.Entity;



import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.poli.Rmanager.usuarioService.model.entity.Empleado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Pedido")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_pedido;
	
	@OneToOne
	@JoinColumn(name = "mesa")
	private Mesa mesa;
	
	@OneToOne
	@JoinColumn(name = "mesero")
	private Empleado mesero;
	
	@ManyToOne
	@JoinColumn(name = "estado")
	private Estado estado;
	
	@Column(name = "fecha_creacion")
	private Date fecha_creacion;
	
	@Column(name = "fecha_ultima_actualización")
	private Date fecha_ultima_actualizacion;
	
    @OneToMany(mappedBy = "id_pedido", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Historial_estado> cambiosEstado;
    
    
}
