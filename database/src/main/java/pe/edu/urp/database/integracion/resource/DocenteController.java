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

import pe.edu.urp.database.core.interfaces.DocenteService;
import pe.edu.urp.database.core.negocio.bean.Docente;
import pe.edu.urp.database.core.util.AppException;

@CrossOrigin(origins = { "http://192.168.42.191:4200", "*" })
@RestController
public class DocenteController {

	@Autowired
	private DocenteService docenteService;

	@RequestMapping(value = "/docente", method = RequestMethod.GET)
	public List<Docente> getListaDocente(HttpServletResponse response, HttpServletRequest request) {

		List<Docente> lista = new ArrayList<Docente>();

		try {

			lista = docenteService.getListaDocente();
			if (lista == null || lista.isEmpty()) {
				return null;
			}

		} catch (AppException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@RequestMapping(value = "/docente", method = RequestMethod.POST)
	public ResponseEntity<?> createDocente(@RequestBody Docente docente, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();
		String newDocente = "";

		System.out.println(docente.getVAMATERNO_DOCENTE());
		System.out.println(docente.getVAPATERNO_DOCENTE());
		System.out.println(docente.getVNOMBRE_DOCENTE());
		System.out.println(docente.getCargo().getICOD_CARGO());
		
		
		try {
			newDocente = docenteService.createDocente(docente);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		objetResponse.put("mensaje", newDocente);
		objetResponse.put("docente", docente);

		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/docente/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getDocenteById(@PathVariable Integer id, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();

		Docente docente = null;

		try {

			docente = docenteService.getDocenteById(id);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (docente == null) {

			objetResponse.put("mensaje", " EL codigo: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Docente>(docente, HttpStatus.OK);
	}

	@RequestMapping(value = "/docente/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDocente(@RequestBody Docente docente, @PathVariable Integer id,
			HttpServletResponse response, HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();
		String newDocente = null;
		Docente objDocente = null;

		try {

			objDocente = docenteService.getDocenteById(id);

			if (objDocente == null) {
				objetResponse.put("mensaje",
						" El Docente con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
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
			docente.setICOD_DOCENTE(id);
			newDocente = docenteService.updateDocente(docente);
		} catch (AppException e) {
			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		objetResponse.put("mensaje", newDocente);
		objetResponse.put("Docente", docente);
		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/docente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteDocenteById(@PathVariable Integer id, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();

		Docente docente = null;
		String newDocente = "";
		try {

			docente = docenteService.getDocenteById(id);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (docente == null) {

			objetResponse.put("mensaje", " EL codigo: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.NOT_FOUND);
		} else {

			try {
				newDocente = docenteService.deleteDocente(id);
			} catch (AppException e) {
				e.printStackTrace();
			}
			objetResponse.put("mensaje", newDocente);
			objetResponse.put("Docente", docente);

		}

		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.OK);

	}
}