package rs.ac.uns.ftn.jdbc.pozoriste.dto;

public class PredstavaDTO {
	
	int idPredstave;
	int ukupanBrojPregleda;
	String nazivPredstave;
	
	public int getIdPredstave() {
		return idPredstave;
	}

	public void setIdPredstave(int idPredstave) {
		this.idPredstave = idPredstave;
	}

	public int getUkupanBrojPregleda() {
		return ukupanBrojPregleda;
	}

	public void setUkupanBrojPregleda(int ukupanBrojPregleda) {
		this.ukupanBrojPregleda = ukupanBrojPregleda;
	}

	public String getNazivPredstave() {
		return nazivPredstave;
	}

	public void setNazivPredstave(String nazivPredstave) {
		this.nazivPredstave = nazivPredstave;
	}

	public PredstavaDTO(int idPredstave, int ukupanBrojPregleda, String nazivPredstave) {
		super();
		this.idPredstave = idPredstave;
		this.ukupanBrojPregleda = ukupanBrojPregleda;
		this.nazivPredstave = nazivPredstave;
	}

	public PredstavaDTO() {}

	@Override
	public String toString() {
		return "PredstavaDTO [idPredstave=" + idPredstave + ", ukupanBrojPregleda=" + ukupanBrojPregleda
				+ ", nazivPredstave=" + nazivPredstave + "]";
	}

	

}
