package pe.edu.urp.database.core.accesodatos.repositorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.urp.database.core.interfaces.CargoDao;
import pe.edu.urp.database.core.negocio.bean.Cargo;
import pe.edu.urp.database.core.util.AppException;
import pe.edu.urp.database.core.util.Conexion;

@Repository
public class CargoDaoImpl implements CargoDao{

	Conexion conexion = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	@Override
	public List<Cargo> getListaCargos() throws AppException {
		
		List<Cargo> listaCargo = new ArrayList<>();

		try {
			conexion = new Conexion();

		} catch (Exception e) {
			
		}

		String query = "select ICodigo_Cargo, VConcepto_Cargo from db_reunion.dbo.tbl_cargo";

		try {
			conexion.getConexion().setAutoCommit(false);
			pstmt = conexion.getConexion().prepareStatement(query);
			rs = pstmt.executeQuery();
			conexion.getConexion().commit();

			while (rs.next()) {
				Cargo cargo = new Cargo();
				cargo.setICodigo_Cargo(rs.getInt("ICodigo_Cargo"));
				cargo.setVConcepto_Cargo(rs.getString("VConcepto_Cargo"));
				listaCargo.add(cargo);
				cargo = null;

			}
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		} finally {
			try {
				conexion.closeResources(conexion.getConexion(), rs, null, null, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listaCargo;
	}

}
