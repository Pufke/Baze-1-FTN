package rs.ac.uns.ftn.db.jdbc.pozoriste.service;

import java.sql.SQLException;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.ScenaDAO;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PozoristeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PredstavaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PozoristeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PredstavaDAOImpl;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.ScenaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PredstavaDTO;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;

import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Uloga;

public class ComplexFunctionalityservice {

	/*
	 * private static final PrikazivanjeDAO prikazivanjeDAO = new
	 * PrikazivanjeDAOImpl();
	 */
	private static final PozoristeDAO pozoristeDAO = new PozoristeDAOImpl();
	private static final ScenaDAO scenaDAO = new ScenaDAOImpl();
	private static final PredstavaDAO predstavaDAO = new PredstavaDAOImpl();
	//Prvi zadatak 
	public void showSceneForTheatre() {
		System.out.println(Pozoriste.getFormattedHeader());
		
		try{
			for(Pozoriste pozoriste : pozoristeDAO.findAll()) {
				System.out.println(pozoriste);
				System.out.println("\t\t----------------------- SCENE -----------------------");
				
				if(scenaDAO.findSceneByTheatre(pozoriste.getId()).size() != 0) {
					System.out.println("\t\t" + Scena.getFormattedHeader());
					for (Scena scena : scenaDAO.findSceneByTheatre(pozoriste.getId())) {
						System.out.println("\t\t" + scena);
					}
				} else {
					System.out.println("\t\tNEMA SCENE");
				}
				System.out.println();
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	//ZADATAK
	/*
	 * Prikazati id nazive i prosecan broj gledalaca predstava koje imaju najveci
	 * prosecan broj gledalaca Za te predstave prikazati listu uloga Pored toga
	 * prikazati koliko ukupno ima mu�kih uloga i koliko ukupno ima �enskih uloga
	 */
	public void prikaziNajveciProsecanBrGledalaca() {

		try {
			for(PredstavaDTO p : predstavaDAO.najveciProsecanBrGledalaca()) {
				int muskeUlogeCount = 0;//za svaku predstavu moramo da resetujemo brojace 
				int zenskeUlogeCount = 0;
				System.out.println("IDPRED \t NAZIV\t PROSECNO_TRAJANJE");
				System.out.println(p.toString());
				//sad treba da odstampamo uloge za tu predstavu 
				System.out.println("IMEULOGE \t POL \t VRSTAULOGE");
				List<Uloga> listaUloga = predstavaDAO.listaUlogaZaPredstaveSaNajvecimProsecnimBrGledalaca(p);
				for(Uloga u: listaUloga) {
					System.out.println(u.getImeulo() +"\t" + u.isPol() + "\t" + u.getVrstaulo());
					if(u.isPol().equals("m")) {
						muskeUlogeCount++;
					}else { 
						zenskeUlogeCount++;
					}
				}
				//prikazati koliko ukupno ima mu�kih uloga i koliko ukupno ima �enskih uloga
				System.out.println("Muskih uloga u ovoj predstavi imamo " + muskeUlogeCount);
				System.out.println("Zenskih uloga u ovoj predstavi imamo " + zenskeUlogeCount);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	/*	 
	 * // Drugi zadatak public void showReportingForShowingShows() {
	 * System.out.println(Predstava.getFormattedHeader()); try { HashMap<Integer,
	 * PrikazivanjeDTO> reultMap = prikazivanjeDAO.findSumAvgCountForShowingShow();
	 * 
	 * for(Integer predstavaId : prikazivanjeDAO.findSumAvgCountForShowingShow()) {
	 * 
	 * }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } }
	 */
}