package rs.ac.uns.ftn.db.jdbc.pozoriste.main;

import java.sql.SQLException;

import rs.ac.uns.ftn.db.jdbc.ui_handler.MainUIHandler;

public class ApplicationPozoriste {

	public static void main(String[] args) throws SQLException {

		// set application log level
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "WARN");

		MainUIHandler mainUIHandler = new MainUIHandler();
		mainUIHandler.handleMainMenu();

	}

}
