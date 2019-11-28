package pe.edu.urp.database.core.negocio.bean;

import java.sql.Date;

public class Asignado {

	private Integer FK_ICOD_TEMA;
	private Integer FK_ICOD_DOCENTE;
	private Date DFEC_ASIGNADO;
	private Tema tema;
	private Docente docente;

	public Asignado() {
		// TODO Auto-generated constructor stub
	}

	public Integer getFK_ICOD_TEMA() {
		return FK_ICOD_TEMA;
	}

	public void setFK_ICOD_TEMA(Integer fK_ICOD_TEMA) {
		FK_ICOD_TEMA = fK_ICOD_TEMA;
	}

	public Integer getFK_ICOD_DOCENTE() {
		return FK_ICOD_DOCENTE;
	}

	public void setFK_ICOD_DOCENTE(Integer fK_ICOD_DOCENTE) {
		FK_ICOD_DOCENTE = fK_ICOD_DOCENTE;
	}

	public Date getDFEC_ASIGNADO() {
		return DFEC_ASIGNADO;
	}

	public void setDFEC_ASIGNADO(Date dFEC_ASIGNADO) {
		DFEC_ASIGNADO = dFEC_ASIGNADO;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

}
