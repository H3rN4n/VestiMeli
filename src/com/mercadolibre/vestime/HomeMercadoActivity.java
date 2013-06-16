package com.mercadolibre.vestime;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


import org.json.JSONException;
import org.json.JSONObject;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.sax.TextElementListener;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeMercadoActivity extends Activity {

	LinearLayout slideHome;
	ImageButton menuBtn;
	int error;
	ConexionHttpGet c;
	ProgressDialog dialog = null;
	AlertDialog errorDialog;
	private Handler mHandler = new Handler();
	public ArrayList<HashMap<String, String>> itemsMen;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MylocalData.getInstance().context = this.getApplicationContext();
		c = new ConexionHttpGet();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		slideHome = (LinearLayout)findViewById(R.id.slide_home);
				
		menuBtn = (ImageButton)findViewById(R.id.menuBtn);
		menuBtn.setOnClickListener(gotoSection);

		errorDialog = new AlertDialog.Builder(this).create();
		dialog = ProgressDialog.show(HomeMercadoActivity.this, "", 
                "Cargando Productos...", true);
		 new Thread(new Runnable() {

				public void run() {
					error = 1;
					try {
						c.getNovedades(); 
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
					
					
			    	
			    	 mHandler.post(new Runnable() {
	                     public void run() {	             						
	             			if(error != 0){
	             				
	             				try {
	             					createList();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	             				dialog.dismiss();
	             			}else{
	             				dialog.dismiss();
	             				errorDialog.setTitle("Vestime - Error");
	             				errorDialog.setMessage("Ha Ocurrido un error en la conexion, vuelve a intentarlo");
	             				errorDialog.setButton("OK", new DialogInterface.OnClickListener() {
	             				   public void onClick(DialogInterface dialog, int which) {
	             				      errorDialog.dismiss();
	             				   }
	             				});
	             				errorDialog.show();
	             			}
	                    	 
	                    	 
	                     }
	                 });
			    			    	
			    }
			  }).start();

	}
	
	
	
	
	void createList() throws IOException{

		int longProd = MylocalData.getInstance().itemsMensLong;

		
		for(int i=0;i<longProd;i+=2){
			
			LinearLayout contentButton = new LinearLayout(this);
			LinearLayout.LayoutParams contentBtnParams = new LinearLayout.LayoutParams(0, 300,1f);
			contentButton.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout contentbuttonLeft = new LinearLayout(this);
			LinearLayout contentbuttonRight = new LinearLayout(this);
			LinearLayout contentCompLeft = new LinearLayout(this);
			LinearLayout contentCompRight = new LinearLayout(this);
			contentbuttonLeft.setOrientation(LinearLayout.VERTICAL);
			contentbuttonRight.setOrientation(LinearLayout.VERTICAL);
			contentCompLeft.setOrientation(LinearLayout.HORIZONTAL);
			contentCompRight.setOrientation(LinearLayout.HORIZONTAL);
			contentbuttonLeft.setBackgroundColor(Color.WHITE);
			contentbuttonRight.setBackgroundColor(Color.WHITE);
						
			contentbuttonLeft.setId(i);
			contentbuttonRight.setId(i+1);
			
			contentbuttonLeft.setOnClickListener(viewProductDetail);
			contentbuttonRight.setOnClickListener(viewProductDetail);
			
			TextView tituloP01 = new TextView(this);
			TextView tituloP02 = new TextView(this);
			
			tituloP01.setPadding(8, 8, 8, 8);
			tituloP02.setPadding(8, 8, 8, 8);
						
			TextView priceP01 = new TextView(this);
			TextView priceP02 = new TextView(this);
						
			tituloP01.setText(MylocalData.getInstance().products.get(i).get("prodTitle"));
			tituloP02.setText(MylocalData.getInstance().products.get(i+1).get("prodTitle"));
			
			tituloP01.setTextColor(Color.BLACK);
			tituloP02.setTextColor(Color.BLACK);
			
			priceP01.setText(MylocalData.getInstance().products.get(i).get("prodPrice"));
			priceP02.setText(MylocalData.getInstance().products.get(i+1).get("prodPrice"));
			
			priceP01.setTextColor(Color.BLACK);
			priceP02.setTextColor(Color.BLACK);
			
			ImageButton sImg01 = new ImageButton(this);
			ImageButton sImg02 = new ImageButton(this);
			
			sImg01.setId(i);
			sImg02.setId(i+2);
			
			sImg01.setOnClickListener(viewProductDetail);
			sImg02.setOnClickListener(viewProductDetail);
					
			LinearLayout.LayoutParams cParamsLeft = new LinearLayout.LayoutParams(0, 400,0.5f);
			LinearLayout.LayoutParams cParamsRigth = new LinearLayout.LayoutParams(0, 400,0.5f);
			
			LinearLayout.LayoutParams cControlParamsLeft = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			LinearLayout.LayoutParams cControlParamsRight = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			
			cParamsLeft.setMargins(2, 2, 2, 2);
			cParamsRigth.setMargins(2, 2, 2, 2);
			
			contentbuttonLeft.setLayoutParams(cParamsLeft);
			contentbuttonRight.setLayoutParams(cParamsRigth);
			

			contentCompLeft.setLayoutParams(cControlParamsLeft);
			contentCompRight.setLayoutParams(cControlParamsRight);
			
			contentCompLeft.setGravity(Gravity.BOTTOM);
			contentCompRight.setGravity(Gravity.BOTTOM);
			
			LinearLayout.LayoutParams imgParamsLeft = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
			LinearLayout.LayoutParams imgParamsRigth = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
			
			sImg01.setBackgroundColor(Color.TRANSPARENT);
			sImg01.setImageBitmap(MylocalData.getInstance().bitmapArray.get(i));
			sImg01.setScaleType(ScaleType.CENTER_CROP);
			sImg01.setPadding(0,0,0,0);
			sImg01.setLayoutParams(imgParamsLeft);
			
			sImg02.setBackgroundColor(Color.TRANSPARENT);
			sImg02.setImageBitmap(MylocalData.getInstance().bitmapArray.get(i+1));
			sImg02.setScaleType(ScaleType.CENTER_CROP);
			sImg02.setPadding(0,0,0,0);
			sImg02.setLayoutParams(imgParamsRigth);
			
			contentbuttonLeft.addView(sImg01);
			contentbuttonRight.addView(sImg02);
			
			contentbuttonLeft.addView(tituloP01);
			contentbuttonRight.addView(tituloP02);
			
			contentCompLeft.addView(priceP01);
			contentCompRight.addView(priceP02);
			
			contentbuttonLeft.addView(contentCompLeft);
			contentbuttonRight.addView(contentCompRight);
			
			contentButton.addView(contentbuttonLeft);
			contentButton.addView(contentbuttonRight);
			
			slideHome.addView(contentButton);
			
			
		}
		
	}
	
	

	
	
	private OnClickListener viewProductDetail = new OnClickListener() {
		Intent intent ;
		int idProduct;
		
		public void onClick(View v) {
			
			idProduct = v.getId();
			
			
		}
		
	};
	
	
	private OnClickListener gotoSection = new OnClickListener() {
		Intent intent ;
		
		public void onClick(View v) {
			
			Intent intent ;
			intent = new Intent(MylocalData.getInstance().context, FilterMercadoActivity.class);
   	     	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
   	     	MylocalData.getInstance().context.startActivity(intent);
   	     	overridePendingTransition( R.anim.slide_in_left, R.anim.slide_in_right );

			
		}
		
	};
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
