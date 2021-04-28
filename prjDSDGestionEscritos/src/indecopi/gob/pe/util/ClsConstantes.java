package indecopi.gob.pe.util;

import java.io.Serializable;

public class ClsConstantes implements Serializable {
    public static final String  CRIPTO_KEY = "@-;?*$]3(X!=";
    public static final String  SEPARADOR = "\\|";
    public static final String  PARAMETRO_GET = "jgapps";
    public static final String  OK = "OK";
    public static final String  ERROR = "ERROR";
    public static final String  FINALIZAR = "FINALIZAR";
    public static final String  REGRESAR = "REGRESAR";
    public static final String  ENVIAR = "ENVIAR";
    public static final String  ALISTA = "ALISTA";
    public static final String  FORMATO_FECHA_COMPLETO = "yyyy-MM-dd HH:mm:ss";
    public static final String  FORMATO_FECHA_DIA = "yyyy-MM-dd";
    public static final String  FORMATO_FECHA_HORA = "HH:mm:ss";
    public static final String  FORMATO_ANIO = "yyyy";
    public static final String  SEPARADOR_EXPEDIENTES = "¬";
    public static final String  SEPARADOR_SIGLAS  = ",";
    public static final String  FORMATO_FECHA  = "yyyy-MM-dd";
    public static final String  FORMATO_FECHA_FILTRO_INICIAL  = "yyyy-MM-dd'T'00:00:00.SSS+hh:mm";
    public static final String  FORMATO_FECHA_FILTRO_FINAL  = "yyyy-MM-dd'T'23:59:59.SSS+hh:mm";
    
    /*
    public static final String  URL_DOWNLOAD_DOC  = "http://10.80.0.160:7003/appTodosPlantillas/pdf/";
    public static final String  URL_UPLOAD_DOC    = "http://10.80.0.160:7003/appTodosPlantillas/docs/uploadSD";
    public static final String  WS_URL_WSDL       = "http://10.80.0.160:7003/appWSGTIFirmaDigital/ClsGeneraTicketService?WSDL";
    public static final String  WS_PKG            = "http://ws.pe.gob.indecopi/";
    public static final String  WS_SERVICE_PORT   = "ClsGeneraTicketService";
    public static final Integer NU_ID_APLICATIVO  =  999;
    public static final String  URL_JNLP          = "jnlp://10.80.0.160:7003/appWSGTIFirmaDigital/firmarDocumentos.jnlp?tk=";
    */
    
    //@tsalinas inicio
    public static final Integer  NU_FLAG_UNO=1;
    public static final Integer  NU_FLAG_CREAR=1 ;
    public static final Integer  NU_FLAG_CORREGIR=2 ;
    public static final Integer  NU_FLAG_FIRMAR=3 ;
    public static final Integer  NU_FLAG_FIRMADO=4 ;
    public static final Integer  NU_FLAG_ENVIAR=5 ;
    
    public static final Integer  NU_FLAG_CERO=0 ;
    public static final String   NU_FLAG_STR_MENOS_UNO="-1" ;
    public static final Integer  NU_FLAG_INT_MENOS_UNO=-1 ;
    public static final Integer  NU_FLAG_INT_VACIDO =null ;
    public static final String  NU_FLAG_STR_VACIDO ="" ;
    //@tsalinas fin
    
    //@mmoya inicio
    public static final String VC_AREA_REG_MARCAS="OSD";
    public static final Integer NU_ID_TIPO_APP_REG_MARCAS=14;
    public static final Integer NU_ID_TIPO_CONS_REG_MARCAS=1;
    public static final Integer NU_ID_TIPO_CONS_REG_MARCAS_2=2;
    public static final Integer NU_ID_TIPO_CONS_REG_MARCAS_3=3;
    
    
    //lista de seguimientos
    //Coordinador
    public static final String VC_ID_INGRESO="168";
    public static final String VC_ID_NOTIF_ORD_PUB="03";
    public static final String VC_ID_EXP_PUB_EVAL_COORD="459";
    public static final String VC_ID_EXP_PUB_REVISADO="460";
    
    //Esp Forma
    public static final String VC_ID_EXP_DEV_ASI_EXP_FORMA="166";
    
    //Esp Registrabilidad
    public static final String VC_ID_EXP_PUBL_REC="457";
    public static final String VC_ID_EXP_PUBL_DEV="463";//VENTANA EX REGISTRABILIDAD, SE USA 166
    
    //lista de links de acciones
    public static final String VC_ASIGNAR_CLASIFICACION="1";
    public static final String VC_ASIGNAR_ESPECIALISTA="2";
    public static final String VC_REVISAR_PROYECTO="3";
    public static final String VC_PROYECTO_REVISADO="4";
    public static final String VC_FIRMAR="5";
    
    //Para Asignar Clasificación
    public static final String VC_COMPLEJO="COMPLEJO";
    public static final String VC_REGULAR="REGULAR";
    public static final String VC_FASTTRACK="FASTTRACK";
    public static final String VC_PLATAFORMA="PLATAFORMA";
    public static final String VC_ID_COMPLEJO="980";
    public static final String VC_MENSAJE_VALIDACION_COMPLEJO="Los expedientes con clasificación Complejo requieren " +
                            "de la selección del especialista para su asignación inmediata. <br>" +
                            "Verifique los expedientes marcados para su asignación respectiva.";
    
    public static final String VC_ID_FASTTRACK="981";
    public static final String VC_ID_PLATAFORMA="982";
    public static final String VC_ID_REGULAR="983";
    public static final String VC_MENSAJE_VALIDACION_SEGUIMIENTOS="Los expedientes con clasificación FastTack, Regular y Plataforma " +
                                "serán asignados al final del día. ";
    
    public static final String VC_ID_COORDINADOR="1";
    public static final String VC_ID_ESP_FORMA="2";
    public static final String VC_ID_ESP_REGISTR="3";
    public static final String VC_ID_ACC_RECIBIDO="4";
    //Para Asignar Registrabilidad
    public static final String VC_ID_ASIG_REGISTRABILIDAD="984";
    public static final String VC_ASIG_REGISTRABILIDAD="ASIGNACION A REGISTRABILIDAD";      
    public static final String VC_ID_PEND_REGISTRABILIDAD="985";
    public static final String VC_PEND_REGISTRABILIDAD="PEND. REGISTRABILIDAD";
  
    public static final String VC_MENSAJE_VALIDACION_REG="Los expedientes con asignación de Registrabilidad requieren " +
                                "de la selección del especialista para su asignación inmediata. <br>" +
                                "Verifique los expedientes asignados.";
    
    //Perfiles de Usuarios
    public static final Integer NU_PERFIL_COORDINADOR=2;
    public static final Integer NU_PERFIL_ESP_FORMA=11;
    public static final Integer NU_PERFIL_ESP_REGISTRABILIDAD=12;

    public static final String VC_REQUISITO_NO = "1";
    public static final String VC_REQUISITO_SI = "2";
    public static final String VC_REQUISITO_MINIMOS_NO_CONFORME = "0";
    public static final String VC_REQUISITO_MINIMOS_CONFORME = "40";
    public static final String VC_REQUISITO_FORMA_CONFORME = "80";
    public static final String VC_REQUISITO_SOL_SIN_CAMBIOS_CONFORME = "100";
    
    //Revisar Proyecto
    public static final String VC_ID_EXP_PUB_DEVUELTO="463";

    
    //@mmoya fin

}
