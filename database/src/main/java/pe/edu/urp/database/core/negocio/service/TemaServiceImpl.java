package pe.edu.urp.database.core.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.urp.database.core.interfaces.TemaDao;
import pe.edu.urp.database.core.interfaces.TemaService;
import pe.edu.urp.database.core.negocio.bean.Tema;
import pe.edu.urp.database.core.util.AppException;

@Service
public class TemaServiceImpl implements TemaService {

	@Autowired
	private TemaDao temaDao;

	@Override
	public List<Tema> getListaTemas() throws AppException {
		return temaDao.getListaTemas();
	}

	@Override
	public String createTema(Tema tema) throws AppException {
		return temaDao.createTema(tema);
	}

	@Override
	public Tema getTemaById(Integer id) throws AppException {
		return temaDao.getTemaById(id);
	}

	@Override
	public String updateTema(Tema tema) throws AppException {
		return temaDao.updateTema(tema);
	}

	@Override
	public String deleteTema(Integer id) throws AppException {
		return temaDao.deleteTema(id);
	}

}
