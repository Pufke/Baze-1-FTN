package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionHikari_CP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PozoristeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;

public class PozoristeDAOImpl implements PozoristeDAO {

	@Override
	public int count() throws SQLException {
		String upit = "select count(*) from pozoriste";
		int result;
		try(Connection conneciton = ConnectionHikari_CP.getConnection();
			PreparedStatement preparedStatement = conneciton.prepareStatement(upit);
			ResultSet resultSet = preparedStatement.executeQuery();){
			
			if(resultSet.isBeforeFirst()) {
				result = resultSet.getInt(1);
				return result;
			}else {
				return -1;
			}
		}
	}

	@Override
	public void delete(Pozoriste entity) throws SQLException {
		String upit = "delete from pozoriste where idpoz=?";
		
		try(Connection conneciton = ConnectionHikari_CP.getConnection();
			PreparedStatement preparedStatement = conneciton.prepareStatement(upit);){
					preparedStatement.setInt(1, entity.getId());
					preparedStatement.executeUpdate();
		}
	}

	@Override
	public void deleteAll() throws SQLException {
		String upit = "delete from pozoriste";
		
		try(Connection conneciton = ConnectionHikari_CP.getConnection();
			PreparedStatement preparedStatement = conneciton.prepareStatement(upit);){
				preparedStatement.executeUpdate();
					
				}

	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		String upit = "delete from pozoriste where idpoz=?";
		
		try(Connection conneciton = ConnectionHikari_CP.getConnection();
			PreparedStatement preparedStatement = conneciton.prepareStatement(upit);){
					preparedStatement.setInt(1, id);
					preparedStatement.executeUpdate();
		}

	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		String upit = "select * from pozoriste where idpoz=?";
		
		try(Connection conneciton = ConnectionHikari_CP.getConnection();
			PreparedStatement preparedStatement = conneciton.prepareStatement(upit);){
					
			 		preparedStatement.setInt(1, id);
					
					try(ResultSet resultSet = preparedStatement.executeQuery()){
						return resultSet.isBeforeFirst();
					}
		}
	}

	@Override
	public Iterable<Pozoriste> findAll() throws SQLException {
		String upit = "select idpoz, nazivpoz, adresapoz, sajt, mesto_idm from pozoriste";
		ArrayList<Pozoriste> result = new ArrayList<Pozoriste>();
		
		try(Connection conneciton = ConnectionHikari_CP.getConnection();
			PreparedStatement preparedStatement = conneciton.prepareStatement(upit);
			ResultSet resultSet = preparedStatement.executeQuery()){
					
					while(resultSet.next()) {
						Pozoriste pozoriste = new Pozoriste(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), 
								                            resultSet.getString(4), resultSet.getInt(5));
						
						result.add(pozoriste);
					}
					
				}
		return result;
	}

	@Override
	public Iterable<Pozoriste> findAllById(Iterable<Integer> ids) throws SQLException {
		StringBuilder stringBuilder = new StringBuilder();
		
		String upitPocetak = "select idpoz, nazivpoz, adresapoz, sajt, mesto_idm from pozoriste where idpoz in (";
		stringBuilder.append(upitPocetak);
		ArrayList<Pozoriste> result = new ArrayList<Pozoriste>();
		
		for(@SuppressWarnings("unused")
			Integer id : ids) {
			stringBuilder.append("?,");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		
		try(Connection conneciton = ConnectionHikari_CP.getConnection();
			PreparedStatement preparedStatement = conneciton.prepareStatement(stringBuilder.toString());){
				
			int i = 0;
				for(Integer id : ids) {
					preparedStatement.setInt(i, id);
					i++;
				}
				
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()) {
						Pozoriste pozoriste = new Pozoriste(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), 
								                            resultSet.getString(4), resultSet.getInt(5));
						
						result.add(pozoriste);
					}
				}
		}
		return result;
	}

	@Override
	public Pozoriste findById(Integer id) throws SQLException {
		String upit = "select idpoz, nazivpoz, adresapoz, sajt, mesto_idm from pozoriste where idpoz = ?";
		
		try(Connection conneciton = ConnectionHikari_CP.getConnection();
			PreparedStatement preparedStatement = conneciton.prepareStatement(upit);){
					
			preparedStatement.setInt(1, id);
			
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					if(resultSet.isBeforeFirst()) {
						resultSet.next();
						Pozoriste pozoriste = new Pozoriste(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), 
								                            resultSet.getString(4), resultSet.getInt(5));
						return pozoriste;
					}else {
						return null;
					}
				}
		}
	}

	@Override
	public void save(Pozoriste entity) throws SQLException {
		String insertKomanda = "insert into pozoriste (nazivpoz, adresapoz, sajt,mesto_idm, idpoz) values (?, ? , ?, ?,?)";
		String updateKomanda = "update pozoriste set nazivpoz=?, adresapoz=?, sajt=?, mesto_idm=? where idpoz=?";
		
		try(Connection conneciton = ConnectionHikari_CP.getConnection();
			PreparedStatement preparedStatement = conneciton
					.prepareStatement(existsById(entity.getId()) ? updateKomanda : insertKomanda);){
			
			preparedStatement.setString(1, entity.getNaziv());
			preparedStatement.setString(2, entity.getAdresa());
			preparedStatement.setString(3, entity.getSajt());
			preparedStatement.setInt(4, entity.getMesto());
			preparedStatement.setInt(5, entity.getId());
			
			preparedStatement.executeUpdate();
		}
		

	}

	@Override
	public void saveAll(Iterable<Pozoriste> entities) throws SQLException {
		String insertKomanda = "insert into pozoriste (nazivpoz, adresapoz, sajt, mesto_idm, idpoz) values (?,?,?,?,?)";
		String updateKomanda = "update pozoriste set nazivpoz=?, adresapoz=?, sajt=?, mesto_idm=? where idpoz=?";
		
		try(Connection conneciton = ConnectionHikari_CP.getConnection();
				PreparedStatement preparedStatementUpdate = conneciton.prepareStatement(updateKomanda);
				PreparedStatement preparedStatementInsert = conneciton.prepareStatement(insertKomanda);){
				
				conneciton.setAutoCommit(false);
				
				for(Pozoriste pozoriste : entities) {
					
						PreparedStatement preparedStatement;
						if(existsById(pozoriste.getId())) {
							preparedStatement = preparedStatementUpdate;
						}else {
							preparedStatement = preparedStatementInsert;
						}
					
						preparedStatement.setString(1, pozoriste.getNaziv());
						preparedStatement.setString(2, pozoriste.getAdresa());
						preparedStatement.setString(3, pozoriste.getSajt());
						preparedStatement.setInt(4, pozoriste.getMesto());
						preparedStatement.setInt(5, pozoriste.getId());
					
						preparedStatement.execute();
				}
				conneciton.commit();
		}
		}

}

