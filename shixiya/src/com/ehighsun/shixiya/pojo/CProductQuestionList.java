package com.ehighsun.shixiya.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * CProductQuestionList entity. @author MyEclipse Persistence Tools
 */

public class CProductQuestionList  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private CProductQuestion CProductQuestion;
     private String title;
     private Set CProductAnswers = new HashSet(0);


    // Constructors

    /** default constructor */
    public CProductQuestionList() {
    }

    
    /** full constructor */
    public CProductQuestionList(CProductQuestion CProductQuestion, String title, Set CProductAnswers) {
        this.CProductQuestion = CProductQuestion;
        this.title = title;
        this.CProductAnswers = CProductAnswers;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public CProductQuestion getCProductQuestion() {
        return this.CProductQuestion;
    }
    
    public void setCProductQuestion(CProductQuestion CProductQuestion) {
        this.CProductQuestion = CProductQuestion;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public Set getCProductAnswers() {
        return this.CProductAnswers;
    }
    
    public void setCProductAnswers(Set CProductAnswers) {
        this.CProductAnswers = CProductAnswers;
    }
   








}