package com.qburst.qbwebservice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qburst.qbwebservice.api.QBTestApi;
import com.qburst.qbwebservice.listeners.QBWebserviceListener;
import com.qburst.qbwebservice.models.QBWebserviceModel;
import com.qburst.qbwebservice.utilities.QBUtility;

public class QBWebserviceDemoActivity extends Activity implements QBWebserviceListener {
    
	ListView _subcategoriesLv;
	String _email, _password;
	int _categoryId = 5;
	QBTestApi _subcategoriesApi;
	QBInternAdapter _InternAdapter;
	private Context _context;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _email = "a";
        _password = "a";
		_context = this;
		
        _subcategoriesLv = (ListView)findViewById(R.id.listView1);
        
        _subcategoriesApi = new QBTestApi( _email, _password, _categoryId, QBWebserviceDemoActivity.this);
        _subcategoriesApi.invoke();
    
    }


	@Override
	public void onSuccess(ArrayList<QBWebserviceModel> subcatList, int apiID) {
		
		_InternAdapter = new QBInternAdapter(subcatList);
		_subcategoriesLv.setAdapter(_InternAdapter);
		//_InternAdapter.notifyDataSetChanged();
	}


	@Override
	public void onFail(String errorResponse, int apiID) {
		QBUtility.showDialogOkWithGoBack("Fetch fail", errorResponse, this);
	}
	
	public class QBInternAdapter extends BaseAdapter{
		
		ArrayList<QBWebserviceModel> _subcatList;

		public QBInternAdapter(ArrayList<QBWebserviceModel> subcatList) {
			this._subcatList = subcatList;
		}

		@Override
		public int getCount() {
			return _subcatList.size();
		}

		@Override
		public Object getItem(int position) {
			return _subcatList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			convertView = LayoutInflater.from(_context).inflate(R.layout.cell, null);
			
			TextView _subcatTv = (TextView)convertView.findViewById(R.id.textView1);
			_subcatTv.setText(_subcatList.get(position).getName());
			return convertView;
		}
		
	}
}


