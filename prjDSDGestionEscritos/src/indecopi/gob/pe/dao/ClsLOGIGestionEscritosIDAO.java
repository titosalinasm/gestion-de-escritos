package indecopi.gob.pe.dao;

import indecopi.gob.pe.bean.ClsDocumentosBean;
import indecopi.gob.pe.bean.ClsFiltroEscritosBean;
import indecopi.gob.pe.bean.ClsUsuarioBean;
import indecopi.gob.pe.utils.ClsResultDAO;

public interface ClsLOGIGestionEscritosIDAO {
    public ClsResultDAO doLstDocumentos(ClsFiltroEscritosBean objFiltro);
    public ClsResultDAO doLstArchivo(ClsUsuarioBean objUsuario, ClsDocumentosBean objDocumento);
    public ClsResultDAO doLstPrincipales();
    public ClsResultDAO doLstDependencia(Integer nuIdDependencia);
    public ClsResultDAO doLstDocumentosSync(ClsFiltroEscritosBean objFiltro);
    public ClsResultDAO doLstDocumentosSyncAutomatico(ClsFiltroEscritosBean objFiltro);
}
