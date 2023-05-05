package datos;
// Generated 03-may-2023 10:36:26 by Hibernate Tools 4.3.1


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
     private Date perNac;
     private String perGrupoSanguineo;
     private char perIdentidadGenero;
     private int perProfesion;
     private String perResidencia;
     private String perProcedencia;
     private String perTelefono;
     private String perContactoEmergencia;
     private String perParentesco;
     private String perEstadoCivil;
     private int perEdad;
     private Date perFechaUlt;
     private String perUsuario;
     private Set usuarioses = new HashSet(0);
     private Set historiases = new HashSet(0);
     private Set antecedenteses = new HashSet(0);

    public Personas() {
    }

	
    public Personas(String perCedula, String perNombres, String perApellidos, char perSexo, Date perNac, String perGrupoSanguineo, char perIdentidadGenero, int perProfesion, String perResidencia, String perProcedencia, String perTelefono, String perContactoEmergencia, String perParentesco, String perEstadoCivil, int perEdad, Date perFechaUlt, String perUsuario) {
        this.perCedula = perCedula;
        this.perNombres = perNombres;
        this.perApellidos = perApellidos;
        this.perSexo = perSexo;
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
    }
    public Personas(String perCedula, String perNombres, String perApellidos, char perSexo, Date perNac, String perGrupoSanguineo, char perIdentidadGenero, int perProfesion, String perResidencia, String perProcedencia, String perTelefono, String perContactoEmergencia, String perParentesco, String perEstadoCivil, int perEdad, Date perFechaUlt, String perUsuario, Set usuarioses, Set historiases, Set antecedenteses) {
       this.perCedula = perCedula;
       this.perNombres = perNombres;
       this.perApellidos = perApellidos;
       this.perSexo = perSexo;
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
       this.usuarioses = usuarioses;
       this.historiases = historiases;
       this.antecedenteses = antecedenteses;
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
    public int getPerProfesion() {
        return this.perProfesion;
    }
    
    public void setPerProfesion(int perProfesion) {
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
    public int getPerEdad() {
        return this.perEdad;
    }
    
    public void setPerEdad(int perEdad) {
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
    public Set getUsuarioses() {
        return this.usuarioses;
    }
    
    public void setUsuarioses(Set usuarioses) {
        this.usuarioses = usuarioses;
    }
    public Set getHistoriases() {
        return this.historiases;
    }
    
    public void setHistoriases(Set historiases) {
        this.historiases = historiases;
    }
    public Set getAntecedenteses() {
        return this.antecedenteses;
    }
    
    public void setAntecedenteses(Set antecedenteses) {
        this.antecedenteses = antecedenteses;
    }




}


