package indecopi.gob.pe.dao;

import indecopi.gob.pe.bean.ClsDocumentosBean;
import indecopi.gob.pe.bean.ClsFiltroExpVinBean;
import indecopi.gob.pe.bean.ClsUsuarioBean;
import indecopi.gob.pe.utils.ClsResultDAO;

public interface ClsIND7GestionEscritosIDAO {
    public ClsResultDAO doVincular(ClsDocumentosBean objDocumento, ClsUsuarioBean  objUsuarioBean);
    public ClsResultDAO doLstPrincipales();
    public ClsResultDAO doLstExpVinculados(ClsFiltroExpVinBean objFiltro, ClsUsuarioBean  objUsuarioBean);
    public ClsResultDAO doLstArchivo(Integer nuIdExpVin);
    public ClsResultDAO getFlagVinculado(String vcDocRef);
}
