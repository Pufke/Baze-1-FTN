package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;

public interface ScenaDAO extends CRUDDao<Scena, Integer> {

		public List<Scena> pronadjiScenePremaPozoristu(Integer idPozorista) throws SQLException;
}
