package com.example.Feeds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.*;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

import com.example.Feeds.Feed;
import com.example.Feeds.FeedMessage;

;;

public class RSSFeedParser {
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LANGUAGE = "language";
	static final String COPYRIGHT = "copyright";
	static final String LINK = "link";
	static final String AUTHOR = "author";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";

	final URL url;

	public RSSFeedParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public Feed readFeed() throws IOException {
		Feed feed = null;
		try {
			boolean isFeedHeader = true;
			String description = "";
			String title = "";
			String link = "";
			String language = "";
			String copyright = "";
			String author = "";
			String pubdate = "";
			String guid = "";

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			InputStream in = read();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			xpp.setInput(br);

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				while (eventType == XmlPullParser.START_DOCUMENT){
					eventType = xpp.next();
				}
				if (eventType == XmlPullParser.START_TAG) {
					String value = xpp.getName();
					if (value.equals(ITEM)) {
						if (isFeedHeader) {
							isFeedHeader = false;
							feed = new Feed(title, link, description, language,
									copyright, pubdate);
						}
					} else if (value.equals(TITLE)) {
						title = xpp.nextText();
					} else if (value.equals(DESCRIPTION)) {
						description = xpp.nextText();
					} else if (value.equals(LINK)) {
						if(!xpp.getNamespace().contains("Atom")){
							link = xpp.nextText();
						}
					} else if (value.equals(GUID)) {
						guid = xpp.nextText();
					} else if (value.equals(LANGUAGE)) {
						language = xpp.nextText();
					} else if (value.equals(AUTHOR)) {
						author = xpp.nextText();
					} else if (value.equals(PUB_DATE)) {
						pubdate = xpp.nextText();
					} else if (value.equals(COPYRIGHT)) {
						copyright = xpp.nextText();
					}
				} else if (eventType == XmlPullParser.END_TAG) {
					if (xpp.getName().equals(ITEM)) {
						FeedMessage message = new FeedMessage();
						message.setAuthor(author);
						message.setDescription(description);
						message.setGuid(guid);
						message.setLink(link);
						message.setTitle(title);
						message.setPubdate(pubdate);
						feed.getMessages().add(message);
						eventType = xpp.next();
						continue;
					}
					
				}
				eventType = xpp.next();
			}

		} catch (XmlPullParserException e) {
			throw new RuntimeException(e);
		}
		return feed;
	}


	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}