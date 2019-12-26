package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionHikari_CP;
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
	public List<Scena> pronadjiScenePremaPozoristu(Integer idPozorista) throws SQLException {
		String upit = "select nazivsce,brojsed,idsce from pozoriste p,scena s where p.idpoz = s.pozoriste_idpoz and idpoz = ?";
		List<Scena> listaScena = new ArrayList<Scena>();

		try(Connection conneciton = ConnectionHikari_CP.getConnection();
			PreparedStatement preparedStatement = conneciton.prepareStatement(upit);){
					
			 		preparedStatement.setInt(1, idPozorista);
					
					try(ResultSet resultSet = preparedStatement.executeQuery()){
						
							while(resultSet.next()) {
								Scena scena = new Scena(resultSet.getInt(3), resultSet.getString(1), 
													resultSet.getInt(2), idPozorista);
								listaScena.add(scena);
							}
							return listaScena;
			
					}
		}
	}

}
