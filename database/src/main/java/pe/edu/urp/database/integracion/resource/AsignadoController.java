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

import pe.edu.urp.database.core.interfaces.AsignadoService;
import pe.edu.urp.database.core.negocio.bean.Asignado;
import pe.edu.urp.database.core.util.AppException;

@CrossOrigin(origins = { "http://192.168.42.191:4200", "*" })
@RestController
public class AsignadoController {

	@Autowired
	private AsignadoService asignadoService;

	@RequestMapping(value = "/asignado", method = RequestMethod.GET)
	public List<Asignado> getListaTema(HttpServletResponse response, HttpServletRequest request) {

		List<Asignado> lista = new ArrayList<Asignado>();

		try {

			lista = asignadoService.getListaAsignacion();
			if (lista == null || lista.isEmpty()) {
				return null;
			}

		} catch (AppException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@RequestMapping(value = "/asignado", method = RequestMethod.POST)
	public ResponseEntity<?> createActividad(@RequestBody Asignado asignado, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();
		String newActividad = "";

		try {

			newActividad = asignadoService.createAsignacion(asignado);

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
		objetResponse.put("asignado", asignado);

		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/asignado/tema/{idt}/docente/{idd}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletetemaById(@PathVariable Integer idt, @PathVariable Integer idd, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();

		String newtema = "";
		try {

			newtema = asignadoService.deleteAsignacion(idt, idd);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		objetResponse.put("mensaje", newtema);
		//objetResponse.put("tema", tema);

		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.OK);

	}

}
