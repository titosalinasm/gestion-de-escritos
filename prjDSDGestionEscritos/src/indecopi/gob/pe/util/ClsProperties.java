package indecopi.gob.pe.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.Properties;

import org.apache.log4j.Logger;

public class ClsProperties implements Serializable {

  static Logger logger = Logger.getLogger(ClsProperties.class);

  public ClsProperties() {
    super();
  }

  public  String getDirectoryFileServerMarcas() {
    Properties props = new Properties();
    String dbPropsFile = "/indecopi/gob/pe/dao/connection.properties";
    String sFileServer = "";
    try {
      InputStream inputStream = this.getClass().getResourceAsStream(dbPropsFile);
      if (inputStream == null) {
        throw new FileNotFoundException("property file '" + dbPropsFile + "' not found in the classpath");
      }
      props.load(inputStream);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    String vc_so = "lnx";
    if (!System.getProperty("file.separator").equals("/"))
      vc_so = "win";

    sFileServer = props.getProperty("ruta_upload_" + vc_so);

    return sFileServer;
  }


}
