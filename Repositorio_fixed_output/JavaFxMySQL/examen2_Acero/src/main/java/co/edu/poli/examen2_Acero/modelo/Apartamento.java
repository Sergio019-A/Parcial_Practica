package co.edu.poli.examen2_Acero.modelo;

public class Apartamento extends Inmueble {

	private double saldo;

	public Apartamento(String numero, String fechaExp, boolean estado, Propietario titular, double saldo) {
		super(numero, fechaExp, estado, titular);
		this.saldo = saldo;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Apartamento [" + super.toString() + ", saldo=" + saldo + "]";
	}
}
