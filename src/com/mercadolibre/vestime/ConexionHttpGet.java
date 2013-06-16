package com.mercadolibre.vestime;

import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.google.gson.Gson;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.google.gson.*;

public class ConexionHttpGet {
	
	private DefaultHttpClient httpClient = new DefaultHttpClient();
    HttpGet request = new HttpGet();
    InputStream content = null;
    String dataTalle;
    String dataColor;
    
    
    public int getNovedades() throws Exception {
    	int longJson;
    	MylocalData.getInstance().strUrl = "https://api.mercadolibre.com/sites/MLA/search?q=hombre&category=MLA1430";
    	//MylocalData.getInstance().strUrl = "https://api.mercadolib";
    	String url = MylocalData.getInstance().strUrl;
 
    	HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(new HttpGet(url));
        StatusLine statusLine = response.getStatusLine();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        response.getEntity().writeTo(out);
        out.close();
        String responseString = out.toString();
        
        if(responseString != "" || responseString != null){
        	
        	MylocalData.getInstance().itemsMensNews = new JSONObject(responseString);
            
            MylocalData.getInstance().jsonItemData = MylocalData.getInstance().itemsMensNews.getJSONArray("results");
            
            MylocalData.getInstance().itemsMensLong = MylocalData.getInstance().jsonItemData.length();
            
            longJson = MylocalData.getInstance().itemsMensNews.length();
            
            MylocalData.getInstance().products = new ArrayList<HashMap<String, String>>();
            
            for(int i = 0; i < MylocalData.getInstance().itemsMensLong; i++){
    			HashMap<String, String> item = new HashMap<String, String>();
    			JSONObject pData =new JSONObject();
    			pData = (JSONObject) MylocalData.getInstance().jsonItemData.get(i);
    			item.put("id", String.valueOf(i));
    			item.put("prodId", pData.getString("id"));
    			item.put("siteId", pData.getString("site_id"));
    			item.put("prodTitle", pData.getString("title"));
    			item.put("prodThumb", pData.getString("thumbnail"));
    			item.put("prodPrice", String.valueOf(pData.getDouble("price")));
    			MylocalData.getInstance().products.add(item);
    			String urlImageStr = pData.getString("thumbnail");
    			urlImageStr = urlImageStr.replace("MLA_v_I_f", "MLA_v_Y_f");
    			URL urlImage = new URL(urlImageStr);
				Bitmap bmp = BitmapFactory.decodeStream(urlImage.openConnection().getInputStream());
    			MylocalData.getInstance().bitmapArray.add(bmp);
    			
    			//System.out.println("lugares: "+MylocalData.getInstance().products.size());
     		}
            
            return 1;
            
        }else{
        	
        	return 0;
        	
        }

    }
    
    
    
    public int getListProductFinal() throws Exception {
    	int longJson;
    	MylocalData.getInstance().strUrl = "https://api.mercadolibre.com/items?ids="+MylocalData.getInstance().ids+"&attributes=title,price,thumbnail,descriptions";
    	//MylocalData.getInstance().strUrl = "https://api.mercadolib";
    	String url = MylocalData.getInstance().strUrl;
 
    	HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(new HttpGet(url));
        StatusLine statusLine = response.getStatusLine();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        response.getEntity().writeTo(out);
        out.close();
        String responseString = out.toString();
        
        if(responseString != "" || responseString != null){
        	
        	JSONArray objetosProductos = new JSONArray(responseString);
        	
        	        	
            longJson = MylocalData.getInstance().itemsMensNews.length();
            
            MylocalData.getInstance().productList = new ArrayList<HashMap<String, String>>();
            
            for(int i = 0; i < MylocalData.getInstance().itemsMensLong; i++){
    			HashMap<String, String> item = new HashMap<String, String>();
    			JSONObject pData =new JSONObject();
    			pData = (org.json.JSONObject) objetosProductos.get(i);
    			item.put("id", String.valueOf(i));
    			item.put("titulo", pData.getString("title"));
    			item.put("img", pData.getString("thumbnail"));
    			item.put("precio", pData.getString("price"));
    			item.put("desc", pData.getString("descriptions"));
    			MylocalData.getInstance().productList.add(item);
    			String urlImageStr = pData.getString("thumbnail");
    			urlImageStr = urlImageStr.replace("MLA_v_I_f", "MLA_v_Y_f");
    			URL urlImage = new URL(urlImageStr);
				Bitmap bmp = BitmapFactory.decodeStream(urlImage.openConnection().getInputStream());
    			MylocalData.getInstance().bitmapArrayList.add(bmp);
    			
    			//System.out.println("lugares: "+MylocalData.getInstance().products.size());
     		}
            
            return 1;
            
        }else{
        	
        	return 0;
        	
        }

    }
    
    
    
    public void getPrevProd() throws Exception {
    	int longJson;
    	MylocalData.getInstance().strUrl = "https://api.mercadolibre.com/sites/MLA/searchbackend?category="+MylocalData.getInstance().catSeleted+"&limit=100";
    	//MylocalData.getInstance().strUrl = "https://api.mercadolib";
    	String url = MylocalData.getInstance().strUrl;
 
    	HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(new HttpGet(url));
        StatusLine statusLine = response.getStatusLine();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        response.getEntity().writeTo(out);
        out.close();
        String responseString = out.toString();
        
        if(responseString != "" || responseString != null){
        	
        	
        	MylocalData.getInstance().idsProducts = new JSONObject(responseString);
        	
        	System.out.println("idsProducts: "+MylocalData.getInstance().idsProducts);
            
            JSONArray resultsIds = MylocalData.getInstance().idsProducts.getJSONArray("result_ids");
            
            System.out.println("resultsIds: "+resultsIds.get(1));
            
            MylocalData.getInstance().array_spinner_id_prod = new String[MylocalData.getInstance().itemsMensLong];
        	
            MylocalData.getInstance().ids="";
            
            for(int i = 0; i < MylocalData.getInstance().itemsMensLong; i++){
            	
            	MylocalData.getInstance().ids +=resultsIds.get(i);
            	
            	MylocalData.getInstance().array_spinner_id_prod[i] = (String) resultsIds.get(i);
            	
            	if(i < (MylocalData.getInstance().itemsMensLong)-1){
            		
            		MylocalData.getInstance().ids +=",";
            		
            	}	
    			
     		}
            
            System.out.println("idsQuery: "+MylocalData.getInstance().ids);
            
            getPrevProdTwo(MylocalData.getInstance().ids);
            
        }else{
        	
        	
        }

    }
    
    
    public void getPrevProdTwo(String ids) throws Exception {
    	int longJson;
    	MylocalData.getInstance().strUrl = "https://api.mercadolibre.com/items?ids="+ids+"&attributes=variations";
    	//MylocalData.getInstance().strUrl = "https://api.mercadolib";
    	String url = MylocalData.getInstance().strUrl;
 
    	HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(new HttpGet(url));
        StatusLine statusLine = response.getStatusLine();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        response.getEntity().writeTo(out);
        out.close();
        String responseString = out.toString();
        
        
        
        if(responseString != "" || responseString != null){
        	
        	JSONArray variationsJson = new JSONArray(responseString);
        	
        	JSONObject intoVariations = (JSONObject)variationsJson.get(0);
        	
        	JSONArray variationsJsoninto = intoVariations.getJSONArray("variations");
        	
        	
        	for(int i = 0; i < variationsJsoninto.length() ; i++){
        	
            	JSONObject attVariationsObj = variationsJsoninto.getJSONObject(i);
            	
            	JSONArray attVariations = attVariationsObj.getJSONArray("attribute_combinations");
            	
            	for(int e = 0; e<attVariations.length();e++){
            		
            		
            		JSONObject attrConvObj = (JSONObject)attVariations.get(e);
            		
            		dataTalle =  MylocalData.getInstance().talleSelectedFilter;
            		dataColor =  MylocalData.getInstance().colorSelectedFilter;

            		System.out.println("attConvinations: "+compFinalF(e,(attVariations.length() - 1), attrConvObj.getString("id")));
                	//if(compFinalF(e,(attVariations.length() - 1), attrConvObj.getString("id"))){
                		System.out.println("attConvinations: "+e+" - "+attrConvObj.getString("id"));
                	//}
            		
            		
            	}
            	
            	
        	}
        	
        	getListProductFinal();
        	
            
        }else{
        	
        	
        }

    }
    
    
public boolean compFinalF(int e, int longVariation, String idAtt){
	
	boolean comp1 = false;
	boolean comp2 = false;
	boolean comp = false;
	
	if(e == longVariation){
		System.out.println("lastVal: "+ MylocalData.getInstance().talleSelectedFilter);
		System.out.println("idAtt: "+ idAtt);
		if(compAtt(idAtt, MylocalData.getInstance().talleSelectedFilter)){
			comp1 = true;
			
		}
		
		
		
	}else{
		
		System.out.println("otherVal: "+ MylocalData.getInstance().colorSelectedFilter);
		System.out.println("idAtt: "+ idAtt);
		
		if(compAtt(idAtt, MylocalData.getInstance().colorSelectedFilter)){
			comp2 = true;
			
		}
		
		
	}
	
	if(comp1 && comp2){
		
		comp = true;
		System.out.println("igual: si");
		
	}else{
		comp = false;
		System.out.println("igual: no");
	}
	
	return comp;
}
    
    
 public boolean compAtt(String dato1, String datoss){
    
    	boolean comp = false;
    	System.out.println("dato1: "+dato1+" dato2: "+datoss);
		if(dato1 == datoss){
			comp = true;
		}
			
		return comp;
    	
    }
 
 
    
    
    private JSONObject JSONObject(String responseString) {
		// TODO Auto-generated method stub
		return null;
	}



	public int getRopaCategory() throws Exception{
    
	    int longJson;
		MylocalData.getInstance().strUrl = "https://api.mercadolibre.com/categories/MLA1430/";
		String url = MylocalData.getInstance().strUrl;
	
		HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response = httpclient.execute(new HttpGet(url));
	    StatusLine statusLine = response.getStatusLine();
	
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    response.getEntity().writeTo(out);
	    out.close();
	    String responseString = out.toString();
	    
	    if(responseString != "" || responseString != null){
	    	        	
	
	    	MylocalData.getInstance().ropaCategory = new JSONObject(responseString);
	    	
	    	JSONArray childCategories = MylocalData.getInstance().ropaCategory.getJSONArray("children_categories");
	    	    	
	    	System.out.println("childCategories: "+childCategories.length());
	    	
	    	MylocalData.getInstance().array_spinner_categories =  new String[childCategories.length()];
	        
	        MylocalData.getInstance().ropaCategories = new ArrayList<HashMap<String, String>>();
	        
	        for(int i = 0; i <= childCategories.length(); i++){
	        	
	        	HashMap<String, String> itemDato = new HashMap<String, String>();
	   		   
					JSONObject categories =new JSONObject();
					
					categories = (JSONObject) childCategories.get(i);
	  		  
					System.out.println("ccategoriassss: "+i+" Cat: "+categories.getString("id"));
					
					itemDato.put("idCategoria", categories.getString("id"));
					
					itemDato.put("nombreCategoria", categories.getString("name"));
					
					MylocalData.getInstance().array_spinner_categories[i] = categories.getString("name");
					
					MylocalData.getInstance().ropaCategories.add(itemDato);
	
	 		}
        
	        return 1;
	        
	    }else{
	    	
	    	return 0;
	    	
	    }

    	
    }
    
    
    public int getDataSpinner() throws Exception {
    	int longJson;
    	MylocalData.getInstance().strUrl = "https://api.mercadolibre.com/categories/MLA109385/attributes";
    	String url = MylocalData.getInstance().strUrl;
 
    	HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(new HttpGet(url));
        StatusLine statusLine = response.getStatusLine();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        response.getEntity().writeTo(out);
        out.close();
        String responseString = out.toString();
        
        if(responseString != "" || responseString != null){
        	        	

        	MylocalData.getInstance().dataFilterSpinner = new JSONArray(responseString);
        	
        	JSONObject objColores = (JSONObject) MylocalData.getInstance().dataFilterSpinner.get(1);
        	
        	JSONObject objTalles = (JSONObject) MylocalData.getInstance().dataFilterSpinner.get(0);
        	
        	MylocalData.getInstance().dataColores = objColores.getJSONArray("values");
        	
        	MylocalData.getInstance().dataTalles = objTalles.getJSONArray("values");

        	MylocalData.getInstance().colores = new ArrayList<HashMap<String, String>>();
        	
        	MylocalData.getInstance().array_spinner_colores =  new String[MylocalData.getInstance().dataColores.length()];
        	
        	MylocalData.getInstance().array_spinner_talles =  new String[MylocalData.getInstance().dataTalles.length()];

            for(int i = 0; i < MylocalData.getInstance().dataColores.length(); i++){
            	
            	HashMap<String, String> itemDato = new HashMap<String, String>();
       		   
 				JSONObject colorData =new JSONObject();
 				
 				colorData = (JSONObject) MylocalData.getInstance().dataColores.get(i);
      		  
 				System.out.println("colores-primarios: "+colorData.getString("id"));
 				
 				itemDato.put("idColor", colorData.getString("id"));
 				
 				itemDato.put("nombreColor", colorData.getString("name"));
 				
 				MylocalData.getInstance().array_spinner_colores[i] = colorData.getString("name");
 				
 				MylocalData.getInstance().colores.add(itemDato);

     		}
            
            MylocalData.getInstance().talles = new ArrayList<HashMap<String, String>>();
            
            for(int i = 0; i < MylocalData.getInstance().dataTalles.length(); i++){
            	
            	HashMap<String, String> itemDato = new HashMap<String, String>();
       		   
 				JSONObject talleData =new JSONObject();
 				
 				talleData = (JSONObject) MylocalData.getInstance().dataTalles.get(i);
      		  
 				//System.out.println("colores-primarios: "+colorData.getString("id"));
 				
 				itemDato.put("idTalle", talleData.getString("id"));
 				
 				itemDato.put("nombreTalle", talleData.getString("name"));
 				
 				MylocalData.getInstance().array_spinner_talles[i] = talleData.getString("name");
 				
 				MylocalData.getInstance().talles.add(itemDato);

     		}
            
            return 1;
            
        }else{
        	
        	return 0;
        	
        }

    }    


}
