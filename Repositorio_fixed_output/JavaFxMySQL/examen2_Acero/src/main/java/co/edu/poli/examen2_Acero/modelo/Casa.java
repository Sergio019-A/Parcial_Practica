package co.edu.poli.examen2_Acero.modelo;

public class Casa extends Inmueble {

	private double limite;

	public Casa(String numero, String fechaExp, boolean estado, Propietario titular, double limite) {
		super(numero, fechaExp, estado, titular);
		this.limite = limite;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	@Override
	public String toString() {
		return "Casa [" + super.toString() + ", limite=" + limite + "]";
	}
}
