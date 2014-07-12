package com.dzy.slidingheaderlistview.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.dzy.slidingheaderlistview.R;
import com.dzy.slidingheaderlistview.xlistview.XListView;
import com.dzy.slidingheaderlistview.xlistview.XListView.IXListViewListener;

/**
 * ʵ��ͷ��Ч��
 * @author Dzy
 * 2014-7-9
 * ����6:26:30
 */
public class MainActivity extends SherlockActivity {

	private XListView xListView;
	private ImageView Stickyimage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// �����Զ���actionbar
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		// ����actionbar��ʾ����
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		// ����actionbar��ʾlogo
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		// �رյ�ǰ��actionbar
		getSupportActionBar().hide();

		xListView = (XListView) findViewById(R.id.xListView);
		xListView.setXListViewListener(listViewListener);
		ArrayList<String> items = loadItems(R.raw.nyc_sites);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		xListView.setAdapter(adapter);
		
		Stickyimage = (ImageView) findViewById(R.id.Stickyimage);
		Stickyimage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "˵ɶ��",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	private IXListViewListener listViewListener = new IXListViewListener() {
		@Override
		public void onRefresh() {
			NewsTask newsTask = new NewsTask();
			newsTask.execute("");
		}

		@Override
		public void onLoadMore() {
			NewsTask newsTask = new NewsTask();
			newsTask.execute("");
		}
	};

	/**
	 * һ���ַ����б���ָ������Դ�Ķ�
	 * 
	 * @author Dzy 2014-7-4 ����2:34:59
	 */
	private ArrayList<String> loadItems(int rawResourceId) {
		try {
			ArrayList<String> countries = new ArrayList<String>();
			InputStream inputStream = getResources().openRawResource(
					rawResourceId);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String line;
			while ((line = reader.readLine()) != null) {
				countries.add(line);
			}
			reader.close();
			return countries;
		} catch (IOException e) {
			return null;
		}
	}


	/**
	 * ���ʷ��������߳�
	 */
	private class NewsTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			xListView.stopLoadMore();
			xListView.stopRefresh();
		}
	}
}
