package pe.edu.urp.database.core.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.urp.database.core.interfaces.CargoDao;
import pe.edu.urp.database.core.interfaces.CargoService;
import pe.edu.urp.database.core.negocio.bean.Cargo;
import pe.edu.urp.database.core.util.AppException;

@Service
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoDao cargoDao;

	@Override
	public List<Cargo> getListaCargos() throws AppException {
		return cargoDao.getListaCargos();
	}

	@Override
	public String createCargo(Cargo cargo) throws AppException {

		return cargoDao.createCargo(cargo);
	}

	@Override
	public Cargo getCargoById(Integer id) throws AppException {
		
		return cargoDao.getCargoById(id);
	}

	@Override
	public String updateCargo(Cargo cargo) throws AppException {

		return cargoDao.updateCargo(cargo);
	}

	@Override
	public String deleteCargo(Integer id) throws AppException {
		return cargoDao.deleteCargo(id);
	}

}
