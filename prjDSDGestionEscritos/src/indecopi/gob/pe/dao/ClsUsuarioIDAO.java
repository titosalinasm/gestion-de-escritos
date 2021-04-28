package indecopi.gob.pe.dao;

import indecopi.gob.pe.bean.ClsUsuarioIndBean;
import indecopi.gob.pe.utils.ClsResultDAO;

public interface ClsUsuarioIDAO {
    public ClsResultDAO getUsuarioGlobal(ClsUsuarioIndBean objUsuarioInd);
}
