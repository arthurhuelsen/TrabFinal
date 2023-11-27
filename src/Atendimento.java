public class Atendimento {
	private int cod;
	private String dataInicio;
	private int duracao;
	private String status;
	private String codigoEvento; // Código do evento

	// Método para calcular custo
	public double calculaCusto() {
		// Implementação do método
		return 0.0;
	}

	// Construtor, getters e setters
	public Atendimento(int cod, String codigoEvento, String dataInicio, int duracao, String status) {
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

}
