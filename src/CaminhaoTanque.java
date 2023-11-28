
public class CaminhaoTanque extends Equipamento {
	private double capacidade;

	// Construtor
	public CaminhaoTanque(int id, String nome, double custoDia, double capacidade) {
		super(id, nome, custoDia);
		this.capacidade = capacidade;
	}

	public double getCapacidade() {
		return capacidade;
	}

	@Override
	public String toString() {
		return "Caminhão Tanque - ID: " + getId() +
				", Nome: " + getNome() +
				", Custo por Dia: " + getCustoDia() +
				", Capacidade: " + capacidade + " litros";
	}

}