package pe.edu.urp.database.core.interfaces;

import java.util.List;

import pe.edu.urp.database.core.negocio.bean.Cargo;
import pe.edu.urp.database.core.util.AppException;

public interface CargoService {

	public List<Cargo> getListaCargos() throws AppException;

	public String createCargo(Cargo cargo) throws AppException;

	public Cargo getCargoById(Integer id) throws AppException;

	public String updateCargo(Cargo cargo) throws AppException;
	
	public String deleteCargo(Integer id) throws AppException;

}
