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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView.ScaleType;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class FilterMercadoActivity extends Activity {

	LinearLayout slideHome;
	ImageButton menuBtn;
	int error;
	ConexionHttpGet c;
	ProgressDialog dialog = null;
	AlertDialog errorDialog;
	private Handler mHandler = new Handler();
	public ArrayList<HashMap<String, String>> itemsMen;
	String[] array_spinner_category;
	Spinner spinnerCategory;
	
	String[] array_spinner_color;
	Spinner spinnerColor;
	
	String[] array_spinner_talles;
	Spinner spinnerTalles;
	
	ImageButton filterButton;
	ImageButton btnRemeras;
	ImageButton btnVestidos;
	ImageButton btnAccesorios;
	ImageButton btnRopaInterior;
	ImageButton btnPantalones;
	ImageButton btnPolleras;
	
	
	String typeSeach;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		MylocalData.getInstance().context = this.getApplicationContext();
		c = new ConexionHttpGet();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
				
		menuBtn = (ImageButton)findViewById(R.id.menuBtn);
		menuBtn.setOnClickListener(gotoSection);
		
		filterButton = (ImageButton)findViewById(R.id.button_filter);
		filterButton.setOnClickListener(searchFilter);
		
		//btnRemeras = (ImageButton)findViewById(R.id.btn_remeras);
		//btnRemeras.setOnClickListener(setTypeSeach);
		
		//btnVestidos = (ImageButton)findViewById(R.id.btn_vestidos);
		//btnVestidos.setOnClickListener(setTypeSeach);
		
		//btnAccesorios = (ImageButton)findViewById(R.id.btn_accesorios);
		//btnAccesorios.setOnClickListener(setTypeSeach);
		
		//btnRopaInterior = (ImageButton)findViewById(R.id.btn_ropa_interior);
		//btnRopaInterior.setOnClickListener(setTypeSeach);
		
		//btnPantalones = (ImageButton)findViewById(R.id.btn_pantalones);
		//btnPantalones.setOnClickListener(setTypeSeach);
		
		//btnPolleras = (ImageButton)findViewById(R.id.btn_polleras);
		//btnPolleras.setOnClickListener(setTypeSeach);
		
		spinnerColor = (Spinner) this.findViewById(R.id.spinner_color);
		
		spinnerTalles = (Spinner) this.findViewById(R.id.spinner_talles);
		
		spinnerCategory = (Spinner) this.findViewById(R.id.spinner_category);
		
		errorDialog = new AlertDialog.Builder(this).create();
		dialog = ProgressDialog.show(FilterMercadoActivity.this, "", 
                "Cargando Categorias...", true);
		 new Thread(new Runnable() {

				public void run() {
					error = 1;
					try {
						c.getRopaCategory();
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
					
					
			    	
			    	 mHandler.post(new Runnable() {
	                     public void run() {	             						
	             			if(error != 0){
	             				
	             				setSpinnerCategory();
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
		 
		 
		 spinnerColor.setOnItemSelectedListener(new OnItemSelectedListener() {
			    
				public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

					String idColor = MylocalData.getInstance().colores.get(position).get("idColor");
					
					MylocalData.getInstance().colorSelectedFilter = idColor;
					
					System.out.println("NNN: "+MylocalData.getInstance().talleSelectedFilter);
										
			    }

			    
			    public void onNothingSelected(AdapterView<?> parentView) {
			        // your code here
			    }

			});
			
		 
		 
		 spinnerTalles.setOnItemSelectedListener(new OnItemSelectedListener() {
			    
				public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

					String idTalle = MylocalData.getInstance().talles.get(position).get("idTalle");
					
					MylocalData.getInstance().talleSelectedFilter = idTalle;
										
			    }

			    
			    public void onNothingSelected(AdapterView<?> parentView) {
			        // your code here
			    }

			});
		 
		 
		 spinnerCategory.setOnItemSelectedListener(new OnItemSelectedListener() {
			    
				public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

					String idCategory = MylocalData.getInstance().ropaCategories.get(position).get("idCategoria");

					MylocalData.getInstance().catSeleted = idCategory;
					
					
					loadDataCat();
										
			    }

			    
			    public void onNothingSelected(AdapterView<?> parentView) {
			        // your code here
			    }

			});

	}
	
	
	void loadDataCat(){
		
		dialog = ProgressDialog.show(FilterMercadoActivity.this, "", 
                "Cargando Colores y Talles...", true);
		 new Thread(new Runnable() {

				public void run() {
				
					error = 1;
					
					try {
						c.getDataSpinner();
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
					
					
			    	
			    	 mHandler.post(new Runnable() {
	                     public void run() {	             						
	             			if(error != 0){
	             				
	             				setSpinner();
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
	 
	
	
	void loadPrevProduct(){
		
		dialog = ProgressDialog.show(FilterMercadoActivity.this, "", 
                "Haciendo Busqueda...", true);
		 new Thread(new Runnable() {

				public void run() {
				
					error = 1;
					
					try {
						c.getPrevProd();
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
					
					
			    	
			    	 mHandler.post(new Runnable() {
	                     public void run() {	             						
	             			if(error != 0){
	             				
	             				
	             				dialog.dismiss();
	             				
	             				redirectList();
	             				
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
	
	void redirectList(){
		
		Intent intent;
			
			intent = new Intent(MylocalData.getInstance().context, ListActivity.class);
	     	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	     	MylocalData.getInstance().context.startActivity(intent);
	     	overridePendingTransition( R.anim.slide_in_left, R.anim.slide_in_right );
	}
	
	void setSpinnerCategory(){
		
		array_spinner_category = MylocalData.getInstance().array_spinner_categories; 
		spinnerCategory.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, array_spinner_category));
		
		
	}
	
	
	void setSpinner(){
			
		array_spinner_color = MylocalData.getInstance().array_spinner_colores; 
		spinnerColor.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, array_spinner_color));
		
		array_spinner_talles = MylocalData.getInstance().array_spinner_talles; 
		spinnerTalles.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, array_spinner_talles));
	}

	
	private OnClickListener searchFilter = new OnClickListener(){
		
		public void onClick(View v) {
			
			loadPrevProduct();
			
		}
	};
	
	
	/*private OnClickListener setTypeSeach = new OnClickListener(){
		
		public void onClick(View v) {
			
			switch (v.getId()) {
			
				case R.id.btn_remeras:
					
					typeSeach = "remeras";
					
					break;
					
				case R.id.btn_vestidos:
					
					typeSeach = "vestidos";
					
					break;
					
				case R.id.btn_accesorios:
					
					typeSeach = "accessios";
					
					break;
				case R.id.btn_ropa_interior:
					
					typeSeach = "ropaInterior";
					
					break;
				case R.id.btn_pantalones:
					
					typeSeach = "pantalones";
					
					break;
				case R.id.btn_polleras:
					
					typeSeach = "pantalones";
					
					break;
				}
			
			System.out.println("typeSeach: "+typeSeach);
			
		}
	};*/
	
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
