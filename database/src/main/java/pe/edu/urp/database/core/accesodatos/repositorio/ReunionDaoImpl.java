package pe.edu.urp.database.core.accesodatos.repositorio;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.urp.database.core.interfaces.ReunionDao;
import pe.edu.urp.database.core.negocio.bean.Reunion;
import pe.edu.urp.database.core.util.AppException;
import pe.edu.urp.database.core.util.Conexion;

@Repository
public class ReunionDaoImpl implements ReunionDao {

	Conexion conexion = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CallableStatement cstmt = null;
	String message = null;

	@Override
	public List<Reunion> getListaReuniones() throws AppException {

		List<Reunion> listaReunion = new ArrayList<>();

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_LISTAR_REUNIONES}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);
			rs = cstmt.executeQuery();
			conexion.getConexion().commit();

			while (rs.next()) {
				Reunion reunion = new Reunion();
				reunion.setICOD_REUNION(rs.getInt(1));
				reunion.setDFEC_REUNION(rs.getDate(2));
				reunion.setDHENT_REUNION(rs.getString(3));
				reunion.setDHSAL_REUNION(rs.getString(4));
				reunion.setVLUGAR_REUNION(rs.getString(5));
				reunion.setVOBS_REUNION(rs.getString(6));

				listaReunion.add(reunion);
				reunion = null;

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

		return listaReunion;
	}

	@Override
	public String createReunion(Reunion reunion) throws AppException {

		try {
			conexion = new Conexion();

		} catch (Exception e) {

		}

		String query = "{call SP_CREATE_REUNION(?,?,?,?,?)}";

		try {
			conexion.getConexion().setAutoCommit(false);
			cstmt = conexion.getConexion().prepareCall(query);

			cstmt.setDate("DFEC_REUNION", reunion.getDFEC_REUNION());
			cstmt.setString("DHENT_REUNION", reunion.getDHENT_REUNION());
			cstmt.setString("DHSAL_REUNION", reunion.getDHSAL_REUNION());
			cstmt.setString("VLUGAR_REUNION", reunion.getVLUGAR_REUNION());
			cstmt.setString("VOBS_REUNION", reunion.getVOBS_REUNION());

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

}
