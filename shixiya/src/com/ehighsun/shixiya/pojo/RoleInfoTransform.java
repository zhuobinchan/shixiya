package com.ehighsun.shixiya.pojo;
// default package



/**
 * RoleInfoTransform entity. @author MyEclipse Persistence Tools
 */

public class RoleInfoTransform  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String roleId;
     private String userId;
     private Integer roleType;


    // Constructors

    /** default constructor */
    public RoleInfoTransform() {
    }

    
    /** full constructor */
    public RoleInfoTransform(String roleId, String userId, Integer roleType) {
        this.roleId = roleId;
        this.userId = userId;
        this.roleType = roleType;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleType() {
        return this.roleType;
    }
    
    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
   








}