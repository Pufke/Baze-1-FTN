package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Predstava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;
import rs.ac.uns.ftn.jdbc.pozoriste.dto.PrikazivanjeDTO;

public interface PrikazivanjeDAO extends CRUDDao<Prikazivanje, Integer> {
	//Metoda kojoj se prosledjuje Prikazivanje a ona vraca PrikazivanjeDTO
	public  PrikazivanjeDTO informacijeOprikazivanju(Predstava entity) throws SQLException;
}
