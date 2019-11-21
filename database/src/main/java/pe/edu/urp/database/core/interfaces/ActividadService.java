package pe.edu.urp.database.core.interfaces;

import java.util.List;

import pe.edu.urp.database.core.negocio.bean.Actividad;
import pe.edu.urp.database.core.util.AppException;

public interface ActividadService {

	public List<Actividad> getListaActividades() throws AppException;

	public String createActividad(Actividad actividad) throws AppException;

	public Actividad getActividadById(Integer id) throws AppException;

	public String updateActividad(Actividad actividad) throws AppException;

	public String deleteActividad(Integer id) throws AppException;
}
