package pe.edu.urp.database.core.accesodatos.repositorio;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.urp.database.core.interfaces.DocenteDao;
import pe.edu.urp.database.core.negocio.bean.Cargo;
import pe.edu.urp.database.core.negocio.bean.Docente;
import pe.edu.urp.database.core.util.AppException;
import pe.edu.urp.database.core.util.Conexion;

@Repository
public class DocenteDaoImpl implements DocenteDao {

	Conexion conexion = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cstmt = null;
	String message = null;

	@Override
	public List<Docente> getListaDocentes() throws AppException {

		List<Docente> listaDocente = new ArrayList<>();

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_LISTAR_DOCENTES}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			rs = cstmt.executeQuery();
			conexion.getConexion().commit();

			while (rs.next()) {
				Docente docente = new Docente();
				docente.setICOD_DOCENTE(rs.getInt(1));
				docente.setVNOMBRE_DOCENTE(rs.getString(2));
				docente.setVAPATERNO_DOCENTE(rs.getString(3));
				docente.setVAMATERNO_DOCENTE(rs.getString(4));
				docente.setCargo(new Cargo(rs.getString(5)));
				listaDocente.add(docente);
				docente = null;

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

		return listaDocente;
	}

	@Override
	public String createDocente(Docente docente) throws AppException {

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_INSERTAR_DOCENTES(?,?,?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			
			cstmt.setString("VNOMBRE_DOCENTE", docente.getVNOMBRE_DOCENTE());
			cstmt.setString("VAPATERNO_DOCENTE", docente.getVAPATERNO_DOCENTE());
			cstmt.setString("VAMATERNO_DOCENTE", docente.getVAMATERNO_DOCENTE());
			cstmt.setInt("FK_ICOD_CARGO", docente.getCargo().getICOD_CARGO());
			
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
	public Docente getDocenteById(Integer id) throws AppException {

		Docente docente = null;

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_OBTEBER_DOCENTE_ID(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_DOCENTE", id);
			rs = cstmt.executeQuery();

			while (rs.next()) {

				docente = new Docente();
				docente.setICOD_DOCENTE(rs.getInt(1));
				docente.setVNOMBRE_DOCENTE(rs.getString(2));
				docente.setVAPATERNO_DOCENTE(rs.getString(3));
				docente.setVAMATERNO_DOCENTE(rs.getString(4));
				docente.setCargo(new Cargo(rs.getInt(5),rs.getString(6)));
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

		return docente;
	}

	@Override
	public String updateDocente(Docente docente) throws AppException {

	
		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_UPDATE_DOCENTE(?,?,?,?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_DOCENTE", docente.getICOD_DOCENTE());
			cstmt.setString("VNOMBRE_DOCENTE", docente.getVNOMBRE_DOCENTE());
			cstmt.setString("VAPATERNO_DOCENTE", docente.getVAPATERNO_DOCENTE());
			cstmt.setString("VAMATERNO_DOCENTE", docente.getVAMATERNO_DOCENTE());
			cstmt.setInt("FK_ICOD_CARGO", docente.getCargo().getICOD_CARGO());

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
	public String deleteDocente(Integer id) throws AppException {

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_DELETE_DOCENTE(?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("ICOD_DOCENTE", id);
			int rest = cstmt.executeUpdate();

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
