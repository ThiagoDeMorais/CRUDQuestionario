package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.AlternativaDao;
import model.entities.Alternativa;
import model.entities.Pergunta;

public class AlternativaDaoJDBC implements AlternativaDao {

	private Connection conn;

	public AlternativaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(AlternativaDao obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AlternativaDao obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Alternativa findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT alternativa.*,pergunta.enunciado as enunciado "
					+ "FROM alternativa INNER JOIN pergunta " + "ON alternativa.id_pergunta = pergunta.id "
					+ "WHERE alternativa.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Pergunta per = instantiatePergunta(rs);
				Alternativa obj = instantiateAlternativa(rs, per);
				return obj;

			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Alternativa instantiateAlternativa(ResultSet rs, Pergunta per) throws SQLException {
		Alternativa obj = new Alternativa();
		obj.setId(rs.getInt("id"));
		obj.setConteudo(rs.getString("conteudo"));
		obj.setEhVerdadeira(rs.getString("eh_verdadeira") == "V" ? true : false);
		obj.setPergunta(per);
		return obj;
	}

	private Pergunta instantiatePergunta(ResultSet rs) throws SQLException {
		Pergunta per = new Pergunta();
		per.setId(rs.getInt("id_pergunta"));
		per.setEnunciado(rs.getString("enunciado"));
		return per;
	}

	@Override
	public List<Alternativa> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alternativa> findByPergunta(Pergunta pergunta) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT alternativa.*,pergunta.enunciado as enunciado\r\n"
					+ "FROM alternativa INNER JOIN pergunta\r\n" + "ON alternativa.id_pergunta = pergunta.id\r\n"
					+ "WHERE id_pergunta = ?\r\n" + "ORDER BY id");

			st.setInt(1, pergunta.getId());

			rs = st.executeQuery();

			List<Alternativa> list = new ArrayList<>();
			Map<Integer, Pergunta> map = new HashMap<>();

			while (rs.next()) {
				Pergunta per = map.get(rs.getInt("id_pergunta"));

				if (per == null) {
					per = instantiatePergunta(rs);
					map.put(rs.getInt("id_pergunta"), per);
				}

				Alternativa obj = instantiateAlternativa(rs, per);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
