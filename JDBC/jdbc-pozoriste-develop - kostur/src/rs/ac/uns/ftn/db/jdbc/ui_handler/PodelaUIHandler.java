package rs.ac.uns.ftn.db.jdbc.ui_handler;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.PodelaDAO;
import rs.ac.uns.ftn.db.jdbc.pozoriste.dao.impl.PodelaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.pozoriste.model.Podela;

public class PodelaUIHandler {

	private static final PodelaDAO podelaDAO= new PodelaDAOImpl();
	
	public void handlePodelaMenu() throws SQLException, ParseException {
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

	private void showAll() throws SQLException {
		Iterable<Podela> listaPodela = podelaDAO.findAll();
		System.out.println(Podela.getFormattedHeader());
		for(Podela podela: listaPodela) {
			System.out.println(podela);
		}

	}

	private void showById() throws SQLException {
		Podela podela =  new Podela();
		System.out.println("ID: ");
		int id = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println(Podela.getFormattedHeader());
		
		podela = podelaDAO.findById(id);
		
		System.out.println(podela);

	}

	private void handleSingleInsert() throws ParseException, SQLException {
		System.out.println("IDPODELE: ");
		int idpod = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println("HONORAR: ");
		double honorar = Double.parseDouble(MainUIHandler.sc.nextLine());
		
		System.out.println("DATUMP: ");
		String s1 = MainUIHandler.sc.nextLine();
		Date datump = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(s1);
		
		System.out.println("DATUMD: ");
		String s2 = MainUIHandler.sc.nextLine();
		Date datumd = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(s2);
		
		System.out.println("MBG: ");
		int mbg = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		System.out.println("IDULOGE: ");
		int iduloge = Integer.parseInt(MainUIHandler.sc.nextLine());
		
		
		podelaDAO.save(new Podela(honorar, datumd, datump, iduloge, idpod, mbg));
		
	}

	private void handleUpdate() {

	}

	private void handleDelete() {

	}

	private void handleMultipleInserts() {
	

	}

}

