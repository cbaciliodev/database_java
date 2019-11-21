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
import pe.edu.urp.database.core.interfaces.CargoService;
import pe.edu.urp.database.core.negocio.bean.Cargo;
import pe.edu.urp.database.core.util.AppException;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@RequestMapping(value = "cargo", method = RequestMethod.GET)
	public List<Cargo> getListaCargo(HttpServletResponse response, HttpServletRequest request) {

		List<Cargo> lista = new ArrayList<Cargo>();

		try {

			lista = cargoService.getListaCargos();
			if (lista == null || lista.isEmpty()) {
				return null;
			}

		} catch (AppException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@RequestMapping(value = "/cargo", method = RequestMethod.POST)
	public ResponseEntity<?> createCargo(@RequestBody Cargo cargo, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();
		String newCargo = "";

		try {

			newCargo = cargoService.createCargo(cargo);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {

			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		objetResponse.put("mensaje", newCargo);
		objetResponse.put("cargo", cargo);

		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/cargo/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCargoById(@PathVariable Integer id, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();

		Cargo cargo = null;

		try {

			cargo = cargoService.getCargoById(id);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cargo == null) {

			objetResponse.put("mensaje", " EL codigo: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cargo>(cargo, HttpStatus.OK);
	}

	@RequestMapping(value = "/cargo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCargo(@RequestBody Cargo cargo, @PathVariable Integer id,
			HttpServletResponse response, HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();
		String newCargo = null;
		Cargo objcargo = null;

		try {

			objcargo = cargoService.getCargoById(id);

			if (objcargo == null) {
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
			cargo.setICOD_CARGO(id);
			newCargo = cargoService.updateCargo(cargo);
		} catch (AppException e) {
			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		objetResponse.put("mensaje", newCargo);
		objetResponse.put("cargo", cargo);
		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/cargo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCargoById(@PathVariable Integer id, HttpServletResponse response,
			HttpServletRequest request) {

		Map<String, Object> objetResponse = new HashMap<String, Object>();

		Cargo cargo = null;
		String newCargo = "";
		try {

			cargo = cargoService.getCargoById(id);

		} catch (DataAccessException e) {

			objetResponse.put("mensaje", "error al realizar el registro a la base de datos.");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (AppException e) {
			objetResponse.put("mensaje", "Error al realizar la busqueda en la base de datos");
			objetResponse.put("error", e.getMessage().concat(": ").concat(e.getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cargo == null) {

			objetResponse.put("mensaje", " EL codigo: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.NOT_FOUND);
		} else {

			try {
				newCargo = cargoService.deleteCargo(id);
			} catch (AppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			objetResponse.put("mensaje", newCargo);
			objetResponse.put("cargo", cargo);

		}

		return new ResponseEntity<Map<String, Object>>(objetResponse, HttpStatus.OK);

	}
}
