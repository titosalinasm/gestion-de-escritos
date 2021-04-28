package indecopi.gob.pe.dao;

import indecopi.gob.pe.bean.ClsArchivoBean;
import indecopi.gob.pe.bean.ClsDocumentosBean;
import indecopi.gob.pe.bean.ClsFiltroEscritosBean;
import indecopi.gob.pe.bean.ClsUsuarioBean;
import indecopi.gob.pe.util.ClsUtils;
import indecopi.gob.pe.utils.ClsResultDAO;
import indecopi.gob.pe.utils.ClsSQLUtils;

import java.io.Serializable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

public class ClsLOGIGestionEscritosDAO implements  ClsLOGIGestionEscritosIDAO, Serializable{
    
    @SuppressWarnings("compatibility:1453072990688115914")
    private static final long serialVersionUID = 4567591512041021502L;
    
    static Logger logger = Logger.getLogger(ClsLOGIGestionEscritosDAO.class);
    //USR_SACMDPV - LOGISTICA
    private static final String SP_LST_DOCUMENTOS  =
            "{call PKG_GESTION_ESCRITOS.SP_LST_DOCUMENTOS(" + ClsSQLUtils.sqlParams(17) + ")}";
    private static final String SP_LST_PRINCIPALES  =
            "{call PKG_GESTION_ESCRITOS.SP_LST_PRINCIPALES(" + ClsSQLUtils.sqlParams(7) + ")}";
    private static final String SP_LST_DEPENDENCIA_LOCAL  =
            "{call PKG_GESTION_ESCRITOS.SP_LST_DEPENDENCIA_LOCAL(" + ClsSQLUtils.sqlParams(4) + ")}";
    private static final String SP_GET_DOCUMENTO_X_ID  =
            "{call PKG_GESTION_ESCRITOS.SP_GET_DOCUMENTO_X_ID(" + ClsSQLUtils.sqlParams(6) + ")}";
    
    private static final String SP_LST_DOCUMENTOS_SYNC  =
            "{call PKG_GESTION_ESCRITOS.SP_LST_DOCUMENTOS_SYNC(" + ClsSQLUtils.sqlParams(8) + ")}";
    
    @Override
    public ClsResultDAO doLstDocumentos(ClsFiltroEscritosBean objFiltro) {
        ClsResultDAO response = null;
        ClsConectionDB2 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        
        List<ClsDocumentosBean> lstDocumentos;
        
        try {
            conne = new ClsConectionDB2();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_DOCUMENTOS);
            int i = 0;

            stmt.setInt     (++i, objFiltro.getNuIdPerfil());
            stmt.setInt     (++i, objFiltro.getNuIdRrHh()!=null?objFiltro.getNuIdRrHh():-1);
            stmt.setString  (++i, objFiltro.getVcUsuario());
            stmt.setInt(++i, objFiltro.getNuIdLocal());
            stmt.setInt(++i, objFiltro.getNuIdDependencia()!=null?objFiltro.getNuIdDependencia():-1);
            stmt.setInt(++i, objFiltro.getNuIdTipoDocSgd());
            stmt.setInt(++i, objFiltro.getNuEstadoInterno());
            stmt.setInt(++i, objFiltro.getNuIdTipoDocId());
            stmt.setString(++i, objFiltro.getVcNroDocIdentidad());
            stmt.setString(++i, objFiltro.getVcRemitente());
            stmt.setInt(++i, objFiltro.getNuIdModalidadIngreso());
            stmt.setDate(++i,objFiltro.getDtFechaIni()!=null?ClsUtils.convertJavaDateToSqlDate(objFiltro.getDtFechaIni()):null);
            stmt.setDate(++i, objFiltro.getDtFechaFin()!=null?ClsUtils.convertJavaDateToSqlDate(objFiltro.getDtFechaFin()):null);
            stmt.setString(++i, objFiltro.getVcNroCorrelativo());

            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();
          
            rs = (ResultSet) stmt.getObject(i - 2);
            lstDocumentos = new ArrayList<ClsDocumentosBean>();
            if (rs != null) {
                ClsDocumentosBean objRespuesta = null;
                    while (rs.next()) {
                        objRespuesta=new ClsDocumentosBean();
                        objRespuesta.setVcCorrelativo(              rs.getString("VC_CORRELATIVO"));
                        objRespuesta.setVcRegistro(                 rs.getString("VC_REGISTRO"));
                        objRespuesta.setVcDocumento(                rs.getString("VC_DOCUMENTO"));
                        objRespuesta.setVcRemitente(                rs.getString("VC_REMITENTE"));
                        objRespuesta.setVcDocRepresentado(          rs.getString("VC_DOC_REPRESENTADO"));
                        objRespuesta.setVcRepresentado(             rs.getString("VC_REPRESENTADO"));
                        objRespuesta.setVcSiglaArea(                rs.getString("VC_SIGLA_AREA"));
                        objRespuesta.setVcAreaNombre(               rs.getString("VC_AREA_NOMBRE"));
                        objRespuesta.setVcSedeNombre(               rs.getString("VC_SEDE_NOMBRE"));
                        objRespuesta.setNuIdSede(                   rs.getInt("NU_ID_SEDE"));
                        objRespuesta.setVcObservacionCompleta(      rs.getString("VC_OBSERVACION_COMPLETA"));
                        objRespuesta.setVcObservacionCorta(         rs.getString("VC_OBSERVACION_CORTA"));
                        objRespuesta.setNuEstado(                   rs.getInt("NU_ESTADO"));
                        objRespuesta.setNuEstadoExterno(            rs.getInt("NU_ESTADO_EXTERNO"));
                        objRespuesta.setNuIdRegistro(               rs.getInt("NU_ID_REGISTRO"));
                        objRespuesta.setVcCorreo(                   rs.getString("VC_CORREO"));
                        objRespuesta.setVcEstadoInterno(            rs.getString("VC_ESTADO_INTERNO"));
                        objRespuesta.setVcEstadoExterno(            rs.getString("VC_ESTADO_EXTERNO"));
                        objRespuesta.setVcDestinatarios(            rs.getString("VC_DESTINATARIOS"));
                        objRespuesta.setVcDescTipoDocSgd(           rs.getString("VC_DESC_TIPO_DOC_SGD"));
                        objRespuesta.setVcDescModIng(               rs.getString("VC_DESC_MOD_ING"));
                        objRespuesta.setVcNroExpediente(            rs.getString("VC_NRO_EXPEDIENTE"));
                        objRespuesta.setVcEsRepresentante(          rs.getString("VC_ES_REPRESENTANTE"));
                        objRespuesta.setVcNroTelefonico(            rs.getString("VC_NRO_TELEFONO"));
                        objRespuesta.setDtFechaPresentacion(        rs.getDate("DT_FECHA_PRESENTACION"));
                        lstDocumentos.add(objRespuesta);
                    }
            }
   
            response = new ClsResultDAO();
      
            response.put("LST_DOCUMENTOS", lstDocumentos);
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
    public ClsResultDAO doLstDocumentosSync(ClsFiltroEscritosBean objFiltro) {
        ClsResultDAO response = null;
        ClsConectionDB2 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        
        List<ClsDocumentosBean> lstDocumentos;
        
        try {
            conne = new ClsConectionDB2();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_DOCUMENTOS_SYNC);
            int i = 0;

            stmt.setInt     (++i, objFiltro.getNuIdPerfil());
            stmt.setInt     (++i, objFiltro.getNuIdRrHh()!=null?objFiltro.getNuIdRrHh():-1);
            stmt.setString  (++i, objFiltro.getVcUsuario());
            stmt.setDate(++i,objFiltro.getDtFechaIni()!=null?ClsUtils.convertJavaDateToSqlDate(objFiltro.getDtFechaIni()):null);
            stmt.setDate(++i, objFiltro.getDtFechaFin()!=null?ClsUtils.convertJavaDateToSqlDate(objFiltro.getDtFechaFin()):null);

            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();
          
            rs = (ResultSet) stmt.getObject(i - 2);
            lstDocumentos = new ArrayList<ClsDocumentosBean>();
            if (rs != null) {
                ClsDocumentosBean objRespuesta = null;
                    while (rs.next()) {
                        objRespuesta=new ClsDocumentosBean();
                        objRespuesta.setVcCorrelativo(              rs.getString("VC_CORRELATIVO"));
                        objRespuesta.setVcRegistro(                 rs.getString("VC_REGISTRO"));
                        objRespuesta.setVcDocumento(                rs.getString("VC_DOCUMENTO"));
                        objRespuesta.setVcRemitente(                rs.getString("VC_REMITENTE"));
                        objRespuesta.setVcDocRepresentado(          rs.getString("VC_DOC_REPRESENTADO"));
                        objRespuesta.setVcRepresentado(             rs.getString("VC_REPRESENTADO"));
                        objRespuesta.setVcSiglaArea(                rs.getString("VC_SIGLA_AREA"));
                        objRespuesta.setVcAreaNombre(               rs.getString("VC_AREA_NOMBRE"));
                        objRespuesta.setVcSedeNombre(               rs.getString("VC_SEDE_NOMBRE"));
                        objRespuesta.setNuIdSede(                   rs.getInt("NU_ID_SEDE"));
                        objRespuesta.setVcObservacionCompleta(      rs.getString("VC_OBSERVACION_COMPLETA"));
                        objRespuesta.setVcObservacionCorta(         rs.getString("VC_OBSERVACION_CORTA"));
                        objRespuesta.setNuEstado(                   rs.getInt("NU_ESTADO"));
                        objRespuesta.setNuEstadoExterno(            rs.getInt("NU_ESTADO_EXTERNO"));
                        objRespuesta.setNuIdRegistro(               rs.getInt("NU_ID_REGISTRO"));
                        objRespuesta.setVcCorreo(                   rs.getString("VC_CORREO"));
                        objRespuesta.setVcEstadoInterno(            rs.getString("VC_ESTADO_INTERNO"));
                        objRespuesta.setVcEstadoExterno(            rs.getString("VC_ESTADO_EXTERNO"));
                        objRespuesta.setVcDestinatarios(            rs.getString("VC_DESTINATARIOS"));
                        objRespuesta.setVcDescTipoDocSgd(           rs.getString("VC_DESC_TIPO_DOC_SGD"));
                        objRespuesta.setVcDescModIng(               rs.getString("VC_DESC_MOD_ING"));
                        objRespuesta.setVcNroExpediente(            rs.getString("VC_NRO_EXPEDIENTE"));
                        objRespuesta.setVcEsRepresentante(          rs.getString("VC_ES_REPRESENTANTE"));
                        objRespuesta.setVcNroTelefonico(            rs.getString("VC_NRO_TELEFONO"));
                        objRespuesta.setDtFechaPresentacion(        rs.getDate("DT_FECHA_PRESENTACION"));
                        lstDocumentos.add(objRespuesta);
                    }
            }
    
            response = new ClsResultDAO();
      
            response.put("LST_DOCUMENTOS_SYNC", lstDocumentos);
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
    public ClsResultDAO doLstDocumentosSyncAutomatico(ClsFiltroEscritosBean objFiltro) {
        ClsResultDAO response = null;
        ClsConectionDB2 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        
        List<ClsDocumentosBean> lstDocumentos;
        
        try {
            conne = new ClsConectionDB2();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_DOCUMENTOS_SYNC);
            int i = 0;

            stmt.setInt     (++i, objFiltro.getNuIdPerfil());
            stmt.setInt     (++i, objFiltro.getNuIdRrHh()!=null?objFiltro.getNuIdRrHh():-1);
            stmt.setString  (++i, objFiltro.getVcUsuario());
            stmt.setDate(++i,objFiltro.getDtFechaIni()!=null?ClsUtils.convertJavaDateToSqlDate(new Date()):null);
            stmt.setDate(++i, objFiltro.getDtFechaFin()!=null?ClsUtils.convertJavaDateToSqlDate(new Date()):null);

            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();
          
            rs = (ResultSet) stmt.getObject(i - 2);
            lstDocumentos = new ArrayList<ClsDocumentosBean>();
            if (rs != null) {
                ClsDocumentosBean objRespuesta = null;
                    while (rs.next()) {
                        objRespuesta=new ClsDocumentosBean();
                        objRespuesta.setVcCorrelativo(              rs.getString("VC_CORRELATIVO"));
                        objRespuesta.setVcRegistro(                 rs.getString("VC_REGISTRO"));
                        objRespuesta.setVcDocumento(                rs.getString("VC_DOCUMENTO"));
                        objRespuesta.setVcRemitente(                rs.getString("VC_REMITENTE"));
                        objRespuesta.setVcDocRepresentado(          rs.getString("VC_DOC_REPRESENTADO"));
                        objRespuesta.setVcRepresentado(             rs.getString("VC_REPRESENTADO"));
                        objRespuesta.setVcSiglaArea(                rs.getString("VC_SIGLA_AREA"));
                        objRespuesta.setVcAreaNombre(               rs.getString("VC_AREA_NOMBRE"));
                        objRespuesta.setVcSedeNombre(               rs.getString("VC_SEDE_NOMBRE"));
                        objRespuesta.setNuIdSede(                   rs.getInt("NU_ID_SEDE"));
                        objRespuesta.setVcObservacionCompleta(      rs.getString("VC_OBSERVACION_COMPLETA"));
                        objRespuesta.setVcObservacionCorta(         rs.getString("VC_OBSERVACION_CORTA"));
                        objRespuesta.setNuEstado(                   rs.getInt("NU_ESTADO"));
                        objRespuesta.setNuEstadoExterno(            rs.getInt("NU_ESTADO_EXTERNO"));
                        objRespuesta.setNuIdRegistro(               rs.getInt("NU_ID_REGISTRO"));
                        objRespuesta.setVcCorreo(                   rs.getString("VC_CORREO"));
                        objRespuesta.setVcEstadoInterno(            rs.getString("VC_ESTADO_INTERNO"));
                        objRespuesta.setVcEstadoExterno(            rs.getString("VC_ESTADO_EXTERNO"));
                        objRespuesta.setVcDestinatarios(            rs.getString("VC_DESTINATARIOS"));
                        objRespuesta.setVcDescTipoDocSgd(           rs.getString("VC_DESC_TIPO_DOC_SGD"));
                        objRespuesta.setVcDescModIng(               rs.getString("VC_DESC_MOD_ING"));
                        objRespuesta.setVcNroExpediente(            rs.getString("VC_NRO_EXPEDIENTE"));
                        objRespuesta.setVcEsRepresentante(          rs.getString("VC_ES_REPRESENTANTE"));
                        objRespuesta.setVcNroTelefonico(            rs.getString("VC_NRO_TELEFONO"));
                        objRespuesta.setDtFechaPresentacion(        rs.getDate("DT_FECHA_PRESENTACION"));
                        lstDocumentos.add(objRespuesta);
                    }
            }
    
            response = new ClsResultDAO();
      
            response.put("LST_DOCUMENTOS_SYNC", lstDocumentos);
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
    public ClsResultDAO doLstArchivo(ClsUsuarioBean objUsuario, ClsDocumentosBean objDocumento) {
        ClsResultDAO response = null;
        ClsConectionDB2 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        
        List<ClsArchivoBean> lstArchivos;
        
        try {
            conne = new ClsConectionDB2();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_GET_DOCUMENTO_X_ID);
            int i = 0;

            stmt.setInt        (++i, objDocumento.getNuIdRegistro());
            stmt.setString     (++i, objUsuario.getVcUsuario());
            stmt.setString     (++i, ClsUtils.getIpAddress());
            
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
                        objRespuesta.setVcNombreOriginal(rs.getString("VC_NOMBRE_ORIGINAL"));
                        objRespuesta.setNuLong(rs.getLong("NU_LONGITUD"));
                        objRespuesta.setVcRutalFinal(rs.getString("VC_RUTA_ALMACENAMIENTO"));
                        objRespuesta.setVcExtension(rs.getString("VC_EXTENSION"));
                        objRespuesta.setVcRutaAlmacenamiento(rs.getString("VC_RUTA_FINAL"));
                        objRespuesta.setVcNombreFinal(ClsUtils.getName(rs.getString("VC_NOMBRE_FINAL")));
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
    public ClsResultDAO doLstPrincipales() {
        ClsResultDAO response = null;
        ClsConectionDB2 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        
         Map<String, String> lstDependencia=null;
         Map<String, String> lstTipoDocumento=null;
         Map<String, String> lstTipoDocSGD=null;
         Map<String, String> lstLocales=null;
         Map<String, String> lstEstados=null;
         Map<String, String> lstModalidadIngreso=null;


        try {
            conne = new ClsConectionDB2();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_PRINCIPALES);
            int i = 0;
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();
            
            rs = (ResultSet) stmt.getObject(i - 6);
            if (rs != null) {
                lstTipoDocumento= new LinkedHashMap<String, String>();
                    while (rs.next()) {
                        lstTipoDocumento.put(rs.getString("VC_DESC_TIPO_DOC_IDENT"), rs.getString("NU_ID_TIPO_DOC_IDENT"));
                    }
            }
            
            rs = (ResultSet) stmt.getObject(i - 5);
            if (rs != null) {
                lstTipoDocSGD= new LinkedHashMap<String, String>();
                    while (rs.next()) {
                        lstTipoDocSGD.put(rs.getString("VC_DESC_TIPO_DOC_SGD"), rs.getString("NU_ID_TIPO_DOC_SGD"));
                    }
            }
            
            rs = (ResultSet) stmt.getObject(i - 4);
            if (rs != null) {
                lstLocales= new LinkedHashMap<String, String>();
                    while (rs.next()) {
                        lstLocales.put(rs.getString("VC_DESC_LOCAL"), rs.getString("NU_ID_LOCAL"));
                    }
            }
            
            rs = (ResultSet) stmt.getObject(i - 3);
            if (rs != null) {
                lstEstados= new LinkedHashMap<String, String>();
                    while (rs.next()) {
                        lstEstados.put(rs.getString("VC_DESC_ESTADO"), rs.getString("NU_ID_ESTADO"));
                    }
            }
            
            rs = (ResultSet) stmt.getObject(i - 2);
            if (rs != null) {
                lstModalidadIngreso= new LinkedHashMap<String, String>();
                    while (rs.next()) {
                        lstModalidadIngreso.put(rs.getString("VC_DESC_MOD_ING"), rs.getString("NU_ID_MOD_ING"));
                    }
            }
            response = new ClsResultDAO();
            response.put("LST_TIPO_DOCUMENTO", lstTipoDocumento);
            response.put("LST_TIPO_DOC_SGD", lstTipoDocSGD);
            response.put("LST_LOCALES", lstLocales);
            response.put("LST_ESTADOS", lstEstados);
            response.put("LST_MODO_INGRESO", lstModalidadIngreso);
            
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
    public ClsResultDAO doLstDependencia(Integer nuIdDependencia) {
        ClsResultDAO response = null;
        ClsConectionDB2 conne = null;
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        
         Map<String, String> lstDependencia=null;

        try {
            conne = new ClsConectionDB2();
            conn = conne.f_getConn();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall(SP_LST_DEPENDENCIA_LOCAL);
            int i = 0;
            stmt.setInt(++i, nuIdDependencia);
            stmt.registerOutParameter(++i, OracleTypes.CURSOR);
            stmt.registerOutParameter(++i, OracleTypes.NUMERIC);
            stmt.registerOutParameter(++i, OracleTypes.VARCHAR);
            stmt.execute();
            
            rs = (ResultSet) stmt.getObject(i - 2);
            if (rs != null) {
                lstDependencia= new LinkedHashMap<String, String>();
                    while (rs.next()) {
                        lstDependencia.put(rs.getString("VC_DESC_DEPENDENCIA"), rs.getString("NU_ID_DEPENDENCIA"));
                    }
            }
            response = new ClsResultDAO();
            response.put("LST_DEPENDENCIA", lstDependencia);
            response.put(ClsResultDAO.CODIGO_ERROR, stmt.getInt(i - 1));
            response.put(ClsResultDAO.MENSAJE_ERROR, stmt.getString(i));
        } catch (Throwable e) {
            logger.info(e);
        } finally {
            conne.f_endConn();
        }
        return response;
    }
}
