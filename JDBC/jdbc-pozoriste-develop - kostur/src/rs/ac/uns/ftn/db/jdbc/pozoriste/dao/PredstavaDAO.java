package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PredstavaDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Uloga;

public interface PredstavaDAO extends CRUDDao<Pozoriste, Integer> {
		//zadatak4
		public List<PredstavaDTO> najveciProsecanBrGledalaca() throws SQLException;
		
		//Za te predstave prikazati listu uloga. for petljom prosledimo predstave i stampamo za svaku uloge
		List<Uloga> listaUlogaZaPredstaveSaNajvecimProsecnimBrGledalaca(PredstavaDTO predstava) throws SQLException;
}