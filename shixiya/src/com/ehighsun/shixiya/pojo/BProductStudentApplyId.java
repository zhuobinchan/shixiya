package com.ehighsun.shixiya.pojo;
// default package



/**
 * BProductStudentApplyId entity. @author MyEclipse Persistence Tools
 */

public class BProductStudentApplyId  implements java.io.Serializable {


    // Fields    

     private BProductCourse BProductCourse;
     private String telephone;


    // Constructors

    /** default constructor */
    public BProductStudentApplyId() {
    }

    
    /** full constructor */
    public BProductStudentApplyId(BProductCourse BProductCourse, String telephone) {
        this.BProductCourse = BProductCourse;
        this.telephone = telephone;
    }

   
    // Property accessors

    public BProductCourse getBProductCourse() {
        return this.BProductCourse;
    }
    
    public void setBProductCourse(BProductCourse BProductCourse) {
        this.BProductCourse = BProductCourse;
    }

    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BProductStudentApplyId) ) return false;
		 BProductStudentApplyId castOther = ( BProductStudentApplyId ) other; 
         
		 return ( (this.getBProductCourse()==castOther.getBProductCourse()) || ( this.getBProductCourse()!=null && castOther.getBProductCourse()!=null && this.getBProductCourse().equals(castOther.getBProductCourse()) ) )
 && ( (this.getTelephone()==castOther.getTelephone()) || ( this.getTelephone()!=null && castOther.getTelephone()!=null && this.getTelephone().equals(castOther.getTelephone()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBProductCourse() == null ? 0 : this.getBProductCourse().hashCode() );
         result = 37 * result + ( getTelephone() == null ? 0 : this.getTelephone().hashCode() );
         return result;
   }   





}