package pe.edu.urp.database.core.negocio.bean;

public class Docente {

	private Integer ICOD_DOCENTE;
	private String VNOMBRE_DOCENTE;
	private String VAPATERNO_DOCENTE;
	private String VAMATERNO_DOCENTE;
	private Cargo cargo;

	public Docente() {
	}

	public Integer getICOD_DOCENTE() {
		return ICOD_DOCENTE;
	}

	public void setICOD_DOCENTE(Integer iCOD_DOCENTE) {
		ICOD_DOCENTE = iCOD_DOCENTE;
	}

	public String getVNOMBRE_DOCENTE() {
		return VNOMBRE_DOCENTE;
	}

	public void setVNOMBRE_DOCENTE(String vNOMBRE_DOCENTE) {
		VNOMBRE_DOCENTE = vNOMBRE_DOCENTE;
	}

	public String getVAPATERNO_DOCENTE() {
		return VAPATERNO_DOCENTE;
	}

	public void setVAPATERNO_DOCENTE(String vAPATERNO_DOCENTE) {
		VAPATERNO_DOCENTE = vAPATERNO_DOCENTE;
	}

	public String getVAMATERNO_DOCENTE() {
		return VAMATERNO_DOCENTE;
	}

	public void setVAMATERNO_DOCENTE(String vAMATERNO_DOCENTE) {
		VAMATERNO_DOCENTE = vAMATERNO_DOCENTE;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}