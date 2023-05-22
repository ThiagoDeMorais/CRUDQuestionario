package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		
		
		Statement st = null;

		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			conn.setAutoCommit(false);

			
			int rows1 = st.executeUpdate("UPDATE questao SET enunciado = 'claudia' WHERE id_questao = 6");
					
			//if(true) {
			//	throw new SQLException("Fake error");
			//}
			int rows2 = st.executeUpdate("UPDATE questao SET enunciado = 'Oi' WHERE id_questao = 5");

			conn.commit();
			
			System.out.println("rows1 " + rows1);
			System.out.println("rows2 " + rows2);

		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transação não concluída" + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro ao tentar voltar a transação" + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
