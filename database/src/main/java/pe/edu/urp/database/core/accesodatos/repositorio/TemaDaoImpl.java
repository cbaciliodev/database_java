package pe.edu.urp.database.core.accesodatos.repositorio;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pe.edu.urp.database.core.interfaces.TemaDao;
import pe.edu.urp.database.core.negocio.bean.Actividad;
import pe.edu.urp.database.core.negocio.bean.Tema;
import pe.edu.urp.database.core.util.AppException;
import pe.edu.urp.database.core.util.Conexion;

public class TemaDaoImpl implements TemaDao {

	Conexion conexion = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cstmt = null;
	String message = null;

	@Override
	public List<Tema> getListaTemas() throws AppException {

		List<Tema> listaTema = new ArrayList<>();

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_LISTAR_TemaS}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			rs = cstmt.executeQuery();
			conexion.getConexion().commit();

			while (rs.next()) {
				Tema tema = new Tema();
				tema.setICOD_TEMA(rs.getInt(1));
				tema.setVDESC_TEMA(rs.getString(2));
				tema.setCEST_TEMA(rs.getString(3));
				tema.setActividad(new Actividad(rs.getInt(4)));;
				listaTema.add(tema);
				tema = null;

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

		return listaTema;
	}

	@Override
	public String createTema(Tema tema) throws AppException {

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_INSERTAR_TemaS(?,?,?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			
			cstmt.setString("VNOMBRE_Tema", tema.getVDESC_TEMA());
			cstmt.setInt("FK_ICOD_CARGO", tema.getActividad().getICOD_ACTIVIDAD());
			
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
	public Tema getTemaById(Integer id) throws AppException {

		Tema tema = null;

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_OBTEBER_Tema_ID(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_Tema", id);
			rs = cstmt.executeQuery();

			while (rs.next()) {

				tema = new Tema();
				tema.setICOD_TEMA(rs.getInt(1));
				tema.setVDESC_TEMA(rs.getString(2));
				tema.setCEST_TEMA(rs.getString(3));
				tema.setActividad(new Actividad(rs.getInt(4)));;
			
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

		return tema;
	}

	@Override
	public String updateTema(Tema tema) throws AppException {

	
		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_UPDATE_Tema(?,?,?,?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_Tema", tema.getICOD_TEMA());
			cstmt.setString("VNOMBRE_Tema", tema.getVDESC_TEMA());
			cstmt.setString("VAPATERNO_Tema", tema.getCEST_TEMA());
			cstmt.setInt("VAMATERNO_Tema", tema.getActividad().getICOD_ACTIVIDAD());

			int rest = cstmt.executeUpdate();

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
	public String deleteTema(Integer id) throws AppException {

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_DELETE_Tema(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_Tema", id);
			int rest = cstmt.executeUpdate();

			if (rest == 1) {
				message = "El registro ha sido eliminado con éxito.";
			} else {
				message = "El registro esta relacionado con la tabla Tema";
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
