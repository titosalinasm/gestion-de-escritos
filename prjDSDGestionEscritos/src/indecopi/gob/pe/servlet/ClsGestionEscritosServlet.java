package indecopi.gob.pe.servlet;

import indecopi.gob.pe.bean.ClsUsuarioBean;
import indecopi.gob.pe.bean.ClsUsuarioIndBean;
import indecopi.gob.pe.dao.ClsUsuarioDAO;
import indecopi.gob.pe.dao.ClsUsuarioIDAO;
import indecopi.gob.pe.srv.ClsGestionEscritosSrv;
import indecopi.gob.pe.utils.ClsConstantes;
import indecopi.gob.pe.utils.ClsCripto;

import indecopi.gob.pe.utils.ClsResultDAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

public class ClsGestionEscritosServlet extends HttpServlet {
    
    @SuppressWarnings("compatibility:6764946291336061806")
    private static final long serialVersionUID = -3971533404908244769L;

    static Logger logger = Logger.getLogger(ClsGestionEscritosServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String jgapps = request.getParameter(ClsConstantes.PARAMETRO_GET);
        
        //jgapps="324054515e4531454724100c3c140a0e1d11";   //rmontalvo DSD
        //jgapps="234e5a525a4b2e4f1a24100d761808"; //ccampos DSD - (COORD PRINC)
        //jgapps="2d4c495b4345334f1b24100c751a0c"; //mardian DSD - (ESPEC FORMA)
        //jgapps="21455e4d58412f52546d5d0c7119030b"; //aherrera DSD
        jgapps="234e5a525a4b2e4f1a24100d761808"; //ccampos DSD
        //jgapps="2d4c495b4345334f1b24100c751a0c"; //mardian DSD
        //jgapps="21455e4d58412f52546d5d0c7119030b"; //aherrera DSD
        //jgapps="2359525c454a3c4f1b24100c741c0e"; //cticona DSD
        //jgapps="235c4e565954384f1d24100c721e0c"; //cquispe DSD -MNCCQUI (ESPEC REGISTR)

        ClsCripto ClsCripto = new ClsCripto();
        String datosUsuario = ClsCripto.desencripta(jgapps);
        request.getSession().invalidate();
        String[] variables = datosUsuario.split(ClsConstantes.SEPARADOR);
        ClsUsuarioIndBean objUsuario = new ClsUsuarioIndBean();
        objUsuario.setVcUsuario(variables[0]);
        objUsuario.setNuIdPerfil(Integer.parseInt(variables[1].toString()));
        objUsuario.setNuIdRRHH(Integer.parseInt(variables[2].toString()));
        //Obtener el tipo de aplicacion
        //logger.info("... Start Aplication: " + vcTipoDoc + "..." );
        logger.info(">>Usuario: " + objUsuario.getVcUsuario());
        logger.info(">>Id RRHH: " + objUsuario.getNuIdRRHH());
        logger.info(">>Perfil: " + objUsuario.getNuIdPerfil());
    
        ClsGestionEscritosSrv objGestionEscritosSrv = (ClsGestionEscritosSrv) request.getSession().getAttribute("clsGestionEscritosSrv");

        ClsUsuarioBean objUsuarioBean = (ClsUsuarioBean) request.getSession().getAttribute("clsUsuarioBean");
        //ClsPermisoUsuarioBean objPermiso=(ClsPermisoUsuarioBean)request.getSession().getAttribute("clsPermisoUsuarioBean");
        
        if (!request.getSession().isNew())
            request.getSession(false).invalidate();       
        
        if(objUsuarioBean==null){
            ClsUsuarioIDAO objUsuarioDAO=new ClsUsuarioDAO();
            ClsResultDAO objResultadoDAO=objUsuarioDAO.getUsuarioGlobal(objUsuario);
            objUsuarioBean=(ClsUsuarioBean)objResultadoDAO.get("OBJ_USR_GLOBAL");
        }
        
        /*
        if(objPermiso==null){
            //Permisos
            ClsUsuarioIDAO objUsuarioDAO=new ClsUsuarioDAO();
            ResultDAO objResultPermisosDAO =objUsuarioDAO.doObtenerPrivilegiosUsuario(objUsuario, 149); //GENERACION DE DOCUMENTOS
            objPermiso=(ClsPermisoUsuarioBean)objResultPermisosDAO.get("OBJ_PERMISOS");
        }
        */
        
        if (objGestionEscritosSrv == null) {
            objGestionEscritosSrv = new ClsGestionEscritosSrv(objUsuario, objUsuarioBean);
        }

        request.getSession().setAttribute("clsGestionEscritosSrv", objGestionEscritosSrv);
        response.sendRedirect(request.getContextPath() + "/procesos/DSD/pgw_gestionEscritos.seam");
        
    }
}
