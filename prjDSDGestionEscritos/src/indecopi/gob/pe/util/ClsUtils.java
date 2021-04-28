package indecopi.gob.pe.util;


import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;

import java.io.UnsupportedEncodingException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.net.URLConnection;

import java.net.URLEncoder;

import java.nio.charset.StandardCharsets;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.FileDataSource;

import javax.faces.context.FacesContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import org.richfaces.json.JSONObject;

public class ClsUtils implements Serializable {
    
    public static String VC_MSG_ERROR_EXCEPTION = "Ocurrió un error, por favor vuelva a intentarlo.";
    static Logger logger = Logger.getLogger(ClsUtils.class);
    
    public ClsUtils() {
        super();
    }
    
    public static String doGenerarNombreArchivoAleatorio() {
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyyMMddHHmmss");
        String response="";
        try{
          response=formatoDeFecha.format(new Date());
          response+= "" +  aleatorio(1000,9999);
        }catch(Exception e){
          response="";
        }
        return response;
    }

    
    public static String doInvertirCadena(String vcCadena){
        String vcCadenaInvertida="";
        for (int x=vcCadena.length()-1;x>=0;x--){
        vcCadenaInvertida = vcCadenaInvertida + vcCadena.charAt(x);
        }
        
        return vcCadenaInvertida;
    }
    
    public static String doGetPath(String vcCadena){
        String vcCadenaInvertida=doInvertirCadena(vcCadena);
        String vcCadenaArray[]=vcCadenaInvertida.split("/");
        String vcPath=vcCadenaArray[0]+"/"+vcCadenaArray[1]+"/";
        return doInvertirCadena(vcPath);
    }
    
    
    public static String PropertyValue(QueryResult result, String propertie){
        return  result.getPropertyValueById(propertie) != null ? result.getPropertyValueById(propertie) + "" : "";
    }
    
    public static int aleatorio(int max,int min){
            return (int)(Math.random()*(max-min))+min;      
    }
    
    public static Object convertirDato(String dato, String tipo) {
        if (dato == null) {
            return dato;
        } else {
            if (tipo.equals("text")) {
                return dato;
            } else {
                if (tipo.equals("int")) {
                    try {
                        return Integer.parseInt(dato);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return null;
                    }
                } else {
                    if (tipo.equals("long")) {
                        try {
                            return Long.parseLong(dato);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            return null;
                        }
                    } else {
                        if (tipo.equals("float")) {
                            try {
                                return Float.parseFloat(dato);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                return null;
                            }
                        } else {
                            if (tipo.equals("double")) {
                                try {
                                    return Double.parseDouble(dato);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                    return null;
                                }
                            } else {
                                if (tipo.equals("date")) {
                                    SimpleDateFormat formato =
                                        new SimpleDateFormat("yyyy-MM-dd");
                                    Date fechaDate = null;
                                    try {
                                        fechaDate = formato.parse(dato);
                                        return fechaDate;
                                    } catch (ParseException ex) {
                                        ex.printStackTrace();
                                        return null;
                                    }
                                } else {
                                    if (tipo.equals("datetime")) {
                                        //SimpleDateFormat formato1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        SimpleDateFormat formato1 =
                                            new SimpleDateFormat("yyyy-MM-dd");
                                        Date fechaDate1 = null;
                                        try {
                                            fechaDate1 = formato1.parse(dato);
                                            return fechaDate1;
                                        } catch (ParseException ex) {
                                            ex.printStackTrace();
                                            return null;
                                        }
                                    } else {
                                        if (tipo.equals("boolean")) {
                                            //varía según la nomenclatura usada
                                            if (dato.equals("N")) {
                                                return false;
                                            } else {
                                                if (dato.equals("S")) {
                                                    return true;
                                                } else {
                                                    return null;
                                                }
                                            }
                                        } else {
                                            return null;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
 

 
 
    public static String getIpAddress(){
        String strIpAddress = "1";
        /*
        try {
           HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
           String ipAddress = request.getHeader("X-FORWARDED-FOR");
           if (ipAddress == null) {
               ipAddress = request.getRemoteAddr();
           }
           //System.out.println("ipAddress:" + ipAddress);
        } catch (Exception e) {
            logger.error(Priority.ERROR_INT, e);
        }
        */
        return strIpAddress;
    }
    
    public static String getMACAddress(){
           String strMACAddress = "";
           try {
              /*HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
              String ipAddress = request.getHeader("X-FORWARDED-FOR");
              if (ipAddress == null) {
                  ipAddress = request.getRemoteAddr();
              }*/
              //System.out.println("ipAddress:" + ipAddress);
           } catch (Exception e) {
               logger.error(Priority.ERROR_INT, e);
           }    
           return strMACAddress;
       }
    
    public static boolean transferFile(String mSrcFileName, //ruta /ruta/123.pdf 
                                             int    nuItemID, //123 si quiero le mando
                                           String mURLServlet, //http://10.80.0.160:7003/gestionNotificacion/...
                                             String mQueryString,//null
                                             String nuCorrDoc)//1
          {
            
               
            
          logger.debug(">>-------------------------->>");  
          logger.debug(">>mSrcFileName=" + mSrcFileName);
          logger.debug(">>nuItemID=" + nuItemID);
          logger.debug(">>mURLServlet=" + mURLServlet);
          logger.debug(">>mQueryString=" + mQueryString);
          logger.debug(">>nuCorrDoc=" + nuCorrDoc);
          
        // get the fully qualified filename and the mere filename.
            String fqfn = mSrcFileName;
            String fname = fqfn.substring(fqfn.lastIndexOf(File.separator)+1).replace("null","");
            logger.debug(">>fname>>" + fname);
            try{
              //importTable importer = jbInit.getImportTable();
              // create the file to be uploaded and a connection to servlet.
              File fileToUpload = new File(fqfn);
              long fileSize = fileToUpload.length();
              // get last mod of this file.
              // The last mod is sent to the servlet as a header.
              long lastMod = fileToUpload.lastModified();
              String strLastMod = String.valueOf(lastMod);
              
              logger.debug(">>strLastMod>>" + strLastMod);
              
              URL serverURL = new URL(mURLServlet);
              URLConnection serverCon = serverURL.openConnection();
              
              // a bunch of connection setup related things.
              serverCon.setDoInput(true);
              serverCon.setDoOutput(true);
              
              // Don't use a cached version of URL connection.
              serverCon.setUseCaches (false);
              serverCon.setDefaultUseCaches (false);
              
              // set headers and their values.
              serverCon.setRequestProperty("Content-Type", "application/octet-stream");
              serverCon.setRequestProperty("Content-Length", Long.toString(fileToUpload.length()));
              serverCon.setRequestProperty("fileName", mSrcFileName.substring(mSrcFileName.lastIndexOf(File.separator)+1).replace("null", ""));
              serverCon.setRequestProperty("fileLastMod", strLastMod);
              serverCon.setRequestProperty("fileQueryString",    mQueryString);
              serverCon.setRequestProperty("fileIdentificador", nuCorrDoc);
              logger.debug(">>" + "fileName" + ">>" + mSrcFileName.substring(mSrcFileName.lastIndexOf(File.separator) + 1).replace("null", ""));
              if (true) 
logger.debug("TEST: 01");
              
              // create file stream and write stream to write file data.
              FileInputStream fis = new FileInputStream(fileToUpload);
              OutputStream os = serverCon.getOutputStream();
logger.debug("TEST: 02");
              try{
                // transfer the file in 4K chunks.
                byte[] buffer = new byte[4096];
                long byteCnt = 0;
                //long percent = 0;
                int newPercent = 0;
                int oldPercent = 0;
logger.debug("TEST: 03");
                while (true){
                  int bytes = fis.read(buffer);
                  byteCnt += bytes;
                  //11-21-02 :
                  //If itemID is greater than -1 this is an import file  transfer
                  //otherwise this is a header graphic file transfer.
                  if (nuItemID > -1){
                    newPercent = (int) ((double) byteCnt/ (double) fileSize * 100.0);
                    int diff = newPercent - oldPercent;
                    if (newPercent == 0 || diff >= 20){
                      oldPercent = newPercent;
                      //jbInit.getImportTable().displayFileTransferStatus(itemID, newPercent);
                    }
                  }
                  if (bytes < 0) break;
                  os.write(buffer, 0, bytes);
                }
logger.debug("TEST: 04");
                os.flush();
logger.debug("TEST: 05");
                if (true) logger.debug("Nro. de bytes enviados: " + byteCnt);
logger.debug("TEST: 06");
              }finally{
                os.close();
                fis.close();
              }
logger.debug("TEST: 07");
              if (true) logger.debug("OK: Transferencia de Archivo Completa");
                BufferedReader reader = new BufferedReader( new InputStreamReader(serverCon.getInputStream()));
logger.debug("TEST: 08");
              try{
                String line;
logger.debug("TEST: 09");
                while ((line = reader.readLine()) != null){
                   if (true) logger.debug(line);
                }
logger.debug("TEST: 10");
              }finally {
                      // close the reader stream from servlet.
               reader.close();
              }
            } // end of the big try block.

            catch (Exception e){
              logger.debug("Exception during file transfer:\n" + e);
              e.printStackTrace();
              logger.debug("Servlet failed. See Java Console for Errors.");
              return false;
            }  // end of catch block.
            logger.debug("File: " + fname + " successfully transferred.");
            return true;
          }
    
    public static java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date==null? null : date.getTime());
    }
    

    public static void copyFileUsingStream(String origen, String destino) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(origen);
            os = new FileOutputStream(destino);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
    public static void doVerEsquela(File ficheroPDF) throws Exception {
        
        logger.debug("### doResponsePdf");
        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = new FileInputStream(ficheroPDF);
        byte[] bytes = new byte[4 * 1024];
        int read = 0;
        if (!ctx.getResponseComplete()) {
            String fileName = ficheroPDF.getName();
            String contentType = "application/pdf";
            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");
            ServletOutputStream out = response.getOutputStream();
            while ((read = fis.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            ctx.responseComplete();
            ficheroPDF.delete();
        }
    }
    
    public static String getToken(String urlWs, String form_urlencoded){
        String strToken = "";
        try{
            
            byte[] postData = form_urlencoded.getBytes(StandardCharsets.UTF_8); 
            int postDataLength = postData.length; 
            //String request = "http://desweblogic2.indecopi.gob.pe:10000/appTodosServicioAutorizacion/oauth/token"; 
            URL url = new URL(urlWs); 
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
            conn.setDoOutput(true); 
            conn.setInstanceFollowRedirects(false); 
            conn.setRequestMethod("POST"); 
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
            conn.setRequestProperty("charset", "utf-8"); 
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength)); 
            conn.setUseCaches(false); 
            try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) { 
                wr.write(postData); 
            } 
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            //creamos un StringBuilder para almacenar la respuesta del web service
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = br.read()) != -1)
            {
              sb.append((char) cp);
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            System.out.println("TOKEN : " +jsonObject.getString("access_token"));
            strToken=jsonObject.getString("access_token");
            conn.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }  
        
        return strToken;
    }
    
    public static String getURLFORM_URLENCODE(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");    
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }    
        return result.toString();
    }
    public static String doFechaPlantilla(Date fecha){

        String formato;
        if (fecha!=null){
            String Mes[]={"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
            SimpleDateFormat dia = new SimpleDateFormat("dd");
            SimpleDateFormat mes = new SimpleDateFormat("MM");
            SimpleDateFormat anio = new SimpleDateFormat("yyyy");
            
            String vdia=dia.format(fecha);
            String vmes=mes.format(fecha);
            String vanio=anio.format(fecha);
            
            int cmes=Integer.parseInt(vmes);
            
        for(int i=0; i<12; i++){
            logger.debug(i+" | "+mes);
            if(i==(cmes-1)){
                vmes=Mes[i];
            }
        }
            formato=vdia+" de "+vmes+" del "+vanio;
            logger.debug(formato);
        }else
        {
            formato=""; 
        }

    return formato;
    }

    @SuppressWarnings("oracle.jdeveloper.java.semantic-warning")
    public static List<String> doListDataSource(){
        List<String> lst=new ArrayList<String>();
        String data=new String();
        data.valueOf("Solicitud");
        lst.add(data);
        return lst;
    }
    
    public static String getAnio(){
        Calendar cal= Calendar.getInstance();
        int year= cal.get(Calendar.YEAR); 
    return ""+year;
    }
    
    public static void doDownloadFile(String vcRuta,String vcNomArchivo,String vcExtension,  String vcNombreDescarga){
    try{
        FacesContext ctx ;
        FileInputStream fis;
        byte[] bytes;
        FileDataSource ds = new FileDataSource(vcRuta+vcNomArchivo+"."+vcExtension);  
        int read;
            ctx = FacesContext.getCurrentInstance();
            fis = new FileInputStream(vcRuta+vcNomArchivo+"."+vcExtension);
            bytes = new byte[1000];
            read = 0;
        if (!ctx.getResponseComplete()) {
            String fileName = vcNombreDescarga+"."+vcExtension;
            logger.info(fileName);
            String contentType = "";
            contentType = ds.getContentType();
            HttpServletResponse response =
                (HttpServletResponse)ctx.getExternalContext().getResponse();
            response.setContentType(contentType);
            response.setHeader("Content-Disposition",
                               "attachment;filename=\"" + fileName + "\"");
            ServletOutputStream out = response.getOutputStream();
            while ((read = fis.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            
            ctx.responseComplete();

        }
        
    } catch (Exception e) {
    
        logger.error(Priority.ERROR_INT, e);
    }

    }
    
    public static Long doNulongArchivo(String vcUrlArchivo){
        Long nuTamanio=0L;
        logger.info("Ruta: "+vcUrlArchivo);
        java.io.File file=new java.io.File(vcUrlArchivo);
        nuTamanio=file.length();
        logger.info("Tamanio: "+nuTamanio);
        return nuTamanio;
    }
    public static  String doReverse(String str){
        char[] try1 = str.toCharArray();
        String reverse="";
     
            for (int i = try1.length - 1; i >= 0; i--)
                reverse+=try1[i]+"";
            
            return reverse;
            
    }
    
    public static  String doExtensionReverse(String str){
        char[] try1 = str.toCharArray();
        String reverse="";
     
            for (int i = try1.length - 1; i >= 0; i--)
                if(!(try1[i]+"").equals("."))
                    reverse+=try1[i]+"";
                else
                    break;
            
            return reverse;
            
    }
    
    public static  String doNameReverse(String str){
        char[] try1 = str.toCharArray();
        String reverse="";
        int z=0;
        int x=0;
        for (int i =0; i< try1.length;  i++){
                if(!(try1[i]+"").equals(".") && z==0){
                
                    }
                else{
                    z=1;
                    if(x!=0){
                    reverse+=try1[i]+"";
                    }
                    
                    x=1;
                }
        } 
            
            return reverse;
            
    }
    
    public static String getExtension(String str){
        String extension=doReverse(doExtensionReverse(str));
        return extension;
    }
    
    public static String getName(String str){
        String reverse=doReverse(doNameReverse(doReverse(str)));
    return reverse;
    }
        
    
}
