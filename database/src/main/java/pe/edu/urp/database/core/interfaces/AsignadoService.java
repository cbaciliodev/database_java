package pe.edu.urp.database.core.interfaces;

import java.util.List;

import pe.edu.urp.database.core.negocio.bean.Asignado;
import pe.edu.urp.database.core.util.AppException;

public interface AsignadoService {
	
	public List<Asignado> getListaAsignacion() throws AppException;

	public String createAsignacion(Asignado asignado) throws AppException;
	
	public String deleteAsignacion(Integer idt, Integer idd) throws AppException;

}
