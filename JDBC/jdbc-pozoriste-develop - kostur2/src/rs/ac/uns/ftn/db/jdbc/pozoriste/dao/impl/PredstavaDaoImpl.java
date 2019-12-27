package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionHikari_CP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PredstaveDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Predstava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;

public class PredstavaDaoImpl implements PredstaveDAO {

	public PredstavaDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Predstava entity) throws SQLException {
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
	public Iterable<Predstava> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Predstava> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Predstava findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Predstava entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAll(Iterable<Predstava> entities) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Predstava> predstaveKojeSePrikazuju() throws SQLException {
		List<Predstava> listaPredstava = new ArrayList<Predstava>();
		String upit = "select distinct(idpred),nazivpred,trajanje,godinapre from predstava p,prikazivanje pr where p.idpred = pr.predstava_idpred";
		
		
		try(Connection connection = ConnectionHikari_CP.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(upit);
			ResultSet resultSet = preparedStatement.executeQuery();){
			
			while(resultSet.next()) {
				listaPredstava.add(new Predstava(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4)));
			}
			
			return listaPredstava;
		}
	}
	
	//Meotda koja izbacuje sva Pirkazivanja za prosledjenu prestavu 
	public List<Prikazivanje> prikazivanjaPredstava(Predstava entity) throws SQLException {
		List<Prikazivanje> listaPredstava = new ArrayList<Prikazivanje>();
		String upit = "select rbr, datumpri,vremepri,brojgled,scena_idsce from prikazivanje where predstava_idpred = ?";
		
		try(Connection connection = ConnectionHikari_CP.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(upit);){
		
		preparedStatement.setInt(1, entity.getIdpred());
		
			try(ResultSet resultSet = preparedStatement.executeQuery();){
				while(resultSet.next()) {
					listaPredstava.add(new Prikazivanje((1), resultSet.getDate(2), resultSet.getDate(3), 
							resultSet.getInt(4),entity.getIdpred(), resultSet.getInt(5)));
				}
				return listaPredstava;
			}
		
		}
		
	}


	}
