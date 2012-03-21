package com.qburst.qbwebservice.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

public class QBUtility {
	
	public static String convertStreamToString(InputStream is)
	{
		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;

			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} catch (IOException e) {
				Log.e(e.getClass().getName()+" convertStreamToString", e.getMessage(), e);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					Log.e(e.getClass().getName()+" convertStreamToString", e.getMessage(), e);
				}
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	public static void showDialogOk(String title, String message,
			Context context) {
		if(context!=null) {
			Dialog dlg = new AlertDialog.Builder(context).setTitle(title)
			.setMessage(message)
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

				}
			}).create();
			dlg.setVolumeControlStream(AudioManager.STREAM_MUSIC);
			dlg.show();
		}
	}

	public static void showDialogOkWithGoBack(String title, String message,
			final Activity activity) {
		if(activity.getApplicationContext()!=null) { 
		AlertDialog.Builder adb = new AlertDialog.Builder(activity);
			adb.setTitle(title);
			adb.setMessage(message);
			adb.setCancelable(false); 
			adb.setNeutralButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					activity.onBackPressed();
				}
			});
			AlertDialog ad = adb.create();
			ad.setVolumeControlStream(AudioManager.STREAM_MUSIC);
			ad.show();
		}
	}

	public static void showToast(String message, Context context) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connec = (ConnectivityManager) context
		.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connec.getActiveNetworkInfo();

		if (netInfo != null && netInfo.isConnected() == true) {
			return true;
		}
		return false;
	}

	public static ProgressDialog showProgressDialog(Context context) {
		ProgressDialog myProgressDialog = new ProgressDialog(context);
		myProgressDialog.setMessage("Please wait...");
		myProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		myProgressDialog.show();
		myProgressDialog.setCancelable(false);
		myProgressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_SEARCH
						&& event.getRepeatCount() == 0) {
					return true;
				}
				return false;
			}

		});
		return myProgressDialog;
	}

	public static void dismissProgressDialog(ProgressDialog myProgressDialog) {
		if (myProgressDialog != null) {
			try {
				myProgressDialog.dismiss();
			} catch (Exception e) {
				Log.e(e.getClass().getName()+": dismissProgressDialog", e.getMessage());
			}
		}
	}

}
