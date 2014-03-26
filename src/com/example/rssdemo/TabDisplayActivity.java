package com.example.rssdemo;

import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TabHost;

public class TabDisplayActivity extends TabActivity {

	private TabHost tabHost;
	String Test = null;
	private int z = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_display);
		String[] urlss = { "http://www.vogella.com/article.rss",
				"http://feeds.bbci.co.uk/news/england/rss.xml",
				"http://www.nasa.gov/rss/dyn/breaking_news.rss" };

		// String [] urls = getIntent().getStringArrayExtra("links");
		ArrayList<String> urls = getIntent().getStringArrayListExtra("links");

		this.tabHost = getTabHost();
		ArrayList<Intent> feedviews = new ArrayList<Intent>();

//		for (int a = 0; a < urls.length; a++) {
//			Intent FeedView = new Intent();
//			FeedView.putExtra("link", urls[a]);
//			feedviews.add(FeedView.setClass(this, FeedViewActivity.class));
//			tabHost.addTab(tabHost
//					.newTabSpec("Tab" + Integer.valueOf(a + 1).toString())
//					.setIndicator("tab" + Integer.valueOf(a + 1).toString())
//					.setContent(FeedView));
//		}
		int a=0;
		for (String link : urls) {
			Intent FeedView = new Intent();
			if (link.toString().equalsIgnoreCase("a")){
				link = urlss[0];
			}
			if (link.toString().equalsIgnoreCase("b")){
				link = urlss[1];
			}
			
			if (link.toString().equalsIgnoreCase("c")){
				link = urlss[2];
			}
			FeedView.putExtra("link", link);
			feedviews.add(FeedView.setClass(this, FeedViewActivity.class));
			tabHost.addTab(tabHost
					.newTabSpec("Tab" + Integer.valueOf(a + 1).toString())
					.setIndicator("tab" + Integer.valueOf(a + 1).toString())
					.setContent(FeedView));
			a++;
		}

	}

}
