package com.example.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import it.alessandro.dbGestionale.pojo.Acquisto;
import it.alessandro.dbGestionale.pojo.Prodotto;
import it.alessandro.dbGestionale.pojo.Tipo;

/**
 * An article on these annotations can be found at:
 * http://loianegroner.com/2011/02/getting-started-with-ibatis-mybatis-annotations/
 * <p/>
 * Notice how the SQL statements are broken up into more readable multiple
 * strings. myBatis automatically adds spaces, but otherwise the statements are
 * the same.
 */
public interface GestionaleMapper {

	@Select({
			"select id_acquisto, id_prodotto, data_scadenza, data_consumo, data_inserimento, flg_consumato from Acquisto where id_acquisto = #{idAcquisto}" })
	@Results({ @Result(column = "id_acquisto", property = "idAcquisto", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "id_prodotto", property = "prodotto.idProdotto", one = @One(select = "getProdottoById")),
			@Result(column = "data_scadenza", property = "dataScadenza", jdbcType = JdbcType.DATE),
			@Result(column = "data_consumo", property = "dataConsumo", jdbcType = JdbcType.DATE),
			@Result(column = "data_inserimento", property = "dataInserimento", jdbcType = JdbcType.DATE),
			@Result(column = "flg_consumato", property = "flgConsumato", jdbcType = JdbcType.DATE) })
	Acquisto getAcquistoByAcquisto(Acquisto acquisto);

	@Select({
			"select id_acquisto, id_prodotto, data_scadenza, data_consumo, data_inserimento, flg_consumato from Acquisto where id_prodotto = #{idProdotto}" })
	@Results({ @Result(column = "id_acquisto", property = "idAcquisto", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "id_prodotto", property = "prodotto.idProdotto", one = @One(select = "getProdottoById")),
			@Result(column = "data_scadenza", property = "dataScadenza", jdbcType = JdbcType.DATE),
			@Result(column = "data_consumo", property = "dataConsumo", jdbcType = JdbcType.DATE),
			@Result(column = "data_inserimento", property = "dataInserimento", jdbcType = JdbcType.DATE),
			@Result(column = "flg_consumato", property = "flgConsumato", jdbcType = JdbcType.DATE) })
	List<Acquisto> getAcquistoByProdotto(Prodotto prodotto);

	@Select({
			"select id_acquisto, id_prodotto, data_scadenza, data_consumo, data_inserimento, flg_consumato from Acquisto where id_prodotto = #{idProdotto}" })
	@Results({ @Result(column = "id_acquisto", property = "idAcquisto", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "id_prodotto", property = "prodotto.idProdotto", one = @One(select = "getProdottoById")),
			@Result(column = "data_scadenza", property = "dataScadenza", jdbcType = JdbcType.DATE),
			@Result(column = "data_consumo", property = "dataConsumo", jdbcType = JdbcType.DATE),
			@Result(column = "data_inserimento", property = "dataInserimento", jdbcType = JdbcType.DATE),
			@Result(column = "flg_consumato", property = "flgConsumato", jdbcType = JdbcType.DATE) })
	List<Acquisto> getAcquistoByTipo(Tipo tipo);

	@Select({
			"select id_prodotto,descrizione_prodotto, id_tipo from Prodotto where id_prodotto = #{idProdotto} and descrizione_prodotto = #{descrizioneProdotto}" })
	@Results({ @Result(column = "id_prodotto", property = "idProdotto", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "descrizione_prodotto", property = "descrizioneProdotto", jdbcType = JdbcType.VARCHAR),
			@Result(column = "id_tipo", property = "tipo", one = @One(select = "getTipoById")) })
	Prodotto getProdottoByProdotto(Prodotto prodotto);

	@Select({ "select id_prodotto,descrizione_prodotto, id_tipo from Prodotto where id_prodotto = #{idprodotto}" })
	@Results({ @Result(column = "id_prodotto", property = "idProdotto", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "descrizione_prodotto", property = "descrizioneProdotto", jdbcType = JdbcType.VARCHAR),
			@Result(column = "id_tipo", property = "tipo", one = @One(select = "getTipoById")) })
	Prodotto getProdottoById(long idprodotto);

	@Select("select * from Tipo where id_tipo = #{idtipo}")
	@Results({ @Result(column = "id_tipo", property = "idTipo", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "descrizione_tipo", property = "descrizioneTipo", jdbcType = JdbcType.VARCHAR) })
	Tipo getTipoById(long idtipo);

	@Insert({ "insert into Prodotto (descrizione_prodotto, id_tipo)",
			"values (#{descrizioneProdotto}, #{tipo.idTipo})" })
	@Options(useGeneratedKeys = true, keyProperty = "idProdotto")
	int insertProdotto(Prodotto prodotto);

	@Insert({ "insert into Tipo (descrizione_tipo)", "values (#{descrizioneTipo})" })
	@Options(useGeneratedKeys = true, keyProperty = "idTipo")
	int insertTipo(Tipo tipo);

	@Insert({ "insert into Acquisto (id_prodotto, data_scadenza, data_consumo, data_inserimento)",
			"values (#{idProdotto},#{dataScadenza}, null, now())" })
	@Options(useGeneratedKeys = true, keyProperty = "idAcquisto")
	int insertAcquisto(Acquisto acquisto);

	@Delete({ "delete from Prodotto where id = #{idProdotto}" })
	void deleteById(long idProdotto);

	@Update({ "update Prodotto", "set descrizione_prodotto = #{descrizioneProdotto},", "id_tipo = #{tipo.idTipo}",
			"where id_prodotto = #{idProdotto}" })
	int updateProdotto(Prodotto prodotto);

	@Update({ "update Tipo", "set descrizione_tipo = #{descrizioneTipo}", "where id_tipo = #{idTipo}" })
	int updateTipo(Tipo tipo);

	@Update({ "update Acquisto", "set descrizione_tipo = #{descrizioneTipo}", "where id_tipo = #{idTipo}" })
	int updateAcquisto(Acquisto acquisto);

	@Select("select count(*) from Prodotto")
	long countProdotto();

	@Select("select count(*) from Tipo")
	long countTipo();

	@Select("select count(*) from Acquisto")
	long countAcquisto();

	@Delete({ "delete from trans_token where id = #{idProdotto}" })
	void deleteProdottoById(Prodotto prodotto);

	@Delete({ "delete from trans_token where id = #{idTipo}" })
	void deleteTipoById(Tipo tipo);

	@Delete({ "delete from trans_token where id = #{idAcquisto}" })
	void deleteAcquistoById(Acquisto acquisto);

}
