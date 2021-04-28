package indecopi.gob.pe.sync;

import indecopi.gob.pe.bean.ClsArchivoBean;
import indecopi.gob.pe.bean.ClsDocumentosBean;
import indecopi.gob.pe.bean.ClsFiltroEscritosBean;
import indecopi.gob.pe.bean.ClsUsuarioBean;
import indecopi.gob.pe.bean.ClsUsuarioIndBean;
import indecopi.gob.pe.dao.ClsIND7GestionEscritosDAO;
import indecopi.gob.pe.dao.ClsIND7GestionEscritosIDAO;
import indecopi.gob.pe.dao.ClsLOGIGestionEscritosDAO;
import indecopi.gob.pe.dao.ClsLOGIGestionEscritosIDAO;
import indecopi.gob.pe.utils.ClsResultDAO;

import java.util.List;

public class ClsVinculacionAutomaticaSync implements Runnable {
    Thread hilo;

    private ClsUsuarioIndBean objUsuario;
    private ClsUsuarioBean objUsuarioBean;
    private ClsFiltroEscritosBean objFiltroBean;
    private List<ClsDocumentosBean> lstDocumentos;

    //Construye un nuevo hilo.
     ClsVinculacionAutomaticaSync(String nombre, ClsFiltroEscritosBean objFiltroBean, ClsUsuarioIndBean objUsuario, ClsUsuarioBean objUsuarioBean){
         hilo= new Thread(this,nombre);
         this.setObjUsuario(objUsuario);
         this.setObjFiltroBean(objFiltroBean);
         this.setObjUsuarioBean(objUsuarioBean);
         
     }

     //Un método de fábrica que crea e inicia un hilo.

     public static ClsVinculacionAutomaticaSync crearYComenzar (String nombre, ClsFiltroEscritosBean objFiltroBean, ClsUsuarioIndBean objUsuario, ClsUsuarioBean objUsuarioBean){
         ClsVinculacionAutomaticaSync miHilo=new ClsVinculacionAutomaticaSync(nombre, objFiltroBean, objUsuario, objUsuarioBean);
         miHilo.hilo.start(); //Inicia el hilo
         return miHilo;
     }

     //Punto de entrada de hilo.
     public void run(){
         
         System.out.println(hilo.getName()+" iniciando.");
         this.objFiltroBean.setNuIdPerfil(this.objUsuario.getNuIdPerfil());
         this.objFiltroBean.setVcUsuario(this.objUsuario.getVcUsuario());
         System.out.println(this.objUsuario.getVcUsuario());
         ClsLOGIGestionEscritosIDAO objEscritosIDAO = new ClsLOGIGestionEscritosDAO();
         ClsResultDAO objResult = objEscritosIDAO.doLstDocumentosSync(this.objFiltroBean);
         if(objResult!=null){
         this.setLstDocumentos((List<ClsDocumentosBean>)objResult.get("LST_DOCUMENTOS_SYNC"));
         } 
         
         for(ClsDocumentosBean objDocumento : this.getLstDocumentos()){
             objDocumento.setNuIdTipoVinculacion(1);
             ClsIND7GestionEscritosIDAO objVincular=new ClsIND7GestionEscritosDAO();
             
             objResult=null;
             objResult = objEscritosIDAO.doLstArchivo(this.objUsuarioBean, objDocumento);
             objDocumento.setLstArchivo((List<ClsArchivoBean>)objResult.get("LST_ARCHIVO"));
             
             for(int i=0; i< objDocumento.getLstArchivo().size(); i++){
                 objDocumento.getLstArchivo().get(i).setNuIdTipoArchivo(4);
              }
             
             objResult=null;
             objResult =objVincular.doVincular(objDocumento, this.objUsuarioBean);
             if(Integer.parseInt(objResult.get(ClsResultDAO.CODIGO_ERROR).toString())==0){
              System.out.println("Vinculado: "+objDocumento.getVcNroExpediente());
             }
         }
     }


    public void setLstDocumentos(List<ClsDocumentosBean> lstDocumentos) {
        this.lstDocumentos = lstDocumentos;
    }

    public List<ClsDocumentosBean> getLstDocumentos() {
        return lstDocumentos;
    }

    public void setObjFiltroBean(ClsFiltroEscritosBean objFiltroBean) {
        this.objFiltroBean = objFiltroBean;
    }

    public ClsFiltroEscritosBean getObjFiltroBean() {
        return objFiltroBean;
    }

    public void setObjUsuario(ClsUsuarioIndBean objUsuario) {
        this.objUsuario = objUsuario;
    }

    public ClsUsuarioIndBean getObjUsuario() {
        return objUsuario;
    }


    public void setObjUsuarioBean(ClsUsuarioBean objUsuarioBean) {
        this.objUsuarioBean = objUsuarioBean;
    }

    public ClsUsuarioBean getObjUsuarioBean() {
        return objUsuarioBean;
    }
}
