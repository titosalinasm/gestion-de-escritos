package indecopi.gob.pe.bean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClsDocumentosBean implements Serializable{
    
    @SuppressWarnings("compatibility:-960344601474697800")
    private static final long serialVersionUID = 2926213481048610747L;

        private String vcCorrelativo;
	private String vcRegistro;
	private String vcDocumento;
	private String vcRemitente;
	private String vcDocRepresentado;
	private String vcRepresentado;
	private String vcSiglaArea;
	private String vcAreaNombre;
	private String vcSedeNombre;
	private Integer nuIdSede;
	private String vcObservacionCompleta;
	private String vcObservacionCorta;
	private Integer nuEstado;
	private Integer nuEstadoExterno;
	private Integer nuIdRegistro;
	private String vcCorreo;
	private String vcEstadoInterno;
	private String vcEstadoExterno;
	private String vcDestinatarios;
	private String vcDescTipoDocSgd;
	private String vcDescModIng;
	private String vcNroExpediente;
	private String vcEsRepresentante;
	private String vcNroTelefonico;
        
        //para vincular
        private Integer nuIdTipoVinculacion;
        private List<ClsArchivoBean> lstArchivo;
        private Date dtFechaPresentacion;
        private Integer nuIdTipoArchivo=4;
        
        //flag si ya esta vinculado
        private boolean blVinculado=false;
        
    public ClsDocumentosBean(){
        this.setLstArchivo(new ArrayList<ClsArchivoBean>());
        this.setDtFechaPresentacion(new Date());
    }

    public void setBlVinculado(boolean blVinculado) {
        this.blVinculado = blVinculado;
    }

    public boolean isBlVinculado() {
        return blVinculado;
    }

    public void setNuIdTipoArchivo(Integer nuIdTipoArchivo) {
        this.nuIdTipoArchivo = nuIdTipoArchivo;
    }

    public Integer getNuIdTipoArchivo() {
        return nuIdTipoArchivo;
    }

    public void setDtFechaPresentacion(Date dtFechaPresentacion) {
        this.dtFechaPresentacion = dtFechaPresentacion;
    }

    public Date getDtFechaPresentacion() {
        return dtFechaPresentacion;
    }

    public void setNuIdTipoVinculacion(Integer nuIdTipoVinculacion) {
        this.nuIdTipoVinculacion = nuIdTipoVinculacion;
    }

    public Integer getNuIdTipoVinculacion() {
        return nuIdTipoVinculacion;
    }

    public String getVcDescTipoDocSgd() {
		return vcDescTipoDocSgd;
	}
	public void setVcDescTipoDocSgd(String vcDescTipoDocSgd) {
		this.vcDescTipoDocSgd = vcDescTipoDocSgd;
	}
	public String getVcCorrelativo() {
		return vcCorrelativo;
	}
	public void setVcCorrelativo(String vcCorrelativo) {
		this.vcCorrelativo = vcCorrelativo;
	}
	public String getVcRegistro() {
		return vcRegistro;
	}
	public void setVcRegistro(String vcRegistro) {
		this.vcRegistro = vcRegistro;
	}
	public String getVcDocumento() {
		return vcDocumento;
	}
	public void setVcDocumento(String vcDocumento) {
		this.vcDocumento = vcDocumento;
	}
	public String getVcRemitente() {
		return vcRemitente;
	}
	public void setVcRemitente(String vcRemitente) {
		this.vcRemitente = vcRemitente;
	}
	public String getVcDocRepresentado() {
		return vcDocRepresentado;
	}
	public void setVcDocRepresentado(String vcDocRepresentado) {
		this.vcDocRepresentado = vcDocRepresentado;
	}
	public String getVcRepresentado() {
		return vcRepresentado;
	}
	public void setVcRepresentado(String vcRepresentado) {
		this.vcRepresentado = vcRepresentado;
	}
	public String getVcSiglaArea() {
		return vcSiglaArea;
	}
	public void setVcSiglaArea(String vcSiglaArea) {
		this.vcSiglaArea = vcSiglaArea;
	}
	public String getVcAreaNombre() {
		return vcAreaNombre;
	}
	public void setVcAreaNombre(String vcAreaNombre) {
		this.vcAreaNombre = vcAreaNombre;
	}
	public String getVcSedeNombre() {
		return vcSedeNombre;
	}
	public void setVcSedeNombre(String vcSedeNombre) {
		this.vcSedeNombre = vcSedeNombre;
	}
	public Integer getNuIdSede() {
		return nuIdSede;
	}
	public void setNuIdSede(Integer nuIdSede) {
		this.nuIdSede = nuIdSede;
	}
	public String getVcObservacionCompleta() {
		return vcObservacionCompleta;
	}
	public void setVcObservacionCompleta(String vcObservacionCompleta) {
		this.vcObservacionCompleta = vcObservacionCompleta;
	}
	public String getVcObservacionCorta() {
		return vcObservacionCorta;
	}
	public void setVcObservacionCorta(String vcObservacionCorta) {
		this.vcObservacionCorta = vcObservacionCorta;
	}
	public Integer getNuEstado() {
		return nuEstado;
	}
	public void setNuEstado(Integer nuEstado) {
		this.nuEstado = nuEstado;
	}
	public Integer getNuEstadoExterno() {
		return nuEstadoExterno;
	}
	public void setNuEstadoExterno(Integer nuEstadoExterno) {
		this.nuEstadoExterno = nuEstadoExterno;
	}
	public Integer getNuIdRegistro() {
		return nuIdRegistro;
	}
	public void setNuIdRegistro(Integer nuIdRegistro) {
		this.nuIdRegistro = nuIdRegistro;
	}
	public String getVcCorreo() {
		return vcCorreo;
	}
	public void setVcCorreo(String vcCorreo) {
		this.vcCorreo = vcCorreo;
	}
	public String getVcEstadoInterno() {
		return vcEstadoInterno;
	}
	public void setVcEstadoInterno(String vcEstadoInterno) {
		this.vcEstadoInterno = vcEstadoInterno;
	}
	public String getVcEstadoExterno() {
		return vcEstadoExterno;
	}
	public void setVcEstadoExterno(String vcEstadoExterno) {
		this.vcEstadoExterno = vcEstadoExterno;
	}
	public String getVcDestinatarios() {
		return vcDestinatarios;
	}
	public void setVcDestinatarios(String vcDestinatarios) {
		this.vcDestinatarios = vcDestinatarios;
	}
	public String getVcDescModIng() {
		return vcDescModIng;
	}
	public void setVcDescModIng(String vcDescModIng) {
		this.vcDescModIng = vcDescModIng;
	}
	public String getVcNroExpediente() {
		return vcNroExpediente;
	}
	public void setVcNroExpediente(String vcNroExpediente) {
		this.vcNroExpediente = vcNroExpediente;
	}
	public String getVcEsRepresentante() {
		return vcEsRepresentante;
	}
	public void setVcEsRepresentante(String vcEsRepresentante) {
		this.vcEsRepresentante = vcEsRepresentante;
	}
	public String getVcNroTelefonico() {
		return vcNroTelefonico;
	}
	public void setVcNroTelefonico(String vcNroTelefonico) {
		this.vcNroTelefonico = vcNroTelefonico;
	}


    public void setLstArchivo(List<ClsArchivoBean> lstArchivo) {
        this.lstArchivo = lstArchivo;
    }

    public List<ClsArchivoBean> getLstArchivo() {
        return lstArchivo;
    }


}
