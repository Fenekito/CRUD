package com.fene.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fene.factory.ConnectionFactory;
import com.fene.model.Pessoa;

public class PessoaDAO {
	
	public void save(Pessoa contato) {
		
		String sql = "INSERT INTO pessoa(nome, idade, datacadastro) VALUES(?, ?, ?)";
		
		Connection con = null;
		PreparedStatement statement = null;
		
		try {
			con = ConnectionFactory.createConnectionToMySQL();
			statement = (PreparedStatement) con.prepareStatement(sql);
			statement.setString(1, contato.getNome());
			statement.setInt(2, contato.getIdade());
			statement.setDate(3, (java.sql.Date) new Date(contato.getDataCadastro().getTime()));
			
			statement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(statement != null) {
					statement.close();
				}
				
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<Pessoa> getContatos() {
		
		String sql = "SELECT * FROM pessoa";
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		Connection con = null;
		PreparedStatement statement = null;
		
		ResultSet result = null;
		
		try {
			con = ConnectionFactory.createConnectionToMySQL();
			
			statement = (PreparedStatement) con.prepareStatement(sql);
			
			result = statement.executeQuery();
			
			//Recupereação de Dados
			while(result.next()) {
				Pessoa pessoa = new Pessoa();
				
				pessoa.setId(result.getInt("id"));
				pessoa.setNome(result.getString("nome"));
				pessoa.setIdade(result.getInt("idade"));
				
				pessoa.setDataCadastro(result.getDate("dataCadastro"));
				
				pessoas.add(pessoa);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(result != null) {
					result.close();
				}
				
				if(statement != null) {
					statement.close();
				}
				
				if(con != null) {
					con.close();
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return pessoas;
	}
	
	public void update(Pessoa contato) {
		
		String sql = "UPDATE pessoa SET nome = ? , idade = ? WHERE id = ?)";
		
		Connection con = null;
		PreparedStatement statement = null;
		
		try {
			con = ConnectionFactory.createConnectionToMySQL();
			statement = (PreparedStatement) con.prepareStatement(sql);
			
			statement.setString(0, contato.getNome());
			statement.setInt(1, contato.getIdade());
			statement.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(statement != null) {
					statement.close();
				}
				if(con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(Pessoa contato) {
		String sql = "DELETE FROM pessoa WHERE nome = ? AND idade = ?";
		
		Connection con = null;
		PreparedStatement statement = null;
		
		try {
			con = ConnectionFactory.createConnectionToMySQL();
			statement = (PreparedStatement) con.prepareStatement(sql);
			
			statement.setString(1, contato.getNome());
			statement.setInt(2, contato.getIdade());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
