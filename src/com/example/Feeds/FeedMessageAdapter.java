package com.example.Feeds;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.rssdemo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.app.Activity;

public class FeedMessageAdapter extends ArrayAdapter<FeedMessage> {

	// declaring our ArrayList of items
	private ArrayList<FeedMessage> messages;
	private int layoutResourceId;
	private Context context;

	/*
	 * here we must override the constructor for ArrayAdapter the only variable
	 * we care about now is ArrayList<Item> objects, because it is the list of
	 * objects we want to display.
	 */
	public FeedMessageAdapter(Context context, int textViewResourceId,
			ArrayList<FeedMessage> messages) {

		super(context, textViewResourceId, messages);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.messages = messages;
	}

	/*
	 * we are overriding the getView method here - this is what defines how each
	 * list item will look.
	 */
	public View getView(int position, View convertView, ViewGroup parent) {

		// assign the view we are converting to a local variable
		View v = convertView;
		MessageHolder holder = null;

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.message,null);
			holder = new MessageHolder();
			TextView msgtitle = (TextView) v.findViewById(R.id.msgtitle);
			TextView msgtitledata = (TextView) v.findViewById(R.id.msgtitledata);
			TextView msgdescription = (TextView) v.findViewById(R.id.msgdescription);
			TextView msgdescriptiondata = (TextView) v.findViewById(R.id.msgdescriptiondata);
			TextView msgauthor = (TextView) v.findViewById(R.id.msgauthor);
			TextView msgauthordata = (TextView) v.findViewById(R.id.msgauthordata);
			TextView msglink = (TextView) v.findViewById(R.id.msglink);
			TextView msglinkdata = (TextView) v.findViewById(R.id.msglinkdata);
			TextView msgpubdate = (TextView) v.findViewById(R.id.msgpubdate);
			TextView msgpubdatedata = (TextView) v.findViewById(R.id.msgpubdatedata);

			v.setTag(holder);
		}

		/*
		 * Recall that the variable position is sent in as an argument to this
		 * method. The variable simply refers to the position of the current
		 * object in the list. (The ArrayAdapter iterates through the list we
		 * sent it)
		 * 
		 * Therefore, i refers to the current Item object.
		 */
		FeedMessage i = messages.get(position);

		if (i != null) {

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.

			TextView msgtitle = (TextView) v.findViewById(R.id.msgtitle);
			TextView msgtitledata = (TextView) v.findViewById(R.id.msgtitledata);
			TextView msgdescription = (TextView) v.findViewById(R.id.msgdescription);
			TextView msgdescriptiondata = (TextView) v.findViewById(R.id.msgdescriptiondata);
			TextView msgauthor = (TextView) v.findViewById(R.id.msgauthor);
			TextView msgauthordata = (TextView) v.findViewById(R.id.msgauthordata);
			TextView msglink = (TextView) v.findViewById(R.id.msglink);
			TextView msglinkdata = (TextView) v.findViewById(R.id.msglinkdata);
			TextView msgpubdate = (TextView) v.findViewById(R.id.msgpubdate);
			TextView msgpubdatedata = (TextView) v.findViewById(R.id.msgpubdatedata);

			// check to see if each individual textview is null.
			// if not, assign some text!
			if (msgtitle != null) {
				msgtitle.setText("Title: ");
			}
			if (msgtitledata != null) {
				msgtitledata.setText(i.getTitle());
			}
			if (msgdescription != null) {
				msgdescription.setText("description: ");
			}
			if (msgdescriptiondata != null) {
				msgdescriptiondata.setText(i.getDescription());
			}
			if (msgauthor != null) {
				msgauthor.setText("author: ");
			}
			if (msgauthordata != null) {
				msgauthordata.setText(i.getAuthor());
			}
			if (msglink != null) {
				msglink.setText("link: ");
			}
			if (msglinkdata != null) {
				msglinkdata.setText(i.getLink());
			}
			if (msgpubdate != null) {
				msgpubdate.setText("pubdate: ");
			}
			if (msgpubdatedata != null) {
				msgpubdatedata.setText(i.getPubdate());
			}

		}

		// the view must be returned to our activity
		return v;

	}

	static class MessageHolder {
		TextView msgtitle;
		TextView msgtitledata;
		TextView msgdescription;
		TextView msgdescriptiondata;
		TextView msgauthor;
		TextView msgauthordata;
		TextView msglink;
		TextView msglinkdata;
		TextView msgpubdate;
		TextView msgpubdatedata;
	}

}