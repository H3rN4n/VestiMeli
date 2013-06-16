package com.mercadolibre.vestime;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ProductViewActivity extends Activity {

	ImageButton menuBtn;
	ImageButton backBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MylocalData.getInstance().context = this.getApplicationContext();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		menuBtn = (ImageButton)findViewById(R.id.menuBtn);
		backBtn = (ImageButton)findViewById(R.id.backBtn);

		menuBtn.setOnClickListener(gotoSection);
		backBtn.setOnClickListener(gotoSection);

		
	}
	
	
	private OnClickListener gotoSection = new OnClickListener() {
		Intent intent ;
		
		public void onClick(View v) {
			



		}
		
	};
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
