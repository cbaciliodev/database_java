package pe.edu.urp.database.core.negocio.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Reunion {

	private Integer ICOD_REUNION;

	private Date DFEC_REUNION;
	private String DHENT_REUNION;
	private String DHSAL_REUNION;
	private String VLUGAR_REUNION;
	private String VOBS_REUNION;

	List<Docente> listaDocentes;

	public Reunion() {
		listaDocentes = new ArrayList<Docente>();
	}

	public Integer getICOD_REUNION() {
		return ICOD_REUNION;
	}

	public void setICOD_REUNION(Integer iCOD_REUNION) {
		ICOD_REUNION = iCOD_REUNION;
	}



	public Date getDFEC_REUNION() {
		return DFEC_REUNION;
	}

	public void setDFEC_REUNION(Date dFEC_REUNION) {
		DFEC_REUNION = dFEC_REUNION;
	}

	public List<Docente> getListaDocentes() {
		return listaDocentes;
	}

	public void setListaDocentes(List<Docente> listaDocentes) {
		this.listaDocentes = listaDocentes;
	}

	
	public String getDHENT_REUNION() {
		return DHENT_REUNION;
	}

	public void setDHENT_REUNION(String dHENT_REUNION) {
		DHENT_REUNION = dHENT_REUNION;
	}

	public String getDHSAL_REUNION() {
		return DHSAL_REUNION;
	}

	public void setDHSAL_REUNION(String dHSAL_REUNION) {
		DHSAL_REUNION = dHSAL_REUNION;
	}

	public String getVLUGAR_REUNION() {
		return VLUGAR_REUNION;
	}

	public void setVLUGAR_REUNION(String vLUGAR_REUNION) {
		VLUGAR_REUNION = vLUGAR_REUNION;
	}

	public String getVOBS_REUNION() {
		return VOBS_REUNION;
	}

	public void setVOBS_REUNION(String vOBS_REUNION) {
		VOBS_REUNION = vOBS_REUNION;
	}

}
