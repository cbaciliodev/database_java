package pe.edu.urp.database.core.interfaces;

import java.util.List;

import pe.edu.urp.database.core.negocio.bean.Reunion;
import pe.edu.urp.database.core.util.AppException;

public interface ReunionDao {

	public List<Reunion> getListaReuniones() throws AppException;
	
	public String createReunion(Reunion reunion) throws AppException;
}
