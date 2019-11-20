package pe.edu.urp.database.core.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.urp.database.core.interfaces.CargoDao;
import pe.edu.urp.database.core.interfaces.CargoService;
import pe.edu.urp.database.core.negocio.bean.Cargo;
import pe.edu.urp.database.core.util.AppException;

@Service
public class CargoServiceImpl implements CargoService{

	@Autowired
	private CargoDao cargoDao;
	
	@Override
	public List<Cargo> getListaCargos() throws AppException {
		// TODO Auto-generated method stub
		return cargoDao.getListaCargos();
	}

}
