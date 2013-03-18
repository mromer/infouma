package com.uma.infouma;

import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		WebView webview =  (WebView) findViewById(R.id.webview);
		webview.setWebViewClient(new MyWebViewClient());
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setPluginsEnabled(true);
		webview.loadUrl("http://192.168.1.104:8080/infoschool-html5/cms/index.html");

	}

	public class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			try {
				URL urlObj = new URL(url);
				if( TextUtils.equals(urlObj.getHost(),"192.168.1.104") ) {
					//Allow the WebView in your application to do its thing
					return false;
				} else {
					//Pass it to the system, doesn't match your domain
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(url));
					startActivity(intent);
					//Tell the WebView you took care of it.
					return true;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}
	}
}
