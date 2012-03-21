package com.qburst.qbwebservice.app;

import android.app.Application;

public class QBWebserviceDemoApp extends Application {

	private static QBWebserviceDemoApp sharedApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		sharedApplication = this;
	}

	public static synchronized QBWebserviceDemoApp getSharedApplication() {
		return sharedApplication;
	}
}
