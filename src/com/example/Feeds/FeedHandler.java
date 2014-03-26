package com.example.Feeds;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

public class FeedHandler extends DefaultHandler {

	private static final String LOG_TAG = "FeedHandler";

	private Feed mFeed;

	// RSS Date Formats:
	// RFC822 Time Zone: EEE, dd MMM yyyy HH:mm:ss Z
	// General Time Zone: EEE, dd MMM yyyy HH:mm:ss z
	// Atom Date Formats: ISO8601 variants
	// Common Atom Date Formats: yyyy-MM-dd'T'HH:mm:ssZ
	// yyyy-MM-dd'T'HH:mm:ssz
	// Google Reader Shared Items Atom Date Format: yyyy-MM-dd'T'HH:mm:ss'Z'
	// Android Developers Blog Atom Date Format: yyyy-MM-dd'T'HH:mm:ss.SSSZ
	private static final String DATE_FORMATS[] = {
			"EEE, dd MMM yyyy HH:mm:ss Z", "EEE, dd MMM yyyy HH:mm:ss z",
			"yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd'T'HH:mm:ssZ",
			"yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ss.SSSZ" };
	private SimpleDateFormat mSimpleDateFormats[] = new SimpleDateFormat[DATE_FORMATS.length];

	// Allowed Namespaces
	private static final Set<String> NAMESPACES = new HashSet<String>(
			Arrays.asList(new String[] { "",
					"http://purl.org/rss/1.0/modules/content/",
					"http://www.w3.org/2005/Atom", "http://purl.org/rss/1.0/",
					"http://purl.org/dc/elements/1.1/" }));

	private boolean isType = false;
	private boolean isFeed = false;
	private boolean isItem = false;
	private boolean isTitle = false;
	private boolean isLink = false;
	private boolean isPubdate = false;
	private boolean isGuid = false;
	private boolean isDescription = false;
	private boolean isContent = false;
	private boolean isSource = false; // used to escape the <source> element in
										// Atom format
	private boolean isEnclosure = false;

	private String mHrefAttribute; // href attribute from link element in Atom
									// format and enclosures for Atom and RSS
									// formats
	private String mMimeAttribute; // Enclosure MIME type attribute from link
									// element for RSS and Atom formats
	private int maxItems = 0;
	private int mNbrItems = 0;
	private StringBuffer mSb;

	public void startDocument() throws SAXException {

	}

	public void endDocument() throws SAXException {

	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
	}

}
