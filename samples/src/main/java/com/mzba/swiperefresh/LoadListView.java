package com.mzba.swiperefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 
 * @author 06peng
 * the listview can auto load more view
 *
 */
public class LoadListView extends ListView {
	/**
	 * the footer view,you can make your custom view instead, such as a Animation view.
	 */
	private TextView textView;
	private boolean isShowLoading;
	
	public LoadListView(final Context context) {
		this(context, null);
	}

	public LoadListView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.listViewStyle);
	}

	public LoadListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		textView = new TextView(context);
		textView.setText("           =========Load More========          ");
        textView.setPadding(10, 10, 10 ,10);
		addFooterView(textView);
		textView.setVisibility(View.GONE);
	}
	
	public void showLoadView() {
		textView.setVisibility(View.VISIBLE);
		isShowLoading = true;
	}
	
	/**
	 * Sleep 500 milliseconds because when the textView setVisible gone,the listview will call the 
	 * onScroll method,so the listview will call the onLoad again and again.
	 */
	public void hideLoadView() {
		textView.setVisibility(View.GONE);
		new Thread() {
			public void run() {
				try {
					Thread.sleep(500);
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
