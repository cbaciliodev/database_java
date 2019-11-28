package pe.edu.urp.database.core.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.urp.database.core.interfaces.AsignadoDao;
import pe.edu.urp.database.core.interfaces.AsignadoService;
import pe.edu.urp.database.core.negocio.bean.Asignado;
import pe.edu.urp.database.core.util.AppException;

@Service
public class AsignadoServiceImpl implements AsignadoService {

	@Autowired
	private AsignadoDao asignadoDao;

	@Override
	public List<Asignado> getListaAsignacion() throws AppException {
		return asignadoDao.getListaAsignacion();
	}

	@Override
	public String createAsignacion(Asignado asignado) throws AppException {
		return asignadoDao.createAsignacion(asignado);
	}

	@Override
	public String deleteAsignacion(Integer idt, Integer idd) throws AppException {
		return asignadoDao.deleteAsignacion(idt, idd);
	}

}
