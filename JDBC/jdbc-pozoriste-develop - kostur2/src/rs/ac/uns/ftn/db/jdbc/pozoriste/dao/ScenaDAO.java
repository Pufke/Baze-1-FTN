package rs.ac.uns.ftn.db.jdbc.pozoriste.dao;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;
<<<<<<< HEAD
import rs.ac.uns.ftn.jdbc.pozoriste.dto.PredstavaDTO;
=======
>>>>>>> 616c5d691c4db64189476a1b699fd2758665a413

public interface ScenaDAO extends CRUDDao<Scena, Integer> {

		public List<Scena> pronadjiScenePremaPozoristu(Integer idPozorista) throws SQLException;
<<<<<<< HEAD
		
		//Prikazuje nazive scena u intervalu plus/minus 20 od broja sedišta koje ima scena Scena Joakim	Vujic
		
		public List<Scena> pronadjiScenePremaPozoristuJoakimVujicKragujevac() throws SQLException;
		
		//Za predstave koje se prikazuju na tim scenama izračunati ukupan broj gledalaca
		//prosledimo scenu racunamo ukupan broj gledalaca
		public List<PredstavaDTO> zaPredstaveKojeSePrikazujuNaScenamaUkupanBrojGledalaca(Scena entity) throws SQLException;
=======
>>>>>>> 616c5d691c4db64189476a1b699fd2758665a413
}
