package com.ehighsun.shixiya.pojo;
// default package

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;


/**
 * LablePaste entity. @author MyEclipse Persistence Tools
 */
@JSONType(orders = { "CProductBroadcasts"})
public class LablePaste  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String imageUrl;
     private String cssClassName;
     private Set CProductBroadcasts = new HashSet(0);


    // Constructors

    /** default constructor */
    public LablePaste() {
    }

    
    /** full constructor */
    public LablePaste(String name, String imageUrl, Set CProductBroadcasts) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.CProductBroadcasts = CProductBroadcasts;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JSONField(serialize = false)
	public Set getCProductBroadcasts() {
		return CProductBroadcasts;
	}


	public void setCProductBroadcasts(Set cProductBroadcasts) {
		CProductBroadcasts = cProductBroadcasts;
	}


	public String getCssClassName() {
		return cssClassName;
	}


	public void setCssClassName(String cssClassName) {
		this.cssClassName = cssClassName;
	}



}