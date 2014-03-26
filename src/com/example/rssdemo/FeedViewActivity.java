package com.example.rssdemo;

import java.io.IOException;
import java.util.ArrayList;

import com.example.Feeds.Feed;
import com.example.Feeds.FeedMessage;
import com.example.Feeds.FeedMessageAdapter;
import com.example.Feeds.FeedsAdapter;
import com.example.Feeds.RSSFeedParser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FeedViewActivity extends Activity {

	// Button mButton;
	// EditText mEdit;
	Feed feed;
	ArrayAdapter<String> adapter;
	ListView channel;
	ListView feedlist;
	ArrayList<Feed> feeds;
	ArrayList<FeedMessage> messages = new ArrayList<FeedMessage>();
	FeedsAdapter fadapter;
	FeedMessageAdapter fmadapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		boolean DEVELOPER_MODE = true;
		if (DEVELOPER_MODE) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.permitAll().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
					.penaltyLog().penaltyDeath().build());
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_view);

		feeds = new ArrayList<Feed>();
		// mButton = (Button) findViewById(R.id.loadbutton);
		// mButton.setOnClickListener(loadhandler);

		channel = (ListView) findViewById(R.id.channel);
		feedlist = (ListView) findViewById(R.id.feedlist);

		fadapter = new FeedsAdapter(FeedViewActivity.this, R.layout.feed, feeds);
		fmadapter = new FeedMessageAdapter(FeedViewActivity.this,
				R.layout.message, messages);

		feedlist.setOnItemClickListener(listener);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		channel.setAdapter(fadapter);
		feedlist.setAdapter(fmadapter);
		String link = getIntent().getStringExtra("link");

		loadurl(link);

	}

	private void loadurl(String link) {
		feed = null;
		feeds = new ArrayList<Feed>();
		fadapter.clear();
		fmadapter.clear();
		RSSFeedParser parser = null;
		try {
			parser = new RSSFeedParser(link);
			try {
				feed = parser.readFeed();
				fadapter.add(feed);
				for (FeedMessage message : feed.getMessages()) {

					fmadapter.add(message);
				}
				fadapter.notifyDataSetChanged();
				fmadapter.notifyDataSetChanged();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (RuntimeException e) {
			Toast.makeText(getBaseContext(), "invalid url", 5000).show();
		}

	}

	ListView.OnItemClickListener listener = new ListView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> AdapterViewparent, View View,
				int position, long id) {
			if (id < -1) {
				return;
			}
			// Toast.makeText(getBaseContext(),
			// feed.getMessages().get((int) id).getLink().toString(), 5000)
			// .show();

			// setContentView(R.layout.webview);
			// WebView webView = (WebView) findViewById(R.id.webview);
			// webView.getSettings().setJavaScriptEnabled(true);
			// webView.loadUrl(feed.getMessages().get((int) id).getLink()
			// .toString());

			Intent browserIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse(feed.getMessages().get((int) id).getLink()
							.toString()));
			startActivity(browserIntent);
		}

	};

	/*
	 * View.OnClickListener loadhandler = new View.OnClickListener() { public
	 * void onClick(View v) {
	 * 
	 * mEdit = (EditText) findViewById(R.id.feed_address); feed = null; feeds =
	 * new ArrayList<Feed>(); fadapter.clear(); fmadapter.clear(); RSSFeedParser
	 * parser = null; if (mEdit.getText().length() > 0) { //
	 * Toast.makeText(getBaseContext(), mEdit.getText().toString(), //
	 * 10000).show();
	 * 
	 * if (mEdit.getText().toString().equalsIgnoreCase("a")) { parser = new
	 * RSSFeedParser( "http://www.vogella.com/article.rss");
	 * 
	 * } else if (mEdit.getText().toString().equalsIgnoreCase("b")) { parser =
	 * new RSSFeedParser( "http://feeds.bbci.co.uk/news/england/rss.xml"); }
	 * else { parser = new RSSFeedParser((mEdit.getText().toString())); }
	 * 
	 * try { feed = parser.readFeed(); fadapter.add(feed); for (FeedMessage
	 * message : feed.getMessages()) {
	 * 
	 * fmadapter.add(message); } fadapter.notifyDataSetChanged();
	 * fmadapter.notifyDataSetChanged(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * } else { Toast.makeText(getBaseContext(), "Feed address is empty", 10000)
	 * .show(); } } };
	 */
}
