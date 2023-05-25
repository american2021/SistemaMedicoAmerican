package datos;
// Generated 22-may-2023 17:51:46 by Hibernate Tools 4.3.1



/**
 * CiudadesId generated by hbm2java
 */
public class CiudadesId  implements java.io.Serializable {


     private String paiCodigo;
     private String prvCodigo;
     private String ciuCodigo;

    public CiudadesId() {
    }

    public CiudadesId(String paiCodigo, String prvCodigo, String ciuCodigo) {
       this.paiCodigo = paiCodigo;
       this.prvCodigo = prvCodigo;
       this.ciuCodigo = ciuCodigo;
    }
   
    public String getPaiCodigo() {
        return this.paiCodigo;
    }
    
    public void setPaiCodigo(String paiCodigo) {
        this.paiCodigo = paiCodigo;
    }
    public String getPrvCodigo() {
        return this.prvCodigo;
    }
    
    public void setPrvCodigo(String prvCodigo) {
        this.prvCodigo = prvCodigo;
    }
    public String getCiuCodigo() {
        return this.ciuCodigo;
    }
    
    public void setCiuCodigo(String ciuCodigo) {
        this.ciuCodigo = ciuCodigo;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CiudadesId) ) return false;
		 CiudadesId castOther = ( CiudadesId ) other; 
         
		 return ( (this.getPaiCodigo()==castOther.getPaiCodigo()) || ( this.getPaiCodigo()!=null && castOther.getPaiCodigo()!=null && this.getPaiCodigo().equals(castOther.getPaiCodigo()) ) )
 && ( (this.getPrvCodigo()==castOther.getPrvCodigo()) || ( this.getPrvCodigo()!=null && castOther.getPrvCodigo()!=null && this.getPrvCodigo().equals(castOther.getPrvCodigo()) ) )
 && ( (this.getCiuCodigo()==castOther.getCiuCodigo()) || ( this.getCiuCodigo()!=null && castOther.getCiuCodigo()!=null && this.getCiuCodigo().equals(castOther.getCiuCodigo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPaiCodigo() == null ? 0 : this.getPaiCodigo().hashCode() );
         result = 37 * result + ( getPrvCodigo() == null ? 0 : this.getPrvCodigo().hashCode() );
         result = 37 * result + ( getCiuCodigo() == null ? 0 : this.getCiuCodigo().hashCode() );
         return result;
   }   


}


