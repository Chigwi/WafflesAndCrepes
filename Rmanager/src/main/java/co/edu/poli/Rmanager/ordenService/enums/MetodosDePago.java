package co.edu.poli.Rmanager.ordenService.enums;

public enum MetodosDePago {
	EFECTIVO{
		public String getMetodo() {
			return "Efectivo";
		}
	}, TARJETA{
		public String getMetodo() {
			return "Tarjeta";
		}
	}, BREB{
		public String getMetodo() {
			return "Bre-b";
		}
	}
}
