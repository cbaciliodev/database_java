package pe.edu.urp.database.integracion.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.urp.database.core.interfaces.CargoService;
import pe.edu.urp.database.core.negocio.bean.Cargo;
import pe.edu.urp.database.core.util.AppException;

@RestController
public class CargoController {

	@Autowired
	private CargoService cargoService;
	
	@RequestMapping(value = "cargo", method = RequestMethod.GET)
	public List<Cargo> getListaCargo(HttpServletResponse response, HttpServletRequest request){
		
		List<Cargo> lista = new ArrayList<Cargo>();
		
		try {
			
			lista = cargoService.getListaCargos();
			if (lista == null || lista.isEmpty()) {
			return null;	
			}
			
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
}
