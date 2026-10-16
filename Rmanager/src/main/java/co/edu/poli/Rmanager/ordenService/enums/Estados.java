package co.edu.poli.Rmanager.ordenService.enums;

public enum Estados {
	
	PENDIENTE{
		public String getMetodo() {
			return "Pendiente";
		}
	}, EN_PREPARACION{
		public String getMetodo() {
			return "En preparacion";
		}
		
	}, ENTREGADO{
		public String getMetodo() {
			return "Entregado";
		}
	}

}
