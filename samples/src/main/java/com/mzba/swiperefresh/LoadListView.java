package com.mzba.swiperefresh;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 
 * @author 06peng
 * the listview can auto load more view
 *
 */
public class LoadListView extends ListView {


    private LoadDataLayout mFooterLaout;
	private boolean isShowLoading;
	
	public LoadListView(final Context context) {
		this(context, null);
	}

	public LoadListView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.listViewStyle);
	}

	public LoadListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
        mFooterLaout = new LoadDataLayout(context);
        mFooterLaout.setPadding(100, 20, 100, 20);
        mFooterLaout.setGravity(Gravity.CENTER_VERTICAL);
        mFooterLaout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
		addFooterView(mFooterLaout);
        mFooterLaout.setVisibility(View.GONE);
	}
	
	public void showLoadView() {
        mFooterLaout.setVisibility(View.VISIBLE);
        mFooterLaout.setRefreshing(true);
		isShowLoading = true;
	}
	
	/**
	 * Sleep 50 milliseconds because when the textView setVisible gone,the listview will call the
	 * onScroll method,so the listview will call the onLoad again and again.
	 */
	public void hideLoadView() {
        mFooterLaout.setVisibility(View.GONE);
        mFooterLaout.setRefreshing(false);
		new Thread() {
			public void run() {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				isShowLoading = false;
			};
		}.start();
	}
	
	/**
	 * is the footer view showing.
	 * @return
	 */
	public boolean isShowLoading() {
		return isShowLoading;
	}
}
