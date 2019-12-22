package rs.ac.uns.ftn.db.jdbc.pozoriste.service;

import java.sql.SQLException;
import java.util.HashMap;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PrikazivanjeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.ScenaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.CRUDDao;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PozoristeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PozoristeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PrikazivanjeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.ScenaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dto.PrikazivanjeDTO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Predstava;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Prikazivanje;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Scena;

public class ComplexFunctionalityservice {

	/*
	 * private static final PrikazivanjeDAO prikazivanjeDAO = new
	 * PrikazivanjeDAOImpl();
	 */
	private static final PozoristeDAO pozoristeDAO = new PozoristeDAOImpl();
	private static final ScenaDAO scenaDAO = new ScenaDAOImpl();
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
