package pe.edu.urp.database.core.negocio.bean;

import java.sql.Date;

public class Actividad {

	private Integer ICOD_ACTIVIDAD;
	private String VDESC_ACTIVIDAD;
	private Date DFEC_ACTIVIDAD;

	public Actividad() {
		super();
	}
	
	

	public Actividad(String vDESC_ACTIVIDAD) {
		super();
		VDESC_ACTIVIDAD = vDESC_ACTIVIDAD;
	}



	public Actividad(Integer iCOD_ACTIVIDAD) {
		super();
		ICOD_ACTIVIDAD = iCOD_ACTIVIDAD;
	}



	public Actividad(Integer iCOD_ACTIVIDAD, String vDESC_ACTIVIDAD, Date dFEC_ACTIVIDAD) {
		super();
		ICOD_ACTIVIDAD = iCOD_ACTIVIDAD;
		VDESC_ACTIVIDAD = vDESC_ACTIVIDAD;
		DFEC_ACTIVIDAD = dFEC_ACTIVIDAD;
	}

	public Integer getICOD_ACTIVIDAD() {
		return ICOD_ACTIVIDAD;
	}

	public void setICOD_ACTIVIDAD(Integer iCOD_ACTIVIDAD) {
		ICOD_ACTIVIDAD = iCOD_ACTIVIDAD;
	}

	public String getVDESC_ACTIVIDAD() {
		return VDESC_ACTIVIDAD;
	}

	public void setVDESC_ACTIVIDAD(String vDESC_ACTIVIDAD) {
		VDESC_ACTIVIDAD = vDESC_ACTIVIDAD;
	}

	public Date getDFEC_ACTIVIDAD() {
		return DFEC_ACTIVIDAD;
	}

	public void setDFEC_ACTIVIDAD(Date dFEC_ACTIVIDAD) {
		DFEC_ACTIVIDAD = dFEC_ACTIVIDAD;
	}

}
