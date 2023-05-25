package datos;
// Generated 22-may-2023 17:51:46 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tratamientos generated by hbm2java
 */
public class Tratamientos  implements java.io.Serializable {


     private Integer traId;
     private Medicamentos medicamentos;
     private String traMedicamento;
     private char traViaAdministracion;
     private String traDosisUnitaria;
     private String traUnidadAdministracion;
     private String traFrecuencia;
     private Date traInicioTratamiento;
     private Date traHora1;
     private Date traHora2;
     private Date traHora3;
     private Date traHora4;
     private Integer traDuracion;
     private Character traDuracionUnidadTiempo;
     private int traCantidad;
     private String traIndicaciones;
     private String traAdvertencias;
     private String traEvolucion;
     private Date traFechaUlt;
     private String traUsuario;
     private int diagnosticosDiagnosticosId;
     private Set medicamentoses = new HashSet(0);

    public Tratamientos() {
    }

	
    public Tratamientos(Medicamentos medicamentos, String traMedicamento, char traViaAdministracion, String traDosisUnitaria, String traUnidadAdministracion, String traFrecuencia, Date traInicioTratamiento, Date traHora1, Date traHora2, Date traHora3, Date traHora4, int traCantidad, String traIndicaciones, String traAdvertencias, String traEvolucion, Date traFechaUlt, String traUsuario, int diagnosticosDiagnosticosId) {
        this.medicamentos = medicamentos;
        this.traMedicamento = traMedicamento;
        this.traViaAdministracion = traViaAdministracion;
        this.traDosisUnitaria = traDosisUnitaria;
        this.traUnidadAdministracion = traUnidadAdministracion;
        this.traFrecuencia = traFrecuencia;
        this.traInicioTratamiento = traInicioTratamiento;
        this.traHora1 = traHora1;
        this.traHora2 = traHora2;
        this.traHora3 = traHora3;
        this.traHora4 = traHora4;
        this.traCantidad = traCantidad;
        this.traIndicaciones = traIndicaciones;
        this.traAdvertencias = traAdvertencias;
        this.traEvolucion = traEvolucion;
        this.traFechaUlt = traFechaUlt;
        this.traUsuario = traUsuario;
        this.diagnosticosDiagnosticosId = diagnosticosDiagnosticosId;
    }
    public Tratamientos(Medicamentos medicamentos, String traMedicamento, char traViaAdministracion, String traDosisUnitaria, String traUnidadAdministracion, String traFrecuencia, Date traInicioTratamiento, Date traHora1, Date traHora2, Date traHora3, Date traHora4, Integer traDuracion, Character traDuracionUnidadTiempo, int traCantidad, String traIndicaciones, String traAdvertencias, String traEvolucion, Date traFechaUlt, String traUsuario, int diagnosticosDiagnosticosId, Set medicamentoses) {
       this.medicamentos = medicamentos;
       this.traMedicamento = traMedicamento;
       this.traViaAdministracion = traViaAdministracion;
       this.traDosisUnitaria = traDosisUnitaria;
       this.traUnidadAdministracion = traUnidadAdministracion;
       this.traFrecuencia = traFrecuencia;
       this.traInicioTratamiento = traInicioTratamiento;
       this.traHora1 = traHora1;
       this.traHora2 = traHora2;
       this.traHora3 = traHora3;
       this.traHora4 = traHora4;
       this.traDuracion = traDuracion;
       this.traDuracionUnidadTiempo = traDuracionUnidadTiempo;
       this.traCantidad = traCantidad;
       this.traIndicaciones = traIndicaciones;
       this.traAdvertencias = traAdvertencias;
       this.traEvolucion = traEvolucion;
       this.traFechaUlt = traFechaUlt;
       this.traUsuario = traUsuario;
       this.diagnosticosDiagnosticosId = diagnosticosDiagnosticosId;
       this.medicamentoses = medicamentoses;
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
    public String getTraMedicamento() {
        return this.traMedicamento;
    }
    
    public void setTraMedicamento(String traMedicamento) {
        this.traMedicamento = traMedicamento;
    }
    public char getTraViaAdministracion() {
        return this.traViaAdministracion;
    }
    
    public void setTraViaAdministracion(char traViaAdministracion) {
        this.traViaAdministracion = traViaAdministracion;
    }
    public String getTraDosisUnitaria() {
        return this.traDosisUnitaria;
    }
    
    public void setTraDosisUnitaria(String traDosisUnitaria) {
        this.traDosisUnitaria = traDosisUnitaria;
    }
    public String getTraUnidadAdministracion() {
        return this.traUnidadAdministracion;
    }
    
    public void setTraUnidadAdministracion(String traUnidadAdministracion) {
        this.traUnidadAdministracion = traUnidadAdministracion;
    }
    public String getTraFrecuencia() {
        return this.traFrecuencia;
    }
    
    public void setTraFrecuencia(String traFrecuencia) {
        this.traFrecuencia = traFrecuencia;
    }
    public Date getTraInicioTratamiento() {
        return this.traInicioTratamiento;
    }
    
    public void setTraInicioTratamiento(Date traInicioTratamiento) {
        this.traInicioTratamiento = traInicioTratamiento;
    }
    public Date getTraHora1() {
        return this.traHora1;
    }
    
    public void setTraHora1(Date traHora1) {
        this.traHora1 = traHora1;
    }
    public Date getTraHora2() {
        return this.traHora2;
    }
    
    public void setTraHora2(Date traHora2) {
        this.traHora2 = traHora2;
    }
    public Date getTraHora3() {
        return this.traHora3;
    }
    
    public void setTraHora3(Date traHora3) {
        this.traHora3 = traHora3;
    }
    public Date getTraHora4() {
        return this.traHora4;
    }
    
    public void setTraHora4(Date traHora4) {
        this.traHora4 = traHora4;
    }
    public Integer getTraDuracion() {
        return this.traDuracion;
    }
    
    public void setTraDuracion(Integer traDuracion) {
        this.traDuracion = traDuracion;
    }
    public Character getTraDuracionUnidadTiempo() {
        return this.traDuracionUnidadTiempo;
    }
    
    public void setTraDuracionUnidadTiempo(Character traDuracionUnidadTiempo) {
        this.traDuracionUnidadTiempo = traDuracionUnidadTiempo;
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
    public String getTraEvolucion() {
        return this.traEvolucion;
    }
    
    public void setTraEvolucion(String traEvolucion) {
        this.traEvolucion = traEvolucion;
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
    public int getDiagnosticosDiagnosticosId() {
        return this.diagnosticosDiagnosticosId;
    }
    
    public void setDiagnosticosDiagnosticosId(int diagnosticosDiagnosticosId) {
        this.diagnosticosDiagnosticosId = diagnosticosDiagnosticosId;
    }
    public Set getMedicamentoses() {
        return this.medicamentoses;
    }
    
    public void setMedicamentoses(Set medicamentoses) {
        this.medicamentoses = medicamentoses;
    }




}


