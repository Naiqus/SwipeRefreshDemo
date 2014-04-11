package com.mzba.swiperefresh;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends BasicFragment {
		
		public static String TAG = PlaceholderFragment.class.getCanonicalName();
		
		private String[] data = new String[20];
		
		public PlaceholderFragment() {
			
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			for (int i = 0;i < data.length; i++) {
				data[i] = "This is value " + i;
			}
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View view = super.onCreateView(inflater, container, savedInstanceState);
			listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data)); 
			return view;
		}
		
		@Override
		public void onRefresh() {
			Log.i(TAG, "--------------------------onRefresh-------------------------");
			new Thread() {
				public void run() {
					try {
						Thread.sleep(3000);
						getActivity().runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								hideSwipeProgress();
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
			}.start();
		}

		@Override
		public void onLoad() {
			Log.i(TAG, "--------------------------OnLoad-------------------------");
			new Thread() {
				public void run() {
					try {
						Thread.sleep(3000);
						getActivity().runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								hideLoadView();
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
			}.start();
		}
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Toast.makeText(getActivity(), "You click the listItem : " +  position, Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			return super.onItemLongClick(parent, view, position, id);
		}
	}

}
