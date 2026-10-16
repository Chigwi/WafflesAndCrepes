package co.edu.poli.Rmanager.ordenService.enums;

public enum Estados {
	
	PENDIENTE{
		public String getMetodo() {
			return "Pendiente";
		}
		public Long getId() {
			return 1L;
		}
		
		
	}, EN_PREPARACION{
		public String getMetodo() {
			return "En preparacion";
		}
		
		public Long getId() {
			return 2L;
		}
		
	}, ENTREGADO{
		public String getMetodo() {
			return "Entregado";
		}
		
		public Long getId() {
			return 3L;
			
		}
		
	}
	
	

}
