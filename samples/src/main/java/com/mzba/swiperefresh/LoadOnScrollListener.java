/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.mzba.swiperefresh;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;


public class LoadOnScrollListener implements OnScrollListener {

	private OnScrollListener externalListener;
	private OnLoadListener mOnLoadListener = null;
	
	private int listViewFirstVisibleItem;
	
	public LoadOnScrollListener(OnLoadListener mOnLoadListener) {
		this.mOnLoadListener = mOnLoadListener;
	}
	

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (externalListener != null) {
			externalListener.onScrollStateChanged(view, scrollState);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		listViewFirstVisibleItem = firstVisibleItem;
		if (firstVisibleItem > 0 && totalItemCount > 0 && (firstVisibleItem + visibleItemCount == totalItemCount)) {
			if (mOnLoadListener != null) {
				if (view instanceof LoadListView) {
					if (!((LoadListView) view).isShowLoading()) {
						((LoadListView) view).showLoadView();
						mOnLoadListener.onLoad();
					}
				}
			}
        }
		if (externalListener != null) {
			externalListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}
	}
	
	public int getListViewFirstVisibleItem() {
		return listViewFirstVisibleItem;
	}
	
	public interface OnLoadListener {
		public void onLoad();
	}
}
