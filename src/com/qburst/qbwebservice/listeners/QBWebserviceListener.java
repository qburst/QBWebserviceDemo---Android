package com.qburst.qbwebservice.listeners;

import java.util.ArrayList;

import com.qburst.qbwebservice.models.QBWebserviceModel;

public interface QBWebserviceListener {

	public void onSuccess(ArrayList<QBWebserviceModel> subcatList, int apiID);
	public void onFail(String errorResponse, int apiID);
}
