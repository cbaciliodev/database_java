package pe.edu.urp.database.core.interfaces;

import java.util.List;

import pe.edu.urp.database.core.negocio.bean.Cargo;
import pe.edu.urp.database.core.util.AppException;

public interface CargoDao {
	
	public List<Cargo> getListaCargos() throws AppException;

}
