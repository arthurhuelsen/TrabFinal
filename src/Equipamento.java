public class Equipamento {
	private int id;
	private String nome;
	private double custoDia;
	private Equipe equipeVinculada;

	// Construtor, getters e setters
	public Equipamento(int id, String nome, double custoDia) {
		this.id = id;
		this.nome = nome;
		this.custoDia = custoDia;
	}

	// Vincula o equipamento a uma equipe
	public void vincularEquipe(Equipe equipe) {
		this.equipeVinculada = equipe;
	}

	// Verifica se o equipamento está vinculado a alguma equipe
	public boolean estaVinculado() {
		return equipeVinculada != null;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getCustoDia() {
		return custoDia;
	}

}