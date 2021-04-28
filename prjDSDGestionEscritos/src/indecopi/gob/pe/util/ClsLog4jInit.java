package indecopi.gob.pe.util;


import indecopi.gob.pe.sync.Planificador;

import java.io.File;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.apache.log4j.PropertyConfigurator;
import javax.servlet.http.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.DailyRollingFileAppender;

public class ClsLog4jInit extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(ClsLog4jInit.class);

  
    public void init(ServletConfig config) throws ServletException {
                    System.out.println("Inicializando log4j");
                    String log4jLocation = config.getInitParameter("log4j-init-file");
                    ServletContext sc = config.getServletContext();
                    if (log4jLocation == null) {
                            System.out.println("-> No se encontró el parámetro de configuración log4j-init-file, se iniciará con BasicConfigurator.");
                            //BasicConfigurator.configure();
                    } else {
                            String webAppPath = sc.getRealPath("/");
                            String log4jProp = webAppPath + log4jLocation;
                            File fileLog = new File(log4jProp);
                            if (fileLog.exists()) {
                                    System.out.println("Configurando con : " + log4jProp);
                                    PropertyConfigurator.configure(log4jProp);
                                    System.out.println("Log4g configurado!");
                            } else {
                                    System.out.println("-> " + log4jProp + " archivo no encontrado, inicializando con BasicConfigurator.");
                                    //BasicConfigurator.configure();
                            }
                    }
                    super.init(config);
                    
                    new Planificador ( ) ;
            }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    response.setContentType("text/html");
    }
}

