package indecopi.gob.pe.srv;

import indecopi.gob.pe.bean.ClsArchivoBean;
import indecopi.gob.pe.bean.ClsUsuarioBean;
import indecopi.gob.pe.bean.ClsUsuarioIndBean;
import indecopi.gob.pe.bean.ClsDocumentosBean;

import indecopi.gob.pe.bean.ClsExpeVinculadosBean;
import indecopi.gob.pe.bean.ClsFiltroEscritosBean;

import indecopi.gob.pe.bean.ClsFiltroExpVinBean;
import indecopi.gob.pe.dao.ClsIND7GestionEscritosDAO;
import indecopi.gob.pe.dao.ClsIND7GestionEscritosIDAO;
import indecopi.gob.pe.dao.ClsLOGIGestionEscritosDAO;
import indecopi.gob.pe.dao.ClsLOGIGestionEscritosIDAO;
import indecopi.gob.pe.sync.ClsVinculacionAutomaticaSync;
import indecopi.gob.pe.util.ClsProperties;
import indecopi.gob.pe.util.ClsUtils;
import indecopi.gob.pe.utils.ClsResultDAO;

import java.io.DataInput;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public class ClsGestionEscritosSrv implements  Serializable{
    
    @SuppressWarnings("compatibility:9211915628840996890")
    private static final long serialVersionUID = -8649751507501391629L;
    
    private static Logger logger = Logger.getLogger(ClsGestionEscritosSrv.class);
    
    private ClsUsuarioBean objUsuarioBean;
    private ClsUsuarioIndBean objUsuario;
    
    private ClsFiltroEscritosBean objFiltroBean;
    private List<ClsDocumentosBean> lstDocumentos;
    private ClsDocumentosBean objDocumentoDetalle;
    private ClsDocumentosBean objDocumentoVincular;
    private ClsDocumentosBean objDocumentoVinExpeM;
    private List<ClsArchivoBean> lstArchivo;
    
    private ClsFiltroExpVinBean objFiltroExpeVin;
    private List<ClsExpeVinculadosBean> lstExpedienteVincualdo;
    
    private List<ClsArchivoBean> lstArchivoVinculados;
    
    private ClsFiltroEscritosBean objFiltroAutomaticoBean;
    
    private ClsArchivoBean objDescargar;
    
    private boolean blFlagMensaje=false;
    private String  vcFMensaje="";
    
    public ClsGestionEscritosSrv() {
        super();
    }
    
    public ClsGestionEscritosSrv(ClsUsuarioIndBean objUsuario, ClsUsuarioBean objUsuarioBean) {
        this.setObjUsuarioBean(objUsuarioBean);
        this.setObjUsuario(objUsuario);
        this.doInicializar();
    }
    
    public void doInicializar(){
        this.objFiltroBean=new ClsFiltroEscritosBean();
        this.objFiltroBean.setDtFechaIni(new Date());
        this.objFiltroBean.setDtFechaFin(new Date());
        
        this.objFiltroAutomaticoBean=new ClsFiltroEscritosBean();
        this.objFiltroAutomaticoBean.setDtFechaIni(new Date());
        this.objFiltroAutomaticoBean.setDtFechaFin(new Date());
        
        this.lstDocumentos=new ArrayList<ClsDocumentosBean>();
        this.objDocumentoDetalle=new ClsDocumentosBean();
        this.objDocumentoVincular= new ClsDocumentosBean();
        this.objDocumentoVinExpeM= new ClsDocumentosBean();
        this.lstArchivo=new  ArrayList<ClsArchivoBean>();
        this.lstExpedienteVincualdo=new ArrayList<ClsExpeVinculadosBean>();
        this.objFiltroExpeVin=new ClsFiltroExpVinBean();
        this.lstArchivoVinculados=new ArrayList<ClsArchivoBean>();
        this.objDescargar=new ClsArchivoBean();
        this.doLstGeneralesLOGI();
        this.doLstGeneralesIND7();
    }
    
    
    public void doVincularAsync(){
        ClsVinculacionAutomaticaSync.crearYComenzar("Vincular()", this.objFiltroAutomaticoBean, this.objUsuario, this.getObjUsuarioBean());
    }
    public void doLstGeneralesLOGI(){
        ClsLOGIGestionEscritosIDAO objEscritosIDAO = new ClsLOGIGestionEscritosDAO();
        ClsResultDAO objResult = objEscritosIDAO.doLstPrincipales();
            
        this.objFiltroBean.setLstTipoDocumento((Map<String, String>)objResult.get("LST_TIPO_DOCUMENTO") );
        this.objFiltroBean.setLstTipoDocSGD((Map<String, String>) objResult.get("LST_TIPO_DOC_SGD"));
        this.objFiltroBean.setLstLocales((Map<String, String>) objResult.get("LST_LOCALES"));
        this.objFiltroBean.setLstEstados((Map<String, String>) objResult.get("LST_ESTADOS"));
        this.objFiltroBean.setLstModalidadIngreso((Map<String, String>) objResult.get("LST_MODO_INGRESO"));
        
        ClsLOGIGestionEscritosIDAO objDependenciaIDAO = new ClsLOGIGestionEscritosDAO();
        ClsResultDAO objResultDependencia = objDependenciaIDAO.doLstDependencia(this.objFiltroBean.getNuIdLocal());
        this.objFiltroBean.setLstDependencia((Map<String, String>) objResultDependencia.get("LST_DEPENDENCIA"));
        
    }
    
    public void doLstGeneralesIND7(){
        ClsIND7GestionEscritosIDAO objEscritosIDAO = new ClsIND7GestionEscritosDAO();
        ClsResultDAO objResult = objEscritosIDAO.doLstPrincipales();
        this.objFiltroBean.setLstTipoVinculacion((Map<String, String>)objResult.get("LST_TIPO_VINCULACION") );
        this.objFiltroBean.setLstTipoArchivo((Map<String, String>)objResult.get("LST_TIPO_ARCHIVO"));
        
    }
    
   public void doLstDocumentos(){
            this.objFiltroBean.setNuIdPerfil(this.objUsuario.getNuIdPerfil());
            this.objFiltroBean.setVcUsuario(this.objUsuario.getVcUsuario());
            ClsLOGIGestionEscritosIDAO objEscritosIDAO = new ClsLOGIGestionEscritosDAO();
            ClsResultDAO objResult = objEscritosIDAO.doLstDocumentos(this.objFiltroBean);
            if(objResult!=null){
            this.setLstDocumentos((List<ClsDocumentosBean>)objResult.get("LST_DOCUMENTOS"));
            for(ClsDocumentosBean objDocumento: this.getLstDocumentos()){
                ClsIND7GestionEscritosIDAO objVincular=new ClsIND7GestionEscritosDAO();
                objResult =objVincular.getFlagVinculado(objDocumento.getVcCorrelativo());
                if(Integer.parseInt(objResult.get(ClsResultDAO.CODIGO_ERROR).toString())==0){
                    if(Integer.parseInt(objResult.get("FLAG_VINCULADO").toString())==0){
                        objDocumento.setBlVinculado(true);
                    }
                }
            }
             
            } 
        }
    
   public void doLimpiar(){
       this.doInicializar();
   }

   public void doVerVincular(ClsDocumentosBean objVincular){
       objVincular.setVcNroExpediente(null);
       objVincular.setNuIdTipoVinculacion(1);
       this.setObjDocumentoVincular(objVincular);
       
       ClsLOGIGestionEscritosIDAO objEscritosIDAO = new ClsLOGIGestionEscritosDAO();
       ClsResultDAO objResult = objEscritosIDAO.doLstArchivo(this.objUsuarioBean, objVincular);
       this.getObjDocumentoVincular().setLstArchivo((List<ClsArchivoBean>)objResult.get("LST_ARCHIVO"));
       for(int i=0; i< this.getObjDocumentoVincular().getLstArchivo().size(); i++){
           this.getObjDocumentoVincular().getLstArchivo().get(i).setNuIdTipoArchivo(4);
        }
   }
    
    public void doVerDetalleDoc(ClsDocumentosBean objDetalle){
        this.setObjDocumentoDetalle(objDetalle);
        ClsLOGIGestionEscritosIDAO objEscritosIDAO = new ClsLOGIGestionEscritosDAO();
        ClsResultDAO objResult = objEscritosIDAO.doLstArchivo(this.objUsuarioBean, objDetalle);
        this.setLstArchivo((List<ClsArchivoBean>)objResult.get("LST_ARCHIVO"));
    }
    
    public void doVerDetalleDocVinculados(Integer nuIdExpVinculado){
        ClsIND7GestionEscritosIDAO objEscritosIDAO = new ClsIND7GestionEscritosDAO();
        ClsResultDAO objResult = objEscritosIDAO.doLstArchivo(nuIdExpVinculado);
        this.setLstArchivoVinculados((List<ClsArchivoBean>)objResult.get("LST_ARCHIVO"));
    }
    
    public void doVincularExpediente(){
        logger.info("doVincularExpediente");
        ClsIND7GestionEscritosIDAO objVincular=new ClsIND7GestionEscritosDAO();
        this.getObjDocumentoVincular().setNuIdTipoVinculacion(2);
        ClsResultDAO objResult = objVincular.doVincular(this.getObjDocumentoVincular(), this.objUsuarioBean);
        if(Integer.parseInt(objResult.get(ClsResultDAO.CODIGO_ERROR).toString())==0){
            for(ClsDocumentosBean objDocumento: this.getLstDocumentos()){
                if(this.getObjDocumentoVincular().getVcCorrelativo().equals(objDocumento.getVcCorrelativo())){
                    objDocumento.setBlVinculado(false);
                }
            }
            
            this.objDocumentoVinExpeM=new ClsDocumentosBean();
            this.blFlagMensaje=true;
            this.setVcFMensaje(objResult.get(ClsResultDAO.MENSAJE_ERROR).toString());
                
                
        }else{
            if(Integer.parseInt(objResult.get(ClsResultDAO.CODIGO_ERROR).toString())==-1){
            this.blFlagMensaje=true;
            this.setVcFMensaje(objResult.get(ClsResultDAO.MENSAJE_ERROR).toString());
            }else{
                this.blFlagMensaje=true;
                this.setVcFMensaje("Lo sentimos, sucedio algo inesperado.");
            }
        }
        
    }
    
    public void doVerSubirArchivo(){
        
    }
    
    public void doVincularExpManual(){
                
        try{        
        for(ClsArchivoBean objArchivo: this.getObjDocumentoVinExpeM().getLstArchivo()) { 
  
        File outputFile = new java.io.File( objArchivo.getVcRutaAlmacenamiento()+ objArchivo.getVcNombreFinal());
        FileOutputStream fo = new FileOutputStream(outputFile);
        fo.write(objArchivo.getDataFile().getData());
        fo.close();
        }
            
        ClsIND7GestionEscritosIDAO objVincular=new ClsIND7GestionEscritosDAO();
        ClsResultDAO objResult =objVincular.doVincular(this.getObjDocumentoVinExpeM(), this.objUsuarioBean);
            
            if(Integer.parseInt(objResult.get(ClsResultDAO.CODIGO_ERROR).toString())==0){
                this.objDocumentoVinExpeM=new ClsDocumentosBean();
                this.blFlagMensaje=true;
                this.setVcFMensaje(objResult.get(ClsResultDAO.MENSAJE_ERROR).toString());
                    
            }else{
                if(Integer.parseInt(objResult.get(ClsResultDAO.CODIGO_ERROR).toString())==-1){
                this.blFlagMensaje=true;
                this.setVcFMensaje(objResult.get(ClsResultDAO.MENSAJE_ERROR).toString());
                }else{
                    this.blFlagMensaje=true;
                    this.setVcFMensaje("Lo sentimos, sucedio algo inesperado.");
                }
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void doLimiparManual(){
        this.objDocumentoVinExpeM=new ClsDocumentosBean();
    }
    
    public void doLimpiarEsPre(){
        this.objFiltroBean=new ClsFiltroEscritosBean();
        this.lstDocumentos= new  ArrayList<ClsDocumentosBean>();
    }

    public void doLimpiarExpVinculados(){
        this.objFiltroExpeVin=new ClsFiltroExpVinBean();
        this.lstExpedienteVincualdo=new ArrayList<ClsExpeVinculadosBean>();
    }
    public void listenerDocumentos(UploadEvent event) throws Exception {
        ClsProperties props= new ClsProperties();
           List<UploadItem> lstItem = event.getUploadItems();
           for (int i=0; i<lstItem.size(); i++) {
              // System.out.println(">>" + item.getData().toString());
                     if (lstItem.get(i).getData() != null) {
                        try{
                            ClsArchivoBean objArchivo=new ClsArchivoBean();
                                objArchivo.setDataFile(lstItem.get(i));
                                objArchivo.setNuIdTipoArchivo(4);
                                objArchivo.setNuLong(Long.valueOf(objArchivo.getDataFile().getFileSize()));
                                objArchivo.setVcNombreFinal("doc_"+ClsUtils.doGenerarNombreArchivoAleatorio());
                                objArchivo.setVcNombreOriginal(objArchivo.getDataFile().getFileName());
                                objArchivo.setVcRutaAlmacenamiento(props.getDirectoryFileServerMarcas());
                                objArchivo.setVcRutalFinal(props.getDirectoryFileServerMarcas());
                                objArchivo.setVcExtension(ClsUtils.getExtension(objArchivo.getDataFile().getFileName()));
                                this.objDocumentoVinExpeM.getLstArchivo().add(objArchivo);
                            
                            }catch(Exception e)
                            {
                            e.printStackTrace();
                            }
                    }        
    
            }
    }
    
    //expediente vinculado
    
    
    public void getDescargar(ClsArchivoBean objArchivo){
        logger.info(objArchivo.getVcRutaAlmacenamiento()+objArchivo.getVcNombreFinal()+"."+objArchivo.getVcExtension());
        logger.info(objArchivo.getVcNombreOriginal());
        this.objDescargar=new ClsArchivoBean();
        this.objDescargar.setVcRutaAlmacenamiento(objArchivo.getVcRutaAlmacenamiento());
        this.objDescargar.setVcNombreFinal(objArchivo.getVcNombreFinal());
        this.objDescargar.setVcExtension(objArchivo.getVcExtension());
        this.objDescargar.setVcNombreOriginal(objArchivo.getVcNombreOriginal());
    
    }
    
    public void getDescargarEscritos(ClsArchivoBean objArchivo){
        logger.info(objArchivo.getVcRutaAlmacenamiento()+objArchivo.getVcNombreFinal()+"."+objArchivo.getVcExtension());
        logger.info(objArchivo.getVcNombreOriginal());
        this.objDescargar=new ClsArchivoBean();
        this.objDescargar.setVcRutaAlmacenamiento(objArchivo.getVcRutalFinal());
        this.objDescargar.setVcNombreFinal(objArchivo.getVcNombreFinal());
        this.objDescargar.setVcExtension(objArchivo.getVcExtension());
        this.objDescargar.setVcNombreOriginal(objArchivo.getVcNombreOriginal());
    
    }
    
    public void doBuscarExpVin(){
        ClsIND7GestionEscritosIDAO objExpVin=new ClsIND7GestionEscritosDAO();
        ClsResultDAO objResult=objExpVin.doLstExpVinculados(this.objFiltroExpeVin, objUsuarioBean);
        this.setLstExpedienteVincualdo((List<ClsExpeVinculadosBean>)objResult.get("LST_EXP_VIN"));
    }

    public void doDescargarModalAuxiliar(){
        
        ClsUtils.doDownloadFile(this.objDescargar.getVcRutaAlmacenamiento(), this.objDescargar.getVcNombreFinal(), this.objDescargar.getVcExtension(), this.objDescargar.getVcNombreOriginal());
    }
    public void setObjDocumentoDetalle(ClsDocumentosBean objDocumentoDetalle) {
        this.objDocumentoDetalle = objDocumentoDetalle;
    }

    public ClsDocumentosBean getObjDocumentoDetalle() {
        return objDocumentoDetalle;
    }

    public void setObjUsuarioBean(ClsUsuarioBean objUsuarioBean) {
        this.objUsuarioBean = objUsuarioBean;

    }

    public ClsUsuarioBean getObjUsuarioBean() {
        return objUsuarioBean;
    }

    public void setLstDocumentos(List<ClsDocumentosBean> lstDocumentos) {
        this.lstDocumentos = lstDocumentos;
    }

    public List<ClsDocumentosBean> getLstDocumentos() {
        return lstDocumentos;
    }


    public void setObjUsuario(ClsUsuarioIndBean objUsuario) {
        this.objUsuario = objUsuario;
    }

    public ClsUsuarioIndBean getObjUsuario() {
        return objUsuario;
    }


    public void setObjFiltroBean(ClsFiltroEscritosBean objFiltroBean) {
        this.objFiltroBean = objFiltroBean;
    }

    public ClsFiltroEscritosBean getObjFiltroBean() {
        return objFiltroBean;
    }
    public void setLstArchivo(List<ClsArchivoBean> lstArchivo) {
        this.lstArchivo = lstArchivo;
    }

    public List<ClsArchivoBean> getLstArchivo() {
        return lstArchivo;
    }

    public void setObjDocumentoVincular(ClsDocumentosBean objDocumentoVincular) {
        this.objDocumentoVincular = objDocumentoVincular;
    }

    public ClsDocumentosBean getObjDocumentoVincular() {
        return objDocumentoVincular;
    }

    public void setObjDocumentoVinExpeM(ClsDocumentosBean objDocumentoVinExpeM) {
        this.objDocumentoVinExpeM = objDocumentoVinExpeM;
    }

    public ClsDocumentosBean getObjDocumentoVinExpeM() {
        return objDocumentoVinExpeM;
    }

    public void setObjFiltroExpeVin(ClsFiltroExpVinBean objFiltroExpeVin) {
        this.objFiltroExpeVin = objFiltroExpeVin;
    }

    public ClsFiltroExpVinBean getObjFiltroExpeVin() {
        return objFiltroExpeVin;
    }


    public void setLstExpedienteVincualdo(List<ClsExpeVinculadosBean> lstExpedienteVincualdo) {
        this.lstExpedienteVincualdo = lstExpedienteVincualdo;
    }

    public List<ClsExpeVinculadosBean> getLstExpedienteVincualdo() {
        return lstExpedienteVincualdo;
    }

    public void setLstArchivoVinculados(List<ClsArchivoBean> lstArchivoVinculados) {
        this.lstArchivoVinculados = lstArchivoVinculados;
    }

    public List<ClsArchivoBean> getLstArchivoVinculados() {
        return lstArchivoVinculados;
    }


    public void setObjFiltroAutomaticoBean(ClsFiltroEscritosBean objFiltroAutomaticoBean) {
        this.objFiltroAutomaticoBean = objFiltroAutomaticoBean;
    }

    public ClsFiltroEscritosBean getObjFiltroAutomaticoBean() {
        return objFiltroAutomaticoBean;
    }

    public void setBlFlagMensaje(boolean blFlagMensaje) {
        this.blFlagMensaje = blFlagMensaje;
    }

    public boolean isBlFlagMensaje() {
        return blFlagMensaje;
    }

    public void setVcFMensaje(String vcFMensaje) {
        this.vcFMensaje = vcFMensaje;
    }

    public String getVcFMensaje() {
        return vcFMensaje;
    }


    public void setObjDescargar(ClsArchivoBean objDescargar) {
        this.objDescargar = objDescargar;
    }

    public ClsArchivoBean getObjDescargar() {
        return objDescargar;
    }
}
