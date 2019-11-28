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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.urp.database.core.interfaces.ReunionService;
import pe.edu.urp.database.core.negocio.bean.Reunion;
import pe.edu.urp.database.core.negocio.bean.Tema;
import pe.edu.urp.database.core.util.AppException;

@CrossOrigin(origins = { "http://192.168.42.191:4200", "*" })
@RestController
public class ReunionController {

	@Autowired
	private ReunionService reunionService;

	@RequestMapping(value = "/reunion", method = RequestMethod.GET)
	public List<Reunion> getListaTema(HttpServletResponse response, HttpServletRequest request) {

		List<Reunion> lista = new ArrayList<>();

		try {

			lista = reunionService.getListaReuniones();
			if (lista == null || lista.isEmpty()) {
				return null;
			}

		} catch (AppException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@RequestMapping(value = "/reunion", method = RequestMethod.POST)
	public ResponseEntity<?> createtema(@RequestBody Reunion reunion, HttpServletResponse response,
			HttpServletRequest request) {

		System.out.println(reunion.getDHENT_REUNION());
		System.out.println(reunion.getDHSAL_REUNION());

		Map<String, Object> objetResponse = new HashMap<String, Object>();
		String newReunion = "";

		try {
			newReunion = reunionService.createReunion((reunion));

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la registar en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		objetResponse.put("mensaje", newReunion);
		objetResponse.put("tema", reunion);

		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.CREATED);
	}
}
