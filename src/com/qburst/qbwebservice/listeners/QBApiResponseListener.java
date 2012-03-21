/*
 * Package Name : com.qmcommerce.android.webservices
 * Author : QMCommerce
 * Copyright : QMCommerce @ 2010-2011
 * Description : Basic web service callback used by APIs .
 */

package com.qburst.qbwebservice.listeners;

import java.util.Map;

public interface QBApiResponseListener {

	public void onResponseReceived(Map<String, Object> response, int apiID);
	public void onFailedToGetResponse(Map<String, Object> errorResponse, int apiID);
}
