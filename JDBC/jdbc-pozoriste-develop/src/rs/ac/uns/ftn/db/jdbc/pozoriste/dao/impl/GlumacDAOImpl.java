package rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.media.jfxmedia.locator.ConnectionHolder;

import rs.ac.uns.ftn.db.jdbc.pozoriste.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.GlumacDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Glumac;

public class GlumacDAOImpl implements GlumacDAO {

	@Override
	public int count() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Glumac entity) throws SQLException {
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
	public Iterable<Glumac> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Glumac> findAllById(Iterable<Integer> ids) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override //Prosledimo MBG i nadje ga pream MBGu
	public Glumac findById(Integer id) throws SQLException {
		String upit = "select datumr,dodatak,pozoriste_idpoz,imeg,mbg,plata,status from glumac where mbg = ? ";
		Glumac glumac = new Glumac();
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(upit);){
		
			preparedStatement.setInt(1, id);
			
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.isBeforeFirst()) {
					resultSet.next();
					glumac = new Glumac(resultSet.getInt(5), resultSet.getString(4), resultSet.getDate(1),
							resultSet.getBoolean(7), resultSet.getInt(6), resultSet.getInt(2), resultSet.getInt(3));
					return glumac;
				}
			}
		}
		return glumac;
		
	}

	@Override
	public void save(Glumac entity) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveAll(Iterable<Glumac> entities) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void uvecanjeUmanjenjePlatePremaIDu(Integer id, Double procenatUvecanjaUmanjenja) throws SQLException {
		String upit = "update glumac set plata = (select plata*? from glumac where mbg =?) where mbg =?";
		

		double procenatUvecanja = (1.0 + procenatUvecanjaUmanjenja/100);
		double procenatUmanjenja =  (1.0 -(Math.abs(procenatUvecanjaUmanjenja)/100));

		System.out.println(procenatUvecanja);
		System.out.println(procenatUmanjenja);
		
		try(Connection connection = ConnectionUtil_HikariCP.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(upit);){

			connection.setAutoCommit(false);
			
			if(procenatUvecanjaUmanjenja > 0) {
				preparedStatement.setDouble(1, procenatUvecanja);
			}else if(procenatUvecanjaUmanjenja < 0) {
				preparedStatement.setDouble(1, procenatUmanjenja);
			}
			
			preparedStatement.setInt(2, id);
			preparedStatement.setInt(3, id);
		
			preparedStatement.executeUpdate();
			
			connection.commit();
		}
	
	}
}
