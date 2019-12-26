package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeScenaDTO;

public interface PrikazivanjeDAO extends CRUDDao<Prikazivanje, Integer> {
	
	//metoda koja prikazuje sve predstave koje se prikazuju na odredjenoj sceni
	List<PrikazivanjeScenaDTO> findBySceneId(Integer idScene) throws SQLException;
	
	//metoda koja racuna ukupan broj gledalaca, prosecan broj gledalaca i broj prikazivanja za svaku predstavu	
	HashMap<Integer, PrikazivanjeDTO> findSumAvgCountForShowingShow() throws SQLException;
	
}
