public class Escavadeira extends Equipamento {
	private String combustivel; // DIESEL, GASOLINA, ALCOOL
	private double carga; // Carga em metros cúbicos (m³)

	public Escavadeira(int id, String nome, double custoDia, String combustivel, double carga) {
		super(id, nome, custoDia);
		this.combustivel = combustivel;
		this.carga = carga;
	}

	// Getters e setters
	public String getCombustivel() {
		return combustivel;
	}

	public double getCarga() {
		return carga;
	}

	@Override
	public String toString() {
		return "Escavadeira - ID: " + getId() +
				", Nome: " + getNome() +
				", Custo por Dia: " + getCustoDia() +
				", Combustível: " + combustivel +
				", Carga: " + carga + " toneladas";
	}

}
