public class Terremoto extends Evento {
	private double magnitude;

	// Construtor, getters e setters
	public Terremoto(String codigo, String data, double latitude, double longitude,
			double magnitude) {
		super(codigo, data, latitude, longitude);
		this.magnitude = magnitude;
	}

	public double getMagnitude() {
		return magnitude;
	}

}
