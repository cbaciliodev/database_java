package pe.edu.urp.database.core.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.urp.database.core.interfaces.ActividadDao;
import pe.edu.urp.database.core.interfaces.ActividadService;
import pe.edu.urp.database.core.negocio.bean.Actividad;
import pe.edu.urp.database.core.util.AppException;

@Service
public class ActividadServiceImpl implements ActividadService {

	@Autowired
	private ActividadDao actividadDao;

	@Override
	public List<Actividad> getListaActividades() throws AppException {
		return actividadDao.getListaActividades();
	}

	@Override
	public String createActividad(Actividad actividad) throws AppException {
		return actividadDao.createActividad(actividad);
	}

	@Override
	public Actividad getActividadById(Integer id) throws AppException {
		return actividadDao.getActividadById(id);
	}

	@Override
	public String updateActividad(Actividad actividad) throws AppException {
		return actividadDao.updateActividad(actividad);
	}

	@Override
	public String deleteActividad(Integer id) throws AppException {
		return actividadDao.deleteActividad(id);
	}

}
