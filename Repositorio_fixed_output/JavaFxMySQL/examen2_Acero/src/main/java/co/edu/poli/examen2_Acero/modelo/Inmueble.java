package co.edu.poli.examen2_Acero.modelo;

public abstract class Inmueble {

	private String numero;

	private String fechaExp;

	private boolean estado;

	private Propietario titular;

	public Inmueble(String numero, String fechaExp, boolean estado, Propietario titular) {
		this.numero = numero;
		this.fechaExp = fechaExp;
		this.estado = estado;
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFechaExp() {
		return fechaExp;
	}

	public void setFechaExp(String fechaExp) {
		this.fechaExp = fechaExp;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Propietario getTitular() {
		return titular;
	}

	public void setTitular(Propietario titular) {
		this.titular = titular;
	}

	public String bloquear() {
		this.estado = false;
		return "Inmueble " + numero + " BLOQUEADA.";
	}

	public String activar() {
		this.estado = true;
		return "Inmueble " + numero + " ACTIVADA.";
	}

	@Override
	public String toString() {
		return "numero=" + numero + ", fechaExp=" + fechaExp + ", estado=" + estado + ", titular=" + titular;
	}
}
