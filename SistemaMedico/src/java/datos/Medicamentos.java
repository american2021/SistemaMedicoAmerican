package datos;
// Generated 13/12/2023 1:09:47 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Medicamentos generated by hbm2java
 */
public class Medicamentos  implements java.io.Serializable {


     private Integer medId;
     private String medNombre;
     private int medDosisUnitaria;
     private String medMedida;
     private Date medFechaUlt;
     private String medUsuario;
     private Set tratamientoses = new HashSet(0);

    public Medicamentos() {
    }

	
    public Medicamentos(String medNombre, int medDosisUnitaria, String medMedida, Date medFechaUlt, String medUsuario) {
        this.medNombre = medNombre;
        this.medDosisUnitaria = medDosisUnitaria;
        this.medMedida = medMedida;
        this.medFechaUlt = medFechaUlt;
        this.medUsuario = medUsuario;
    }
    public Medicamentos(String medNombre, int medDosisUnitaria, String medMedida, Date medFechaUlt, String medUsuario, Set tratamientoses) {
       this.medNombre = medNombre;
       this.medDosisUnitaria = medDosisUnitaria;
       this.medMedida = medMedida;
       this.medFechaUlt = medFechaUlt;
       this.medUsuario = medUsuario;
       this.tratamientoses = tratamientoses;
    }
   
    public Integer getMedId() {
        return this.medId;
    }
    
    public void setMedId(Integer medId) {
        this.medId = medId;
    }
    public String getMedNombre() {
        return this.medNombre;
    }
    
    public void setMedNombre(String medNombre) {
        this.medNombre = medNombre;
    }
    public int getMedDosisUnitaria() {
        return this.medDosisUnitaria;
    }
    
    public void setMedDosisUnitaria(int medDosisUnitaria) {
        this.medDosisUnitaria = medDosisUnitaria;
    }
    public String getMedMedida() {
        return this.medMedida;
    }
    
    public void setMedMedida(String medMedida) {
        this.medMedida = medMedida;
    }
    public Date getMedFechaUlt() {
        return this.medFechaUlt;
    }
    
    public void setMedFechaUlt(Date medFechaUlt) {
        this.medFechaUlt = medFechaUlt;
    }
    public String getMedUsuario() {
        return this.medUsuario;
    }
    
    public void setMedUsuario(String medUsuario) {
        this.medUsuario = medUsuario;
    }
    public Set getTratamientoses() {
        return this.tratamientoses;
    }
    
    public void setTratamientoses(Set tratamientoses) {
        this.tratamientoses = tratamientoses;
    }




}


