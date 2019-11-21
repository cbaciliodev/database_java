package pe.edu.urp.database.core.accesodatos.repositorio;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.urp.database.core.interfaces.ActividadDao;
import pe.edu.urp.database.core.negocio.bean.Actividad;
import pe.edu.urp.database.core.util.AppException;
import pe.edu.urp.database.core.util.Conexion;

@Repository
public class ActividadDaoImpl implements ActividadDao {

	Conexion conexion = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cstmt = null;
	String message = null;

	@Override
	public List<Actividad> getListaActividades() throws AppException {

		List<Actividad> listaActividad = new ArrayList<>();

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_LISTAR_ACTIVIDADES}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			rs = cstmt.executeQuery();
			conexion.getConexion().commit();

			while (rs.next()) {
				Actividad actividad = new Actividad();
				actividad.setICOD_ACTIVIDAD(rs.getInt(1));
				actividad.setVDESC_ACTIVIDAD(rs.getString(2));
				actividad.setDFEC_ACTIVIDAD(rs.getDate(3));
				listaActividad.add(actividad);
				actividad = null;

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

		return listaActividad;
	}

	@Override
	public String createActividad(Actividad actividad) throws AppException {
		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_INSERTAR_ACTIVIDAD(?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setString("VDESC_ACTIVIDAD", actividad.getVDESC_ACTIVIDAD());
			cstmt.setDate("DFEC_ACTIVIDAD", actividad.getDFEC_ACTIVIDAD());
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
	public Actividad getActividadById(Integer id) throws AppException {

		Actividad actividad = null;

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_OBTEBER_ACTIVIDAD_ID(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_ACTIVIDAD", id);
			rs = cstmt.executeQuery();

			while (rs.next()) {

				actividad = new Actividad();
				actividad.setICOD_ACTIVIDAD(rs.getInt(1));
				actividad.setVDESC_ACTIVIDAD(rs.getString(2));
				actividad.setDFEC_ACTIVIDAD(rs.getDate(3));
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

		return actividad;
	}

	@Override
	public String updateActividad(Actividad actividad) throws AppException {

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_UPDATE_ACTIVIDAD(?,?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);

			cstmt.setInt("ICOD_ACTIVIDAD", actividad.getICOD_ACTIVIDAD());
			cstmt.setString("VDESC_ACTIVIDAD", actividad.getVDESC_ACTIVIDAD());
			cstmt.setDate("DFEC_ACTIVIDAD", actividad.getDFEC_ACTIVIDAD());

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
	public String deleteActividad(Integer id) throws AppException {
		
		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_DELETE_ACTIVIDAD(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_ACTIVIDAD", id);
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
