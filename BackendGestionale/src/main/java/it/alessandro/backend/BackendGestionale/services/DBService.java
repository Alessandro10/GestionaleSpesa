package it.alessandro.backend.BackendGestionale.services;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.ibatis.session.SqlSession;

import com.example.mybatis.DBConnection;
import com.example.mybatis.GestionaleMapper;

import it.alessandro.dbGestionale.pojo.Acquisto;
import it.alessandro.dbGestionale.pojo.Prodotto;
import it.alessandro.dbGestionale.pojo.Tipo;

@Path("/servizio")
public class DBService {

	private HttpServletRequest request;

	private GestionaleMapper mapper;

	private SqlSession sqlSession;

	private static final String TIMEOUT = "timeout";

	private GestionaleMapper getMapper() {
		Date dataTimeout = (Date) request.getAttribute(TIMEOUT);
		if (dataTimeout.after(new Date())) {
			return mapper;
		} else {
			if (getSqlSession() != null) {
				getSqlSession().close();
				setSqlSession(sqlSession);
			}
		}
		return null;
	}

	private void setMapper(GestionaleMapper mapper) {
		this.mapper = mapper;
	}

	private void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	private SqlSession getSqlSession() {
		return this.sqlSession;
	}

	@GET
	@Path("/sessione")
	public Response getSession() {
		request.getSession(true);

		final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs

		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date afterAddingTenMins = new Date(t + (1 * ONE_MINUTE_IN_MILLIS));

		request.setAttribute(TIMEOUT, afterAddingTenMins);

		DBConnection.init();

		assertNotNull(DBConnection.factory);

		SqlSession s = DBConnection.factory.openSession();

		GestionaleMapper mapper = s.getMapper(GestionaleMapper.class);

		setMapper(mapper);

		return Response.status(200).entity("OK").build();
	}

	@GET
	@Path("/inserisci/prodotto/{descrizioneProdotto}/{idTipo}")
	public Response inserisciProdotto(@PathParam("descrizioneProdotto") String descrizioneProdotto,
			@PathParam("idTipo") int idTipo) {

		GestionaleMapper mapper = getMapper();

		Prodotto prodotto = new Prodotto();

		prodotto.setDescrizionoProdotto(descrizioneProdotto);

		Tipo tipo = mapper.getTipoById(idTipo);

		prodotto.setTipo(tipo);

		mapper.insertProdotto(prodotto);

		System.out.println(prodotto.getDescrizionoProdotto());

		Prodotto prodotto2 = mapper.getProdottoByProdotto(prodotto);

		String output = prodotto2.getDescrizionoProdotto() + " - " + prodotto2.getTipo().getDescrizioneTipo();

		getSqlSession().commit();

		return Response.status(200).entity(output).build();

	}

	@GET
	@Path("/inserisci/tipo/{descrizioneTipo}")
	public Response inserisciTipo(@PathParam("descrizioneTipo") String descrizioneTipo) {

		DBConnection.init();

		assertNotNull(DBConnection.factory);

		SqlSession s = DBConnection.factory.openSession();

		GestionaleMapper mapper = s.getMapper(GestionaleMapper.class);

		Tipo tipo = new Tipo(descrizioneTipo);

		int result = mapper.insertTipo(tipo);

		s.commit();
		s.close();

		return Response.status(200).entity(result).build();

	}

	@GET
	@Path("/inserisci/acquisto/{idProdotto}/{dataScadenza}/")
	public Response inserisciAcquisto(@PathParam("idProdotto") int idProdotto,
			@PathParam("dataScadenza") Date dataScadenza) {

		DBConnection.init();

		assertNotNull(DBConnection.factory);

		SqlSession s = DBConnection.factory.openSession();

		GestionaleMapper mapper = s.getMapper(GestionaleMapper.class);

		Acquisto acquisto = new Acquisto(idProdotto, dataScadenza);

		int result = mapper.insertAcquisto(acquisto);

		s.commit();
		s.close();

		return Response.status(200).entity(result).build();

	}

	@GET
	@Path("/inserisci/prodotto/{idProdotto}/{descrizioneProdotto}/{idTipo}")
	public Response updateProdotto(@PathParam("idProdotto") long idProdotto,
			@PathParam("descrizioneProdotto") String descrizioneProdotto, @PathParam("idTipo") long idTipo) {

		DBConnection.init();

		assertNotNull(DBConnection.factory);

		SqlSession s = DBConnection.factory.openSession();

		GestionaleMapper mapper = s.getMapper(GestionaleMapper.class);

		Prodotto prodotto = new Prodotto();

		prodotto.setDescrizionoProdotto(descrizioneProdotto);

		Tipo tipo = mapper.getTipoById(idTipo);

		prodotto.setTipo(tipo);

		mapper.insertProdotto(prodotto);

		System.out.println(prodotto.getDescrizionoProdotto());

		Prodotto prodotto2 = mapper.getProdottoByProdotto(prodotto);

		String output = prodotto2.getDescrizionoProdotto() + " - " + prodotto2.getTipo().getDescrizioneTipo();

		s.commit();
		s.close();

		return Response.status(200).entity(output).build();

	}

}