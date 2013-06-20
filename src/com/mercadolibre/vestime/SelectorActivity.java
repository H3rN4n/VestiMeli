package com.mercadolibre.vestime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SelectorActivity extends Activity {

	LinearLayout slideHome;
	int slideWidth;
	int slideHeight;
	LinearLayout scrollList;
	String typeList = "videos";
	ImageButton itemBtn;
	ImageButton menuBtn;
	ImageButton backBtn;
    ImageButton filterButton;
    ImageButton cat1;
    ImageButton cat2;
    ImageButton cat3;
    ImageButton cat4;
    ImageButton cat5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_selector);
       

		menuBtn = (ImageButton)findViewById(R.id.menuBtn);
		/*backBtn = (ImageButton)findViewById(R.id.backBtn);*/

		/*backBtn.setOnClickListener(gotoSection);*/
		menuBtn.setOnClickListener(gotoSection);
		
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
			

				case R.id.cat1:
				
	       	     	
	       	     	break;
	       	     	
				case R.id.cat2:
					
				    break;

                case R.id.cat3:

                    break;

                case R.id.cat4:

                    break;

                case R.id.cat5:

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
