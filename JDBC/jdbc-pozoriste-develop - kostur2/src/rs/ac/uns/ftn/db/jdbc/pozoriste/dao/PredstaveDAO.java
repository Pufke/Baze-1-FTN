package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Predstava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;

public interface PredstaveDAO extends CRUDDao<Predstava, Integer> {

		//Metoda koja vraca listu predstava koje se prikazuju
		public List<Predstava> predstaveKojeSePrikazuju() throws SQLException;
		
		//Meotda koja izbacuje sva Pirkazivanja za prosledjenu prestavu 
		public List<Prikazivanje> prikazivanjaPredstava(Predstava entity) throws SQLException;
<<<<<<< HEAD
		
=======
>>>>>>> 616c5d691c4db64189476a1b699fd2758665a413
}
