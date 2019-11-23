package pe.edu.urp.database.core.interfaces;

import java.util.List;

import pe.edu.urp.database.core.negocio.bean.Tema;
import pe.edu.urp.database.core.util.AppException;

public interface TemaService {
	
	public List<Tema> getListaTemas() throws AppException;

	public String createTema(Tema tema) throws AppException;

	public Tema getTemaById(Integer id) throws AppException;

	public String updateTema(Tema tema) throws AppException;

	public String deleteTema(Integer id) throws AppException;

}
