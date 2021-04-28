package indecopi.gob.pe.bean;

import java.io.Serializable;

import java.util.Date;

public class ClsFiltroExpVinBean implements Serializable{
    @SuppressWarnings("compatibility:5452729115098364501")
    private static final long serialVersionUID = 6014807024558883559L;
    
    private Date dtFechaIni;
    private Date dtFechaFin;
    private Integer nuIdTipoVinculacion;
    private String vcNroExpediente;
    private String vcNroDoc;
    private String vcCorrreo;
    
    public ClsFiltroExpVinBean() {
       this.setDtFechaIni(new Date());
       this.setDtFechaFin(new Date());
    }


    public void setDtFechaIni(Date dtFechaIni) {
        this.dtFechaIni = dtFechaIni;
    }

    public Date getDtFechaIni() {
        return dtFechaIni;
    }

    public void setDtFechaFin(Date dtFechaFin) {
        this.dtFechaFin = dtFechaFin;
    }

    public Date getDtFechaFin() {
        return dtFechaFin;
    }

    public void setNuIdTipoVinculacion(Integer nuIdTipoVinculacion) {
        this.nuIdTipoVinculacion = nuIdTipoVinculacion;
    }

    public Integer getNuIdTipoVinculacion() {
        return nuIdTipoVinculacion;
    }

    public void setVcNroExpediente(String vcNroExpediente) {
        this.vcNroExpediente = vcNroExpediente;
    }

    public String getVcNroExpediente() {
        return vcNroExpediente;
    }

    public void setVcNroDoc(String vcNroDoc) {
        this.vcNroDoc = vcNroDoc;
    }

    public String getVcNroDoc() {
        return vcNroDoc;
    }

    public void setVcCorrreo(String vcCorrreo) {
        this.vcCorrreo = vcCorrreo;
    }

    public String getVcCorrreo() {
        return vcCorrreo;
    }
}
