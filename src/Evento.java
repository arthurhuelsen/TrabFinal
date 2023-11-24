import java.util.ArrayList;
import java.util.List;

public class Evento {
	private static final List<String> codigosExistentes = new ArrayList<>();

	private String codigo;
	private String data; // Assume-se que a data é uma String no formato "dd/MM/yyyy"
	private double latitude;
	private double longitude;

	public Evento(String codigo, String data, double latitude, double longitude) {
		if (codigo == null) {
			throw new IllegalArgumentException("O código não pode ser nulo ou vazio.");
		}
		if (codigosExistentes.contains(codigo)) {
			throw new IllegalArgumentException("Código de evento já existe.");
		}

		this.codigo = codigo;
		this.data = data;
		this.latitude = latitude;
		this.longitude = longitude;

		codigosExistentes.add(codigo); // Adiciona o código na lista para garantir a unicidade
	}

	// Getters e setters
	public String getCodigo() {
		return codigo;
	}

	public String getData() {
		return data;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
