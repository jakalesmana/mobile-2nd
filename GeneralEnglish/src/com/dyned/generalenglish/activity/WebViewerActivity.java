package com.dyned.generalenglish.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dyned.generalenglish1.R;

public class WebViewerActivity extends BaseActivity {
	
	@SuppressLint("SetJavaScriptEnabled")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_webviewer);
		disableHomeButton();
		
		String title = getIntent().getStringExtra("title");
		
		if(title != null) setHeaderTitle(title);
		
		String url = getIntent().getStringExtra("url_menu");
		
		WebView wvContent = (WebView)findViewById(R.id.wvContent);
		wvContent.getSettings().setDefaultZoom(ZoomDensity.FAR);
		wvContent.getSettings().setJavaScriptEnabled(true);
		wvContent.loadUrl(url);
		wvContent.setWebViewClient(new MyWebViewClient());
	}
	
	private class MyWebViewClient extends WebViewClient {
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	    	view.loadUrl(url);
	    	return false;
	    }
	}
}
