package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public void insert(Alternativa obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO \r\n"
					+ "Alternativa (id_pergunta, conteudo, eh_verdadeira) \r\n"
					+ "VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getPergunta().getId());
			st.setString(2, obj.getConteudo());
			st.setString(3, obj.EhVerdadeira() == true ? "V":"F");
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected>0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Erro inesperado, nenhumk linha alterada!");
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
		
		
	}

	@Override
	public void update(Alternativa obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Alternativa\r\n"
					+ "SET id_pergunta = ?, conteudo = ?, eh_verdadeira = ?\r\n"
					+ "WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getPergunta().getId());
			st.setString(2, obj.getConteudo());
			st.setString(3, obj.EhVerdadeira() == true ? "V":"F");
			st.setInt(4, obj.getId());
			
			st.executeUpdate();
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
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
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT alternativa.*,pergunta.enunciado as enunciado\r\n"
					+ "FROM alternativa INNER JOIN pergunta\r\n" + "ON alternativa.id_pergunta = pergunta.id\r\n"
					+ "ORDER BY id");

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
