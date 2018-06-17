package it.alessandro.dbGestionale.pojo;

public class Tipo {
	private long idTipo = -1;
	private String descrizioneTipo = "";

	public Tipo() {

	}

	public Tipo(String descrizioneTipo) {
		this.descrizioneTipo = descrizioneTipo;
	}

	public long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescrizioneTipo() {
		return descrizioneTipo;
	}

	public void setDescrizioneTipo(String descrizioneTipo) {
		this.descrizioneTipo = descrizioneTipo;
	}
}
