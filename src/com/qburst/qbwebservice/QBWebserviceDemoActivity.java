
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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        _email = "a";
        _password = "a";

        _subcategoriesLv = (ListView) findViewById(R.id.listView1);

        _subcategoriesApi = new QBTestApi(_email, _password, _categoryId, QBWebserviceDemoActivity.this);
        _subcategoriesApi.invoke();

    }

    @Override
    public void onSuccess(ArrayList<QBWebserviceModel> subcatList, int apiID) {

        _InternAdapter = new QBInternAdapter(this, subcatList);
        _subcategoriesLv.setAdapter(_InternAdapter);
        // _InternAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String errorResponse, int apiID) {
        QBUtility.showDialogOkWithGoBack("Fetch fail", errorResponse, this);
    }

    public class QBInternAdapter extends BaseAdapter {
        LayoutInflater _inflater;
        ArrayList<QBWebserviceModel> _subcatList;

        public QBInternAdapter(Context context, ArrayList<QBWebserviceModel> subcatList) {
            this._inflater = LayoutInflater.from(context);
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
            ViewHolder holder;

            if (convertView == null) {
                convertView = _inflater.inflate(R.layout.cell, null);

                holder = new ViewHolder();
                holder.subCatTextView = (TextView) convertView.findViewById(R.id.textView1);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // Bind the data efficiently with the holder.
            holder.subCatTextView.setText(_subcatList.get(position).getName());

            return convertView;
        }
    }
    
    static class ViewHolder {
        TextView subCatTextView;
    }
}
