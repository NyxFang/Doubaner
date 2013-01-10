package org.nyx.fang.douban.activitys;

import org.nyx.fang.douban.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.res.Configuration;
import org.nyx.fang.douban.utils.DouBanAuthorize;
import org.nyx.fang.douban.utils.RequestAnalysis;

import android.webkit.HttpAuthHandler;
import android.net.Uri;
import java.util.Map;

public class LoginActivity extends Activity {
	
	private WebView webView;
	
	private DouBanAuthorize auth;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		getDouBanAuthorize();
	}
	
	private void getDouBanAuthorize(){
		
		auth = new DouBanAuthorize();
		
		webView = (WebView)findViewById(R.string.showWeb);
		webView.loadUrl(auth.authURL());
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public void onPageFinished(WebView v,String url){
				String code = "";
				Map<String , String> mapReq = RequestAnalysis.URLRequest(url);
				if(Uri.parse(url).equals(R.string.authcallbackURL)){
					Log.d("Debug","equals"+url);
					
				}
				for(String key : mapReq.keySet()){
					code = mapReq.get(key);
				}
				Log.d("Debug","code:"+code+"url:"+url);
				
//				auth.getToken(code);
				
			}
		});
	}
	
}

