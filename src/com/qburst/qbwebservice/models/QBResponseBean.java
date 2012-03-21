package com.qburst.qbwebservice.models;

import java.util.ArrayList;

public class QBResponseBean extends QBBaseBean {


	private String status;	
	private ArrayList<QBWebserviceModel> subcategories;	
	private String description;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<QBWebserviceModel> getSubCat() {
		return subcategories;
	}

	public void setSubCat(ArrayList<QBWebserviceModel> subCat) {
		this.subcategories = subCat;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String description) {
		this.description = description;
	}
	
}
