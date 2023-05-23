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
import model.dao.PerguntaDao;
import model.entities.Alternativa;
import model.entities.Pergunta;

public class PerguntaDaoJDBC implements PerguntaDao {
	
	private Connection conn;

	public PerguntaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Pergunta obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO \r\n"
					+ "Pergunta (enunciado) \r\n"
					+ "VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getEnunciado());
			
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
	public void update(Pergunta obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Pergunta\r\n"
					+ "SET enunciado = ?\r\n"
					+ "WHERE id = ?",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getEnunciado());
			st.setInt(2, obj.getId());

			st.executeUpdate();
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM Pergunta\r\n"
					+ "WHERE id = ?");
			
			st.setInt(1, id );
			st.executeUpdate();
		}catch(SQLException e ) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public Pergunta findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM questionario3.pergunta\r\n"
					+ "WHERE id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Pergunta per = instantiatePergunta(rs);
				return per;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Pergunta instantiatePergunta(ResultSet rs) throws SQLException {
		Pergunta per = new Pergunta();
		per.setId(rs.getInt("id"));
		per.setEnunciado(rs.getString("enunciado"));
		return per;
	}
	
	@Override
	public List<Pergunta> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM questionario3.pergunta\r\n"
					+ "ORDER BY id");

			rs = st.executeQuery();

			List<Pergunta> list = new ArrayList<>();

			while (rs.next()) {
				Pergunta per = instantiatePergunta(rs);
				list.add(per);
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
