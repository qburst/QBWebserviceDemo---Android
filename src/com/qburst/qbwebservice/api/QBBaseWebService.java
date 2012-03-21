package com.qburst.qbwebservice.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.qburst.qbwebservice.app.QBWebserviceDemoApp;
import com.qburst.qbwebservice.listeners.QBApiResponseListener;
import com.qburst.qbwebservice.models.QBBaseBean;
import com.qburst.qbwebservice.utilities.QBUtility;

public class QBBaseWebService extends AsyncTask<String, Void, String> {

	private String _postContent = null;
	private static final DefaultHttpClient httpClient = new DefaultHttpClient();
	private HttpResponse _httpResponse = null;
	private QBApiResponseListener _apiListener;
	private int _httpStatus = 0;
	private Class _responseClass;
	private String _authHeaderValue = null;
	private int _apiId;

	/**
	 * Basic constructor for a HTTP GET / POST.
	 * 
	 * @param apiListener
	 *            - QMResponse listener.
	 * @param postParams
	 *            - HTTP Post message.
	 * @param responseClass
	 *            - The response bean class which holds the webservice response
	 * @param apiId
	 */
	public QBBaseWebService(QBApiResponseListener apiListener,
			String _postContent, Class responseClass, int apiId) {
		this._apiListener = apiListener;
		this._postContent = _postContent;
		this._responseClass = responseClass;
		this._apiId = apiId;
	}

	public void setAuthenticationParams(String headerValue) {
		this._authHeaderValue = headerValue;

	}

	/**
	 * Get HTTP response in the background
	 */
	@Override
	protected String doInBackground(String... urls) {

		String serverResponse = null;
		String lang = "en";

		urls[0] += "?lang=" + lang;
		Log.d("url+lang", urls[0]);

		synchronized (httpClient) {

			if (QBUtility.isNetworkAvailable(QBWebserviceDemoApp
					.getSharedApplication())) {
				try {

					if (_postContent != null) {
						_httpResponse = this.doJsonPostRequest(urls[0]);
					} else {
						_httpResponse = this.doJsonGetRequest(urls[0]);
					}

					if (_httpResponse != null) {

						serverResponse = QBUtility
								.convertStreamToString(_httpResponse
										.getEntity().getContent());
						_httpStatus = _httpResponse.getStatusLine()
								.getStatusCode();
					} else {
						serverResponse = QBApiConstants.kTimeOutExceptionKey;
					}
				} catch (ClientProtocolException e) {
					Log.e(e.getClass().getName() + "doInBackground",
							e.getMessage(), e);
				} catch (IOException e) {
					Log.e(e.getClass().getName() + "doInBackground",
							e.getMessage(), e);
				}
			} else {
				serverResponse = QBApiConstants.kNetworkExceptionKey;
			}
		}

		Log.d("serverResponse", "" + serverResponse);
		return serverResponse;
	}

	/**
	 * Get response bean and return to calling Api
	 */

	@Override
	protected void onPostExecute(String serverResponse) {

		Map<String, Object> response = new HashMap<String, Object>();
		response.put(QBApiConstants.kHttpStatusKey, _httpStatus);

		if (serverResponse != null
				&& (_httpStatus == QBApiConstants.kHttpStatusOK || _httpStatus == QBApiConstants.kHttpStatusCreated)) {

			response.put(QBApiConstants.kApiSuccessMsgKey, serverResponse);

			Gson gson = new Gson();
			QBBaseBean baseBean = gson.fromJson(serverResponse, _responseClass);
			response.put(QBApiConstants.kResponseBeanKey, baseBean);

			Log.d("API STATUS", "API ID : " + _apiId + ", STATUS = "
					+ _httpStatus);

			_apiListener.onResponseReceived(response, _apiId);

		} else if (serverResponse == QBApiConstants.kNetworkExceptionKey) {

			response.put(QBApiConstants.kNetworkError, 1);
			_apiListener.onFailedToGetResponse(response, _apiId);

		} else if (serverResponse == QBApiConstants.kTimeOutExceptionKey) {

			response.put(QBApiConstants.kTimeoutError, 1);
			_apiListener.onFailedToGetResponse(response, _apiId);
		} else {
			response.put(QBApiConstants.kApiFailMsgKey, 1);
			Log.e("Invalid Server Response", "" + serverResponse);
			_apiListener.onFailedToGetResponse(response, _apiId);

		}

	}

	/**
	 * Performs a HTTP Get Request using JSON and returns an HTTP response.
	 * 
	 * @return http get response
	 * 
	 */
	public HttpResponse doJsonGetRequest(String _url)
			throws ConnectTimeoutException, SocketTimeoutException {

		HttpGet request = new HttpGet(_url);
		HttpResponse httpResponse = null;

		if (_authHeaderValue != null) {
			request.addHeader(QBApiConstants.kAuthHeaderKey, _authHeaderValue);
		}

		try {
			HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),
					QBApiConstants.kRequestTimeOutInMills);
			HttpConnectionParams.setSoTimeout(httpClient.getParams(),
					QBApiConstants.kRequestTimeOutInMills);

			httpResponse = httpClient.execute(request);
		} catch (ClientProtocolException e) {
			httpResponse = null;
		} catch (IOException e) {
			httpResponse = null;
		}
		return httpResponse;

	}

	/**
	 * Performs an HTTP Post request using JSON and returns an HTTP Response.
	 * 
	 * @return http post response
	 * 
	 */
	public HttpResponse doJsonPostRequest(String url) {
		HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),
				QBApiConstants.kRequestTimeOutInMills);
		HttpConnectionParams.setSoTimeout(httpClient.getParams(),
				QBApiConstants.kRequestTimeOutInMills);
		HttpResponse httpResponse = null;

		HttpPost httppost = new HttpPost(url);

		// Add post json body.
		StringEntity se;
		try {
			se = new StringEntity(_postContent);
			httppost.setHeader("Accept", "application/json");
			httppost.setHeader("Content-type", "application/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
					"application/json"));
			httppost.setEntity(se);

			Log.d("post body", se.toString());

			// Execute the request
			try {
				httpResponse = httpClient.execute(httppost);
			} catch (ConnectTimeoutException ctEx) {
				httpResponse = null;
			} catch (SocketTimeoutException e) {
				httpResponse = null;
			} catch (NoRouteToHostException ctEx) {
				httpResponse = null;
			} catch (UnknownServiceException ctEx) {
				httpResponse = null;
			} catch (UnknownHostException uhEx) {
				httpResponse = null;
			} catch (ConnectException cnEx) {
				httpResponse = null;
			} catch (ClientProtocolException e) {
				httpResponse = null;
			} catch (IOException e) {
				httpResponse = null;
			}

		} catch (UnsupportedEncodingException e) {
			Log.e(e.getClass().getName() + "doJsonPostRequest", e.getMessage(),
					e);
		}
		return httpResponse;
	}

}
