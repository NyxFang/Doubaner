/**
 * 
 */
package org.nyx.fang.douban.utils;

/**
 * @author Lei.fang
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;
public class DouBanAuthorize {

	/**
	 * get Authorize refresh token etc...
	 */
	private final String API_KEY = "09eab9b614fb26761dc02d223ef17362";
	
	private final String SECRET = "c75d81742eee6eb1";
	
	private final String CALLBACK = "http://mobileApp.com";
	
	private final String _URL = "https://www.douban.com/service/auth2/auth";
	
	private final String _tokenURL = "https://www.douban.com/service/auth2/token";
	
	private final String ACCESS_DENIED = "access_denied";
	
	private final String TOKEN_EXPIRED = "access_token_has_expired";
	
	public DouBanAuthorize() {
		
	}
	
	public String authURL(){
		
//		return _URL+"?"+"client_id="+API_KEY+"&"+"redirect_uri="+CALLBACK+"&"+"response_type=code&scope=shuo_basic_r,shuo_basic_w,douban_basic_common";
		return "https://www.douban.com/service/auth2/auth?client_id=09eab9b614fb26761dc02d223ef17362&redirect_uri=http://mobileApp.com&response_type=code&scope=shuo_basic_r,shuo_basic_w,douban_basic_common";
	}
	
	public String tokenURL(){
		return _tokenURL;
	}
	
	public void getToken(String code){
		
		try {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("client_id", API_KEY));
            params.add(new BasicNameValuePair("client_secret", SECRET));
            params.add(new BasicNameValuePair("redirect_uri", CALLBACK));
            params.add(new BasicNameValuePair("grant_type", "authorization_code"));
            params.add(new BasicNameValuePair("code", code));
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(tokenURL());
            httppost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = httpclient.execute(httppost);
            String data = new BasicResponseHandler().handleResponse(response);            
            Log.d("Debug","test"+data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
	}
	
}
