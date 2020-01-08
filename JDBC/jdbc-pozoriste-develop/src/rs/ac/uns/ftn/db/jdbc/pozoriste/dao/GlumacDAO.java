package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Glumac;

public interface GlumacDAO extends CRUDDao<Glumac, Integer> {
//Fja koja kao parametar prima id klijenta i procenat uvecanja ili umanjenja plate
		public void uvecanjeUmanjenjePlatePremaIDu(Integer id, Double procenatUvecanjaUmanjenja) throws SQLException;
	
}
