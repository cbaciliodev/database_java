package pe.edu.urp.database.core.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.urp.database.core.interfaces.DocenteDao;
import pe.edu.urp.database.core.interfaces.DocenteService;
import pe.edu.urp.database.core.negocio.bean.Docente;
import pe.edu.urp.database.core.util.AppException;

@Service
public class DocenteServiceImpl implements DocenteService{

	@Autowired
	private DocenteDao docenteDao;
	@Override
	public List<Docente> getListaDocente() throws AppException {
 		return docenteDao.getListaDocentes();
	}

	@Override
	public String createDocente(Docente docente) throws AppException {
		return docenteDao.createDocente(docente);
	}

	@Override
	public Docente getDocenteById(Integer id) throws AppException {
		return docenteDao.getDocenteById(id);
	}

	@Override
	public String updateDocente(Docente docente) throws AppException {
		return docenteDao.updateDocente(docente);
	}

	@Override
	public String deleteDocente(Integer id) throws AppException {
		return docenteDao.deleteDocente(id);
	}

}
