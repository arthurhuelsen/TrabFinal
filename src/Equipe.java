import java.util.ArrayList;
import java.util.List;

public class Equipe {
	private static final double RAIO_DA_TERRA_KM = 6371.01; // Raio aproximado da Terra em quilômetros
	private static final double MAXIMA_DISTANCIA_ATENDIMENTO = 5000; // Máxima distância de atendimento em quilômetros

	private String codinome;
	private int quantidadeMembros;
	private double latitude;
	private double longitude;
	private List<Equipamento> equipamentos;
	private boolean alocada; // Flag para indicar se a equipe está alocada

	// Construtor, getters e setters
	public Equipe(String codinome, int quantidade, double latitude, double longitude) {
		this.codinome = codinome;
		this.quantidadeMembros = quantidade;
		this.latitude = latitude;
		this.longitude = longitude;
		this.equipamentos = new ArrayList<>();
		this.alocada = false;
	}

	public void adicionarEquipamento(Equipamento equipamento) {
		if (!equipamentos.contains(equipamento)) {
			equipamentos.add(equipamento);
		}
	}

	// Verifica se um equipamento específico está vinculado à equipe
	public boolean temEquipamento(Equipamento equipamento) {
		return equipamentos.contains(equipamento);
	}

	public boolean podeAtender(Evento evento) {
		double latEvento = evento.getLatitude();
		double lonEvento = evento.getLongitude();
		double distancia = calcularDistancia(this.latitude, this.longitude, latEvento, lonEvento);
		return distancia <= MAXIMA_DISTANCIA_ATENDIMENTO;
	}

	// Fórmula de Haversine para calcular a distância entre dois pontos da Terra
	public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
		double lat1Rad = Math.toRadians(lat1);
		double lat2Rad = Math.toRadians(lat2);
		double deltaLat = Math.toRadians(lat2 - lat1);
		double deltaLon = Math.toRadians(lon2 - lon1);

		double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
				+ Math.cos(lat1Rad) * Math.cos(lat2Rad)
						* Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return RAIO_DA_TERRA_KM * c;
	}

	public String getCodinome() {
		return codinome;
	}

	public int getQuantidadeMembros() {
		return quantidadeMembros;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public List<Equipamento> getEquipamentos() {
		return new ArrayList<>(equipamentos); // Retorna uma cópia para evitar modificação externa da lista original
	}

	public void alocar() {
		this.alocada = true;
	}

	public void desalocar() {
		this.alocada = false;
	}

	public boolean estaAlocada() {
		return this.alocada;
	}

	@Override
	public String toString() {
		return "Equipe - Codinome: " + getCodinome() +
				", Quantidade de Membros: " + getQuantidadeMembros() +
				", Latitude: " + getLatitude() +
				", Longitude: " + getLongitude();
	}
}
