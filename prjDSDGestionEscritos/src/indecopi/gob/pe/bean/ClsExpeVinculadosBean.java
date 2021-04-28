package indecopi.gob.pe.bean;

import java.io.Serializable;

import java.util.Date;

public class ClsExpeVinculadosBean implements Serializable{
    @SuppressWarnings("compatibility:1432259177223504849")
    private static final long serialVersionUID = -5827476204470768832L;
    
    private Integer nuIdExpVin;
    private String vcNroExpediente;
    private String vcTipoVinculacion;
    private String vcNroDoc;
    private String vcCorreo;
    private Date dtFechaPresentacion;
    private Date dtFechaVinculacion;


    public void setNuIdExpVin(Integer nuIdExpVin) {
        this.nuIdExpVin = nuIdExpVin;
    }

    public Integer getNuIdExpVin() {
        return nuIdExpVin;
    }

    public void setVcNroExpediente(String vcNroExpediente) {
        this.vcNroExpediente = vcNroExpediente;
    }

    public String getVcNroExpediente() {
        return vcNroExpediente;
    }

    public void setVcTipoVinculacion(String vcTipoVinculacion) {
        this.vcTipoVinculacion = vcTipoVinculacion;
    }

    public String getVcTipoVinculacion() {
        return vcTipoVinculacion;
    }

    public void setVcNroDoc(String vcNroDoc) {
        this.vcNroDoc = vcNroDoc;
    }

    public String getVcNroDoc() {
        return vcNroDoc;
    }

    public void setVcCorreo(String vcCorreo) {
        this.vcCorreo = vcCorreo;
    }

    public String getVcCorreo() {
        return vcCorreo;
    }

    public void setDtFechaPresentacion(Date dtFechaPresentacion) {
        this.dtFechaPresentacion = dtFechaPresentacion;
    }

    public Date getDtFechaPresentacion() {
        return dtFechaPresentacion;
    }

    public void setDtFechaVinculacion(Date dtFechaVinculacion) {
        this.dtFechaVinculacion = dtFechaVinculacion;
    }

    public Date getDtFechaVinculacion() {
        return dtFechaVinculacion;
    }


}
