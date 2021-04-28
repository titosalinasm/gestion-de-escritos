package indecopi.gob.pe.bean;

import java.io.Serializable;

public class ClsValidacionBean implements Serializable{
    
    @SuppressWarnings("compatibility:-3886424704019413285")
    private static final long serialVersionUID = -32819352693212447L;
    //Filtro principal
    private boolean blTipoApp=false;
    
    //Filtro Agregar
    private boolean blATipoApp=false;
    private boolean blAAreaDoc=false;
    private boolean blANomCoor=false;
    private boolean blAUsuRed=false;
    private boolean blAUsuSSE=false;


    public void setBlTipoApp(boolean blTipoApp) {
        this.blTipoApp = blTipoApp;
    }

    public boolean isBlTipoApp() {
        return blTipoApp;
    }

    public void setBlATipoApp(boolean blATipoApp) {
        this.blATipoApp = blATipoApp;
    }

    public boolean isBlATipoApp() {
        return blATipoApp;
    }

    public void setBlAAreaDoc(boolean blAAreaDoc) {
        this.blAAreaDoc = blAAreaDoc;
    }

    public boolean isBlAAreaDoc() {
        return blAAreaDoc;
    }

    public void setBlANomCoor(boolean blANomCoor) {
        this.blANomCoor = blANomCoor;
    }

    public boolean isBlANomCoor() {
        return blANomCoor;
    }

    public void setBlAUsuRed(boolean blAUsuRed) {
        this.blAUsuRed = blAUsuRed;
    }

    public boolean isBlAUsuRed() {
        return blAUsuRed;
    }

    public void setBlAUsuSSE(boolean blAUsuSSE) {
        this.blAUsuSSE = blAUsuSSE;
    }

    public boolean isBlAUsuSSE() {
        return blAUsuSSE;
    }

}
