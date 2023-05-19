package datos;
// Generated 19-may-2023 9:47:12 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Medicamentos generated by hbm2java
 */
public class Medicamentos  implements java.io.Serializable {


     private Integer medId;
     private Tratamientos tratamientos;
     private String medNombre;
     private Date medFechaUlt;
     private String medUsuario;
     private Set tratamientoses = new HashSet(0);

    public Medicamentos() {
    }

	
    public Medicamentos(Tratamientos tratamientos, String medNombre, Date medFechaUlt, String medUsuario) {
        this.tratamientos = tratamientos;
        this.medNombre = medNombre;
        this.medFechaUlt = medFechaUlt;
        this.medUsuario = medUsuario;
    }
    public Medicamentos(Tratamientos tratamientos, String medNombre, Date medFechaUlt, String medUsuario, Set tratamientoses) {
       this.tratamientos = tratamientos;
       this.medNombre = medNombre;
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
    public Tratamientos getTratamientos() {
        return this.tratamientos;
    }
    
    public void setTratamientos(Tratamientos tratamientos) {
        this.tratamientos = tratamientos;
    }
    public String getMedNombre() {
        return this.medNombre;
    }
    
    public void setMedNombre(String medNombre) {
        this.medNombre = medNombre;
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


