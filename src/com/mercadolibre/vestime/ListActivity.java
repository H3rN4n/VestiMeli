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
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ListActivity extends Activity {

	LinearLayout slideHome;
	int slideWidth;
	int slideHeight;
	LinearLayout scrollList;
	String typeList = "videos";
	ImageButton itemBtn;
	ImageButton menuBtn;
	ImageButton backBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
       

		menuBtn = (ImageButton)findViewById(R.id.menuBtn);
		backBtn = (ImageButton)findViewById(R.id.backBtn);

		backBtn.setOnClickListener(gotoSection);
		menuBtn.setOnClickListener(gotoSection);
		
		scrollList = (LinearLayout) findViewById(R.id.ListScroll);
				
		for(int i=0;i<MylocalData.getInstance().productList.size();i++){
			
				LinearLayout contentButton = new LinearLayout(this);
				LinearLayout contentText = new LinearLayout(this);
				TextView titleItem = new TextView(this);
				TextView descItem = new TextView(this);
				ImageView thumbList = new ImageView(this);
				
				LinearLayout.LayoutParams contentBtnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150);
				contentBtnParams.setMargins(0, 2, 0, 0);
				contentText.setPadding(5, 5, 8, 5);
				contentText.setOrientation(LinearLayout.VERTICAL);
				contentButton.setLayoutParams(contentBtnParams);
				contentButton.setOrientation(LinearLayout.HORIZONTAL);
				contentButton.setBackgroundColor(0xFFC3C3C3);

				LinearLayout.LayoutParams thumbListParams = new LinearLayout.LayoutParams(150,150);
				thumbList.setLayoutParams(thumbListParams);
				thumbList.setImageBitmap(MylocalData.getInstance().bitmapArrayList.get(i));
				thumbList.setScaleType(ScaleType.CENTER_CROP);
				
				
				LinearLayout.LayoutParams contentTextParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,150);
				contentTextParams.setMargins(5, 0, 0, 0);
				contentText.setLayoutParams(contentTextParams);
				
				
				titleItem.setText(MylocalData.getInstance().productList.get(i).get("titulo"));
				titleItem.setTextSize(15.5f);
				
				
				descItem.setText("PRECIO: $"+MylocalData.getInstance().productList.get(i).get("precio"));
				descItem.setTextSize(12.5f);
				
				
				contentButton.addView(thumbList);
				contentText.addView(titleItem);
				contentText.addView(descItem);
				contentButton.addView(contentText);
				
				contentButton.setOnClickListener(selectedItem);
				
				scrollList.addView(contentButton);
				
			
			
		}
		
	}
	
	
	private OnClickListener selectedItem = new OnClickListener() {
		int idGeneral;
		Intent intent;

		public void onClick(View v) {
			
			intent = new Intent(MylocalData.getInstance().context, ProductViewActivity.class);
   	     	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   	     	MylocalData.getInstance().context.startActivity(intent);
   	     	overridePendingTransition( R.anim.slide_in_left, R.anim.slide_in_right );


		}
	
	};
	
	
	private OnClickListener gotoSection = new OnClickListener() {
		Intent intent ;
		
		public void onClick(View v) {
			

			switch(v.getId()){
			

				case R.id.backBtn:
				
	       	     	
	       	     	break;
	       	     	
				case R.id.menuBtn:
					
				break;
					
			}

		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
