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

public class FeedsAdapter extends ArrayAdapter<Feed> {

	// declaring our ArrayList of items
	private ArrayList<Feed> feed = new ArrayList<Feed>();
	private int layoutResourceId;
	private Context context;

	/*
	 * here we must override the constructor for ArrayAdapter the only variable
	 * we care about now is ArrayList<Item> objects, because it is the list of
	 * objects we want to display.
	 */
	public FeedsAdapter(Context context, int textViewResourceId,
			ArrayList<Feed> feeds) {

		super(context, textViewResourceId, feeds);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.feed = feeds;
	}

	/*
	 * we are overriding the getView method here - this is what defines how each
	 * list item will look.
	 */
	public View getView(int position, View convertView, ViewGroup parent) {

		// assign the view we are converting to a local variable
		View v = convertView;
		FeedHolder holder = null;
		//System.out.println(feed.size());

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			v = new TextView(getContext());
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			v = inflater.inflate(R.layout.feed,null);
			holder = new FeedHolder();
			holder.tt = (TextView) v.findViewById(R.id.feedtitle);
			holder.ttd = (TextView) v.findViewById(R.id.feedtitledata);
			holder.mt = (TextView) v.findViewById(R.id.feedlink);
			holder.mtd = (TextView) v.findViewById(R.id.feedlinkdata);
			holder.bt = (TextView) v.findViewById(R.id.feeddescription);
			holder.btd = (TextView) v.findViewById(R.id.feeddescriptiondata);

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
		Feed i = feed.get(position);

		if (i != null) {

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.

			TextView tt = (TextView) v.findViewById(R.id.feedtitle);
			TextView ttd = (TextView) v.findViewById(R.id.feedtitledata);
			TextView mt = (TextView) v.findViewById(R.id.feedlink);
			TextView mtd = (TextView) v.findViewById(R.id.feedlinkdata);
			TextView bt = (TextView) v.findViewById(R.id.feeddescription);
			TextView btd = (TextView) v.findViewById(R.id.feeddescriptiondata);

			// check to see if each individual textview is null.
			// if not, assign some text!
			if (tt != null) {
				tt.setText("Title: ");
			}
			if (ttd != null) {
				ttd.setText(i.getTitle());
			}
			if (mt != null) {
				mt.setText("link: ");
			}
			if (mtd != null) {
				mtd.setText(i.getLink());
			}
			if (bt != null) {
				bt.setText("description: ");
			}
			if (btd != null) {
				btd.setText(i.getDescription());
			}

		}

		// the view must be returned to our activity
		return v;

	}

	static class FeedHolder {
		TextView tt;
		TextView ttd;
		TextView mt;
		TextView mtd;
		TextView bt;
		TextView btd;
	}

}