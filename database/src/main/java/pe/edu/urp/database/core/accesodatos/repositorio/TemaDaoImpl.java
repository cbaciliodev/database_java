package pe.edu.urp.database.core.accesodatos.repositorio;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.urp.database.core.interfaces.TemaDao;
import pe.edu.urp.database.core.negocio.bean.Actividad;
import pe.edu.urp.database.core.negocio.bean.Tema;
import pe.edu.urp.database.core.util.AppException;
import pe.edu.urp.database.core.util.Conexion;

@Repository
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

		String query = "{call SP_LISTAR_TEMAS}";

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
				tema.setActividad(new Actividad(rs.getInt(4)));
				;
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

		String query = "{call SP_INSERTAR_TEMA(?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);

			cstmt.setString("VDESC_TEMA", tema.getVDESC_TEMA());
			cstmt.setInt("FK_ICOD_ACTIVIDAD", tema.getActividad().getICOD_ACTIVIDAD());

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

		String query = "{call SP_OBTEBER_TEMA_ID(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_TEMA", id);
			rs = cstmt.executeQuery();

			while (rs.next()) {

				tema = new Tema();
				tema.setICOD_TEMA(rs.getInt(1));
				tema.setVDESC_TEMA(rs.getString(2));
				tema.setCEST_TEMA(rs.getString(3));
				tema.setActividad(new Actividad(rs.getInt(4), rs.getString(5)));
				;

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

		String query = "{call SP_UPDATE_TEMA(?,?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_TEMA", tema.getICOD_TEMA());
			cstmt.setString("VDESC_TEMA", tema.getVDESC_TEMA());
			cstmt.setInt("FK_ICOD_ACTIVIDAD", tema.getActividad().getICOD_ACTIVIDAD());

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

		String query = "{call SP_DELETE_TEMA(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_TEMA", id);
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

	@Override
	public List<Tema> getListaTemasById(Integer id) throws AppException {

		List<Tema> listaTema = new ArrayList<>();

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_OBTEBER_LISTA_TEMA_ID(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_ACTIVIDAD", id);
			rs = cstmt.executeQuery();
			conexion.getConexion().commit();

			while (rs.next()) {
				Tema tema = new Tema();
				tema.setICOD_TEMA(rs.getInt(1));
				tema.setVDESC_TEMA(rs.getString(2));
				tema.setCEST_TEMA(rs.getString(3));
				tema.setActividad(new Actividad(rs.getInt(4),rs.getString(5)));
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
}
