package com.qburst.qbwebservice.api;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.qburst.qbwebservice.R;
import com.qburst.qbwebservice.listeners.QBApiResponseListener;
import com.qburst.qbwebservice.listeners.QBWebserviceListener;
import com.qburst.qbwebservice.models.QBWebserviceModel;
import com.qburst.qbwebservice.models.QBRequestBean;
import com.qburst.qbwebservice.models.QBResponseBean;

public class QBTestApi implements QBApiResponseListener {

	String _email, _password;
	int _categoryId;
	QBWebserviceListener _internApiListner;
	private Context context;

	public QBTestApi(String email, String password, int categoryId,
			QBWebserviceListener callingActivity) {

		this._email = email;
		this._password = password;
		this._categoryId = categoryId;
		this._internApiListner = callingActivity;

	}

	public void invoke() {
		String apiUrl = QMApiConstants.kBaseURL
				+ QMApiConstants.kSubCategoriesURL;

		QBRequestBean subCatRequest = new QBRequestBean();
		subCatRequest.setEmail(_email);
		subCatRequest.setPassword(_password);
		subCatRequest.setCatId(_categoryId);

		QBBaseWebService webserviceTask = new QBBaseWebService(this,
				subCatRequest.toJsonString(), QBResponseBean.class,
				QMApiConstants.kSubCategoryWebServiceId);

		webserviceTask.execute(apiUrl);
	}

	@Override
	public void onResponseReceived(Map<String, Object> response, int apiID) {

		QBResponseBean respBean = new QBResponseBean();
		if (response.containsKey(QMApiConstants.kApiSuccessMsgKey)) {

			Log.v("response", response.toString());

			respBean = (QBResponseBean) response
					.get(QMApiConstants.kResponseBeanKey);

			if (respBean.getStatus().equals("1")) {
				ArrayList<QBWebserviceModel> CatList = new ArrayList<QBWebserviceModel>();
				CatList = respBean.getSubCat();

				_internApiListner.onSuccess(CatList, apiID);
			} else {
				String errorMsg = respBean.getDesc();
				_internApiListner.onFail(errorMsg, apiID);
			}
		}
	}

	@Override
	public void onFailedToGetResponse(Map<String, Object> errorResponse,
			int apiID) {

		String error = null;
		if (errorResponse.containsKey(QMApiConstants.kNetworkError)) {
			error = context.getString(R.string.kNetworkError);
			Log.e("Server Response", "No Network");
		} else if (errorResponse.containsKey(QMApiConstants.kTimeoutError)) {
			error = context.getString(R.string.kTimeoutError);
			Log.e("Server Response", "Server Timeout");
		} else if (errorResponse.containsKey(QMApiConstants.kApiFailMsgKey))
			error = context.getString(R.string.kApiError);

		_internApiListner.onFail("" + error, apiID);
	}
}
