package indecopi.gob.pe.bean;

import java.io.Serializable;

import org.richfaces.model.UploadItem;


public class ClsArchivoBean implements Serializable {
    
    @SuppressWarnings("compatibility:-2535321065705865653")
    private static final long serialVersionUID = 3211060003383602790L;


    private String vcNombreOriginal;
	private Long nuLong;
	private String vcRutalFinal;
	private String vcExtension;
	private String vcRutaAlmacenamiento;
	private String vcNombreFinal;
        //Vincular
        private Integer nuIdTipoArchivo;
        private UploadItem dataFile;
        
    

    public void setDataFile(UploadItem dataFile) {
        this.dataFile = dataFile;
    }

    public UploadItem getDataFile() {
        return dataFile;
    }

    public void setNuIdTipoArchivo(Integer nuIdTipoArchivo) {
        this.nuIdTipoArchivo = nuIdTipoArchivo;
    }

    public Integer getNuIdTipoArchivo() {
        return nuIdTipoArchivo;
    }

    public String getVcNombreOriginal() {
		return vcNombreOriginal;
	}
	public void setVcNombreOriginal(String vcNombreOriginal) {
		this.vcNombreOriginal = vcNombreOriginal;
	}
	public Long getNuLong() {
		return nuLong;
	}
	public void setNuLong(Long nuLong) {
		this.nuLong = nuLong;
	}
	public String getVcRutalFinal() {
		return vcRutalFinal;
	}
	public void setVcRutalFinal(String vcRutalFinal) {
		this.vcRutalFinal = vcRutalFinal;
	}
	public String getVcExtension() {
		return vcExtension;
	}
	public void setVcExtension(String vcExtension) {
		this.vcExtension = vcExtension;
	}
	public String getVcRutaAlmacenamiento() {
		return vcRutaAlmacenamiento;
	}
	public void setVcRutaAlmacenamiento(String vcRutaAlmacenamiento) {
		this.vcRutaAlmacenamiento = vcRutaAlmacenamiento;
	}
	public String getVcNombreFinal() {
		return vcNombreFinal;
	}
	public void setVcNombreFinal(String vcNombreFinal) {
		this.vcNombreFinal = vcNombreFinal;
	}
	
	
}
