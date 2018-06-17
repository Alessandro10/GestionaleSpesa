package it.alessandro.dbGestionale.pojo;

import java.util.Date;

public class Acquisto {

	private long idAcquisto = -1;
	private Prodotto prodotto = null;
	private Date dataScadenza;
	private Date dataInserimento;
	private Date dataConsumo;
	private int flgConsumato = 0;

	public Acquisto(long idProdotto, Date dataScadenza) {
		prodotto = new Prodotto(idProdotto);
		this.dataScadenza = dataScadenza;
	}

	public long getIdAcquisto() {
		return idAcquisto;
	}

	public void setIdAcquisto(long idAcquisto) {
		this.idAcquisto = idAcquisto;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataConsumo() {
		return dataConsumo;
	}

	public void setDataConsumo(Date dataConsumo) {
		this.dataConsumo = dataConsumo;
	}

	public int getFlgConsumato() {
		return flgConsumato;
	}

	public void setFlgConsumato(int flgConsumato) {
		this.flgConsumato = flgConsumato;
	}

}
