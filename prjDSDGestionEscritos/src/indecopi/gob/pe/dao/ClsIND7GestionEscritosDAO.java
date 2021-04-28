package indecopi.gob.pe.dao;

import indecopi.gob.pe.bean.ClsArchivoBean;
import indecopi.gob.pe.bean.ClsDocumentosBean;
import indecopi.gob.pe.bean.ClsExpeVinculadosBean;
import indecopi.gob.pe.bean.ClsFiltroEscritosBean;
import indecopi.gob.pe.bean.ClsFiltroExpVinBean;
import indecopi.gob.pe.bean.ClsUsuarioBean;
import indecopi.gob.pe.util.ClsUtils;
import indecopi.gob.pe.utils.ClsResultDAO;
import indecopi.gob.pe.utils.ClsSQLUtils;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;

import oracle.jdbc.OracleTypes;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.apache.log4j.Logger;

public class ClsIND7GestionEscritosDAO implements ClsIND7GestionEscritosIDAO, Serializable{
    
    @SuppressWarnings("compatibility:-8731994239088965544")
    private static final long serialVersionUID = 6932969206298646922L;
    
    static Logger logger = Logger.getLogger(ClsIND7GestionEscritosDAO.class);
    
    //USR_MARCAS - INDECOPI_7
    private static final String SP_VINCULAR_DOCUMENTO  =
            "{call PKG_GESTION_ESCRITOS.SP_VINCULAR_DOCUMENTO(" + ClsSQLUtils.sqlParams(19) + ")}";
    private static final String SP_LST_GENERAL  =
            "{call PKG_GESTION_ESCRITOS.SP_LST_GENERAL(" + ClsSQLUtils.sqlParams(4) + ")}";
    
    private static final String SP_LST_EXP_VINCULADOS  =
            "{call PKG_GESTION_ESCRITOS.SP_LST_EXP_VINCULADOS(" + ClsSQLUtils.sqlParams(10) + ")}";
    private static final String SP_GET_DOCUMENTO_X_ID  =
            "{call PKG_GESTION_ESCRITOS.SP_GET_DOCUMENTO_X_ID(" + ClsSQLUtils.sqlParams(4) + ")}";
    
    private static final String SP_GET_FLAG_VINCULADO  =
            "{call PKG_GESTION_ESCRITOS.SP_GET_FLAG_VINCULADO(" + ClsSQLUtils.sqlParams(4) + ")}";
    
    @Override
    public ClsResultDAO doVincular(ClsDocumentosBean objDocumento, ClsUsuarioBean  objUsuarioBean) {
        ClsResultDAO response = null;
        ClsConectionDB1 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        
        ArrayDescriptor arDescriptorStr = null;
        ArrayDescriptor arDescriptorNum = null;
    
        
        try {
            conne = new ClsConectionDB1();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_VINCULAR_DOCUMENTO);
            
            arDescriptorStr = ArrayDescriptor.createDescriptor("STR_ARRAY", conn);
            arDescriptorNum = ArrayDescriptor.createDescriptor("NUM_ARRAY", conn);
            
            String[] varVcRuta = new String[objDocumento.getLstArchivo().size()];
            String[] varVcNomOriginal = new String[objDocumento.getLstArchivo().size()];
            String[] varVcNomFinal = new String[objDocumento.getLstArchivo().size()];
            String[] varVcExtension = new String[objDocumento.getLstArchivo().size()];
            Integer[] varNuIdTipoArchivo = new Integer[objDocumento.getLstArchivo().size()];
            Long[] varNuLong = new Long[objDocumento.getLstArchivo().size()];
            
            for (int j = 0; j < objDocumento.getLstArchivo().size(); j++) {
                varVcRuta[j]           = objDocumento.getLstArchivo().get(j).getVcRutalFinal();
                varVcNomOriginal[j]    = objDocumento.getLstArchivo().get(j).getVcNombreOriginal();
                varVcNomFinal[j]       = objDocumento.getLstArchivo().get(j).getVcNombreFinal();
                varVcExtension[j]      = objDocumento.getLstArchivo().get(j).getVcExtension();
                varNuIdTipoArchivo[j]  = objDocumento.getLstArchivo().get(j).getNuIdTipoArchivo();
                varNuLong[j]           = objDocumento.getLstArchivo().get(j).getNuLong();
            }
            
            ARRAY arVcRuta          = new ARRAY(arDescriptorStr, conn, varVcRuta            );
            ARRAY arVcNomOriginal   = new ARRAY(arDescriptorStr, conn, varVcNomOriginal     );
            ARRAY arVcNomFinal      = new ARRAY(arDescriptorStr, conn, varVcNomFinal        );
            ARRAY arVcExtension     = new ARRAY(arDescriptorStr, conn, varVcExtension       );
            ARRAY arNuIdTipoArchivo = new ARRAY(arDescriptorNum, conn, varNuIdTipoArchivo   );
            ARRAY arNuLong          = new ARRAY(arDescriptorNum, conn, varNuLong            );
            
            int i = 0;

            stmt.setString  (++i, objUsuarioBean.getVcUsuario());
            stmt.setString(++i, ClsUtils.getIpAddress());
            stmt.setString(++i, objDocumento.getVcNroExpediente());
            stmt.setString(++i, objDocumento.getVcDocumento());
            stmt.setString(++i, objDocumento.getVcCorreo());
            stmt.setString(++i, objDocumento.getVcNroTelefonico());
            stmt.setString(++i, objDocumento.getVcObservacionCompleta());
            stmt.setString(++i, objDocumento.getVcCorrelativo());
            stmt.setInt(++i,    objDocumento.getNuIdTipoVinculacion());
            stmt.setDate(++i,   objDocumento.getDtFechaPresentacion()!=null?ClsUtils.convertJavaDateToSqlDate(objDocumento.getDtFechaPresentacion()):null);
            
            stmt.setArray(++i, arVcRuta);
            stmt.setArray(++i, arVcNomOriginal);
            stmt.setArray(++i, arVcNomFinal);
            stmt.setArray(++i, arVcExtension);
            stmt.setArray(++i, arNuIdTipoArchivo);
            stmt.setArray(++i, arNuLong);
            
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();
          
            rs = (ResultSet) stmt.getObject(i - 2);
           
            response = new ClsResultDAO();
            logger.info("Codigo: "+stmt.getString(i-1));
            logger.info("Mensaje: "+stmt.getString(i));
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
          
        } catch (Throwable e) {
            logger.info(e);
            e.printStackTrace();
        } finally {
            conne.f_endConn();
        }
        return response;
    }
    
    @Override
    public ClsResultDAO doLstPrincipales() {
        ClsResultDAO response = null;
        ClsConectionDB1 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        
         Map<String, String> lstTipoVinculacion=null;
        Map<String, String> lstTipoArchivo=null;


        try {
            conne = new ClsConectionDB1();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_GENERAL);
            int i = 0;
            
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();
            
            
            
            rs = (ResultSet) stmt.getObject(i - 3);
            if (rs != null) {
                lstTipoVinculacion= new LinkedHashMap<String, String>();
                    while (rs.next()) {
                        lstTipoVinculacion.put(rs.getString("VC_NOMBRE_CAMPO"), rs.getString("NU_CODIGO_INTERNO"));
                    }
            }
            
            rs = (ResultSet) stmt.getObject(i - 2);
            if (rs != null) {
                lstTipoArchivo= new LinkedHashMap<String, String>();
                    while (rs.next()) {
                        lstTipoArchivo.put(rs.getString("VC_DESCRIPCION"), rs.getString("NU_ID_TIPO_ARCHIVO"));
                    }
            }
            
            response = new ClsResultDAO();
            response.put("LST_TIPO_VINCULACION", lstTipoVinculacion);
            response.put("LST_TIPO_ARCHIVO", lstTipoArchivo);
            
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }
    
    @Override
    public ClsResultDAO doLstExpVinculados(ClsFiltroExpVinBean objFiltro, ClsUsuarioBean  objUsuarioBean) {
        ClsResultDAO response = null;
        ClsConectionDB1 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        
         List<ClsExpeVinculadosBean> lstExpedienteVincualdo=null;;


        try {
            conne = new ClsConectionDB1();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_EXP_VINCULADOS);
            int i = 0;
            
            stmt.setString  (++i, objUsuarioBean.getVcUsuario());
            stmt.setDate    (++i, objFiltro.getDtFechaIni()!=null?ClsUtils.convertJavaDateToSqlDate(objFiltro.getDtFechaIni()):null);
            stmt.setDate    (++i, objFiltro.getDtFechaFin()!=null?ClsUtils.convertJavaDateToSqlDate(objFiltro.getDtFechaFin()):null);
            stmt.setInt     (++i, objFiltro.getNuIdTipoVinculacion());
            stmt.setString  (++i, objFiltro.getVcNroExpediente());
            stmt.setString  (++i, objFiltro.getVcNroDoc());
            stmt.setString  (++i, objFiltro.getVcCorrreo());
            
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();
            
            
            rs = (ResultSet) stmt.getObject(i - 2);
            if (rs != null) {
                lstExpedienteVincualdo= new ArrayList<ClsExpeVinculadosBean>();
                    while (rs.next()) {
                        ClsExpeVinculadosBean objRespuesta=new ClsExpeVinculadosBean();
                        objRespuesta.setNuIdExpVin(rs.getInt("NU_ID_EXPE_VINCULADO"));
                        logger.info(objRespuesta.getNuIdExpVin());
                        objRespuesta.setVcNroExpediente(rs.getString("VC_NRO_EXPEDIENTE"));
                        objRespuesta.setVcTipoVinculacion(rs.getString("VC_TIPO_VINCULACION"));
                        objRespuesta.setVcNroDoc(rs.getString("VC_NRO_DOC"));
                        objRespuesta.setVcCorreo(rs.getString("VC_CORREO"));
                        objRespuesta.setDtFechaPresentacion(rs.getDate("DT_FECHA_PRESENTACION"));
                        objRespuesta.setDtFechaVinculacion(rs.getDate("DT_FECHA_VINCULACION"));
                        lstExpedienteVincualdo.add(objRespuesta);
                    }
            }
            
            response = new ClsResultDAO();
            
            response.put("LST_EXP_VIN", lstExpedienteVincualdo);
            
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }
    

    @Override
    public ClsResultDAO doLstArchivo(Integer nuIdExpVin) {
        ClsResultDAO response = null;
        ClsConectionDB1 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        
        List<ClsArchivoBean> lstArchivos;
        
        try {
            conne = new ClsConectionDB1();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_DOCUMENTO_X_ID);
            int i = 0;

            stmt.setInt        (++i, nuIdExpVin);
            
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();
          
            rs = (ResultSet) stmt.getObject(i - 2);
            lstArchivos = new ArrayList<ClsArchivoBean>();
            if (rs != null) {
                ClsArchivoBean objRespuesta = null;
                    while (rs.next()) {
                        objRespuesta=new ClsArchivoBean();
                        logger.info(rs.getString("VC_NOMBRE_ORIGINAL"));
                        objRespuesta.setVcNombreOriginal(rs.getString("VC_NOMBRE_ORIGINAL"));
                        objRespuesta.setNuLong(rs.getLong("NU_LONGITUD"));
                        objRespuesta.setVcRutalFinal(rs.getString("VC_RUTA_FINAL"));
                        objRespuesta.setVcExtension(rs.getString("VC_EXTENSION"));
                        objRespuesta.setVcRutaAlmacenamiento(rs.getString("VC_RUTA_ALMACENAMIENTO"));
                        objRespuesta.setVcNombreFinal(rs.getString("VC_NOMBRE_FINAL"));
                        objRespuesta.setNuIdTipoArchivo(4);
                        lstArchivos.add(objRespuesta);
                    }
            }
    
            response = new ClsResultDAO();
      
            response.put("LST_ARCHIVO", lstArchivos);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
          
        } catch (Throwable e) {
            logger.info(e);
            e.printStackTrace();
        } finally {
            conne.f_endConn();
        }
        return response;
    }

    @Override
    public ClsResultDAO getFlagVinculado(String vcDocRef) {
        ClsResultDAO response = null;
        ClsConectionDB1 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        
        try {
            conne = new ClsConectionDB1();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_FLAG_VINCULADO);
            int i = 0;

            stmt.setString(++i, vcDocRef);
            
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();
    
            response = new ClsResultDAO();
      
            response.put("FLAG_VINCULADO", stmt.getInt(i - 2));
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
          
        } catch (Throwable e) {
            logger.info(e);
            e.printStackTrace();
        } finally {
            conne.f_endConn();
        }
        return response;
    }


}
