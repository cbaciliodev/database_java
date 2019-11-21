package pe.edu.urp.database.core.negocio.bean;

public class Cargo {

	private Integer ICOD_CARGO;
	private String VDESC_CARGO;

	public Cargo() {
	}

	public Cargo(Integer iCOD_CARGO, String vDESC_CARGO) {
		super();
		ICOD_CARGO = iCOD_CARGO;
		VDESC_CARGO = vDESC_CARGO;
	}
	
	

	public Cargo(String vDESC_CARGO) {
		super();
		VDESC_CARGO = vDESC_CARGO;
	}

	public Integer getICOD_CARGO() {
		return ICOD_CARGO;
	}

	public void setICOD_CARGO(Integer iCOD_CARGO) {
		ICOD_CARGO = iCOD_CARGO;
	}

	public String getVDESC_CARGO() {
		return VDESC_CARGO;
	}

	public void setVDESC_CARGO(String vDESC_CARGO) {
		VDESC_CARGO = vDESC_CARGO;
	}

}
