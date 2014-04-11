package com.mzba.swiperefresh;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
/**
 * 
 * @author 06peng
 *
 */
public class BasicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoadOnScrollListener.OnLoadListener,
						AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

	private SwipeRefreshLayout refreshLayout;
	public LoadListView listView;
	public LoadOnScrollListener onScrollListener;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		refreshLayout = (SwipeRefreshLayout) inflater.inflate(R.layout.activity_swipe_refresh, null); 
		listView = (LoadListView) refreshLayout.findViewById(R.id.listview);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);
		onScrollListener = new LoadOnScrollListener(this);
		listView.setOnScrollListener(onScrollListener);
		initSwipeOptions();
		return refreshLayout;
	}
	
	public View getContentView(View view) {
		refreshLayout.addView(view, view.getLayoutParams());
		initSwipeOptions();
		return refreshLayout;
	}
	
	private void initSwipeOptions() {
        refreshLayout.setOnRefreshListener(this);
        setAppearance();
    }
 
    private void setAppearance() {
        refreshLayout.setColorScheme(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light,
        android.R.color.holo_orange_light,
        android.R.color.holo_red_light);
    }
    
    public void startRefresh() {
    	refreshLayout.startRefresh();
    }
 
    /**
     * It shows the SwipeRefreshLayout progress
     */
    public void showSwipeProgress() {
        refreshLayout.setRefreshing(true);
    }
 
    /**
     * It shows the SwipeRefreshLayout progress
     */
    public void hideSwipeProgress() {
        refreshLayout.setRefreshing(false);
    }
 
    /**
     * Enables swipe gesture
     */
    public void enableSwipe() {
        refreshLayout.setEnabled(true);
    }
 
    /**
     * Disables swipe gesture. It prevents manual gestures but keeps the option tu show
     * refreshing programatically.
     */
    public void disableSwipe() {
        refreshLayout.setEnabled(false);
    }
 
    /**
     * It must be overriden by parent classes if manual swipe is enabled.
     */
    @Override 
    public void onRefresh() {
        // Empty implementation
    }

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		return false;
	}
	
	public void showLoadView() {
		listView.showLoadView();
	}
	
	public void hideLoadView() {
		listView.hideLoadView();
	}
	
	public void isShowLoading() {
		listView.isShowLoading();
	}
}
