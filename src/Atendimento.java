public class Atendimento {
	private int cod;
	private String dataInicio;
	private int duracao;
	private String status;
	private String codigoEvento; // Código do evento
	private Equipe equipeAlocada;

	// Método para calcular custo
	public double calculaCusto(double distanciaDeslocamento) {
		double custoEquipe = duracao * 250 * equipeAlocada.getQuantidadeMembros();

		double custoEquipamentos = 0;
		for (Equipamento equipamento : equipeAlocada.getEquipamentos()) {
			custoEquipamentos += duracao * equipamento.getCustoDia();
		}

		double custoDeslocamento = distanciaDeslocamento *
				(100 * equipeAlocada.getQuantidadeMembros() + 0.1 * custoEquipamentos);

		return custoEquipe + custoEquipamentos + custoDeslocamento;
	}

	// Construtor, getters e setters
	public Atendimento(int cod, String dataInicio, int duracao, String status, String codigoEvento) {
		this.cod = cod;
		this.codigoEvento = codigoEvento;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.status = status;

	}

	public int getCod() {
		return cod;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public int getDuracao() {
		return duracao;
	}

	public String getStatus() {
		return status;
	}

	public String getCodigoEvento() {
		return codigoEvento;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setEquipe(Equipe equipe) {
		this.equipeAlocada = equipe;
	}

	public Equipe getEquipe() {
		return equipeAlocada;
	}

	@Override
	public String toString() {
		return "Atendimento - Código: " + getCod() +
				", Data de Início: " + getDataInicio() +
				", Duração: " + getDuracao() + " dias" +
				", Status: " + getStatus();
	}

}
