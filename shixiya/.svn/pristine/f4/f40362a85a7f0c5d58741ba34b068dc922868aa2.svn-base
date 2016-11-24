package com.ehighsun.shixiya.pojo;
// default package



/**
 * CProductStudentApplyId entity. @author MyEclipse Persistence Tools
 */

public class CProductStudentApplyId  implements java.io.Serializable {


    // Fields    

     private String telephone;
     private CProductBroadcast CProductBroadcast;


    // Constructors

    /** default constructor */
    public CProductStudentApplyId() {
    }

    
    /** full constructor */
    public CProductStudentApplyId(String telephone, CProductBroadcast CProductBroadcast) {
        this.telephone = telephone;
        this.CProductBroadcast = CProductBroadcast;
    }

   
    // Property accessors

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public CProductBroadcast getCProductBroadcast() {
        return this.CProductBroadcast;
    }
    
    public void setCProductBroadcast(CProductBroadcast CProductBroadcast) {
        this.CProductBroadcast = CProductBroadcast;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CProductStudentApplyId) ) return false;
		 CProductStudentApplyId castOther = ( CProductStudentApplyId ) other; 
         
		 return ( (this.getTelephone()==castOther.getTelephone()) || ( this.getTelephone()!=null && castOther.getTelephone()!=null && this.getTelephone().equals(castOther.getTelephone()) ) )
 && ( (this.getCProductBroadcast()==castOther.getCProductBroadcast()) || ( this.getCProductBroadcast()!=null && castOther.getCProductBroadcast()!=null && this.getCProductBroadcast().equals(castOther.getCProductBroadcast()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTelephone() == null ? 0 : this.getTelephone().hashCode() );
         result = 37 * result + ( getCProductBroadcast() == null ? 0 : this.getCProductBroadcast().hashCode() );
         return result;
   }   





}