package com.mercadolibre.vestime;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;

public class MylocalData {
	
	public static Context context;
	
	public JSONObject itemsMensNews;
	
	public JSONObject ropaCategory;
	
	public JSONObject idsProducts;
	
	public JSONObject arrProducts;
	
	public JSONArray dataFilterSpinner;
	
	public JSONArray dataColores;
	
	public JSONArray dataTalles;
	
	public int itemsMensLong;
	
	public JSONArray jsonItemData;
	
	public String strUrl;
	
	public String colorSelectedFilter;
	
	public String talleSelectedFilter;
	
	ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();
	
	ArrayList<Bitmap> bitmapArrayList = new ArrayList<Bitmap>();
	
	public JSONArray arrItems;

    private static MylocalData mInstance= null;
    
    public ArrayList<HashMap<String, String>> products;
    
    public ArrayList<HashMap<String, String>> talles;
    
    public ArrayList<HashMap<String, String>> colores;
    
    public ArrayList<HashMap<String, String>> ropaCategories;
    
    public ArrayList<HashMap<String, String>> productList;
	
	public String[] array_spinner_colores;
	public String[] array_spinner_talles;
	public String[] array_spinner_categories;
	public String[] array_spinner_id_prod;
	public String catSeleted;
	public String ids;
    
    
    public static synchronized MylocalData getInstance(){

        if(null == mInstance){

                mInstance = new MylocalData();

        }

        return mInstance;
    }
    
    
}