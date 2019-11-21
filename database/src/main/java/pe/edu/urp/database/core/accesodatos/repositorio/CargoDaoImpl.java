package pe.edu.urp.database.core.accesodatos.repositorio;

import java.sql.CallableStatement;
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
public class CargoDaoImpl implements CargoDao {

	Conexion conexion = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cstmt = null;
	String message = null;

	@Override
	public List<Cargo> getListaCargos() throws AppException {

		List<Cargo> listaCargo = new ArrayList<>();

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_LISTAR_CARGOS}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			rs = cstmt.executeQuery();
			conexion.getConexion().commit();

			while (rs.next()) {
				Cargo cargo = new Cargo();
				cargo.setICOD_CARGO(rs.getInt("ICOD_CARGO"));
				cargo.setVDESC_CARGO(rs.getString("VDESC_CARGO"));
				listaCargo.add(cargo);
				cargo = null;

			}
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		} finally {
			try {
				conexion.closeResources(conexion.getConexion(), rs, null, cstmt, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return listaCargo;
	}

	@Override
	public String createCargo(Cargo cargo) throws AppException {

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_INSERTAR_CARGOS(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setString("VDESC_CARGO", cargo.getVDESC_CARGO());
			int rest = cstmt.executeUpdate();

			if (rest == 1) {
				message = "El registro ha sido creado con éxito.";
			}

			conexion.getConexion().commit();

		} catch (Exception e) {
			throw new AppException(e.getMessage());
		} finally {
			try {
				conexion.closeResources(conexion.getConexion(), rs, null, cstmt, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return message;
	}

	@Override
	public Cargo getCargoById(Integer id) throws AppException {

		Cargo cargo = null;

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_OBTEBER_CARGOS_ID(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_CARGO", id);
			rs = cstmt.executeQuery();

			while (rs.next()) {

				cargo = new Cargo();
				cargo.setICOD_CARGO(rs.getInt(1));
				cargo.setVDESC_CARGO(rs.getString(2));
			}

			conexion.getConexion().commit();

		} catch (Exception e) {
			throw new AppException(e.getMessage());
		} finally {
			try {
				conexion.closeResources(conexion.getConexion(), rs, null, cstmt, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return cargo;
	}

	@Override
	public String updateCargo(Cargo cargo) throws AppException {

		System.out.println(cargo.getICOD_CARGO());

		System.out.println(cargo.getVDESC_CARGO());
		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_UPDATE_CARGOS(?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);

			cstmt.setInt("ICOD_CARGO", cargo.getICOD_CARGO());
			cstmt.setString("VDESC_CARGO", cargo.getVDESC_CARGO());

			int rest = cstmt.executeUpdate();

			System.out.println(rest + " rest");

			if (rest == 1) {
				message = "El registro ha sido actualizado con éxito.";
			}

			conexion.getConexion().commit();

		} catch (Exception e) {
			throw new AppException(e.getMessage());
		} finally {
			try {
				conexion.closeResources(conexion.getConexion(), rs, null, cstmt, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return message;
	}

	@Override
	public String deleteCargo(Integer id) throws AppException {

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_DELETE_CARGO(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_CARGO", id);
			int rest = cstmt.executeUpdate();

			System.out.println(rest + " respuesta");

			if (rest == 1) {
				message = "El registro ha sido eliminado con éxito.";
			} else {
				message = "El registro esta relacionado con la tabla Docente";
			}

			conexion.getConexion().commit();

		} catch (Exception e) {

			message = e.getMessage();

		} finally {
			try {
				conexion.closeResources(conexion.getConexion(), rs, null, cstmt, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return message;
	}

}