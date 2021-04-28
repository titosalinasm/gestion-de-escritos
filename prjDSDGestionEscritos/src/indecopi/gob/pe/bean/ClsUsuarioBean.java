package indecopi.gob.pe.bean;

import java.io.Serializable;

public class ClsUsuarioBean implements Serializable{
    @SuppressWarnings("compatibility:-1058981403240572678")
    private static final long serialVersionUID = 1L;
    private String vcUsuario;
    private String vcNombre;
    private String vcEmail;
    private int nuIdPerfil;
    private int nuIdRRHH;
    private String vcArea;
    private String vcSubArea;
    public ClsUsuarioBean() {
        super();
        this.nuIdRRHH=0;
    }

    public void setVcUsuario(String vcUsuario) {
        this.vcUsuario = vcUsuario;
    }

    public String getVcUsuario() {
        return vcUsuario;
    }

    public void setVcNombre(String vcNombre) {
        this.vcNombre = vcNombre;
    }

    public String getVcNombre() {
        return vcNombre;
    }

    public void setNuIdPerfil(int nuIdPerfil) {
        this.nuIdPerfil = nuIdPerfil;
    }

    public int getNuIdPerfil() {
        return nuIdPerfil;
    }

    public void setNuIdRRHH(int nuIdRRHH) {
        this.nuIdRRHH = nuIdRRHH;
    }

    public int getNuIdRRHH() {
        return nuIdRRHH;
    }

    public void setVcArea(String vcArea) {
        this.vcArea = vcArea;
    }

    public String getVcArea() {
        return vcArea;
    }

    public void setVcSubArea(String vcSubArea) {
        this.vcSubArea = vcSubArea;
    }

    public String getVcSubArea() {
        return vcSubArea;
    }

    public void setVcEmail(String vcEmail) {
        this.vcEmail = vcEmail;
    }

    public String getVcEmail() {
        return vcEmail;
    }
}
