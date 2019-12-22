package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PredstavaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PrikazivanjeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PredstavaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeScenaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Uloga;

public class PredstavaDAOImpl implements PredstavaDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Pozoriste entity) throws SQLException {
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
	public Iterable<Pozoriste> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Pozoriste> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pozoriste findById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Pozoriste entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAll(Iterable<Pozoriste> entities) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PredstavaDTO> najveciProsecanBrGledalaca() throws SQLException {
		List<PredstavaDTO> result = new ArrayList<PredstavaDTO>();
		String query = "select p.idpred, p.nazivpred, avg(pr.brojgled) from predstava p, prikazivanje pr where p.idpred = pr.predstava_idpred group by  p.idpred, p.nazivpred having avg(brojgled) >= ALL( SELECT AVG(brojgled)  FROM Predstava P, Prikazivanje Pr WHERE P.idpred = Pr.predstava_idpred GROUP BY P.idpred )";
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();){
			
			while (resultSet.next()) {
				PredstavaDTO predstava = new PredstavaDTO(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getDouble(3));
				result.add(predstava);
			}	
		}
		return result;
	}

	@Override
	public List<Uloga> listaUlogaZaPredstaveSaNajvecimProsecnimBrGledalaca(PredstavaDTO predstava) throws SQLException {
		List<Uloga> result = new ArrayList<Uloga>();
		String query = "select imeulo,pol,vrstaulo  from uloga where predstava_idpred = ?";
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);){
		
				preparedStatement.setInt(1, predstava.getIdpred());
		
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while(resultSet.next()) {
						Uloga uloga = new Uloga(resultSet.getString(1), resultSet.getString(2),
											resultSet.getString(3), predstava.getIdpred());
						result.add(uloga);
					}
				}
		}
		return result;
		
	}

	

}
