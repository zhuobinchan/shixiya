package com.ehighsun.shixiya.pojo;
// default package



/**
 * CProductAnswer entity. @author MyEclipse Persistence Tools
 */

public class CProductAnswer  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private CProductQuestionList CProductQuestionList;
     private Student student;
     private String content;


    // Constructors

    /** default constructor */
    public CProductAnswer() {
    }

    
    /** full constructor */
    public CProductAnswer(CProductQuestionList CProductQuestionList, Student student, String content) {
        this.CProductQuestionList = CProductQuestionList;
        this.student = student;
        this.content = content;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public CProductQuestionList getCProductQuestionList() {
        return this.CProductQuestionList;
    }
    
    public void setCProductQuestionList(CProductQuestionList CProductQuestionList) {
        this.CProductQuestionList = CProductQuestionList;
    }

    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
   








}