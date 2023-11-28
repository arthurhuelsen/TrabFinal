public class Barco extends Equipamento {
	private int capacidade;

	// Construtor
	public Barco(int id, String nome, double custoDia, int capacidade) {
		super(id, nome, custoDia);
		this.capacidade = capacidade;
	}

	public int getCapacidade() {
		return capacidade;
	}

	@Override
	public String toString() {
		return "Barco - ID: " + getId() +
				", Nome: " + getNome() +
				", Custo por Dia: " + getCustoDia() +
				", Capacidade: " + capacidade + " pessoas";
	}

}