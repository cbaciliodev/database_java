package pe.edu.urp.database.core.interfaces;

import java.util.List;
import pe.edu.urp.database.core.negocio.bean.Docente;
import pe.edu.urp.database.core.util.AppException;

public interface DocenteDao {

	public List<Docente> getListaDocentes() throws AppException;

	public String createDocente(Docente docente) throws AppException;

	public Docente getDocenteById(Integer id) throws AppException;

	public String updateDocente(Docente docente) throws AppException;

	public String deleteDocente(Integer id) throws AppException;
	
}
