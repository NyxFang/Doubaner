package org.nyx.fang.douban;

import org.nyx.fang.douban.activitys.LoginActivity;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


public class DouBaner extends Activity {
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        login();
    }
    private void login(){
    	findViewById(R.string.loginbtn).setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			Intent intent = new Intent();
    			intent.setClass(DouBaner.this, LoginActivity.class);
    			startActivity(intent);
    			DouBaner.this.finish();
    		}
    	});
    }
}