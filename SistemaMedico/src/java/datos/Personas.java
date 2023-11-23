package datos;
// Generated 23/11/2023 16:28:38 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Personas generated by hbm2java
 */
public class Personas  implements java.io.Serializable {


     private Integer perId;
     private String perCedula;
     private String perNombres;
     private String perApellidos;
     private char perSexo;
     private Character perEsPaciente;
     private Date perNac;
     private String perGrupoSanguineo;
     private char perIdentidadGenero;
     private String perProfesion;
     private String perResidencia;
     private String perProcedencia;
     private String perTelefono;
     private String perContactoEmergencia;
     private String perParentesco;
     private String perEstadoCivil;
     private String perEdad;
     private Date perFechaUlt;
     private String perUsuario;
     private char perOrientacionSexual;
     private Set usuarioses = new HashSet(0);
     private Set historiasesForMedicoPerId = new HashSet(0);
     private Set historiasesForPacientePerId = new HashSet(0);

    public Personas() {
    }

	
    public Personas(String perCedula, String perNombres, String perApellidos, char perSexo, Date perNac, String perGrupoSanguineo, char perIdentidadGenero, String perProfesion, String perEstadoCivil, String perEdad, Date perFechaUlt, String perUsuario, char perOrientacionSexual) {
        this.perCedula = perCedula;
        this.perNombres = perNombres;
        this.perApellidos = perApellidos;
        this.perSexo = perSexo;
        this.perNac = perNac;
        this.perGrupoSanguineo = perGrupoSanguineo;
        this.perIdentidadGenero = perIdentidadGenero;
        this.perProfesion = perProfesion;
        this.perEstadoCivil = perEstadoCivil;
        this.perEdad = perEdad;
        this.perFechaUlt = perFechaUlt;
        this.perUsuario = perUsuario;
        this.perOrientacionSexual = perOrientacionSexual;
    }
    public Personas(String perCedula, String perNombres, String perApellidos, char perSexo, Character perEsPaciente, Date perNac, String perGrupoSanguineo, char perIdentidadGenero, String perProfesion, String perResidencia, String perProcedencia, String perTelefono, String perContactoEmergencia, String perParentesco, String perEstadoCivil, String perEdad, Date perFechaUlt, String perUsuario, char perOrientacionSexual, Set usuarioses, Set historiasesForMedicoPerId, Set historiasesForPacientePerId) {
       this.perCedula = perCedula;
       this.perNombres = perNombres;
       this.perApellidos = perApellidos;
       this.perSexo = perSexo;
       this.perEsPaciente = perEsPaciente;
       this.perNac = perNac;
       this.perGrupoSanguineo = perGrupoSanguineo;
       this.perIdentidadGenero = perIdentidadGenero;
       this.perProfesion = perProfesion;
       this.perResidencia = perResidencia;
       this.perProcedencia = perProcedencia;
       this.perTelefono = perTelefono;
       this.perContactoEmergencia = perContactoEmergencia;
       this.perParentesco = perParentesco;
       this.perEstadoCivil = perEstadoCivil;
       this.perEdad = perEdad;
       this.perFechaUlt = perFechaUlt;
       this.perUsuario = perUsuario;
       this.perOrientacionSexual = perOrientacionSexual;
       this.usuarioses = usuarioses;
       this.historiasesForMedicoPerId = historiasesForMedicoPerId;
       this.historiasesForPacientePerId = historiasesForPacientePerId;
    }
   
    public Integer getPerId() {
        return this.perId;
    }
    
    public void setPerId(Integer perId) {
        this.perId = perId;
    }
    public String getPerCedula() {
        return this.perCedula;
    }
    
    public void setPerCedula(String perCedula) {
        this.perCedula = perCedula;
    }
    public String getPerNombres() {
        return this.perNombres;
    }
    
    public void setPerNombres(String perNombres) {
        this.perNombres = perNombres;
    }
    public String getPerApellidos() {
        return this.perApellidos;
    }
    
    public void setPerApellidos(String perApellidos) {
        this.perApellidos = perApellidos;
    }
    public char getPerSexo() {
        return this.perSexo;
    }
    
    public void setPerSexo(char perSexo) {
        this.perSexo = perSexo;
    }
    public Character getPerEsPaciente() {
        return this.perEsPaciente;
    }
    
    public void setPerEsPaciente(Character perEsPaciente) {
        this.perEsPaciente = perEsPaciente;
    }
    public Date getPerNac() {
        return this.perNac;
    }
    
    public void setPerNac(Date perNac) {
        this.perNac = perNac;
    }
    public String getPerGrupoSanguineo() {
        return this.perGrupoSanguineo;
    }
    
    public void setPerGrupoSanguineo(String perGrupoSanguineo) {
        this.perGrupoSanguineo = perGrupoSanguineo;
    }
    public char getPerIdentidadGenero() {
        return this.perIdentidadGenero;
    }
    
    public void setPerIdentidadGenero(char perIdentidadGenero) {
        this.perIdentidadGenero = perIdentidadGenero;
    }
    public String getPerProfesion() {
        return this.perProfesion;
    }
    
    public void setPerProfesion(String perProfesion) {
        this.perProfesion = perProfesion;
    }
    public String getPerResidencia() {
        return this.perResidencia;
    }
    
    public void setPerResidencia(String perResidencia) {
        this.perResidencia = perResidencia;
    }
    public String getPerProcedencia() {
        return this.perProcedencia;
    }
    
    public void setPerProcedencia(String perProcedencia) {
        this.perProcedencia = perProcedencia;
    }
    public String getPerTelefono() {
        return this.perTelefono;
    }
    
    public void setPerTelefono(String perTelefono) {
        this.perTelefono = perTelefono;
    }
    public String getPerContactoEmergencia() {
        return this.perContactoEmergencia;
    }
    
    public void setPerContactoEmergencia(String perContactoEmergencia) {
        this.perContactoEmergencia = perContactoEmergencia;
    }
    public String getPerParentesco() {
        return this.perParentesco;
    }
    
    public void setPerParentesco(String perParentesco) {
        this.perParentesco = perParentesco;
    }
    public String getPerEstadoCivil() {
        return this.perEstadoCivil;
    }
    
    public void setPerEstadoCivil(String perEstadoCivil) {
        this.perEstadoCivil = perEstadoCivil;
    }
    public String getPerEdad() {
        return this.perEdad;
    }
    
    public void setPerEdad(String perEdad) {
        this.perEdad = perEdad;
    }
    public Date getPerFechaUlt() {
        return this.perFechaUlt;
    }
    
    public void setPerFechaUlt(Date perFechaUlt) {
        this.perFechaUlt = perFechaUlt;
    }
    public String getPerUsuario() {
        return this.perUsuario;
    }
    
    public void setPerUsuario(String perUsuario) {
        this.perUsuario = perUsuario;
    }
    public char getPerOrientacionSexual() {
        return this.perOrientacionSexual;
    }
    
    public void setPerOrientacionSexual(char perOrientacionSexual) {
        this.perOrientacionSexual = perOrientacionSexual;
    }
    public Set getUsuarioses() {
        return this.usuarioses;
    }
    
    public void setUsuarioses(Set usuarioses) {
        this.usuarioses = usuarioses;
    }
    public Set getHistoriasesForMedicoPerId() {
        return this.historiasesForMedicoPerId;
    }
    
    public void setHistoriasesForMedicoPerId(Set historiasesForMedicoPerId) {
        this.historiasesForMedicoPerId = historiasesForMedicoPerId;
    }
    public Set getHistoriasesForPacientePerId() {
        return this.historiasesForPacientePerId;
    }
    
    public void setHistoriasesForPacientePerId(Set historiasesForPacientePerId) {
        this.historiasesForPacientePerId = historiasesForPacientePerId;
    }




}


