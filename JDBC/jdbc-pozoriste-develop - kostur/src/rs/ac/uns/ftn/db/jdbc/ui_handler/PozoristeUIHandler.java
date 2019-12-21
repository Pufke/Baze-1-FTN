package rs.ac.uns.ftn.db.jdbc.ui_handler;

import java.sql.SQLException;

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
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showById() {
		System.out.println("IDPOZ: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		try {
			Pozoriste pozoriste = pozoristeDao.findById(id);
			
			System.out.println(Pozoriste.getFormattedHeader());
			System.out.println(pozoriste);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void handleSingleInsert() {
		System.out.println("IDPOZ: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());

		System.out.println("Naziv: ");
		String naziv = MainUIHandler.sc.nextLine();

		System.out.println("Adresa: ");
		String adresa = MainUIHandler.sc.nextLine();

		System.out.println("Sajt: ");
		String sajt = MainUIHandler.sc.nextLine();
		
		System.out.println("Mesto: ");
		String mesto = MainUIHandler.sc.nextLine();

		try {
			pozoristeDao.save(new Pozoriste(id, naziv, adresa, sajt,mesto));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void handleUpdate() {

	}

	private void handleDelete() {

	}

	private void handleMultipleInserts() {
	

	}

}
