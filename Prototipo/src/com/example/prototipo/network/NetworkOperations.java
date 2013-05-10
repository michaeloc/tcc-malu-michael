package com.example.prototipo.network;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.boye.httpclientandroidlib.Consts;
import ch.boye.httpclientandroidlib.HttpEntity;
import ch.boye.httpclientandroidlib.HttpRequest;
import ch.boye.httpclientandroidlib.HttpResponse;
import ch.boye.httpclientandroidlib.NameValuePair;
import ch.boye.httpclientandroidlib.ProtocolException;
import ch.boye.httpclientandroidlib.client.ClientProtocolException;
import ch.boye.httpclientandroidlib.client.entity.UrlEncodedFormEntity;
import ch.boye.httpclientandroidlib.client.methods.HttpGet;
import ch.boye.httpclientandroidlib.client.methods.HttpPost;
import ch.boye.httpclientandroidlib.cookie.Cookie;
import ch.boye.httpclientandroidlib.entity.mime.MultipartEntity;
import ch.boye.httpclientandroidlib.entity.mime.content.FileBody;
import ch.boye.httpclientandroidlib.entity.mime.content.StringBody;
import ch.boye.httpclientandroidlib.impl.client.DefaultHttpClient;
import ch.boye.httpclientandroidlib.impl.client.DefaultRedirectStrategy;
import ch.boye.httpclientandroidlib.message.BasicNameValuePair;
import ch.boye.httpclientandroidlib.protocol.HttpContext;
import ch.boye.httpclientandroidlib.util.EntityUtils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
public class NetworkOperations 
{
public static final String MAIN_URL = "http://10.0.2.2/elgg/";
	
	private static NetworkOperations networkOp;
	private static DefaultHttpClient httpclient;
	private Context context;
	
	private String username;
	private String password;
	private HttpResponse response;
	private HttpEntity entity;
	private NetworkOperations(){}	
	
	public static NetworkOperations getNetworkOperations(Context context)
	{
		if(networkOp == null)
			networkOp = new NetworkOperations();

		networkOp.setContext(context);
		
		if(httpclient == null)
		{
			httpclient = new DefaultHttpClient();
			httpclient.log.enableDebug(true);
		}
		
		return networkOp;
	}
	
	public boolean isOnline() 
	{
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnected() ) {
	        return true;
	    }
	    return false;
	}
	
	public boolean doLogin(){

		if(isOnline())
		{
			httpclient = new DefaultHttpClient();
			
			List<Cookie> cookies;
			
			try{
								
				httpGetRequestion(httpclient);
				
	            if(isCookieElggperm(getCookies())){
	            	return true;
	            }
               
	            System.out.println("Initial set of cookies:");
	            
	            cookies = getCookies();
	            
	            if (cookies.isEmpty()) {
	                System.out.println("None");
	            } else {
	                for (int i = 0; i < cookies.size(); i++) {
	                    System.out.println("- " + cookies.get(i).toString());
	                }
	            }
	            
	            httpGetPost();
	            if(isCookieElggperm(getCookies())){
                	return true;
                }

                return false;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	public boolean doLogin(String username, String password)
	{
		this.username = username;
		this.password = password;

		return doLogin();
	}
	
	private void setContext(Context context) 
	{
		this.context = context;
	}
	
	private void httpGetRequestion(DefaultHttpClient httpclient)
	{
		HttpGet httpget = new HttpGet(MAIN_URL);
        
		try {
				response = httpclient.execute(httpget);
				entity = response.getEntity();
				EntityUtils.consume(entity);
				
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
	
	private void httpGetPost()
	{
		HttpPost httpPost = new HttpPost(MAIN_URL+"/action/login");

		try {
        	
			httpPost.setEntity(new UrlEncodedFormEntity(getNameValuePair()));
			httpPost.setEntity(new UrlEncodedFormEntity(getNameValuePair(), Consts.UTF_8));
			response = httpclient.execute(httpPost);
	        entity = response.getEntity();
	        EntityUtils.consume(entity);
	        
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
        
		
	}
	private List<NameValuePair> getNameValuePair()
	{
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("username", username));
        nvps.add(new BasicNameValuePair("password", password));
        nvps.add(new BasicNameValuePair("persistent", "true"));
        return nvps;
		
	}
	private List<Cookie> getCookies()
	{
		return httpclient.getCookieStore().getCookies();
		
	}
	private boolean isCookieElggperm(List<Cookie> cookies)
	{
		for (int i = 0; i < cookies.size(); i++) {
        	if(cookies.get(i).getName().contentEquals("elggperm"))
        		if(cookies.get(i).getExpiryDate().after( new Date()))
        			return true;
        }
		return false;
	}
	
}
