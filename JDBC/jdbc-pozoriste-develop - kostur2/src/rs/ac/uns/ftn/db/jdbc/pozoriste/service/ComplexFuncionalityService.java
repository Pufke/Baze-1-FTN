package rs.ac.uns.ftn.db.jdbc.pozoriste.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PozoristeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.ScenaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PredstaveDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PrikazivanjeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PozoristeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PredstavaDaoImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PrikazivanjeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.ScenaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Predstava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;
<<<<<<< HEAD
import rs.ac.uns.ftn.jdbc.pozoriste.dto.PredstavaDTO;
=======
>>>>>>> 616c5d691c4db64189476a1b699fd2758665a413
import rs.ac.uns.ftn.jdbc.pozoriste.dto.PrikazivanjeDTO;

public class ComplexFuncionalityService {

	private static final PozoristeDAO pozoristeDAO = new PozoristeDAOImpl();
	private static final ScenaDAO scenaDAO = new ScenaDAOImpl();
	private static final PredstaveDAO predstavaDAO = new PredstavaDaoImpl();
	private static final PrikazivanjeDAO prikazivanjeDAO = new PrikazivanjeDAOImpl();
 
	public void prikaziListuScena() throws SQLException {

		for (Pozoriste pozoriste : pozoristeDAO.findAll()) {
			System.out.println("\n");
			System.out.println(Pozoriste.getFormattedHeader());
			System.out.println(pozoristeDAO.findById(pozoriste.getId()));
			List<Scena> listaScena = new ArrayList<Scena>();
			listaScena = scenaDAO.pronadjiScenePremaPozoristu(pozoriste.getId());
			System.out.println("-------------SCENE--------------");
			
			if(listaScena.size() != 0) {
				for(Scena scena: listaScena) {
						System.out.println(" IDSCENE: " + scena.getIdsce() +
							"  Naziv scene: " + scena.getNazivsce() + "   Broj Sedista: " + scena.getBrojsed());
				}
			}else {
				System.out.println("Pozoriste NEMA scene!");
			}
			
			 
		}

	}

	public void predstaveKojeSePrikazuju() throws SQLException {
		List<Predstava> listaPredstava = new ArrayList<Predstava>();
		List<Prikazivanje> listaPrikazivanje = new ArrayList<Prikazivanje>();
		
		listaPredstava = predstavaDAO.predstaveKojeSePrikazuju();
	
		for(Predstava predstava: listaPredstava) {
			System.out.println(Predstava.getFormattedHeader());
			System.out.println(predstava);
			
			listaPrikazivanje = predstavaDAO.prikazivanjaPredstava(predstava);
					
			System.out.println(Prikazivanje.getFormattedHeader());
			for(Prikazivanje prikazivanje: listaPrikazivanje) {
				System.out.println(prikazivanje);				
			}
			PrikazivanjeDTO prikazivanjeDTO = prikazivanjeDAO.informacijeOprikazivanju(predstava);
			System.out.println("-----Ukupan broj gledalaca-------");
			System.out.println(prikazivanjeDTO.getUkupanBrojGledalaca());
			System.out.println("-----Prosecan broj gledalaca-------");
			System.out.println(prikazivanjeDTO.getProsecanBrojGledalaca());
			System.out.println("-----broj prikazivanja-------");
			System.out.println(prikazivanjeDTO.getBrojPrikazivanja());
		}
	}
<<<<<<< HEAD
	
	public void treciKomplexniUpit() throws SQLException {
		System.out.println(Scena.getFormattedHeader());
		List<Scena> listaScena = scenaDAO.pronadjiScenePremaPozoristuJoakimVujicKragujevac();
		List<PredstavaDTO> listaPredstavaDTO = new ArrayList<PredstavaDTO>();
		
		for(Scena scena: listaScena) {
			System.out.println(scena);
	
			listaPredstavaDTO = scenaDAO.zaPredstaveKojeSePrikazujuNaScenamaUkupanBrojGledalaca(scena);	
		
			if(listaPredstavaDTO.size() == 0) {
				System.out.println("NEMA PREDSTAVA!!!");
			}
			for(PredstavaDTO entity: listaPredstavaDTO) {
				System.out.println(entity);
			}
		}
		
		
	}
=======
>>>>>>> 616c5d691c4db64189476a1b699fd2758665a413

}
