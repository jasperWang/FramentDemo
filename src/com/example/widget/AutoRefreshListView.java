package com.example.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class AutoRefreshListView extends ListView implements OnScrollListener {

	public AutoRefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		
		
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

}
