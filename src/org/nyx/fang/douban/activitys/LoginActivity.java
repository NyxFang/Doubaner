package org.nyx.fang.douban.activitys;

import java.util.Map;

import org.nyx.fang.douban.R;
import org.nyx.fang.douban.utils.DouBanAuthorize;
import org.nyx.fang.douban.utils.RequestAnalysis;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
				for(String key : mapReq.keySet()){
					code = mapReq.get(key);
				}
				if(Uri.parse(url).toString().equals("http://mobileapp.com/?code="+code)){
					saveAuthInfo(auth.getToken(code));
				}else{
					Log.d("Debug", "sorry ... user "+code);
				}
			}
		});
	}
	
	private void saveAuthInfo(String info){
		webView.destroy();
		// saveTokenInfo()....
		Intent intent = new Intent();
		intent.setClass(LoginActivity.this, Index.class);
		startActivity(intent);
		LoginActivity.this.finish();
	}
	
}

