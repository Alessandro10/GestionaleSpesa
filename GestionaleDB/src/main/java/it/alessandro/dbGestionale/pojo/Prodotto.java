package it.alessandro.dbGestionale.pojo;

public class Prodotto {
	private long idProdotto = -1;
	private String descrizioneProdotto;
	private Tipo tipo = new Tipo();

	public Prodotto() {

	}

	public Prodotto(long idProdotto) {
		this.idProdotto = idProdotto;
	}

	public long getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(long idProdotto) {
		this.idProdotto = idProdotto;
	}

	public String getDescrizionoProdotto() {
		return descrizioneProdotto;
	}

	public void setDescrizionoProdotto(String descrizionoProdotto) {
		this.descrizioneProdotto = descrizionoProdotto;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
