package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PozoristeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PrikazivanjeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeScenaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Predstava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;

public class PrikazivanjeDAOImpl implements PrikazivanjeDAO {

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
	public HashMap<Integer, PrikazivanjeDTO> findSumAvgCountForShowingShow() throws SQLException {
		HashMap<Integer, PrikazivanjeDTO> result = new HashMap<Integer,PrikazivanjeDTO>();
		
		String query = "SELECT idpred, sum(brojgled), avg(brojgled), count(*)\r\n" + 
				"from prikazivanje pr, predstava p\r\n" + 
				"where pr.predstava_idpred = p.idpred\r\n" + 
				"group by idpred";
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);){
			
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				while(resultSet.next()) {
					PrikazivanjeDTO prikazivanje = new PrikazivanjeDTO(resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4));
					result.put(resultSet.getInt(1), prikazivanje);
				}
			}
			
		}
		return result;
	}

	//TRECI ZADATAK
	//metoda koja prikazuje predstave koje se prikazuju na odredjenoj sceni
	@Override
	public List<PrikazivanjeScenaDTO> findBySceneId(Integer idScene) throws SQLException {
		String query = "select predstava_idpred,sum(brojgled) from prikazivanje where scena_idsce = ? group by predstava_idpred";
		List<PrikazivanjeScenaDTO> prikazivanjeList = new ArrayList<PrikazivanjeScenaDTO>();
		
		try(Connection conneciton = ConnectionUtil_HikariCP.getConnection();
			PreparedStatement preparedStatement = conneciton.prepareStatement(query);){
			
			preparedStatement.setInt(1, idScene);
			
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				while(resultSet.next()) {
					PrikazivanjeScenaDTO prikazivanje = new PrikazivanjeScenaDTO(resultSet.getInt(1),
							resultSet.getInt(2));
					prikazivanjeList.add(prikazivanje);
				}
			}
			
		}
		return prikazivanjeList;
	}

}