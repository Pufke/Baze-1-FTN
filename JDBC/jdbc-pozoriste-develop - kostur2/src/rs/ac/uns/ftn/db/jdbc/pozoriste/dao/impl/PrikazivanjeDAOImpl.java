package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD

=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 616c5d691c4db64189476a1b699fd2758665a413

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionHikari_CP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PrikazivanjeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Predstava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;
import rs.ac.uns.ftn.jdbc.pozoriste.dto.PrikazivanjeDTO;

public class PrikazivanjeDAOImpl implements PrikazivanjeDAO {

	public PrikazivanjeDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Prikazivanje entity) throws SQLException {
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
	public Iterable<Prikazivanje> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Prikazivanje> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Prikazivanje findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Prikazivanje entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAll(Iterable<Prikazivanje> entities) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public  PrikazivanjeDTO informacijeOprikazivanju(Predstava entity) throws SQLException {
		PrikazivanjeDTO prikazivanjaDTO = new PrikazivanjeDTO();
		String upit = "select count(predstava_idpred),sum(brojgled), round(avg(brojgled),2) from prikazivanje  where predstava_idpred = ? group by predstava_idpred";
		
		try(Connection connection = ConnectionHikari_CP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(upit);){
				
				preparedStatement.setInt(1, entity.getIdpred());
				
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					if(resultSet.isBeforeFirst()) {
						resultSet.next();
						 prikazivanjaDTO = new PrikazivanjeDTO(resultSet.getInt(2),resultSet.getDouble(3),resultSet.getInt(1));
					}else {
						return null;
					}
				}
				return prikazivanjaDTO;
			}
	}

}
