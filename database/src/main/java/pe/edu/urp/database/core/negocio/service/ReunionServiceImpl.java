package pe.edu.urp.database.core.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.urp.database.core.interfaces.ReunionDao;
import pe.edu.urp.database.core.interfaces.ReunionService;
import pe.edu.urp.database.core.negocio.bean.Reunion;
import pe.edu.urp.database.core.util.AppException;

@Service
public class ReunionServiceImpl implements ReunionService {

	@Autowired
	ReunionDao reunionDao;

	@Override
	public List<Reunion> getListaReuniones() throws AppException {
		return reunionDao.getListaReuniones();
	}

	@Override
	public String createReunion(Reunion reunion) throws AppException {
		return reunionDao.createReunion(reunion);
	}

}
