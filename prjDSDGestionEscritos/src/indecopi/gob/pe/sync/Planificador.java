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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Planificador {
    

    
    Timer timer;
    int counter1 = 0;
    int counter2 = 0;
    Date fecha;
    boolean finTarea = false;
    public Planificador ( ) {
    //se crea un planificador que planificara 2 tareas
    timer = new Timer ( ) ;
    //la Tarea1 se ejecuta pasado 1 segundo y luego periódicamente cada segundo
   // timer.schedule ( new Tarea1 ( ) , 1000, 5000) ;
    //la Tarea2 se ejecuta pasado 5 segundos y luego periodicamente cada segundo
    timer.schedule ( new Tarea2 ( ) , 1000, 60000) ;
    }
    
    class Tarea1 extends TimerTask{
        public void run ( ) {
        System.out.println ( "Ejecucion nro "+ counter1+" e la Tarea1") ;
        if (counter1 < 5){
        System.out.println ( "El contador tiene valor:" + counter1) ;
        counter1++;
        }
        else{
        //Si la otra tarea ya ha acabado mata al planificador
        if (finTarea)
        {
        System.out.println ( "***Fin Planificador***") ;
        timer.cancel();
        }
        //Si la otra tarea todavia no ha acabado solo se desplanifica, el
        //planificador sigue funcionando
        else
        {
        System.out.println ( "***Fin Tarea1***") ;
        this.cancel ( ) ;
        System.out.println ( "Tarea1 desplanificada.") ;
        finTarea = true;
        }
        }
        }
    }
    //Tarea2: Muestra la fecha actual
    //cuando el contador llega a 10 se desplanifica la tarea
    

    public class Tarea2 extends TimerTask {

    public void run () {
    System.out.println ( "Ejecucion nro "+ counter2+" de la Tarea2") ;
    fecha = new Date();
    counter2++;
  
   // if(Integer.parseInt(fecha.getMinutes()+"")==2){
      List<ClsDocumentosBean> lstDocumentos=new ArrayList<ClsDocumentosBean>();
      ClsFiltroEscritosBean objFiltroBean=new ClsFiltroEscritosBean();
      ClsUsuarioBean objUsuarioBean= new ClsUsuarioBean();
      objUsuarioBean.setVcUsuario("automatico");
      objFiltroBean.setNuIdPerfil(1);
      objFiltroBean.setVcUsuario("automatico");
      System.out.println(objFiltroBean.getVcUsuario());
      
        ClsLOGIGestionEscritosIDAO objEscritosIDAO = new ClsLOGIGestionEscritosDAO();
        ClsResultDAO objResult = objEscritosIDAO.doLstDocumentosSyncAutomatico(objFiltroBean);
        if(objResult!=null){
        lstDocumentos=((List<ClsDocumentosBean>)objResult.get("LST_DOCUMENTOS_SYNC"));
        System.out.println(lstDocumentos.size());
        } 
        
     
        for(ClsDocumentosBean objDocumento : lstDocumentos){
            objDocumento.setNuIdTipoVinculacion(1);
            ClsIND7GestionEscritosIDAO objVincular=new ClsIND7GestionEscritosDAO();
            
            objResult=null;
            objResult = objEscritosIDAO.doLstArchivo(objUsuarioBean, objDocumento);
            objDocumento.setLstArchivo((List<ClsArchivoBean>)objResult.get("LST_ARCHIVO"));
            
            for(int i=0; i< objDocumento.getLstArchivo().size(); i++){
                objDocumento.getLstArchivo().get(i).setNuIdTipoArchivo(4);
             }
            
            objResult=null;
            objResult =objVincular.doVincular(objDocumento, objUsuarioBean);
            if(Integer.parseInt(objResult.get(ClsResultDAO.CODIGO_ERROR).toString())==0){
             System.out.println("Vinculado: "+objDocumento.getVcNroExpediente());
            }
          
        }
        
   // }
    /*
    if (counter2 < 50){
    System.out.println ("Fecha: "+ fecha.getHours()) ;
    counter2++;
    }
    else{
    //Si la otra tarea ya ha acabado mata al planificador
    if (finTarea)
    {
    System.out.println ( "***Fin Planificador***") ;
    timer.cancel();
    }
    //Si la otra tarea todavia no ha acabado solo se desplanifica, el
    //planificador sigue funcionando
    else
    {
    System.out.println ("***Fin Tarea2***") ;
    this.cancel ( ) ;
    System.out.println ("Tarea2 desplanificada.") ;
    finTarea = true;
    }
    }
    */
    }
    
    }

}

