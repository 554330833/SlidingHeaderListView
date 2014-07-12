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
 * 实现头部效果
 * @author Dzy
 * 2014-7-9
 * 下午6:26:30
 */
public class MainActivity extends SherlockActivity {

	private XListView xListView;
	private ImageView Stickyimage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// 可以自定义actionbar
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		// 不在actionbar显示名字
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		// 不在actionbar显示logo
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		// 关闭当前的actionbar
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
				Toast.makeText(getApplicationContext(), "说啥哪",
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
	 * 一个字符串列表，从指定的资源阅读
	 * 
	 * @author Dzy 2014-7-4 下午2:34:59
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
	 * 访问服务器的线程
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
