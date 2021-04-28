package indecopi.gob.pe.dao;

import indecopi.gob.pe.bean.ClsUsuarioBean;
import indecopi.gob.pe.bean.ClsUsuarioIndBean;
import indecopi.gob.pe.bean.ClsUsuarioIndBean;
import indecopi.gob.pe.util.SQLUtils;

import indecopi.gob.pe.utils.ClsResultDAO;
import indecopi.gob.pe.utils.ClsSQLUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class ClsUsuarioDAO implements ClsUsuarioIDAO{
    static Logger logger = Logger.getLogger(ClsUsuarioDAO.class);
    
    private static final String SP_GET_USR_GLOBALES  =
            "{call USR_CONFIR.PKG_GENERAL.SP_GET_USR_GLOBALES(" + ClsSQLUtils.sqlParams(5) + ")}";
    
    public ClsUsuarioDAO() {
        super();
    }
    
    public ClsResultDAO getUsuarioGlobal(ClsUsuarioIndBean objUsuarioInd) {
        logger.info("getUsuarioGlobal()");
        ClsResultDAO response = null;
        ClsConectionDB1 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        ClsUsuarioBean objUsuario=null;
        try {
            conne = new ClsConectionDB1(); 
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_USR_GLOBALES);
            int i = 0;
            stmt.setString(++i, (objUsuarioInd.getNuIdRRHH()+""));
            stmt.setString(++i, objUsuarioInd.getVcUsuario());
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMBER);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();

            rs = (ResultSet) stmt.getObject(i-2); // Resultado
            
            if (rs != null) {
                objUsuario=new ClsUsuarioBean();
                while (rs.next()) {
                    objUsuario.setNuIdRRHH(rs.getInt("NU_ID_RRHH"));
                    objUsuario.setVcNombre(rs.getString("VC_NOMBRE"));
                    objUsuario.setVcArea(rs.getString("VC_AREA"));
                    objUsuario.setVcUsuario(objUsuarioInd.getVcUsuario());
                    objUsuario.setVcEmail(rs.getString("VC_EMAIL"));
                }
            }
            response = new ClsResultDAO();
            response.put("OBJ_USR_GLOBAL", objUsuario);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getString(i-1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
            
        } catch (Throwable e) {
        } finally {
            SQLUtils.closeStatement(stmt);
            SQLUtils.closeConnection(conn);
        }
        return response;
    }

}
