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
        
        MylocalData.getInstance().context = this.getApplicationContext();
		setContentView(R.layout.activity_category_selector);

		menuBtn = (ImageButton)findViewById(R.id.menuBtn);
		/*backBtn = (ImageButton)findViewById(R.id.backBtn);*/
        cat1 = (ImageButton)findViewById(R.id.cat1);
        cat2 = (ImageButton)findViewById(R.id.cat2);
        cat3 = (ImageButton)findViewById(R.id.cat3);
        cat4 = (ImageButton)findViewById(R.id.cat4);
        cat5 = (ImageButton)findViewById(R.id.cat5);
		/*backBtn.setOnClickListener(gotoSection);*/
		menuBtn.setOnClickListener(gotoSection);
        cat1.setOnClickListener(gotoSection);
        cat2.setOnClickListener(gotoSection);
        cat3.setOnClickListener(gotoSection);
        cat4.setOnClickListener(gotoSection);
        cat5.setOnClickListener(gotoSection);
		
	}
	
	
	private OnClickListener selectedItem = new OnClickListener() {
		int idGeneral;
		Intent intent;

		public void onClick(View v) {
			
			/*intent = new Intent(MylocalData.getInstance().context, ProductViewActivity.class);
   	     	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   	     	MylocalData.getInstance().context.startActivity(intent);
   	     	overridePendingTransition( R.anim.slide_in_left, R.anim.slide_in_right );*/

		}
	
	};
	
	
	private OnClickListener gotoSection = new OnClickListener() {
		Intent intent ;
		
		public void onClick(View v) {
			

			switch(v.getId()){
			

				case R.id.cat1:

                    MylocalData.getInstance().firstCatSelector = "MLA1455";
                    intent = new Intent(MylocalData.getInstance().context, HomeMercadoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MylocalData.getInstance().context.startActivity(intent);
                    overridePendingTransition( R.anim.slide_in_left, R.anim.slide_in_right );
				
	       	     	
	       	     	break;
	       	     	
				case R.id.cat2:

                    MylocalData.getInstance().firstCatSelector = "MLA1455";
                    intent = new Intent(MylocalData.getInstance().context, HomeMercadoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MylocalData.getInstance().context.startActivity(intent);
                    overridePendingTransition( R.anim.slide_in_left, R.anim.slide_in_right );
					
				    break;

                case R.id.cat3:

                    MylocalData.getInstance().firstCatSelector = "MLA1455";
                    intent = new Intent(MylocalData.getInstance().context, HomeMercadoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MylocalData.getInstance().context.startActivity(intent);
                    overridePendingTransition( R.anim.slide_in_left, R.anim.slide_in_right );

                    break;

                case R.id.cat4:

                    MylocalData.getInstance().firstCatSelector = "MLA1455";
                    intent = new Intent(MylocalData.getInstance().context, HomeMercadoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MylocalData.getInstance().context.startActivity(intent);
                    overridePendingTransition( R.anim.slide_in_left, R.anim.slide_in_right );

                    break;

                case R.id.cat5:

                    MylocalData.getInstance().firstCatSelector = "MLA1455";
                    intent = new Intent(MylocalData.getInstance().context, HomeMercadoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MylocalData.getInstance().context.startActivity(intent);
                    overridePendingTransition( R.anim.slide_in_left, R.anim.slide_in_right );

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
