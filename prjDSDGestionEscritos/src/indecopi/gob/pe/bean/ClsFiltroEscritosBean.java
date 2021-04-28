package indecopi.gob.pe.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import java.util.Map;

import javax.faces.model.SelectItem;

public class ClsFiltroEscritosBean implements Serializable{
        
    @SuppressWarnings("compatibility:-4890665927029978219")
    private static final long serialVersionUID = 4983938108867570976L;

    private Integer nuIdPerfil;
            private Integer nuIdRrHh;
            private String vcUsuario;
            private Integer nuIdLocal=1;
            private Integer nuIdDependencia=10014;
            private Integer nuIdTipoDocSgd=35;
            private Integer nuEstadoInterno=-1;
            private Integer nuIdTipoDocId;
            private String vcNroDocIdentidad;
            private String vcRemitente;
            private Integer nuIdModalidadIngreso=-1;
            private Date dtFechaIni;
            private Date dtFechaFin;
            
            private String vcNroCorrelativo;
            
            private Map<String, String> lstDependencia;
            private Map<String, String> lstTipoDocumento;
            private Map<String, String> lstTipoDocSGD;
            private Map<String, String> lstLocales;
            private Map<String, String> lstEstados;
            private Map<String, String> lstModalidadIngreso;
            
            private Map<String, String> lstTipoVinculacion;
            private Map<String, String> lstTipoArchivo;

    public void setLstTipoArchivo(Map<String, String> lstTipoArchivo) {
        this.lstTipoArchivo = lstTipoArchivo;
    }

    public Map<String, String> getLstTipoArchivo() {
        return lstTipoArchivo;
    }

    public void setLstTipoVinculacion(Map<String, String> lstTipoVinculacion) {
        this.lstTipoVinculacion = lstTipoVinculacion;
    }

    public Map<String, String> getLstTipoVinculacion() {
        return lstTipoVinculacion;
    }

    public Integer getNuIdPerfil() {
                    return nuIdPerfil;
            }

            public void setNuIdPerfil(Integer nuIdPerfil) {
                    this.nuIdPerfil = nuIdPerfil;
            }

            public Integer getNuIdRrHh() {
                    return nuIdRrHh;
            }

            public void setNuIdRrHh(Integer nuIdRrHh) {
                    this.nuIdRrHh = nuIdRrHh;
            }

            public String getVcUsuario() {
                    return vcUsuario;
            }

            public void setVcUsuario(String vcUsuario) {
                    this.vcUsuario = vcUsuario;
            }

            public Integer getNuIdLocal() {
                    return nuIdLocal;
            }

            public void setNuIdLocal(Integer nuIdLocal) {
                    this.nuIdLocal = nuIdLocal;
            }

            public Integer getNuIdDependencia() {
                    return nuIdDependencia;
            }

            public void setNuIdDependencia(Integer nuIdDependencia) {
                    this.nuIdDependencia = nuIdDependencia;
            }

            public Integer getNuIdTipoDocSgd() {
                    return nuIdTipoDocSgd;
            }

            public void setNuIdTipoDocSgd(Integer nuIdTipoDocSgd) {
                    this.nuIdTipoDocSgd = nuIdTipoDocSgd;
            }

            public Integer getNuEstadoInterno() {
                    return nuEstadoInterno;
            }

            public void setNuEstadoInterno(Integer nuEstadoInterno) {
                    this.nuEstadoInterno = nuEstadoInterno;
            }

            public Integer getNuIdTipoDocId() {
                    return nuIdTipoDocId;
            }

            public void setNuIdTipoDocId(Integer nuIdTipoDocId) {
                    this.nuIdTipoDocId = nuIdTipoDocId;
            }

            public String getVcNroDocIdentidad() {
                    return vcNroDocIdentidad;
            }

            public void setVcNroDocIdentidad(String vcNroDocIdentidad) {
                    this.vcNroDocIdentidad = vcNroDocIdentidad;
            }

            public String getVcRemitente() {
                    return vcRemitente;
            }

            public void setVcRemitente(String vcRemitente) {
                    this.vcRemitente = vcRemitente;
            }

            public void setNuIdModalidadIngreso(Integer nuIdModalidadIngreso) {
                this.nuIdModalidadIngreso = nuIdModalidadIngreso;
            }
        
            public Integer getNuIdModalidadIngreso() {
                return nuIdModalidadIngreso;
            }

            public Date getDtFechaIni() {
                    return dtFechaIni;
            }

            public void setDtFechaIni(Date dtFechaIni) {
                    this.dtFechaIni = dtFechaIni;
            }

            public Date getDtFechaFin() {
                    return dtFechaFin;
            }

            public void setDtFechaFin(Date dtFechaFin) {
                    this.dtFechaFin = dtFechaFin;
            }

            public String getVcNroCorrelativo() {
                    return vcNroCorrelativo;
            }

            public void setVcNroCorrelativo(String vcNroCorrelativo) {
                    this.vcNroCorrelativo = vcNroCorrelativo;
            }


    public void setLstDependencia(Map<String, String> lstDependencia) {
        this.lstDependencia = lstDependencia;
    }

    public Map<String, String> getLstDependencia() {
        return lstDependencia;
    }

    public void setLstTipoDocumento(Map<String, String> lstTipoDocumento) {
        this.lstTipoDocumento = lstTipoDocumento;
    }

    public Map<String, String> getLstTipoDocumento() {
        return lstTipoDocumento;
    }

    public void setLstTipoDocSGD(Map<String, String> lstTipoDocSGD) {
        this.lstTipoDocSGD = lstTipoDocSGD;
    }

    public Map<String, String> getLstTipoDocSGD() {
        return lstTipoDocSGD;
    }

    public void setLstLocales(Map<String, String> lstLocales) {
        this.lstLocales = lstLocales;
    }

    public Map<String, String> getLstLocales() {
        return lstLocales;
    }

    public void setLstEstados(Map<String, String> lstEstados) {
        this.lstEstados = lstEstados;
    }

    public Map<String, String> getLstEstados() {
        return lstEstados;
    }

    public void setLstModalidadIngreso(Map<String, String> lstModalidadIngreso) {
        this.lstModalidadIngreso = lstModalidadIngreso;
    }

    public Map<String, String> getLstModalidadIngreso() {
        return lstModalidadIngreso;
    }
}
