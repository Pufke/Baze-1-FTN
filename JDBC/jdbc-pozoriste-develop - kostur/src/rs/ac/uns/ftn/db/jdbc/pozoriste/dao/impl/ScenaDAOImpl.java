package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.ScenaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;

public class ScenaDAOImpl implements ScenaDAO {

	public ScenaDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Scena entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Scena> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Scena> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scena findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Scena entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAll(Iterable<Scena> entities) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Scena> findSceneByTheatre(Integer theatreId) throws SQLException {
		String query = "select idsce,nazivsce, brojsed ,pozoriste_idpoz from scena where pozoriste_idpoz = ?";
		List<Scena> scenaList  = new ArrayList<Scena>();
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);){
			
			preparedStatement.setInt(1, theatreId);
			
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				while(resultSet.next()) {
					Scena scena = new Scena(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
							resultSet.getInt(4));
					scenaList.add(scena);
				}
			}
		}
		return scenaList;
	}

}