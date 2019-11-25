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

import pe.edu.urp.database.core.interfaces.ActividadService;
import pe.edu.urp.database.core.negocio.bean.Actividad;
import pe.edu.urp.database.core.util.AppException;

@CrossOrigin(origins = { "http://192.168.42.191:4200", "*" })
@RestController
public class ActividadController {

	@Autowired
	private ActividadService actividadService;

	@RequestMapping(value = "actividad", method = RequestMethod.GET)
	public List<Actividad> getListaActividad(HttpServletResponse response, HttpServletRequest request) {

		List<Actividad> lista = new ArrayList<Actividad>();

		try {

			lista = actividadService.getListaActividades();
			if (lista == null || lista.isEmpty()) {
				return null;
			}

		} catch (AppException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@RequestMapping(value = "/actividad", method = RequestMethod.POST)
	public ResponseEntity<?> createActividad(@RequestBody Actividad actividad, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();
		String newActividad = "";

		try {

			newActividad = actividadService.createActividad(actividad);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {

			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		objetResponse.put("mensaje", newActividad);
		objetResponse.put("actividad", actividad);

		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/actividad/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getActividadById(@PathVariable Integer id, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();

		Actividad actividad = null;

		try {

			actividad = actividadService.getActividadById(id);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (actividad == null) {

			objetResponse.put("mensaje", " EL codigo: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Actividad>(actividad, HttpStatus.OK);
	}

	@RequestMapping(value = "/actividad/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateActividad(@RequestBody Actividad actividad, @PathVariable Integer id,
			HttpServletResponse response, HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();
		String newActividad = null;
		Actividad objActividad = null;

		try {

			objActividad = actividadService.getActividadById(id);

			if (objActividad == null) {
				objetResponse.put("mensaje",
						" El cargo con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
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
			newActividad = actividadService.updateActividad(actividad);
		} catch (AppException e) {
			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		objetResponse.put("mensaje", newActividad);
		objetResponse.put("actividad", actividad);
		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/actividad/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteActividadById(@PathVariable Integer id, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();

		Actividad actividad = null;
		String newActividad = "";
		try {

			actividad = actividadService.getActividadById(id);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (actividad == null) {

			objetResponse.put("mensaje", " EL codigo: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.NOT_FOUND);
		} else {

			try {
				newActividad = actividadService.deleteActividad(id);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objetResponse.put("mensaje", newActividad);
			objetResponse.put("actividad", actividad);

		}

		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.OK);

	}

}
