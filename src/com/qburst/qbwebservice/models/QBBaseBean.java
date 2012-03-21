package com.qburst.qbwebservice.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class QBBaseBean {
	
	
	public QBBaseBean() {
		
	}

	public String toJsonString() {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(this);
	}

	
}
