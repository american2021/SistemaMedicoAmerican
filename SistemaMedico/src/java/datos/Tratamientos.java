package datos;
// Generated 12/12/2023 11:30:07 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tratamientos generated by hbm2java
 */
public class Tratamientos  implements java.io.Serializable {


     private Integer traId;
     private Medicamentos medicamentos;
     private String traCodigoCie;
     private String traEdicionCie;
     private String traEstado;
     private String traFrecuencia;
     private String traViaAdministracion;
     private int traDuracion;
     private int traCantidad;
     private String traIndicaciones;
     private String traAdvertencias;
     private Date traFechaUlt;
     private String traUsuario;
     private Set historiaTratamientos = new HashSet(0);

    public Tratamientos() {
    }

	
    public Tratamientos(Medicamentos medicamentos, String traEstado, String traFrecuencia, String traViaAdministracion, int traDuracion, int traCantidad, String traIndicaciones, String traAdvertencias, Date traFechaUlt, String traUsuario) {
        this.medicamentos = medicamentos;
        this.traEstado = traEstado;
        this.traFrecuencia = traFrecuencia;
        this.traViaAdministracion = traViaAdministracion;
        this.traDuracion = traDuracion;
        this.traCantidad = traCantidad;
        this.traIndicaciones = traIndicaciones;
        this.traAdvertencias = traAdvertencias;
        this.traFechaUlt = traFechaUlt;
        this.traUsuario = traUsuario;
    }
    public Tratamientos(Medicamentos medicamentos, String traCodigoCie, String traEdicionCie, String traEstado, String traFrecuencia, String traViaAdministracion, int traDuracion, int traCantidad, String traIndicaciones, String traAdvertencias, Date traFechaUlt, String traUsuario, Set historiaTratamientos) {
       this.medicamentos = medicamentos;
       this.traCodigoCie = traCodigoCie;
       this.traEdicionCie = traEdicionCie;
       this.traEstado = traEstado;
       this.traFrecuencia = traFrecuencia;
       this.traViaAdministracion = traViaAdministracion;
       this.traDuracion = traDuracion;
       this.traCantidad = traCantidad;
       this.traIndicaciones = traIndicaciones;
       this.traAdvertencias = traAdvertencias;
       this.traFechaUlt = traFechaUlt;
       this.traUsuario = traUsuario;
       this.historiaTratamientos = historiaTratamientos;
    }
   
    public Integer getTraId() {
        return this.traId;
    }
    
    public void setTraId(Integer traId) {
        this.traId = traId;
    }
    public Medicamentos getMedicamentos() {
        return this.medicamentos;
    }
    
    public void setMedicamentos(Medicamentos medicamentos) {
        this.medicamentos = medicamentos;
    }
    public String getTraCodigoCie() {
        return this.traCodigoCie;
    }
    
    public void setTraCodigoCie(String traCodigoCie) {
        this.traCodigoCie = traCodigoCie;
    }
    public String getTraEdicionCie() {
        return this.traEdicionCie;
    }
    
    public void setTraEdicionCie(String traEdicionCie) {
        this.traEdicionCie = traEdicionCie;
    }
    public String getTraEstado() {
        return this.traEstado;
    }
    
    public void setTraEstado(String traEstado) {
        this.traEstado = traEstado;
    }
    public String getTraFrecuencia() {
        return this.traFrecuencia;
    }
    
    public void setTraFrecuencia(String traFrecuencia) {
        this.traFrecuencia = traFrecuencia;
    }
    public String getTraViaAdministracion() {
        return this.traViaAdministracion;
    }
    
    public void setTraViaAdministracion(String traViaAdministracion) {
        this.traViaAdministracion = traViaAdministracion;
    }
    public int getTraDuracion() {
        return this.traDuracion;
    }
    
    public void setTraDuracion(int traDuracion) {
        this.traDuracion = traDuracion;
    }
    public int getTraCantidad() {
        return this.traCantidad;
    }
    
    public void setTraCantidad(int traCantidad) {
        this.traCantidad = traCantidad;
    }
    public String getTraIndicaciones() {
        return this.traIndicaciones;
    }
    
    public void setTraIndicaciones(String traIndicaciones) {
        this.traIndicaciones = traIndicaciones;
    }
    public String getTraAdvertencias() {
        return this.traAdvertencias;
    }
    
    public void setTraAdvertencias(String traAdvertencias) {
        this.traAdvertencias = traAdvertencias;
    }
    public Date getTraFechaUlt() {
        return this.traFechaUlt;
    }
    
    public void setTraFechaUlt(Date traFechaUlt) {
        this.traFechaUlt = traFechaUlt;
    }
    public String getTraUsuario() {
        return this.traUsuario;
    }
    
    public void setTraUsuario(String traUsuario) {
        this.traUsuario = traUsuario;
    }
    public Set getHistoriaTratamientos() {
        return this.historiaTratamientos;
    }
    
    public void setHistoriaTratamientos(Set historiaTratamientos) {
        this.historiaTratamientos = historiaTratamientos;
    }




}


