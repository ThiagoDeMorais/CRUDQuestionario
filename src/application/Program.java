package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("UPDATE questao "
					+ "SET enunciado = ? "
					+ "WHERE "
					+ "(id_questao = ?)");
			
			st.setString(1, "Não leibe");
			st.setInt(2, 7);
			
			int rowsAffected = st.executeUpdate();
			System.out.println("Pronto! Quantidade de linhas alteradas:" + rowsAffected);
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	
		

	}

}
