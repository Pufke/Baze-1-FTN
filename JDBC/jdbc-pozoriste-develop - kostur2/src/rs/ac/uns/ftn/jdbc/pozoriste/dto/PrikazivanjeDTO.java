package rs.ac.uns.ftn.jdbc.pozoriste.dto;

public class PrikazivanjeDTO {

	
	int ukupanBrojGledalaca;
	double prosecanBrojGledalaca;
	double brojPrikazivanja;
	
	public PrikazivanjeDTO() {}
	
	public PrikazivanjeDTO(int ukupanBrojGledalaca, double prosecanBrojGledalaca, double brojPrikazivanja) {
		this.ukupanBrojGledalaca = ukupanBrojGledalaca;
		this.prosecanBrojGledalaca = prosecanBrojGledalaca;
		this.brojPrikazivanja = brojPrikazivanja;
	}
	
	public int getUkupanBrojGledalaca() {
		return ukupanBrojGledalaca;
	}
	public void setUkupanBrojGledalaca(int ukupanBrojGledalaca) {
		this.ukupanBrojGledalaca = ukupanBrojGledalaca;
	}
	public double getProsecanBrojGledalaca() {
		return prosecanBrojGledalaca;
	}
	public void setProsecanBrojGledalaca(double prosecanBrojGledalaca) {
		this.prosecanBrojGledalaca = prosecanBrojGledalaca;
	}
	public double getBrojPrikazivanja() {
		return brojPrikazivanja;
	}
	public void setBrojPrikazivanja(int brojPrikazivanja) {
		this.brojPrikazivanja = brojPrikazivanja;
	}
}
