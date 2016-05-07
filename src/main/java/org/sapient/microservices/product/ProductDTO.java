package org.sapient.microservices.product;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

//@JsonRootName("Product")
public class ProductDTO implements Serializable{



	protected String code;
	
	protected String name;

	protected String type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}
