package pe.edu.urp.database.core.negocio.bean;

public class Tema {

	private Integer ICOD_TEMA;
	private String VDESC_TEMA;
	private String CEST_TEMA;
	private Actividad actividad;

	public Tema() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Tema(Integer iCOD_TEMA, String vDESC_TEMA, String cEST_TEMA) {
		super();
		ICOD_TEMA = iCOD_TEMA;
		VDESC_TEMA = vDESC_TEMA;
		CEST_TEMA = cEST_TEMA;
	}



	public Tema(Actividad actividad) {
		super();
		this.actividad = actividad;
	}



	public Integer getICOD_TEMA() {
		return ICOD_TEMA;
	}

	public void setICOD_TEMA(Integer iCOD_TEMA) {
		ICOD_TEMA = iCOD_TEMA;
	}

	public String getVDESC_TEMA() {
		return VDESC_TEMA;
	}

	public void setVDESC_TEMA(String vDESC_TEMA) {
		VDESC_TEMA = vDESC_TEMA;
	}

	public String getCEST_TEMA() {
		return CEST_TEMA;
	}

	public void setCEST_TEMA(String cEST_TEMA) {
		CEST_TEMA = cEST_TEMA;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

}
