package pe.edu.urp.database.core.accesodatos.repositorio;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.urp.database.core.interfaces.AsignadoDao;
import pe.edu.urp.database.core.negocio.bean.Asignado;
import pe.edu.urp.database.core.negocio.bean.Docente;
import pe.edu.urp.database.core.negocio.bean.Tema;
import pe.edu.urp.database.core.util.AppException;
import pe.edu.urp.database.core.util.Conexion;

@Repository
public class AsignadoDaoImpl implements AsignadoDao {

	Conexion conexion = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cstmt = null;
	String message = null;

	@Override
	public List<Asignado> getListaAsignacion() throws AppException {
		List<Asignado> listaAsignado = new ArrayList<>();

		System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjj");

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_LISTAR_TEMAS_DOCENTES}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			rs = cstmt.executeQuery();
			conexion.getConexion().commit();

			while (rs.next()) {
				System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjj");

				// AD.FK_ICOD_TEMA,
				// AD.FK_ICOD_DOCENTE,
				// AD.DFEC_ASIGNADO,
				// T.FK_ICOD_ACTIVIDAD,
				// T.VDESC_TEMA,
				// T.CEST_TEMA,
				// T.FK_ICOD_ACTIVIDAD,
				// D.ICOD_DOCENTE,
				// D.VNOMBRE_DOCENTE,
				// D.VAPATERNO_DOCENTE,
				// D.VAMATERNO_DOCENTE,
				// D.FK_ICOD_CARGO

				Asignado asignado = new Asignado();
				asignado.setFK_ICOD_TEMA(rs.getInt(1));
				asignado.setFK_ICOD_DOCENTE(rs.getInt(2));
				asignado.setDFEC_ASIGNADO(rs.getDate(3));
				asignado.setTema(new Tema(rs.getInt(4), rs.getString(5), rs.getString(6)));
				asignado.setDocente(new Docente(rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11)));

				listaAsignado.add(asignado);
				asignado = null;

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

		return listaAsignado;
	}

	@Override
	public String createAsignacion(Asignado asignado) throws AppException {

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_INSERTAR_ASIGNAR_TEMA_DOCENTE(?,?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("FK_ICOD_TEMA", asignado.getFK_ICOD_TEMA());
			cstmt.setInt("FK_ICOD_DOCENTE", asignado.getFK_ICOD_DOCENTE());
			cstmt.setDate("DFEC_ASIGNADO", asignado.getDFEC_ASIGNADO());

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
	public String deleteAsignacion(Integer idt, Integer idd) throws AppException {

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_DELETE_ASIGNACION_TEMA_DOCENTE(?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			cstmt.setInt("FK_ICOD_TEMA", idt);
			cstmt.setInt("FK_ICOD_DOCENTE", idd);
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
