public class Atendimento {
	private int cod;
	private String dataInicio;
	private int duracao;
	private String status;

	// Método para calcular custo
	public double calculaCusto() {
		// Implementação do método
		return 0.0;
	}

	// Construtor, getters e setters
	public Atendimento(int cod, String dataInicio, int duracao, String status) {
		this.cod = cod;
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

}
