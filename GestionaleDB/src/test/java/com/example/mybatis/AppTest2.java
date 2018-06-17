package com.example.mybatis;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import it.alessandro.dbGestionale.pojo.Prodotto;

public class AppTest2 {

	@Test
	public void testApp() {
		App.init();

		assertNotNull(App.factory);

		SqlSession s = App.factory.openSession();

		GestionaleMapper mapper = s.getMapper(GestionaleMapper.class);

		// Tipo tipo = mapper.getTipoById(1);
		//
		// System.out.println(tipo);

		Prodotto prodotto = new Prodotto();
		prodotto.setIdProdotto(1);
		prodotto.setDescrizionoProdotto("salsiccie");
		// System.out.println(prodotto.getDescrizionoProdotto().toString());
		// prodotto.setDescrizionoProdotto("salsiccie");

		Prodotto prodotto2 = mapper.getProdottoByProdotto(prodotto);

		System.out.println(prodotto2.getDescrizionoProdotto() + " - " + prodotto2.getTipo().getDescrizioneTipo());

		s.commit();
		s.close();
	}

}
