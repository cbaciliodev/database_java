package pe.edu.urp.database.integracion.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.urp.database.core.interfaces.TemaService;
import pe.edu.urp.database.core.negocio.bean.Tema;
import pe.edu.urp.database.core.util.AppException;

@CrossOrigin(origins = { "http://192.168.42.191:4200", "*" })
@RestController
public class TemaController {

	@Autowired
	private TemaService temaService;

	@RequestMapping(value = "/tema", method = RequestMethod.GET)
	public List<Tema> getListaTema(HttpServletResponse response, HttpServletRequest request) {

		List<Tema> lista = new ArrayList<Tema>();

		try {

			lista = temaService.getListaTemas();
			if (lista == null || lista.isEmpty()) {
				return null;
			}

		} catch (AppException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@RequestMapping(value = "/temas/{id}", method = RequestMethod.GET)
	public List<Tema> getListaTemaById(@PathVariable Integer id, HttpServletResponse response,
			HttpServletRequest request) {

		List<Tema> lista = new ArrayList<Tema>();

		try {

			lista = temaService.getListaTemasById(id);
			if (lista == null || lista.isEmpty()) {
				return null;
			}

		} catch (AppException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@RequestMapping(value = "/tema", method = RequestMethod.POST)
	public ResponseEntity<?> createtema(@RequestBody Tema tema, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();
		String newtema = "";

		try {
			newtema = temaService.createTema(tema);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la registar en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		objetResponse.put("mensaje", newtema);
		objetResponse.put("tema", tema);

		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/tema/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> gettemaById(@PathVariable Integer id, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();

		Tema tema = null;

		try {

			tema = temaService.getTemaById(id);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (tema == null) {

			objetResponse.put("mensaje", " EL codigo: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Tema>(tema, HttpStatus.OK);
	}

	@RequestMapping(value = "/tema/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatetema(@RequestBody Tema tema, @PathVariable Integer id, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();
		String newtema = null;
		Tema objtema = null;

		try {

			objtema = temaService.getTemaById(id);

			if (objtema == null) {
				objetResponse.put("mensaje",
						" El tema con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.NOT_FOUND);
			}

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			e.printStackTrace();
		}

		try {
			tema.setICOD_TEMA(id);
			;
			newtema = temaService.updateTema(tema);
		} catch (AppException e) {
			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		objetResponse.put("mensaje", newtema);
		objetResponse.put("tema", tema);
		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/tema/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletetemaById(@PathVariable Integer id, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();

		Tema tema = null;
		String newtema = "";
		try {

			tema = temaService.getTemaById(id);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (tema == null) {

			objetResponse.put("mensaje", " EL codigo: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.NOT_FOUND);
		} else {

			try {
				newtema = temaService.deleteTema(id);
			} catch (AppException e) {
				e.printStackTrace();
			}
			objetResponse.put("mensaje", newtema);
			objetResponse.put("tema", tema);

		}

		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.OK);

	}
}