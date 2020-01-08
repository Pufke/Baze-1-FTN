package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionHikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PodelaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Podela;

public class PodelaDAOImpl implements PodelaDAO {

	@Override
	public int count() throws SQLException {
		String upit = "select count(*) from podela";
		
		try(Connection connection = ConnectionHikariCP.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(upit);
			ResultSet resultSet = preparedStatement.executeQuery();){
			
			if(resultSet.isBeforeFirst()) {
				resultSet.next();
				return resultSet.getInt(1);
			}else {
				return -1;
			}
		}
	}

	@Override
	public void delete(Podela entity) throws SQLException {
		String upit = "delete from podela where idpod = ?";
		
		try(Connection connection = ConnectionHikariCP.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(upit);){
			
				preparedStatement.setInt(1, entity.getIdpodele());
				preparedStatement.executeUpdate();
			}
	}
		

	@Override
	public void deleteAll() throws SQLException {
		String upit = "delete from podela";
		
				try(Connection connection = ConnectionHikariCP.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(upit);){
					
					preparedStatement.executeUpdate();
				}
				

	}

	@Override
	public void deleteById(Integer id) throws SQLException {
	String upit = "delete from podela where idpod = ?";
		
		try(Connection connection = ConnectionHikariCP.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(upit);){
			
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
			}

	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		String upit = "select * from podela where idpod = ?";
		
		try(Connection connection = ConnectionHikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(upit);){
		
			preparedStatement.setInt(1, id);
			try(ResultSet resultSet = preparedStatement.executeQuery();){
				if(resultSet.isBeforeFirst()) {
					return true;
				}else {
					return false;
				}
				
			}
		}
	}

	@Override
	public Iterable<Podela> findAll() throws SQLException {
		List<Podela> listaPodela = new ArrayList<Podela>();
		String upit = "select honorar,datumd,datump, uloga_idul,idpod,glumac_mbg from podela";
		
		try(Connection connection = ConnectionHikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(upit);
				ResultSet resultSet = preparedStatement.executeQuery();){
			
			while(resultSet.next()) {
				listaPodela.add(new Podela(resultSet.getDouble(1), resultSet.getDate(2), resultSet.getDate(3), 
						resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6)));
			}
			return listaPodela;
		}
		
	}

	@Override
	public Iterable<Podela> findAllById(Iterable<Integer> ids) throws SQLException {
		List<Podela> listaPodela = new ArrayList<Podela>();
		StringBuilder stringBuilder = new StringBuilder();
		
		String upitPocetak = "select honorar,datumd,datump, uloga_idul,idpod,glumac_mbg from podela where idpod in (";
		
		stringBuilder.append(upitPocetak);
		
		for(@SuppressWarnings("unused") 
			Integer id: ids) {
			stringBuilder.append("?,");
		}
		
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		stringBuilder.append(")");
		
		String upit = stringBuilder.toString();
		
		try(Connection connection = ConnectionHikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(upit);){
				
			int i =0;
			for(Integer id: ids) {
				preparedStatement.setInt(++i, id);
			}
			
			try(ResultSet resultSet = preparedStatement.executeQuery()){
			
				while(resultSet.next()) {
					listaPodela.add(new Podela(resultSet.getDouble(1), resultSet.getDate(2), resultSet.getDate(3), 
							resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6)));
				}
				return listaPodela;
			}
		}
	}

	@Override
	public Podela findById(Integer id) throws SQLException {
		Podela podela = new Podela();
		String upit = "select honorar,datumd,datump, uloga_idul,idpod,glumac_mbg from podela where idpod=?";
		
		try(Connection connection = ConnectionHikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(upit);){
				
			preparedStatement.setInt(1, id);
			
			try(ResultSet resultSet = preparedStatement.executeQuery()){
			
				if(resultSet.next()) {
					podela = new Podela(resultSet.getDouble(1), resultSet.getDate(2), resultSet.getDate(3), 
							resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
					return podela;
				}else {
					return null;
				}
				
			}
		}
	}

	@Override
	public void save(Podela entity) throws SQLException {
		String updateUpit = "update podela set honorar = ? ,datumd = ?,datump = ?, uloga_idul = ? ,glumac_mbg =? where idpod = ?";
		String insertUpit = "insert into podela (honorar,datumd,datump, uloga_idul,glumac_mbg,idpod) values (?,?,?,?,?,?)";
		
		try(Connection connection = ConnectionHikariCP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(existsById(entity.getIdpodele()) ? updateUpit : insertUpit)){
			int i = 0;
			
			preparedStatement.setDouble(++i, entity.getHonorar());
			preparedStatement.setDate(++i, (java.sql.Date) entity.getDatumd());
			preparedStatement.setDate(++i, (java.sql.Date) entity.getDatump());
			preparedStatement.setInt(++i, entity.getIduloge());
			preparedStatement.setInt(++i,  entity.getMbg());
			preparedStatement.setInt(++i,  entity.getIdpodele());
			
			preparedStatement.executeUpdate();
			
		}
	}

	@Override
	public void saveAll(Iterable<Podela> entities) throws SQLException {
		String updateUpit = "update podela set honorar = ? ,datumd = ?,datump = ?, uloga_idul = ? ,glumac_mbg =? where idpod = ?";
		String insertUpit = "insert into podela (honorar,datumd,datump, uloga_idul,glumac_mbg,idpod) values (?,?,?,?,?,?)";

		try(Connection connection = ConnectionHikariCP.getConnection();
			PreparedStatement preparedStatementUpdate = connection.prepareStatement(updateUpit);
			PreparedStatement preparedStatementInsert = connection.prepareStatement(insertUpit);){
				
			connection.setAutoCommit(false);
			for (Podela entity : entities) {

				int i = 0;
				PreparedStatement preparedStatement;
			
				if(existsById(entity.getIdpodele())) {
					preparedStatement = preparedStatementUpdate;
				}	else {
					preparedStatement = preparedStatementInsert;
				}
				
				preparedStatement.setDouble(++i, entity.getHonorar());
				preparedStatement.setDate(++i, (java.sql.Date) entity.getDatumd());
				preparedStatement.setDate(++i, (java.sql.Date) entity.getDatump());
				preparedStatement.setInt(++i, entity.getIduloge());
				preparedStatement.setInt(++i,  entity.getMbg());
				preparedStatement.setInt(++i,  entity.getIdpodele());
				
				preparedStatement.execute();
			}
				
		}
	}

}
