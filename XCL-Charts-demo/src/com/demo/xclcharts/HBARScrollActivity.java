package com.demo.xclcharts;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HBARScrollActivity extends Activity {
	
	com.demo.xclcharts.view.BarChart07View chart = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hbarscroll);
		
		chart = (com.demo.xclcharts.view.BarChart07View)findViewById(R.id.BarChart07View);  
		//chart.setChartRange(chart.getLeft(),chart.getBottom());
		
		chart.setChartRange(chart.getLeft(),chart.getBottom(),chart.getRight() - chart.getLeft(),chart.getBottom() - chart.getTop());
		
	//	setChartRange
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hbarscroll, menu);
		return true;
	}

}
