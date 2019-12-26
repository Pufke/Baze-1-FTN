package rs.ac.uns.ftn.db.jdbc.ui_handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PozoristeDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PozoristeDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Pozoriste;

public class PozoristeUIHandler {

	private static final PozoristeDAO pozoristeDao = new PozoristeDAOImpl();

	public void handlePozoristeMenu() {
		String answer;
		do {
			System.out.println("\nOdaberite opciju za rad nad pozoristima:");
			System.out.println("1 - Prikaz svih");
			System.out.println("2 - Prikaz po identifikatoru");
			System.out.println("3 - Unos jednog pozorista");
			System.out.println("4 - Unos vise pozorista");
			System.out.println("5 - Izmena po identifikatoru");
			System.out.println("6 - Brisanje po identifikatoru");
			System.out.println("X - Izlazak iz rukovanja pozoristima");

			answer = MainUIHandler.sc.nextLine();

			switch (answer) {
			case "1":
				showAll();
				break;
			case "2":
				showById();
				break;
			case "3":
				handleSingleInsert();
				break;
			case "4":
				handleMultipleInserts();
				break;
			case "5":
				handleUpdate();
				break;
			case "6":
				handleDelete();
				break;

			}

		} while (!answer.equalsIgnoreCase("X"));
	}

	private void showAll() {
		System.out.println(Pozoriste.getFormattedHeader());
		
		try {
			for(Pozoriste pozoriste : pozoristeDao.findAll()) {
				System.out.println(pozoriste);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

	}

	private void showById() {
		System.out.println("IDPOZ: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			Pozoriste pozoriste = pozoristeDao.findById(id);
			System.out.println(pozoriste);
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	private void handleSingleInsert() {
		System.out.println("IDPOZ:");
		int idPoz = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println("NazivPOZ:");
		String NazivPOZ =MainUIHandler.sc.nextLine();
		
		System.out.println("Adresapoz:");
		String AdresaPoz =MainUIHandler.sc.nextLine();
		
		System.out.println("Sajt:");
		String sajt = MainUIHandler.sc.nextLine();
		
		System.out.println("Mesto(int):");
		int mesto = Integer.parseInt(MainUIHandler.sc.nextLine());
	
		
		try {
			Pozoriste pozoriste = new Pozoriste(idPoz, NazivPOZ, AdresaPoz, sajt, mesto);
			pozoristeDao.save(pozoriste);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void handleUpdate() {
		System.out.println("IDPOZ: ");
		int idPoz = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			if(!pozoristeDao.existsById(idPoz)) {
				System.out.println("Uneta vrednost ne postoji");
			}
			
			
			System.out.println("NazivPOZ:");
			String NazivPOZ =MainUIHandler.sc.nextLine();
			
			System.out.println("Adresapoz:");
			String AdresaPoz =MainUIHandler.sc.nextLine();
			
			System.out.println("Sajt:");
			String sajt = MainUIHandler.sc.nextLine();
			
			System.out.println("Mesto(int):");
			int mesto = Integer.parseInt(MainUIHandler.sc.nextLine());
			
			pozoristeDao.save(new Pozoriste(idPoz, NazivPOZ, AdresaPoz, sajt, mesto));
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	private void handleDelete() {
		System.out.println("IDPOZ :");
		int idPoz = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			pozoristeDao.deleteById(idPoz);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void handleMultipleInserts() {
		List<Pozoriste> pozoristeList = new ArrayList<>();
		String answer;
		
		do {
			System.out.println("IDPOZ:");
			int idPoz = Integer.parseInt(MainUIHandler.sc.nextLine());
			
			System.out.println("NazivPOZ:");
			String NazivPOZ =MainUIHandler.sc.nextLine();
			
			System.out.println("Adresapoz:");
			String AdresaPoz =MainUIHandler.sc.nextLine();
			
			System.out.println("Sajt:");
			String sajt = MainUIHandler.sc.nextLine();
			
			System.out.println("Mesto(int):");
			int mesto = Integer.parseInt(MainUIHandler.sc.nextLine());
			
			pozoristeList.add(new Pozoriste(idPoz, NazivPOZ, AdresaPoz, sajt, mesto));
			
			System.out.println("Prekinuti unos? X za potvrdu");
			answer = MainUIHandler.sc.nextLine();
		} while(!answer.equalsIgnoreCase("X"));
		
		try {
			pozoristeDao.saveAll(pozoristeList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
